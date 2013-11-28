package it.seda.sem.persistence;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import it.seda.sem.domain.Cliente;
import it.seda.sem.mvc.annotations.BusinessRepository;



@BusinessRepository
public interface ClientMapper {

	void insertClient(Cliente client);

	void updateClient(Cliente client);

	void deleteClient(BigInteger id);

	Cliente getClient(BigInteger id);

	int listClientCount();

	List<Cliente> listClient(RowBounds rowBounds);
    

}
