package it.seda.template.container.command;

import it.seda.template.utils.ThemeComparator;

import java.util.TreeSet;

public class CommandThemeFactory {
	
	
	
	
	
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
	
	private static TreeSet<String> resolve(String themeArgs) {
		 String themes=null;
		 if(themeArgs!=null && themeArgs.startsWith(CommandThemeDefinitions.INCLUDE)) {
			 themes=themeArgs.substring(CommandThemeDefinitions.INCLUDE.length(),themeArgs.length()-CommandThemeDefinitions.END.length());
		 }
		 else if(themeArgs!=null && themeArgs.startsWith(CommandThemeDefinitions.EXCLUDE)) {
			 themes=themeArgs.substring(CommandThemeDefinitions.EXCLUDE.length(),themeArgs.length()-CommandThemeDefinitions.END.length());
		 } 
		 else {
			 themes=themeArgs;
		 }
		 TreeSet<String> outTree=resolveThemes(themes); 
		 return outTree;
	}

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

	static public boolean includePattern(String themeArgs){
		if(themeArgs==null) return true;
		if(themeArgs.startsWith(CommandThemeDefinitions.INCLUDE)&&themeArgs.endsWith(CommandThemeDefinitions.END)) return true;
		if(!themeArgs.startsWith(CommandThemeDefinitions.INCLUDE)&&!themeArgs.startsWith(CommandThemeDefinitions.EXCLUDE)) return true;
		return false;
		}
	
	static public boolean excludePattern(String themeArgs){
		if(themeArgs.startsWith(CommandThemeDefinitions.EXCLUDE)&&themeArgs.endsWith(CommandThemeDefinitions.END)) return true;
		return false;}

}
