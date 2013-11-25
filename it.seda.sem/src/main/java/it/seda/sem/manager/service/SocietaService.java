package it.seda.sem.manager.service;

import java.math.BigInteger;
import java.util.List;

import it.seda.sem.domain.Societa;
import it.seda.sem.persistence.SocietaMapper;
import it.seda.sem.security.domain.AccountTO;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SocietaService {
	
	
	@Inject private SocietaMapper societaMapper;

	@Transactional("transactionBusinessManager")
	public void insertSocieta(Societa societa) {
		societaMapper.insertSocieta(societa);
		
	}
	
	@Transactional("transactionBusinessManager")
	public void updateSocieta(Societa societa) {
		societaMapper.updateSocieta(societa);
	}
	
	@Transactional("transactionBusinessManager")
	public void deleteSocieta(BigInteger id) {
		societaMapper.deleteSocieta(id);
	}
	
	@Transactional("transactionBusinessManager")
	public Societa  getSocieta(BigInteger id) {
		Societa societa=societaMapper.getSocieta(id);
		return societa;
		
	}
	
	@Transactional("transactionBusinessManager")
	public int listSocietaCount() {
		int rowsNumber=societaMapper.listSocietaCount();
		return rowsNumber;
		
	}
	
	@Transactional("transactionBusinessManager")
	public List<Societa> listSocieta(RowBounds rowBounds) {
		List<Societa> cl=societaMapper.listSocieta(rowBounds);
		return cl;
		
	}
	
	@Transactional("transactionBusinessManager")
	public List<Societa> listSocieta() {
		List<Societa> cl=societaMapper.listSocieta();
		return cl;
		
	}

}

