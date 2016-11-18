package com.bob.compliance.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bob.compliance.model.Client;
import com.bob.compliance.model.Status;
import com.bob.compliance.services.ClientServices;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientServices clientServices;
	
	ObjectMapper mapper;

	static final Logger logger = Logger.getLogger(ClientController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addClient(@RequestBody Client client) {
		try {
			clientServices.saveEntity(client);
			return new Status(1, "Client added Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Client> getClients() {

		List<Client> clientList = null;
		try {
			clientList = clientServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return clientList;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Client geClient(@PathVariable("id") Integer id) {
		Client client = null;
		try {
			client = clientServices.getEntityById(id);
			
			mapper = new ObjectMapper();
			System.out.println("JSON String : "+mapper.writeValueAsString(client));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status updateClient(@RequestBody Client client) {
		try {
			Client updatedClient = clientServices.updateEntity(client);
			
			if (updatedClient == null)	{
				return new Status(0, "Client record not found");
			}
			return new Status(1, "Client updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, e.toString());
		}
	}


}
