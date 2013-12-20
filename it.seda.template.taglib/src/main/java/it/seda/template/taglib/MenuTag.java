package it.seda.template.taglib;

import it.seda.template.container.menu.MenuItem;
import it.seda.template.taglib.menu.DefaultRenderer;
import it.seda.template.taglib.menu.MultiRowRenderer;
import it.seda.template.taglib.menu.Renderer;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MenuTag extends SimpleTagSupport {
	
	protected String menuAttribute;
	
	protected String prefix;
	
	protected String classItem;
	protected String classItemSelected;
	protected String classItemLink;
	protected String classItemLinkSelected;
	
	protected String element;
	protected String elementClass;
	
	protected boolean useModifier;
	protected boolean useSelectedParent;
	protected Renderer renderer;
	
	protected PageContext pageContext;
	
	static final String PREFIX="menu-";
	static final String CLASS_ITEM="menu-item";
	static final String CLASS_ITEM_SELECTED="menu-item-selected";
	static final String CLASS_ITEM_LINK="menu-item-link";
	static final String CLASS_ITEM_LINK_SELECTED="menu-item-link-selected";
	
	static final String ELEMENT="div";
	static final String CLASS_ELEMENT="main-menu";	
	
	public void setVar(String var) {
		menuAttribute=var;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}	
	public void setClassItem(String classItem) {
		this.classItem = classItem;
	}
	public void setClassItemSelected(String classItemSelected) {
		this.classItemSelected = classItemSelected;
	}
	public void setClassItemLink(String classItemLink) {
		this.classItemLink = classItemLink;
	}
	public void setClassItemLinkSelected(String classItemLinkSelected) {
		this.classItemLinkSelected = classItemLinkSelected;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public void setElementClass(String elementClass) {
		this.elementClass = elementClass;
	}
	public void setUseModifier(boolean useModifier) {
		this.useModifier=useModifier;
	}
	public void setUseSelectedParent(boolean useSelectedParent) {
		this.useSelectedParent = useSelectedParent;
	}
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	protected void resolvePageContext() {
		if (pageContext==null) {
			pageContext = (PageContext) getJspContext();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void doTag() throws JspException, IOException {
		resolvePageContext();
		resolveCssClass();
		resolveRenderer();
		
		String attribute=null;
		if (menuAttribute==null) {
			attribute="menu_items";
		}
		List<MenuItem> roots = (List<MenuItem>)pageContext.getRequest().getAttribute(attribute);
		StringBuilder buffer = null;
		try {
			buffer = new StringBuilder();
			
			if (roots!=null && roots.size()>0) {
				renderer.render(roots, buffer);
				pageContext.getOut().write(buffer.toString());				
			}
		} finally {
			buffer=null;
			recycleTag();
		}
	}

	
	private void resolveRenderer() {
		if (renderer!=null) {
			if (!(renderer instanceof Renderer)) {
				throw new IllegalArgumentException("provided renderer is not an instance of " + Renderer.class);
			}
		} else if (useSelectedParent) {
			renderer = new MultiRowRenderer(pageContext);
		} else {
			renderer = new DefaultRenderer(pageContext);
		}
		renderer.setPrefix(prefix);
		renderer.setElement(element);
		renderer.setElementClass(elementClass);
		
		renderer.setClassItem(classItem);
		renderer.setClassItemSelected(classItemSelected);
		renderer.setClassItemLink(classItemLink);
		renderer.setClassItemLinkSelected(classItemLinkSelected);
		
		renderer.setUseModifier(useModifier);
	}

	private void resolveCssClass() {
		classItem=defaultable(classItem, CLASS_ITEM);
		classItemSelected=defaultable(classItemSelected,CLASS_ITEM_SELECTED);
		classItemLink=defaultable(classItemLink,CLASS_ITEM_LINK);
		classItemLinkSelected=defaultable(classItemLinkSelected, CLASS_ITEM_LINK_SELECTED);
		
		element=defaultable(element, ELEMENT);
		elementClass=defaultable(elementClass, CLASS_ELEMENT);
		
		prefix=defaultable(prefix, PREFIX);
	}

	protected String defaultable(String value, String def) {
		if (value==null) {
			return def;
		}
		return value;
	}
	
	private void recycleTag() {
		prefix=null;
		renderer=null;
		
		classItem=null;
		classItemSelected=null;
		classItemLink=null;
		classItemLinkSelected=null;
		
		useSelectedParent=false;
		useModifier=false;
		menuAttribute=null;
	}

}
