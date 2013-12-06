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
	
	protected boolean readonly;
	protected boolean emptyopt;
	
	public void setReadonly(boolean readonly) {
		this.readonly=readonly;
	}
	
	public void setEmptyOption(boolean emptyopt) {
		this.emptyopt=emptyopt;
	}	
	
	public void setCssClass(String cssClass) {
		this.cssClass=cssClass;
	}	
	
	@Override
	public int getPriority() {
		return priority;
	}		

	public abstract String getValue();
	
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
	
	protected void applyReadonly(StringBuilder stringBuilder) {
		if (readonly) {
			stringBuilder.append(" disabled=\"disabled\"");
		}		
	}
	
	protected void applyEmptyOption(StringBuilder stringBuilder) {
		if (emptyopt) {
			stringBuilder.append("<option value=\"\"></option>");
		}
	}
	
	protected void applyHiddenField(StringBuilder stringBuilder) {
		if (readonly) {
			stringBuilder.append("<input type=\"hidden\"");
			stringBuilder.append(" id=\"").append(fullPath).append("\"");
			stringBuilder.append(" name=\"").append(fullPath).append("\"");
			stringBuilder.append(" value=\"").append(getValue()).append("\"");
			stringBuilder.append(">");
		}
	}	

}