package it.seda.template.taglib.time.renderer;

import java.io.IOException;

/**
 * 
 * @author f.ricci
 *
 */
public class FixedPartRenderer extends AbstractPartRenderer {

	private StringBuilder stringBuilder;

	public FixedPartRenderer(int priority, String value, String cssClass) {
		super(null, DateTimePart.NO_PART, priority);
		setCssClass(cssClass);
		stringBuilder=new StringBuilder("<span");
		applyClass(stringBuilder);
		stringBuilder.append(">");
		stringBuilder.append(value.replaceAll(" ", "&nbsp;"));
		stringBuilder.append("</span>");
	}

	@Override
	public String render() throws IOException {
		return stringBuilder.toString();
	}
	
}	
