package it.seda.template.taglib.time.renderer;


public abstract class AbstractRenderer implements Renderer {

	protected String cssClass;
	protected String cssClassSep;
	protected boolean readonly;
	protected boolean emptyopt;
	
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public void setCssClassSep(String cssClassSep) {
		this.cssClassSep = cssClassSep;
	}
	public void setReadonly(boolean readonly) {
		this.readonly=readonly;
	}
	public void setEmptyopt(boolean emptyopt) {
		this.emptyopt=emptyopt;
	}	
	protected Integer nullOrInteger(String value) {
		if (value.isEmpty() || value.trim().length()==0) {
			return null;
		}
		return Integer.valueOf(value);
	}
	protected void applyToPart(PartRenderer renderer) {
		renderer.setCssClass(cssClass);
		renderer.setEmptyOption(emptyopt);
		renderer.setReadonly(readonly);
	}
}
