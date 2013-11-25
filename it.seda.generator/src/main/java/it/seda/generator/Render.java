package it.seda.generator;

import org.apache.velocity.app.Velocity;




public class Render {
	
	private Velocity velocity;
	private Context context;
	private Writer writer;
	private InputStream inputStream;

	public Render() {
		velocity.evaluate(context, writer, logTag, inputStream)
		
	}

}
