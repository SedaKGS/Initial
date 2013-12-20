/**
 * 
 */
package it.seda.template.taglib.menu;

import it.seda.template.container.menu.MenuItem;

import java.util.Collection;

/**
 * @author f.ricci
 *
 */
public interface Renderer {

	void render(Collection<MenuItem> roots, StringBuilder buffer);
	
	void setUseModifier(boolean useModifier);
	void setClassItem(String classItem);
	void setClassItemSelected(String classItemSelected);
	void setClassItemLink(String classItemLink);
	void setClassItemLinkSelected(String classItemLinkSelected);

	void setElement(String element);
	void setElementClass(String elementClass);

	void setPrefix(String prefix);
}
