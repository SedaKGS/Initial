package it.seda.template.container.command;

import java.util.TreeSet;

/**
 * Syntax used in the parameter tag's attribute "themes". 
 * Furthermore we define the methods used to manage the themes set.
 */

public interface CommandTheme {
	
	static public final String INCLUDE="include(";
    static public final String EXCLUDE="exclude(";
    static public final String END=")";
    static public final String DEFAULT_THEME="";
	
    /**
     * Method that checks if a theme has to be included or excluded
     */
	boolean evaluateTheme(String theme);
	/**
     * Method that returns the set of themes in the current CommandTheme's implementation.
     */
	TreeSet<String> getThemesSet();
	
}
