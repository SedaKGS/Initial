package com.seda.entrate.formulari.service;
import  com.seda.entrate.formulari.domain.Comune;
import  com.seda.entrate.formulari.persistence.ComuneMapper;
import javax.inject.Inject;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.seda.jdbc.commons.DefaultDataPage;
import java.util.ArrayList;


import it.seda.ws.restfull.data.WebServiceOutput;



@Service
public class ComuneService {
	
	;
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
	
	@Transactional(value="transactionManager",readOnly = true)
	public Comune  getComuneById(Comune comune) {
		Comune tmp= comuneMapper.getComune(comune);
		return tmp;
		
	}
	
	
	
	@Transactional(value="transactionManager",readOnly = true)
	public List<Comune> listComune(RowBounds rowBounds) {
		List<Comune> cl=comuneMapper.listComune(rowBounds);
		return cl;
		
	}
	
	@Transactional(value="transactionManager",readOnly = true)
	public WebServiceOutput  listComuneByFilterWS(Comune comune,RowBounds rowBounds) {
	  List<Comune> list=comuneMapper.listComuneByFilter(comune,rowBounds);
	  DefaultDataPage<Comune> editOutPage =  new DefaultDataPage<Comune>(list);
	  
	  int totalRows=comuneMapper.listComuneCount(comune);
	  
	  WebServiceOutput listOutput = new WebServiceOutput();
	  listOutput.setModelName("listaContribuentiServizio");
      listOutput.setOrderBy("codice fiscale");
      listOutput.setStatus("ok");
      listOutput.setValue("");
	  listOutput.buildDataPage(editOutPage,rowBounds.getLimit(),rowBounds.getOffset(),totalRows);
      listOutput.setElementList(list);
      return listOutput;
	}
	
	
	
	
	@Transactional("transactionManager")
	public WebServiceOutput  getComuneByIdWS(Comune comune) {
		Comune tmp= comuneMapper.getComune(comune);

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
