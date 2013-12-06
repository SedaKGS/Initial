package it.seda.template.taglib.time.renderer;

import java.io.IOException;

/**
 * 
 * @author f.ricci
 *
 */
public interface PartRenderer {

	int getPriority();
	void setReadonly(boolean readonly);
	void setEmptyOption(boolean emptyopt);
	void setCssClass(String cssClass);
	
	String render() throws IOException;
	
}
