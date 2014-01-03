package it.seda.sem.manager.service;

import it.seda.jdbc.commons.DataPage;
import it.seda.jdbc.commons.DefaultDataPage;
import it.seda.jdbc.ibatis.RowBoundsHelper;
import it.seda.sem.domain.Societa;
import it.seda.sem.persistence.SocietaMapper;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SocietaService {
	
	
	@Inject private SocietaMapper societaMapper;

	@Transactional
	public void insertSocieta(Societa societa) {
		societaMapper.insertSocieta(societa);
		
	}
	
	@Transactional
	public void updateSocieta(Societa societa) {
		societaMapper.updateSocieta(societa);
	}
	
	@Transactional
	public void deleteSocieta(BigInteger id) {
		societaMapper.deleteSocieta(id);
	}
	
	@Transactional(readOnly=true)
	public Societa  getSocieta(BigInteger id) {
		Societa societa=societaMapper.getSocieta(id);
		return societa;
		
	}
	
	@Transactional(readOnly=true)
	public List<Societa> listSocieta() {
		List<Societa> cl=societaMapper.listSocieta();
		return cl;
		
	}

	
	@Transactional(readOnly=true)
	public DataPage<Societa> listSocieta(int pageNumber, int rowsPerPage) {
		RowBoundsHelper rbh = new RowBoundsHelper(rowsPerPage, pageNumber);
		
		int totalRows=societaMapper.listSocietaCount();
		
		List<Societa> societaList=societaMapper.listSocieta(rbh.buildRowBounds());
		
		DataPage<Societa> serverPage=new DefaultDataPage<Societa>(societaList);
		rbh.decorate(serverPage, totalRows);
		
		return serverPage;
	}	
	
}
