package it.seda.sem.manager.service;
import  it.seda.sem.domain.Test;
import  it.seda.sem.persistence.TestMapper;
import javax.inject.Inject;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TestService {
	
		
	@Inject private TestMapper testMapper;
	
	@Transactional("transactionBusinessManager")
	public void insertTest(Test test) {
		testMapper.insertTest(test);      
	}
	
	
	@Transactional("transactionBusinessManager")
	public void updateTest(Test test) {
		testMapper.updateTest(test);
	}
	
	@Transactional("transactionBusinessManager")
	public void deleteTest(Test test) {
		testMapper.deleteTest(test);
	}
	
	@Transactional("transactionBusinessManager")
	public Test  getTestById(Test test) {
		Test tmp= testMapper.getTestById(test);
		return tmp;
		
	}
	
	@Transactional("transactionBusinessManager")
	public int listTestCount() {
		int rowsNumber=testMapper.listTestCount();
		return rowsNumber;
		
	}
	
	@Transactional("transactionBusinessManager")
	public List<Test> listTest(RowBounds rowBounds) {
		List<Test> cl=testMapper.listTest(rowBounds);
		return cl;
		
	}
	
	
	
	
}
