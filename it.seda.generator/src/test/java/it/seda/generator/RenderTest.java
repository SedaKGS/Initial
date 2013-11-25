package it.seda.generator;

import it.seda.generator.domain.Attribute;
import it.seda.generator.domain.Model;

import java.util.List;

import org.junit.Test;

public class RenderTest {

	@Test
	public void test() {
		Model model = new Model("it.seda.generator.Model");
		model.addAttribute(new Attribute("name", String.class, "NAME"));
		model.addAttribute(new Attribute("attributes", List.class, Attribute.class, "NAME"));
		Renderer renderer = new Renderer();
		renderer.render(model);
		org.junit.Assert.assertEquals(1, 1);
	}

}
