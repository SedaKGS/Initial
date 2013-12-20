package it.seda.template.taglib.menu;

import it.seda.template.container.menu.MenuItem;
import it.seda.template.taglib.I18N;

import javax.servlet.jsp.PageContext;


public abstract class AbstractRenderer implements Renderer{
	
	protected PageContext pageContext;
	protected String prefix;
	protected String classItem;
	protected String classItemSelected;
	protected String classItemLink;
	protected String classItemLinkSelected;	
	
	protected String element;
	protected String elementClass;
	
	protected boolean useModifier;	
	
	@Override
	public void setPrefix(String prefix) {
		this.prefix=prefix;
	}

	@Override
	public void setUseModifier(boolean use) {
		this.useModifier=use;
	}

	@Override
	public void setClassItem(String cssClass) {
		this.classItem=cssClass;
	}

	@Override
	public void setClassItemSelected(String cssClass) {
		this.classItemSelected=cssClass;
	}

	@Override
	public void setClassItemLink(String cssClass) {
		this.classItemLink=cssClass;
	}

	@Override
	public void setClassItemLinkSelected(String cssClass) {
		this.classItemLinkSelected=cssClass;
	}

	@Override
	public void setElement(String element) {
		this.element=element;
	}

	@Override
	public void setElementClass(String elementClass) {
		this.elementClass=elementClass;
	}

	public AbstractRenderer(PageContext pageContext) {
		this.pageContext=pageContext;
	}
	
	protected void addAnchor(MenuItem menuItem, StringBuilder buffer, int i) {
		final String modifier = useModifier?(i==0?"":String.valueOf(i)):"";
		
		startAnchor(buffer);

		buffer.append(" href=\"");
		
		if (menuItem.getHasLink()) {
			buffer.append(pageContext.getServletContext().getContextPath())
				.append(menuItem.getPath())
				.append("\"");
		} else {
			buffer.append("#");
		}
		
		buffer.append("\"");

		buffer.append(" class=\"").append(menuItem.getHasLink()?classItemLink:classItem).append(modifier);
		
		if (menuItem.isSelected()) {
			buffer.append(" ").append(menuItem.getHasLink()?classItemLinkSelected:classItemSelected);
		}
		
		buffer.append("\">");
		
		buffer.append(I18N.printMessage(prefix+menuItem.getId())); 
		
		endAnchor(buffer);
		
	}	
	
	protected static void startTag(StringBuilder buffer, String element, String cssClass) {
		buffer.append("<").append(element);
		if (cssClass!=null) {
			buffer.append(" class=\"");
			buffer.append(cssClass);
			buffer.append("\"");
		}
		buffer.append(">");
	}
	protected static void entTag(StringBuilder buffer, String element) {
		buffer.append("</").append(element).append(">");
	}
	
	protected static void startParent(StringBuilder buffer) {
		buffer.append("<ul>");
	}
	protected static void endParent(StringBuilder buffer) {
		buffer.append("</ul>");
	}	
	protected static void startItem(StringBuilder buffer) {
		buffer.append("<li>");
	}
	protected static void endItem(StringBuilder buffer) {
		buffer.append("</li>");
	}	
	protected static void startAnchor(StringBuilder buffer) {
		buffer.append("<a");
	}
	protected static void endAnchor(StringBuilder buffer) {
		buffer.append("</a>");
	}

}
