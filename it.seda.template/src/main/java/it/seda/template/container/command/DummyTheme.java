package it.seda.template.container.command;

import java.util.TreeSet;

/**
 * This class is used to define a particular theme that is is used when something
 * goes in an unexpected way in the syntax's evaluation.
 *
 */

public class DummyTheme extends AbstractCommandTheme {
	
	/**
	 * This is the class constructor.
	 */
	public DummyTheme(TreeSet<String> themesSet) {
		this.themesSet = themesSet;
	}
	 /**
	  * This method returns always true.
	  */
	@Override
	public boolean evaluateTheme(String theme) {
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
	 * Verifies if an object is an istance of DummyTheme
	 */
	@Override
	boolean instanceOf(Object o) {
		if(o!=null&&o instanceof DummyTheme) return true;
		return false;
	}

}
