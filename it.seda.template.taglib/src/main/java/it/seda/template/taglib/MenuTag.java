package it.seda.template.taglib;

import it.seda.template.container.menu.MenuItem;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MenuTag extends SimpleTagSupport {
	
	protected String menuAttribute;
	protected PageContext pageContext;
	
	static final String CLASS_ITEM="menu-item";
	static final String CLASS_ITEM_SELECTED="menu-item-selected";
	static final String CLASS_ITEM_LINK="menu-item-link";
	static final String CLASS_ITEM_LINK_SELECTED="menu-item-link-selected";
	
	public void SetVar(String var) {
		menuAttribute=var;
	}
	
	protected void resolvePageContext() {
		if (pageContext==null) {
			pageContext = (PageContext) getJspContext();
		}
	}
	
	public void doTag() throws JspException {
		resolvePageContext();
		
		String attribute=null;
		if (menuAttribute==null) {
			attribute="menu_items";
		}
		

		List<MenuItem> roots = (List<MenuItem>)pageContext.getAttribute(attribute);
		
		StringBuilder buffer = new StringBuilder();
		
		if (roots.size()>0) {
			treeToBuffer(roots,buffer);
		}

		pageContext.getOut().write(buffer.toString());
	}

	
	
	
	private void treeToBuffer(List<MenuItem> roots, StringBuilder buffer) {
		startParent(buffer);
		for (MenuItem menuItem : roots) {
			elementToBuffer(menuItem, buffer, 0);	
		}
		endParent(buffer);
	}

	private void elementToBuffer(MenuItem menuItem, StringBuilder buffer, int i) {
		final String modifier = i==0?"":String.valueOf(i);
		
		startItem(buffer);
		startAnchor(buffer);
		
		if (menuItem.getHasLink()) {
			buffer.append(" href=\"")
				.append(pageContext.getServletContext().getContextPath())
				.append("\"");
			
			buffer.append(" class=\"").append(CLASS_ITEM_LINK).append(modifier);
					
			if (menuItem.isSelected()) {
				buffer.append(" ").append(CLASS_ITEM_LINK_SELECTED);
			}
			
			buffer.append("\"");
		} else {
			
		}
		
		endAnchor(buffer);
		endItem(buffer);
	}

	protected void startParent(StringBuilder buffer) {
		buffer.append("<ul>");
	}
	protected void endParent(StringBuilder buffer) {
		buffer.append("</ul>");
	}	
	protected void startItem(StringBuilder buffer) {
		buffer.append("<li>");
	}
	protected void endItem(StringBuilder buffer) {
		buffer.append("</li>");
	}	
	protected void startAnchor(StringBuilder buffer) {
		buffer.append("<a");
	}
	protected void endAnchor(StringBuilder buffer) {
		buffer.append("</a>");
	}	
}
