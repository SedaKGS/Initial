package it.seda.template.container.command;

import java.util.TreeSet;

public interface CommandTheme {
	
	boolean evaluateTheme(String theme);
	TreeSet<String> getThemesSet();
}
