package it.seda.sem.persistence;

import org.apache.ibatis.session.RowBounds;
import java.util.List;
import it.seda.sem.domain.Test;
import it.seda.sem.mvc.annotations.BusinessRepository;
import org.apache.ibatis.session.RowBounds;


@BusinessRepository
public interface TestMapper {

	void insertTest(Test Test); 	
	void updateTest(Test Test);
	void deleteTest(Test Test); 
    Test  getTestById(Test Test); 
    int listTestCount(); 
	List<Test> listTest(RowBounds rowBounds); 
}
