package it.seda.template.container.command;

import it.seda.template.utils.ThemeComparator;

import java.util.TreeSet;

/**
 * This class is responsable in the choosing of a particular command theme using
 *the theme syntax definition:
 *<ul>
 * <li>
 * If the theme attribute is in the form of a String List ie: "them1,theme2,..." is 
 * choosen an IncludeTheme.
 * </li>
 * <li>
 * If the theme attribute is in the form "include(theme1,theme2,...)" is 
 * choosen an IncludTheme.
 * </li>
 * <li>
 * If the attribute theme is in the form "exclude(theme1,theme2,...)" is 
 * choosen an ExcludTheme."
 * </li>
 *</ul>
 *
 */

public class CommandThemeFactory {
	
	/**
	 * 
	 * Returns the CommandTheme implementation by analyzing the syntax of the themes
	 * attribute in parameter tag.
	 */
	public static CommandTheme parseTheme(String themeArgs) {
		if(includePattern(themeArgs)){
			TreeSet<String> treeThemes=resolve(themeArgs);
			return new IncludeTheme(treeThemes);
		}
		if(excludePattern(themeArgs)){
			TreeSet<String> treeThemes=resolve(themeArgs);
			return new ExcludeTheme(treeThemes);
		}
		return new DummyTheme(new TreeSet<String>());	
	}
	
	/**
	 *Returns an ordered set analyzing syntax in the themes attribute.
	 */
	
	private static TreeSet<String> resolve(String themeArgs) {
		 String themes=null;
		 if(themeArgs!=null && themeArgs.startsWith(CommandTheme.INCLUDE)) {
			 themes=themeArgs.substring(CommandTheme.INCLUDE.length(),themeArgs.length()-CommandTheme.END.length());
		 }
		 else if(themeArgs!=null && themeArgs.startsWith(CommandTheme.EXCLUDE)) {
			 themes=themeArgs.substring(CommandTheme.EXCLUDE.length(),themeArgs.length()-CommandTheme.END.length());
		 } 
		 else {
			 themes=themeArgs;
		 }
		 TreeSet<String> outTree=resolveThemes(themes); 
		 return outTree;
	}
	
	/**
	 *Returns an ordered set analyzing the Strings's list in the themes attribute.
	 */

	private static TreeSet<String> resolveThemes(String themes) {
		
		String [] arrayThemes=null;
		TreeSet<String> themesSet=new TreeSet<String>(new ThemeComparator<String>());
        if(themes==null){
        	themesSet.add("");
        	return themesSet;
		}
		if(themes.contains(",")){
			themes.split(",");
		}
		else{
			arrayThemes=new String[]{themes};
		}
		
		for (String theme : arrayThemes) {
			themesSet.add(theme);
		}
		
		return themesSet;
	}
	/**
	 *Checks if the themes attribute is an include attribute.
	 */
	static public boolean includePattern(String themeArgs){
		if(themeArgs==null) return true;
		if(themeArgs.startsWith(CommandTheme.INCLUDE)&&themeArgs.endsWith(CommandTheme.END)) return true;
		if(!themeArgs.startsWith(CommandTheme.INCLUDE)&&!themeArgs.startsWith(CommandTheme.EXCLUDE)) return true;
		return false;
		}
	/**
	 *Checks if the themes attribute is an exclude attribute.
	 */
	static public boolean excludePattern(String themeArgs){
		if(themeArgs.startsWith(CommandTheme.EXCLUDE)&&themeArgs.endsWith(CommandTheme.END)) return true;
		return false;}

}
