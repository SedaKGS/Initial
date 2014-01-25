package it.seda.template.container.command;
import java.util.TreeSet;


/**
 * Represent the CommandTheme implementation responsible for the inclusion of a particular theme.
 * 
 */
public class IncludeTheme extends AbstractCommandTheme {
	
	
	/**
	 * This is the class constructor.
	 */
	public IncludeTheme(String defaultTheme, TreeSet<String> themesSet) {
		super(defaultTheme, themesSet);
	}

	 /**
	  * This method checks if a particular theme has to be included.
	  */
	public boolean evaluateTheme(String themeArgs) {
		if(themesSet.contains(themeArgs)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the set of the themes.
	 */
	@Override
	public TreeSet<String> getThemesSet() {
		return this.themesSet;
	}

	/**
	 * Verifies if an object is an istance of IncludeTheme
	 */
	@Override
	boolean instanceOf(Object o) {
		if(o!=null&&o instanceof IncludeTheme) {
			return true;
		}
		return false;
	}

	/**
	 * Verifies when two different command themes  are equals, checking if they contain
	 * the same set of themes.
	 */

	@Override
	public boolean equals(Object ct) {
		if(ct==null||!instanceOf(ct)){
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
