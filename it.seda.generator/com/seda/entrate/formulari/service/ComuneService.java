package com.seda.entrate.formulari.service;
import  com.seda.entrate.formulari.domain.Comune;
import  com.seda.entrate.formulari.persistence.ComuneMapper;
import javax.inject.Inject;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import it.seda.ws.restfull.data.WebServiceOutput;



@Service
public class ComuneService {
	
	;
	
	@Inject private ComuneMapper comuneMapper;
	
	@Transactional("transactionManager")
	public void insertComune(Comune comune) {
		comuneMapper.insertComune(comune);      
	}
	
	
	@Transactional("transactionManager")
	public void updateComune(Comune comune) {
		comuneMapper.updateComune(comune);
	}
	
	@Transactional("transactionManager")
	public void deleteComune(Comune comune) {
		comuneMapper.deleteComune(comune);
	}
	
	@Transactional("transactionManager")
	public Comune  getComuneById(Comune comune) {
		Comune tmp= comuneMapper.getComuneById(comune);
		return tmp;
		
	}
	
	@Transactional("transactionManager")
	public int listComuneCount() {
		int rowsNumber=comuneMapper.listComuneCount();
		return rowsNumber;
		
	}
	
	@Transactional("transactionManager")
	public List<Comune> listComune(RowBounds rowBounds) {
		List<Comune> cl=comuneMapper.listComune(rowBounds);
		return cl;
		
	}
	
	
	
	
	@Transactional("transactionManager")
	public WebServiceOutput  getComuneByIdWS(Comune comune) {
		Comune tmp= comuneMapper.getComuneById(comune);

	  List<Comune>  listEdit=new ArrayList<Comune>();
	  listEdit.add(tmp);
	  
	  DefaultDataPage<Comune> editOutPage =  new DefaultDataPage<Comune>(listEdit);
	  
	  WebServiceOutput editOutput = new WebServiceOutput();
	  editOutput.setModelName("listaContribuentiServizio");
      editOutput.setOrderBy("codice fiscale");
      editOutput.setStatus("ok");
      editOutput.setValue("");
	  editOutput.buildDataPage(editOutPage,1, 15,1);
      editOutput.setElementList(listEdit);
	  
     return editOutput;
		
	}
	
}
