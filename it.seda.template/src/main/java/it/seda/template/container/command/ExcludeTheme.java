package it.seda.template.container.command;

import java.util.TreeSet;

/**
 * Represent the CommandTheme implementation responsible for the exclusion of a particular theme.
 * 
 */

public class ExcludeTheme extends AbstractCommandTheme {


	/**
	 * This is the class constructor.
	 */
	public ExcludeTheme(String defaultTheme,TreeSet<String> themesSet) {
		super(defaultTheme, themesSet);
	}

	/**
	 * This method checks if a particular theme has to be excluded.
	 */
	public boolean evaluateTheme(String themeArgs) {
		if(themesSet.contains(themeArgs)) {
			return false;
		}

		if(themeArgs.equals(defaultTheme)){
			return false;
		}

		return true;
	}



	/**
	 * Returns the set of the themes.
	 */
	@Override
	public TreeSet<String> getThemesSet() {
		return this.themesSet;
	}

	/**
	 * Verifies if an object is an istance of ExcludeTheme
	 */
	@Override
	boolean instanceOf(Object o) {
		if(o!=null&&o instanceof ExcludeTheme) return true;
		return false;
	}


	/**
	 * Verifies when two different command themes  are equals, checking if they contain
	 * the same set of themes.
	 */
	@Override
	public boolean equals(Object ct) {
		if(ct==null||!instanceOf(ct)) {
			return false;
		}
		CommandTheme commandTheme=(CommandTheme) ct;
		if(themesSet.size()== commandTheme.getThemesSet().size()
				&& themesSet.containsAll(commandTheme.getThemesSet())) {
			return true;
		}
		return false;
	}


}


