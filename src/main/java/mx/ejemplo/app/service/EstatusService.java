package mx.ejemplo.app.service;

import java.util.List;

import mx.ejemplo.app.model.Estatus;

public interface EstatusService {

	public List<Estatus> findAll();

	public void createEstatus(Estatus estatus);

	public void updateEstatus(String id, Estatus estatus);

	public Estatus findById(String id);

	public void delete(String id);
}
