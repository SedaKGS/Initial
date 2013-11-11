package it.seda.template.container.command;

import java.util.TreeSet;

public class ExcludeTheme extends AbstractCommandTheme {

 
	
 public ExcludeTheme(TreeSet<String> themesSet) {
		this.themesSet = themesSet;
	}


 public boolean evaluateTheme(String themeArgs) {
		if(themesSet.contains(themeArgs)) return false;
		return true;
 }


@Override
public TreeSet<String> getThemesSet() {
	return this.themesSet;
}


@Override
boolean instanceOf(Object o) {
	if(o!=null&&o instanceof ExcludeTheme) return true;
	return false;
}


@Override
public boolean equals(Object ct) {
	if(ct==null||!instanceOf(ct)) return false;
	CommandTheme commandTheme=(CommandTheme) ct;
	if(themesSet.size()== commandTheme.getThemesSet().size()&& themesSet.containsAll(commandTheme.getThemesSet())) return true;
	return false;
}


}


