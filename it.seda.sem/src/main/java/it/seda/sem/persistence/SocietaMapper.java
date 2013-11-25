package it.seda.sem.persistence;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import it.seda.sem.domain.Societa;
import it.seda.sem.mvc.annotations.BusinessRepository;



@BusinessRepository
public interface SocietaMapper {

	void insertSocieta(Societa societa);

	void updateSocieta(Societa societa);

	void deleteSocieta(BigInteger id);

	Societa getSocieta(BigInteger id);

	int listSocietaCount();

	List<Societa> listSocieta(RowBounds rowBounds);
	
	List<Societa> listSocieta();
    

}

