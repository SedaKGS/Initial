package it.seda.template.taglib.time.renderer;

import java.io.IOException;

/**
 * 
 * @author f.ricci
 *
 */
public class FixedPartRenderer extends AbstractPartRenderer {

	private String value;
	
	public FixedPartRenderer(int priority, String value, String cssClass) {
		super(null, DateTimePart.NO_PART, priority);
		this.value=value;
		setCssClass(cssClass);
	}

	@Override
	public String render() throws IOException {
		final StringBuilder stringBuilder=new StringBuilder("<span");
		applyClass(stringBuilder);
		stringBuilder.append(">");
		stringBuilder.append(value.replaceAll(" ", "&nbsp;"));
		stringBuilder.append("</span>");		
		return stringBuilder.toString();
	}

	@Override
	public String getValue() {
		return value;
	}
	
}	
