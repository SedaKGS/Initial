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
public class MultiRowRenderer extends AbstractRenderer {

	public MultiRowRenderer(PageContext pageContext) {
		super(pageContext);
	}

	@Override
	public void render(Collection<MenuItem> roots, StringBuilder buffer) {
		treeToBuffer(roots, buffer, 0);
	}
	
	private void treeToBuffer(Collection<MenuItem> roots, StringBuilder buffer, int i) {
		for (MenuItem menuItem : roots) {
			startTag(buffer, element, elementClass + (useModifier&&i>0?"-child":""));
			startParent(buffer);
			elementToBuffer(menuItem, buffer, i);
			endParent(buffer);			
			entTag(buffer, element);
		}
	}	

	private void elementToBuffer(MenuItem menuItem, StringBuilder buffer, int i) {
		startItem(buffer);
		addAnchor(menuItem, buffer, i);
		endItem(buffer);
		
		if (menuItem.isSelected() && menuItem.getHasChildren()) {
			// render another unordered list of items
			treeToBuffer(menuItem.getChildren(), buffer, i+1);
		}		
	}

}
