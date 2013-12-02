package it.seda.template.taglib.time.renderer;

/**
 * 
 * @author f.ricci
 *
 */
public abstract class AbstractPartRenderer implements PartRenderer {

	protected String path;
	protected int priority;
	protected DateTimePart part;
	
	protected String fullPath;
	
	protected String cssClass;
	
	public void setCssClass(String cssClass) {
		this.cssClass=cssClass;
	}	
	
	@Override
	public int getPriority() {
		return priority;
	}		
	
	public AbstractPartRenderer(String path, DateTimePart part, int priority) {
		this.path=path;
		this.part=part;
		this.priority=priority;
		this.fullPath=this.part.getFullPath(path);
	}

	
	protected void applyClass(StringBuilder stringBuilder) {
		if (cssClass!=null) {
			stringBuilder.append(" class=\"");
			stringBuilder.append(cssClass);
			stringBuilder.append("\"");
		}		
	}

}