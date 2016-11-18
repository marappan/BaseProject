package com.bob.compliance.services;

import java.util.List;

import com.bob.compliance.model.Client;

public interface ClientServices {
	public boolean saveEntity(Client client) throws Exception;
	public List<Client> getEntityList() throws Exception;
	public Client getEntityById(Integer id) throws Exception;
	public Client updateEntity(Client client) throws Exception;

}
