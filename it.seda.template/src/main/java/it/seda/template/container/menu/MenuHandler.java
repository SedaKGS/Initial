/**
 * 
 */
package it.seda.template.container.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author f.ricci
 *
 */
public class MenuHandler {

	private Logger logger = LoggerFactory.getLogger(MenuHandler.class);
	private List<Menu> menus= new ArrayList<Menu>();
	
	public boolean contains(Menu menu) {
		return menus.contains(menu);
	}
	public Menu get(String id) {
		for (Menu menu: menus) {
			if (menu.getId().equals(id)) {
				return menu;
			}
		}
		return null;
	}
	
	public void addMenu(Menu menu) {
		menus.add(menu);
	}
	
	public void addAllMenu(List<Menu> menus) {
		menus.clear();
		menus.addAll(menus);
	}	

	public List<MenuItem> resolve(HttpServletRequest request) {
		Map<String, MenuItem> tempMap = new TreeMap<String, MenuItem>();
		
		List<MenuItem> roots = new ArrayList<MenuItem>();
		for (Menu menu: menus) {
			if (menu.isInRole(request)) {
				MenuItem menuItem = new MenuItem(menu, menu.matches(request));
				tempMap.put(menu.getId(), menuItem);
				if (menu.getId().indexOf('.') == -1) { //root 
					roots.add(menuItem);
				}				
			} 
		}

		if (roots.isEmpty() && tempMap.size()>0) {
			roots=resolveRoots(tempMap, 1); // roots may contains one or more dots
		}
		
		for (String menuId : tempMap.keySet()) {
			MenuItem menuItem = tempMap.get(menuId);
			if (roots.contains(menuItem)) { // root
				continue;
			}

			final int dot = menuId.lastIndexOf('.');
			if (dot>-1) {
				final String fatherId = menuId.substring(0,dot);
				MenuItem father = tempMap.get(fatherId);
				if (father!=null) {
					father.addChild(menuItem);
				} else {
					if (logger.isDebugEnabled()) {
						logger.debug("'" + menuId + "' without parent '" + fatherId + "'");
					}						
				}
			}			
			
		}

		return roots;
	}
	
	private List<MenuItem> resolveRoots(Map<String, MenuItem> tempMap, int dots) {
		List<MenuItem> roots=null;
		
		for (MenuItem menuItem: tempMap.values()) {
			final int count = StringUtils.countOccurrencesOf(menuItem.getId(), ".");
			if (count == dots) {
				if (roots==null) {
					roots=new ArrayList<MenuItem>();
				}
				roots.add(menuItem);
			} 
		}
		
		return roots==null?resolveRoots(tempMap, dots+1):roots;
	}
	
	
}
