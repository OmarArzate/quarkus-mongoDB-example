package mx.ejemplo.app.service;

import java.util.List;

import org.bson.types.ObjectId;

import mx.ejemplo.app.model.TRole;

public interface RoleService {

	public List<TRole> findAll();

	public void createRole(TRole role);

	public void updateRole(String id, TRole role);

	public TRole findById(String id);

	public void delete(String id);
}
