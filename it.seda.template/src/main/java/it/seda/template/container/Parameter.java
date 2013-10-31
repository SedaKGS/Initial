package it.seda.template.container;

import it.seda.template.renderer.Renderer;

/**
 * 
 * @author f.ricci
 *
 */

public class Parameter {

	private String key;
	private String value;
	private Renderer renderer;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Renderer getRenderer() {
		return renderer;
	}
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	@Override
	public String toString() {
		return "Parameter [key=" + key + ", value=" + value + ", renderer="
				+ renderer + "]";
	}
	
}
