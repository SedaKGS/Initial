package it.seda.template.utils;

import java.util.Comparator;


public class ThemeComparator <T> implements Comparator<String>  {

	@Override
	public int compare(String thl, String thr) {
		return -1*thl.compareTo(thr);
	}

}
