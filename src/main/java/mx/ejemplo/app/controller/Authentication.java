package mx.ejemplo.app.controller;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import mx.ejemplo.app.model.AuthRequest;
import mx.ejemplo.app.model.AuthResponse;
import mx.ejemplo.app.model.Usuario;
import mx.ejemplo.app.service.UsuarioService;
import mx.ejemplo.app.utils.PBKDF2Encoder;
import mx.ejemplo.app.utils.TokenUtils;
import mx.ejemplo.app.utils.Utils;

@Path("/auth")
public class Authentication {

    @Inject
    PBKDF2Encoder passwordEncoder;

    @Inject
    UsuarioService usuarioService;

    @ConfigProperty(name = "com.artbyte.jwt.duration")
    public Long duration;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String issuer;

    private static final Logger LOG = Logger.getLogger(UsuarioController.class);

    @POST
    @PermitAll
    @Path("/login")
    public Response login(AuthRequest authRequest) {
        Usuario u = usuarioService.findByUserName(authRequest.username);
        LOG.info("usuario: " + authRequest.username);

        if (u != null && u.password.equals(passwordEncoder.encode(authRequest.getPassword()))) {
            if (u.isPasswordExpired()) {
                return Response.ok("La contraseña expiro").status(Status.UNAUTHORIZED).build();
            } else {
                try {
                    String token = TokenUtils.generateToken(u.username, u.rol, duration, issuer);
                    LOG.info("token: " + token);
                    return Response.ok(new AuthResponse(u.username, u.rol, token, u.estatus)).build();
                } catch (Exception e) {
                    LOG.info("excepcion e: " + e);
                    return Response.status(Status.UNAUTHORIZED).build();
                }
            }
        } else {
            LOG.info("Error usuario == null o password != password");
            return Response.ok("Usuario o contraseña incorrecta").status(Status.UNAUTHORIZED).build();
        }

    }
}
