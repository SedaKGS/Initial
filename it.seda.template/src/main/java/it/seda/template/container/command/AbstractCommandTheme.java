package it.seda.template.container.command;

import java.util.TreeSet;

public abstract class AbstractCommandTheme implements CommandTheme{
	
	protected TreeSet<String> themesSet;

	@Override
	public boolean evaluateTheme(String theme) {
		// TODO Auto-generated method stub
		return false;
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
