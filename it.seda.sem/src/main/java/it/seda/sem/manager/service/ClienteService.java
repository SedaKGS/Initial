package it.seda.sem.manager.service;

import it.seda.sem.domain.Cliente;
import it.seda.sem.jdbc.RowBoundsHelper;
import it.seda.sem.persistence.ClienteMapper;
import it.seda.template.taglib.DatagridTag.Page;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
	
	
	@Inject private ClienteMapper clienteMapper;

	@Transactional
	public void insertCliente(Cliente client) {
		clienteMapper.insertCliente(client);
	}

	@Transactional
	public void updateCliente(Cliente client) {
		clienteMapper.updateCliente(client);
	}

	@Transactional
	public void deleteCliente(BigInteger id) {
		clienteMapper.deleteCliente(id);
	}

	public Cliente  getCliente(BigInteger id) {
		Cliente cliente=clienteMapper.getCliente(id);
		return cliente;
	}

	@Transactional(readOnly=true)
	public Page<Cliente> listClienti(int pageNumber, int rowsPerPage) {
		
		RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		int totalrows=clienteMapper.listClientiCount();
		
		List<Cliente> clientiList=clienteMapper.listClienti(rbh.buildRowBounds());		
		
		Page<Cliente> clientiPage=new Page<Cliente>(clientiList);
		rbh.decorate(clientiPage, totalrows);
		
		return clientiPage;
		
	}

}
