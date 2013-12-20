/**
 * 
 */
package it.seda.template.taglib.menu;

import it.seda.template.container.menu.MenuItem;

import java.util.Collection;

import javax.servlet.jsp.PageContext;

/**
 * @author f.ricci
 *
 */
public class DefaultRenderer extends AbstractRenderer {

	public DefaultRenderer(PageContext pagecontext) {
		super(pagecontext);
	}

	@Override
	public void render(Collection<MenuItem> roots, StringBuilder buffer) {
		startTag(buffer, element, elementClass);
		treeToBuffer(roots, buffer, 0);
		entTag(buffer, element);
	}
	
	private void treeToBuffer(Collection<MenuItem> roots, StringBuilder buffer, int i) {
		startParent(buffer);
		for (MenuItem menuItem : roots) {
			elementToBuffer(menuItem, buffer, i);	
		}
		endParent(buffer);
	}	

	private void elementToBuffer(MenuItem menuItem, StringBuilder buffer, int i) {
		startItem(buffer);
		addAnchor(menuItem, buffer, i);
		
		if (menuItem.getHasChildren()) {
			treeToBuffer(menuItem.getChildren(), buffer, i+1);
		}
		endItem(buffer);
	}

}
