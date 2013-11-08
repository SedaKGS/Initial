package it.seda.template.spring.theme;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.theme.AbstractThemeResolver;

public class URIBasedThemeResolver extends AbstractThemeResolver {

	@Override
	public String resolveThemeName(HttpServletRequest request) {
		System.out.println("aaa");
		
		return getDefaultThemeName();
	}

	@Override
	public void setThemeName(HttpServletRequest request,
			HttpServletResponse response, String themeName) {
		
		throw new UnsupportedOperationException("Cannot change theme - use a different theme resolution strategy");
		
	}

	
}
