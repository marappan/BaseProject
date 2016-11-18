package com.bob.compliance.dao;

import java.util.List;

import com.bob.compliance.model.Client;

public interface ClientDao {

	public boolean saveEntity(Client client) throws Exception;
	public boolean updateEntity(Client client) throws Exception;
	public List<Client> getEntityList() throws Exception;
	public Client getEntityById(Integer id) throws Exception;
}
