package it.seda.template.taglib.time.renderer;

import java.io.IOException;

/**
 * 
 * @author f.ricci
 *
 */
public class AmPmPartRenderer extends AbstractPartRenderer {

	public final static String[] AMPM = new String[]{"AM","PM"}; //TODO please fix me in a static way	
	
	private StringBuilder stringBuilder;
	
	private String[] values;
	private String value;

	public AmPmPartRenderer(String path, int priority, String[] values, String value) {
		super(path, DateTimePart.AMPM, priority);
		this.value=value;
		this.values=values;
		stringBuilder=new StringBuilder("<select id=\"");			
		stringBuilder.append(fullPath);
		stringBuilder.append("\" ");
		stringBuilder.append("name=\"");
		stringBuilder.append(fullPath);
		stringBuilder.append("\"");
		applyClass(stringBuilder);
		stringBuilder.append(">");		
		
		for (int i = 0; i < AMPM.length; i++) {
			stringBuilder.append("<option value=\"");
			stringBuilder.append(AMPM[i]);
			stringBuilder.append("\"");
			stringBuilder.append(AMPM[i].equals(value)?" selected=\"selected\"":"");
			stringBuilder.append(">");
			stringBuilder.append(values[i]);
			stringBuilder.append("</option>");				
		}
		
		stringBuilder.append("</select>");
	}

	@Override
	public String render() throws IOException {
		return stringBuilder.toString();
	}
	
}