package com.bob.compliance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.compliance.dao.ClientDao;
import com.bob.compliance.model.Client;


@Service
@Transactional
public class ClientServicesImpl implements ClientServices {

	@Autowired
	private ClientDao clientDao;
	
	public boolean saveEntity(Client client) throws Exception {
		return clientDao.saveEntity(client);
	}

	public List<Client> getEntityList() throws Exception {
		return clientDao.getEntityList();
	}

	@Override
	public Client getEntityById(Integer id) throws Exception {
		return clientDao.getEntityById(id);
	}

	@Override
	public Client updateEntity(Client client) throws Exception {
		Client existingClient = getEntityById(client.getClientId());
		if (existingClient != null)	{
			saveEntity(client);
			existingClient = client;
		}
		return existingClient;
	}

}
