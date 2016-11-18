package com.bob.compliance.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.bob.compliance.model.Client;

public class ClientDaoImpl implements ClientDao {

	@Autowired
	private SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean saveEntity(Client client) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.saveOrUpdate(client);
		tx.commit();
		session.close();
		return false;
	}

	@Override
	public boolean updateEntity(Client client) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(client);
		tx.commit();
		session.close();
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Client> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Client> clientList = session.createCriteria(Client.class).list();
		tx.commit();
		session.close();
		return clientList;
	}
	
	@Override
	public Client getEntityById(Integer id) throws Exception {
		session = sessionFactory.openSession();
		Client client = (Client) session.load(Client.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return client;
	}

}
