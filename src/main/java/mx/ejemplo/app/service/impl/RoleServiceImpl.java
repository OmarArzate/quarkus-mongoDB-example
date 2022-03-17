package mx.ejemplo.app.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import mx.ejemplo.app.dao.RoleDAO;
import mx.ejemplo.app.model.TRole;
import mx.ejemplo.app.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Inject
	RoleDAO roleDao;

	@Override
	public List<TRole> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAllRoles();
	}

	@Override
	public void createRole(TRole role) {
		// TODO Auto-generated method stub
		roleDao.persist(role);
	}

	@Override
	public TRole findById(String id) {
		ObjectId idO = new ObjectId(id);
		return roleDao.findById(idO);
	}

	@Override
	public void delete(String id) {
		ObjectId idO = new ObjectId(id);
		TRole role = roleDao.findById(idO);
		roleDao.delete(role);
	}

	@Override
	public void updateRole(String id, TRole role) {
		ObjectId idO = new ObjectId(id);
		TRole roleActual = null;		
		try {
			roleActual = roleDao.findById(idO);
		    roleActual.setNombre(role.getNombre());
		}catch(Exception e){
		   	
		}
		roleDao.update(roleActual);
	}

}
