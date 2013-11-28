package it.seda.generator.domain;

import it.seda.generator.util.GeneratorUtils;

public class I18NMessages {
	
	private String clazzMes;
	private String nameMes;
	private String namespaceMes;
	private String clazzVal;
	private String nameVal;
	private String namespaceVal;
	private Form form;

	public I18NMessages(String clazzMes,String clazzVal) {
		this.clazzMes=clazzMes;
		this.nameMes=GeneratorUtils.resolveClassName(clazzMes);
		this.namespaceMes=GeneratorUtils.resolveClassNamespace(clazzMes);
		this.clazzVal=clazzVal;
		this.nameVal=GeneratorUtils.resolveClassName(clazzVal);
		this.namespaceVal=GeneratorUtils.resolveClassNamespace(clazzVal);
	}
    
	

	public String getClazzMes() {
		return clazzMes;
	}



	public void setClazzMes(String clazzMes) {
		this.clazzMes = clazzMes;
	}



	public String getNameMes() {
		return nameMes;
	}



	public void setNameMes(String nameMes) {
		this.nameMes = nameMes;
	}



	public String getNamespaceMes() {
		return namespaceMes;
	}



	public void setNamespaceMes(String namespaceMes) {
		this.namespaceMes = namespaceMes;
	}



	public String getClazzVal() {
		return clazzVal;
	}



	public void setClazzVal(String clazzVal) {
		this.clazzVal = clazzVal;
	}



	public String getNameVal() {
		return nameVal;
	}



	public void setNameVal(String nameVal) {
		this.nameVal = nameVal;
	}



	public String getNamespaceVal() {
		return namespaceVal;
	}



	public void setNamespaceVal(String namespaceVal) {
		this.namespaceVal = namespaceVal;
	}



	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}



	@Override
	public String toString() {
		return "I18NMessages [clazzMes=" + clazzMes + ", nameMes=" + nameMes
				+ ", namespaceMes=" + namespaceMes + ", clazzVal=" + clazzVal
				+ ", nameVal=" + nameVal + ", namespaceVal=" + namespaceVal
				+ ", form=" + form + "]";
	}

	
    
	
}
