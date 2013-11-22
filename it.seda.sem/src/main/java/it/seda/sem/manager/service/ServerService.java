package it.seda.sem.manager.service;

import it.seda.sem.domain.Server;
import it.seda.sem.persistence.ServerMapper;

import java.math.BigInteger;
import java.util.List;




import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServerService {
    
	@Inject private ServerMapper serverMapper;
	
	@Transactional("transactionBusinessManager")
	public void insertServer(Server server) {
		serverMapper.insertServer(server);
		
	}
	
	@Transactional("transactionBusinessManager")
	public void updateServer(Server server) {
		serverMapper.updateServer(server);
	}
	
	@Transactional("transactionBusinessManager")
	public void deleteServer(BigInteger id) {
		serverMapper.deleteServer(id);
	}
	
	@Transactional("transactionBusinessManager")
	public Server  getServer(BigInteger id) {
		Server server=serverMapper.getServer(id);
		return server;
		
	}
	
	@Transactional("transactionBusinessManager")
	public int listServerCount() {
		int rowsNumber=serverMapper.listServerCount();
		return rowsNumber;
		
	}
	
	@Transactional("transactionBusinessManager")
	public List<Server> listServer(RowBounds rowBounds) {
		List<Server> cl=serverMapper.listServer(rowBounds);
		return cl;
		
	}

}
