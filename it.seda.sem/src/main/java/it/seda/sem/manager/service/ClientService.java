package it.seda.sem.manager.service;

import java.math.BigInteger;
import java.util.List;

import it.seda.sem.domain.Cliente;
import it.seda.sem.mvc.manager.models.FormClient;
import it.seda.sem.persistence.ClientMapper;
import it.seda.sem.security.domain.AccountTO;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
	
	
	@Inject private ClientMapper clientMapper;

	@Transactional("transactionBusinessManager")
	public void insertClient(Cliente client) {
		clientMapper.insertClient(client);
		
	}
	
	@Transactional("transactionBusinessManager")
	public void updateClient(Cliente client) {
		clientMapper.updateClient(client);
	}
	
	@Transactional("transactionBusinessManager")
	public void deleteClient(BigInteger id) {
		clientMapper.deleteClient(id);
	}
	
	@Transactional("transactionBusinessManager")
	public Cliente  getClient(BigInteger id) {
		Cliente cliente=clientMapper.getClient(id);
		return cliente;
		
	}
	
	@Transactional("transactionBusinessManager")
	public int listClientCount() {
		int rowsNumber=clientMapper.listClientCount();
		return rowsNumber;
		
	}
	
	@Transactional("transactionBusinessManager")
	public List<Cliente> listClient(RowBounds rowBounds) {
		List<Cliente> cl=clientMapper.listClient(rowBounds);
		return cl;
		
	}

}
