package it.seda.sem.manager.service;

import it.seda.sem.domain.Cliente;
import it.seda.sem.domain.Server;
import it.seda.sem.jdbc.RowBoundsHelper;
import it.seda.sem.persistence.ServerMapper;
import it.seda.template.taglib.DatagridTag.Page;

import java.math.BigInteger;
import java.util.List;






import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServerService {
    
	@Inject private ServerMapper serverMapper;
	
	@Transactional
	public void insertServer(Server server) {
		serverMapper.insertServer(server);
		
	}
	
	@Transactional
	public void updateServer(Server server) {
		serverMapper.updateServer(server);
	}
	
	@Transactional
	public void deleteServer(BigInteger id) {
		serverMapper.deleteServer(id);
	}
	
	public Server  getServer(BigInteger id) {
		Server server=serverMapper.getServer(id);
		return server;
		
	}
	
	@Transactional
	public Page<Server> listServer(int pageNumber, int rowsPerPage) {
		RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		
		int totalRows=serverMapper.listServerCount();
		
		List<Server> serverList=serverMapper.listServer(rbh.buildRowBounds());
		
		Page<Server> serverPage=new Page<Server>(serverList);
		rbh.decorate(serverPage, totalRows);
		
		return serverPage;
		
	}

}
