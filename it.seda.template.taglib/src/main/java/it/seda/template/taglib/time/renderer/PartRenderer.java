package it.seda.template.taglib.time.renderer;

import java.io.IOException;

/**
 * 
 * @author f.ricci
 *
 */
public interface PartRenderer {

	int getPriority();
	
	String render() throws IOException;
	
}
