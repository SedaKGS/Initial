package it.seda.sem.persistence;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import it.seda.sem.domain.Cliente;
import it.seda.sem.mvc.annotations.BusinessRepository;


@BusinessRepository
public interface ClienteMapper {
    
	Cliente selectClienti(BigInteger id);
	void  insertClienti(Cliente cliente);
	void updateClienti(Cliente cliente);
	void deleteClienti(Cliente cliente);
	List<Cliente> listClienti(RowBounds rowBounds);

}
