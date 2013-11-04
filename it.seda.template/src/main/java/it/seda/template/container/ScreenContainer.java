/**
 * 
 */
package it.seda.template.container;

import java.util.HashMap;
import java.util.Map;

/**
 * @author f.ricci
 *
 */
public class ScreenContainer {
	
	private Map<String, Screen> screens;
	
	public void addScreen(Screen screen) {
		screens.put(screen.getName(), screen);
	}
	
	public boolean contains(String name) {
		return screens.containsKey(name);
	}
	
	public Screen getScreen(String name) {
		return screens.get(name);
	}	
	
	public ScreenContainer() {
		screens=new HashMap<String, Screen>();
	}

	@Override
	public String toString() {
		return "ScreenContainer [screens=" + screens + "]";
	}
	
}
