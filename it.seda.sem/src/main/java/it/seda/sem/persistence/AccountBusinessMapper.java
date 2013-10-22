package it.seda.sem.persistence;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import it.seda.sem.domain.Clienti;
import it.seda.sem.mvc.annotations.BusinessRepository;


@BusinessRepository
public interface AccountBusinessMapper {
    
	Clienti selectClienti(BigInteger id);
	void  insertClienti(Clienti cliente);
	void updateClienti(Clienti cliente);
	void deleteClienti(Clienti cliente);
	List<Clienti> listClienti(RowBounds rowBounds);

}
