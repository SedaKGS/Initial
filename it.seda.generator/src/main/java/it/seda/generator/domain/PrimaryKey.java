package it.seda.generator.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PrimaryKey {
	
	private List<Attribute> primaryKey;
	

	public PrimaryKey(Model model) {
		primaryKey=buildPrimaryKey(model.getAttributes());
	}

	private List<Attribute> buildPrimaryKey(List<Attribute> attributes){
		Collections.sort(attributes, getComparator());
		List<Attribute> lst=new ArrayList<Attribute>();
		for (Attribute attribute : attributes) {
			if(attribute.getPk()>0) lst.add(attribute);
		}
		return lst;
	}

	
	private Comparator<Attribute> getComparator(){
		Comparator<Attribute> comparator=new Comparator<Attribute>(){

			@Override
			public int compare(Attribute atl, Attribute atr) {
				
				if(atl.getPk()<atr.getPk()){
				return -1;
				}
				if(atl.getPk()>atr.getPk()){
					return 1;
				}
				return 0;
			}

			
			
		};
		
		return comparator;
		
	}
    
	
}
