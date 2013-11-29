package it.seda.generator.domain;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.seda.generator.XMLGeneratorParser;
import it.seda.generator.util.GeneratorUtils;


public class Attribute {

	private String name;
	private Class<?> type;
	private Class<?> generic;
	private String column;
	private int datagrid;
	private int form;
	private int pk;
	private int notEmpty;
	private String pattern;
	private String field;
	private int auto;
	
	private String inputType;
	
	private Logger logger = LoggerFactory.getLogger(Attribute.class);
	
	
	
	
    
	public int getAuto() {
		return auto;
	}
	public void setAuto(int auto) {
		this.auto = auto;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getInputType() {
		return inputType;
	}
	public int getNumericInputType() {
		if(inputType!=null) return 1;
		return 0;
	}
	public int getNumericListType() {
		if(type!=null&&type.equals(List.class)) return 1;
		return 0;
	}
	public int getNumericBooleanType(){
		if(type!=null&&type.equals(boolean.class)) return 1;
		return 0;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public int getDatagrid() {
		return datagrid;
	}
	public void setDatagrid(int datagrid) {
		this.datagrid = datagrid;
	}
	public int getForm() {
		return form;
	}
	public void setForm(int form) {
		this.form = form;
	}
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getName() {
		return name;
	}
	public String getUpperName(){
		return GeneratorUtils.capitalize(name);
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isNotPkList(){
		return !(pk>0&&type.equals(List.class));
	}
	public boolean isNotList(){
		return !(type.equals(List.class));
	}
	public String getNameUcase() {
		return GeneratorUtils.capitalize(name);
	}	
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	
	public String printType() {
		StringBuilder stringBuilder = new StringBuilder(type.getName());
		if (generic!=null) {
			stringBuilder.append("<").append(generic.getName()).append(">");
		}
		return stringBuilder.toString();
	}
	
	public Class<?> getGeneric() {
		return generic;
	}
	public void setGeneric(Class<?> generic) {
		this.generic = generic;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	
	

	public int getNotEmpty() {
		return notEmpty;
	}
	public void setNotEmpty(int notEmpty) {
		this.notEmpty = notEmpty;
	}
	public String getPattern() {
		return pattern;
	}
	
	public int getPatternNumeric()
	{
		if(isPattern()) {
			return 1;
		}
		else return 0;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	private boolean isNotEmpty(){
		if(this.notEmpty>0) return true;
		return false;
	}
	
	private boolean isPattern(){
		if(pattern!=null){
		try {
		  Pattern.compile(pattern);
		  return true;
		} catch (PatternSyntaxException e) {
			logger.warn("REGEX "+pattern+"of attribute"+this.name+" is not valid. "+e);
		}
		}
	return false;
	}
	public Attribute() {}
	
	public Attribute(String name, Class<?> type, String columnName) {
		super();
		this.name = name;
		this.type = type;
		this.column = column;
	}
	
	public Attribute(String name, Class<?> type, Class<?> generic, String columnName) {
		super();
		this.name = name;
		this.type = type;
		this.generic=generic;
		this.column = column;
	}	
	
	
	
	
	@Override
	public String toString() {
		return "Attribute [name=" + name + ", type=" + type + ", generic="
				+ generic + ", column=" + column + ", datagrid=" + datagrid
				+ ", form=" + form + ", pk=" + pk + "]";
	}
	
	
	
	
	
	
	
}
