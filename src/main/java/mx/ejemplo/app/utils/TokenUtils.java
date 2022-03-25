package mx.ejemplo.app.utils;


import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logging.Logger;

import io.smallrye.jwt.build.Jwt;
import mx.ejemplo.app.controller.UsuarioController;
import mx.ejemplo.app.model.TRole;

public class TokenUtils {

	  private static final Logger LOG = Logger.getLogger(UsuarioController.class); 

    public static String generateToken(String username, TRole rol, Long duration, String issuer) throws Exception{
        LOG.info("entra genera");
        String privateKeyLocation = "/privatekey.pem";
        PrivateKey privateKey = readPrivateKey(privateKeyLocation);
        
        Set<String> groups = new HashSet<>();
        groups.add(rol.nombre.toString());
        
        String token =
        Jwt.issuer(issuer) 
        .upn(username) 
        .groups(groups) 
        .claim(Claims.birthdate.name(), duration) 
        .sign(privateKey);
        
        return token;
    }
    
    
    public static PrivateKey readPrivateKey(final String pemResName)throws Exception{
        LOG.info("entra read: "+pemResName);
        try(InputStream contentIS = TokenUtils.class.getResourceAsStream(pemResName)){
            byte[] tmp = new byte[4096];
            int length = contentIS.read(tmp);
            return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
        }catch(Exception e){
            LOG.info("cae en exception: "+e);
            return null;
        }
    }

    public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception{
        LOG.info("entra decod");
        byte[] encodedBytes = toEncodedBytes(pemEncoded);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    public static byte[] toEncodedBytes(final String pemEncoded){
        LOG.info("entra endco");
        final String normalizedPem = removeBeginEnd(pemEncoded);
        return Base64.getDecoder().decode(normalizedPem);
    }

    public static String removeBeginEnd(String pem){
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
		pem = pem.replaceAll("-----END (.*)----", "");
		pem = pem.replaceAll("\r\n", "");
		pem = pem.replaceAll("\n", "");
		return pem.trim();
    }

    public static int currentTimeInSecs(){
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS/100);
    }
    
}
