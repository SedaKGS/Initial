package it.seda.template.container.command;

import java.util.TreeSet;

public abstract class AbstractCommandTheme implements CommandTheme{
	
	protected TreeSet<String> themesSet;
	protected String defaultTheme="";

	public String getDefaultTheme() {
		return defaultTheme;
	}
	
	public AbstractCommandTheme(String defaultTheme, TreeSet<String> themesSet) {
		this.defaultTheme=defaultTheme;
		this.themesSet=themesSet;
	}
	
	@Override
	public boolean equals(Object ct) {
		if(ct==null||!instanceOf(ct)) return false;
		CommandTheme commandTheme=(CommandTheme) ct;
		if(themesSet.size()== commandTheme.getThemesSet().size()&& themesSet.containsAll(commandTheme.getThemesSet())) return true;
		return false;
	}
	
	abstract boolean instanceOf(Object o);
	

}
