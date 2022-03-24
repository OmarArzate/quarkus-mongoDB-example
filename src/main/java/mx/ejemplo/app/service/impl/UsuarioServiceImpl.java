package mx.ejemplo.app.service.impl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.util.ArrayBuilders;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import mx.ejemplo.app.constante.EConstante;
import mx.ejemplo.app.dao.EstatusDAO;
import mx.ejemplo.app.dao.RoleDAO;
import mx.ejemplo.app.dao.UsuarioDAO;
import mx.ejemplo.app.model.Estatus;
import mx.ejemplo.app.model.TRole;
import mx.ejemplo.app.model.Usuario;
import mx.ejemplo.app.service.UsuarioService;
import mx.ejemplo.app.utils.PBKDF2Encoder;
import mx.ejemplo.app.utils.Utils;

import org.jboss.logging.Logger;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Inject
	UsuarioDAO usuarioDao;

	@Inject
	RoleDAO roleDao;

	@Inject
	EstatusDAO estatusDao;

	@Inject
	PBKDF2Encoder passwordEncoder;

	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAllUsers();
	}

	private static final Logger LOG = Logger.getLogger(UsuarioServiceImpl.class);

	@Override
	public String createUser(String id, Usuario usuario) {

		if (Utils.validPassword(usuario.password)) {
			if(usuario.password != (usuario.username)){

				ObjectId idO = new ObjectId(id);
				String primerIngreso = EConstante.estatusPrimerIngreso;
				ObjectId idEstatus = new ObjectId(primerIngreso);
				
				TRole rol = roleDao.findById(idO);
				usuario.password = passwordEncoder.encode(usuario.password);
				Estatus estatus = estatusDao.findById(idEstatus);
				
				/*Aqui se agrega la contraseña en el arreglo de contraseñas antiguas*/
				ArrayList arrPass = new ArrayList<>();
				arrPass.add(usuario.password);
				usuario.setOldPasswords(arrPass);
				/***/
				
				usuario.setEstatus(estatus);
				usuario.setRol(rol);
				usuarioDao.persist(usuario);
				return "";
			}else{
				return "La contraseña no puede ser igual al username";
			}

		} else {
			LOG.info(" error de contraseña");
			return "La contraseña debe ser almenos de 8 caracteres y contener una mayuscula y un numero";
		}

	}

	@Override
	public void updateUser(Usuario usuario, String id) {
		Usuario usuarioActual = null;
		try {
			usuarioActual = findById(id);
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setA_paterno(usuario.getA_paterno());
			usuarioActual.setA_materno(usuario.getA_materno());
			usuarioActual.setUsername(usuario.getUsername());
			usuarioActual.setCorreo(usuario.getCorreo());
			usuarioActual.setTelefono(usuario.getTelefono());
			usuarioActual.setRol(usuario.getRol());
		} catch (Exception e) {

		}

		usuarioDao.update(usuarioActual);
	}

	@Override
	public Usuario findById(String id) {
		ObjectId idO = new ObjectId(id);
		return usuarioDao.findById(idO);
	}

	@Override
	public Usuario findByUserName(String username) {
		return usuarioDao.findByUserName(username);
	}

	@Override
	public void delete(String id) {
		Usuario deleteUser = findById(id);
		usuarioDao.delete(deleteUser);
	}

	@Override
	public String updatePassword(String id, String password, String confirmarPass) {
		ObjectId idO = new ObjectId(id);
		ObjectId idEstatus = new ObjectId(EConstante.estatusActivo);
		Usuario usuarioActual = usuarioDao.findById(idO);
		LOG.info(usuarioActual);
		Estatus estatusActivo = estatusDao.findById(idEstatus);
		ArrayList arrOldPass = new ArrayList<>();
		if (Utils.validPassword(password) && Utils.validPassword(confirmarPass)) {
			if(usuarioActual.username != password){

				try {
					if (confirmarPass.equals(password)) {
						arrOldPass = usuarioActual.getOldPasswords();
						// if(usuarioActual.password.equals(passwordEncoder.encode(password))){
							/**Aqui se compara la nueva contraseña con las ya existentes */
							if (arrOldPass.contains(passwordEncoder.encode(password))) {
								return "La contraseña nueva no puede ser igual a las contraseñas anteriores";
							} else {
								usuarioActual.setPassword(passwordEncoder.encode(password));
								usuarioActual.setEstatus(estatusActivo);
								/*Aqui se agrega la contraseña en el arreglo de contraseñas antiguas*/
								LOG.info("size"+arrOldPass.size());
								if (arrOldPass.size() < 5) {
									arrOldPass.add(usuarioActual.password);
									usuarioActual.setOldPasswords(arrOldPass);
								} else {
									Random random = new Random();
									int posPass = random.nextInt(4) + 0;
									/**Aqui se sustituye una contraseña antigua por la nueva */
									arrOldPass.set(posPass, usuarioActual.password);
									usuarioActual.setOldPasswords(arrOldPass);
								}
								/***/
								
								usuarioActual.setChangePassword(new Date());
								usuarioDao.update(usuarioActual);
								return "";
							}
						} else {
							return "Las contraseñas no coinciden";
						}
						
					} catch (Exception e) {
						return "Error al actualziar la contraseña";
					}
				}else{
					return "La contraseña no puede ser igual al username";
				}
		} else {
			return "La contraseña debe ser almenos de 8 caracteres y contener una mayuscula y un numero";
		}
	}
}
