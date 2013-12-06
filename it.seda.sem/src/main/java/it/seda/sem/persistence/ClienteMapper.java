package it.seda.sem.persistence;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import it.seda.sem.domain.Cliente;
import it.seda.sem.mvc.annotations.BusinessRepository;



@BusinessRepository
public interface ClienteMapper {

	void insertCliente(Cliente client);

	void updateCliente(Cliente client);

	void deleteCliente(BigInteger id);

	Cliente getCliente(BigInteger id);

	int listClientiCount();
	
	List<Cliente> listClienti(RowBounds rowBounds);

}
