package it.seda.template.taglib.time.renderer;

import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;

/**
 * 
 * @author f.ricci
 *
 */
public interface Renderer {

	public final static Comparator<PartRenderer> PART_COMPARATOR = new Comparator<PartRenderer>() {
		@Override
		public int compare(PartRenderer o1, PartRenderer o2) {
			return o1.getPriority()-o2.getPriority();
		}
	};
	
	void render(Writer writer) throws IOException;
	
}
