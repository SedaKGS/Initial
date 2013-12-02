package it.seda.template.taglib.time.renderer;

import java.io.IOException;
/**
 * 
 * @author f.ricci
 *
 */
public class BoundedPartRenderer extends AbstractPartRenderer {

	private StringBuilder stringBuilder = new StringBuilder();
	
	protected int value;
	protected int min;
	protected int max;
	protected int digits;
	protected char pad;

	protected String format;
	
	public BoundedPartRenderer(String path, DateTimePart part, int priority, int min, int max, int digits, char pad, int value) {
		super(path, part, priority);
		this.min=min;
		this.max=max;
		this.digits=digits;
		this.pad=pad;
		this.value=value;
		
		stringBuilder.append("<select id=\"");
		stringBuilder.append(fullPath);
		stringBuilder.append("\" ");
		stringBuilder.append("name=\"");
		stringBuilder.append(fullPath);
		stringBuilder.append("\"");
		applyClass(stringBuilder);
		stringBuilder.append(">");

		for (int i = min; i < max; i++) {
			stringBuilder.append("<option value=\"");
			stringBuilder.append(i);
			stringBuilder.append("\"");
			stringBuilder.append(value==i?" selected=\"selected\"":"");
			stringBuilder.append(">");
			stringBuilder.append(String.format(getFormat(),i));
			stringBuilder.append("</option>");
		}
		
		stringBuilder.append("</select>");
		
	}
	
	public String render() throws IOException {
		return stringBuilder.toString();
	}

	protected String getFormat() {
		if (format==null) {
			format="%"+(pad==' '?"":pad)+digits+"d";
		}
		return format;
	}
}