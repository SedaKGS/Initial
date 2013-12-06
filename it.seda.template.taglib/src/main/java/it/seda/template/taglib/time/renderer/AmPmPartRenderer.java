package it.seda.template.taglib.time.renderer;

import java.io.IOException;

/**
 * 
 * @author f.ricci
 *
 */
public class AmPmPartRenderer extends AbstractPartRenderer {

	public final static String[] AMPM = new String[]{"AM","PM"}; //TODO please fix me in a static way	
	
	private String[] values;
	private String value;


	@Override
	public String getValue() {
		return value==null?"":value;
	}	
	
	public AmPmPartRenderer(String path, int priority, String[] values, String value) {
		super(path, DateTimePart.AMPM, priority);
		this.value=value;
		this.values=values;
	}

	@Override
	public String render() throws IOException {
		final StringBuilder stringBuilder=new StringBuilder("<select id=\"");			
		stringBuilder.append(fullPath);
		stringBuilder.append("\" ");
		stringBuilder.append("name=\"");
		stringBuilder.append(fullPath);
		stringBuilder.append("\"");
		applyClass(stringBuilder);
		applyReadonly(stringBuilder);		
		stringBuilder.append(">");		
		
		applyEmptyOption(stringBuilder);
		
		for (int i = 0; i < AMPM.length; i++) {
			stringBuilder.append("<option value=\"");
			stringBuilder.append(AMPM[i]);
			stringBuilder.append("\"");
			stringBuilder.append((value!=null && AMPM[i].equals(value))?" selected=\"selected\"":"");
			stringBuilder.append(">");
			stringBuilder.append(values[i]);
			stringBuilder.append("</option>");				
		}
		
		stringBuilder.append("</select>");
		
		applyHiddenField(stringBuilder);		
		return stringBuilder.toString();
	}

	
}