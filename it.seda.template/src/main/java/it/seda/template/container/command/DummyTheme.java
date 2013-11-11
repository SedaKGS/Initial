package it.seda.template.container.command;

import java.util.TreeSet;

public class DummyTheme extends AbstractCommandTheme {
	
	private TreeSet<String> themesSet;
    
	
	public DummyTheme(TreeSet<String> themesSet) {
		this.themesSet = themesSet;
	}
	
	@Override
	public boolean evaluateTheme(String theme) {
		return true;
	}

	@Override
	public TreeSet<String> getThemesSet() {
		return this.themesSet;
	}

	@Override
	boolean instanceOf(Object o) {
		if(o!=null&&o instanceof DummyTheme) return true;
		return false;
	}

}
