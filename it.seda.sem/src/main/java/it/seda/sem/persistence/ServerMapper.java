package it.seda.sem.persistence;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.session.RowBounds;


import it.seda.sem.domain.Server;
import it.seda.sem.mvc.annotations.BusinessRepository;

@BusinessRepository
public interface ServerMapper {
	
	void insertServer(Server server);

	void updateServer(Server server);

	void deleteServer(BigInteger id);

	Server getServer(BigInteger id);

	int listServerCount();

	List<Server> listServer(RowBounds rowBounds);

}
