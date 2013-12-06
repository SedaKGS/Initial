package it.seda.template.taglib.time.renderer;

import java.io.IOException;
/**
 * 
 * @author f.ricci
 *
 */
public class BoundedPartRenderer extends AbstractPartRenderer {

	protected Integer value;
	protected int rawvalue;
	protected int min;
	protected int max;
	protected int digits;
	protected char pad;

	protected String format;

	@Override
	public String getValue() {
		return value==null?"":String.valueOf(value);
	}	
	
	public BoundedPartRenderer(String path, DateTimePart part, int priority, int min, int max, int digits, char pad, Integer value) {
		super(path, part, priority);
		this.min=min;
		this.max=max;
		this.digits=digits;
		this.pad=pad;
		this.value=value;
		if (value!=null) {
			rawvalue=value.intValue();
		} else {
			rawvalue=-1;
		}
	}
	
	public String render() throws IOException {
		final StringBuilder stringBuilder = new StringBuilder();		
		stringBuilder.append("<select id=\"");
		stringBuilder.append(fullPath);
		stringBuilder.append("\" ");
		stringBuilder.append("name=\"");
		stringBuilder.append(fullPath);
		stringBuilder.append("\"");
		
		applyClass(stringBuilder);
		applyReadonly(stringBuilder);
		
		stringBuilder.append(">");
		
		applyEmptyOption(stringBuilder);
		
		for (int i = min; i < max; i++) {
			stringBuilder.append("<option value=\"");
			stringBuilder.append(i);
			stringBuilder.append("\"");
			stringBuilder.append(rawvalue==i?" selected=\"selected\"":"");
			stringBuilder.append(">");
			stringBuilder.append(String.format(getFormat(),i));
			stringBuilder.append("</option>");
		}
		stringBuilder.append("</select>");
		
		applyHiddenField(stringBuilder);		
		
		return stringBuilder.toString();
	}

	protected String getFormat() {
		if (format==null) {
			format="%"+(pad==' '?"":pad)+digits+"d";
		}
		return format;
	}

}