package mx.ejemplo.app.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import mx.ejemplo.app.dao.EstatusDAO;
import mx.ejemplo.app.model.Estatus;
import mx.ejemplo.app.service.EstatusService;

@Service
public class EstatusServiceImpl implements EstatusService{
	
	@Inject
	EstatusDAO estatusDao;

	@Override
	public List<Estatus> findAll() {
		// TODO Auto-generated method stub
		return estatusDao.findAllRoles();
	}

	@Override
	public void createEstatus(Estatus estatus) {
		// TODO Auto-generated method stub
		estatusDao.persist(estatus);
	}

	@Override
	public Estatus findById(String id) {
		ObjectId idO = new ObjectId(id);
		return estatusDao.findById(idO);
	}

	@Override
	public void delete(String id) {
		ObjectId idO = new ObjectId(id);
		Estatus role = estatusDao.findById(idO);
		estatusDao.delete(role);
	}

	@Override
	public void updateEstatus(String id, Estatus estatus) {
		ObjectId idO = new ObjectId(id);
		Estatus estatusActual = null;		
		try {
			estatusActual = estatusDao.findById(idO);
		    estatusActual.setNombre(estatus.getNombre());
		}catch(Exception e){
		   	
		}
		estatusDao.update(estatusActual);
	}

}
