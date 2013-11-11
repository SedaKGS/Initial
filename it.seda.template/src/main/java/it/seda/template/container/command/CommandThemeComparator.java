package it.seda.template.container.command;

import java.util.Comparator;
import java.util.TreeSet;

public class CommandThemeComparator<T> implements Comparator<CommandTheme> {

	@Override
	public int compare(CommandTheme ctl, CommandTheme ctr) {
		TreeSet<String> themeSetLeft=ctl.getThemesSet();
		TreeSet<String> themeSetRight=ctr.getThemesSet();
		if(themeSetLeft.contains(CommandThemeDefinitions.DEFAULT_THEME)) return  1;
		if(themeSetRight.contains(CommandThemeDefinitions.DEFAULT_THEME)) return -1;
		return 0;
	}

	

}
