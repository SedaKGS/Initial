package com.seda.entrate.formulari.persistence;

import org.apache.ibatis.session.RowBounds;
import java.util.List;
import com.seda.entrate.formulari.domain.Comune;
import org.springframework.stereotype.Service;
import org.apache.ibatis.session.RowBounds;


@Service
public interface ComuneMapper {

	void insertComune(Comune Comune); 	
	void updateComune(Comune Comune);
	void deleteComune(Comune Comune); 
    Comune  getComuneById(Comune Comune); 
    int listComuneCount(); 
	List<Comune> listComune(RowBounds rowBounds); 
}
