/**
 * 
 */
package it.seda.template.taglib;

import it.seda.template.taglib.time.DateTime;
import it.seda.template.taglib.time.renderer.DateRenderer;
import it.seda.template.taglib.time.renderer.DateTimeRendererFactory;
import it.seda.template.taglib.time.renderer.TimeRenderer;

import java.lang.reflect.Method;
import java.util.Locale;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.tags.form.AbstractFormTag;
import org.springframework.web.servlet.tags.form.FormTag;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class DateTimeSelectorTag extends TagSupport implements TryCatchFinally {

	private Logger logger = LoggerFactory.getLogger(DateTimeSelectorTag.class);
	
	public static enum Selector {
		DATETIME, DATE, TIME;
	}
	
	private DateTime dateTime;
	private String modelAttribute;
	private Object model;
	private BeanWrapper beanWrapper;
	private String path;
	private Selector selector=Selector.DATE;
	
	private Integer minYear;
	private Integer maxYear;
	
	private String cssClass;
	private String cssClassSep;
	
	private boolean readonly;
	private boolean emptyopt;
	
	public void setPath(String path) {
		this.path=path;
	}	
	
	public void setSelector(String selector) {
		this.selector=Selector.valueOf(selector.toUpperCase());
	}
	
	public void setCssClass(String cssClass) {
		this.cssClass=cssClass;
	}
	
	public void setCssClassSep(String cssClassSep) {
		this.cssClassSep=cssClassSep;
	}	
	
	public void setReadonly(boolean readonly) {
		this.readonly=readonly;
	}
	
	public void setEmptyopt(boolean emptyopt) {
		this.emptyopt=emptyopt;
	}	
	
	public int doEndTag() throws JspTagException {

		Locale currentLocale = LocaleContextHolder.getLocale();
		logger.debug(path +" currentLocale '" + currentLocale +"'");

		DateTimeRendererFactory dateTimeRendererFactory=new DateTimeRendererFactory(currentLocale);

		resolveModelAttribute();
		resolveDateTime();

		DateRenderer dateRenderer = null;
		TimeRenderer timeRenderer = null;
		try {
			switch (selector) {
			case DATETIME:
				dateRenderer = dateTimeRendererFactory.buildDateRenderer(path, dateTime, minYear, maxYear);
				timeRenderer = dateTimeRendererFactory.buildTimeRenderer(path, dateTime);
				break;
			case DATE:				
				dateRenderer = dateTimeRendererFactory.buildDateRenderer(path, dateTime, minYear, maxYear);
				break;
			case TIME:
				timeRenderer = dateTimeRendererFactory.buildTimeRenderer(path, dateTime);				
				break;
			}
			if (dateRenderer!=null) {
				dateRenderer.setCssClass(cssClass);
				dateRenderer.setCssClassSep(cssClassSep);
				dateRenderer.setEmptyopt(emptyopt);
				dateRenderer.setReadonly(readonly);
				dateRenderer.render(pageContext.getOut());
			}
			
			if (timeRenderer!=null) {
				if (dateRenderer!=null) {
					pageContext.getOut().write("&nbsp;");
				}
				timeRenderer.setCssClass(cssClass);
				timeRenderer.setCssClassSep(cssClassSep);
				timeRenderer.setEmptyopt(emptyopt);
				timeRenderer.setReadonly(readonly);
				timeRenderer.render(pageContext.getOut());
			}
			
		} catch (Throwable x) {
			logger.error(x.getMessage(), x);
		} 

		return EVAL_PAGE;
	}	
	
	private void resolveModelAttribute() {

		FormTag parent = (FormTag)findAncestorWithClass(this, AbstractFormTag.class);
		if (parent!=null) {
			Method modelAttributeGet = ReflectionUtils.findMethod(FormTag.class, "getModelAttribute");
			if (modelAttributeGet!=null) {
				if (!modelAttributeGet.isAccessible()) {
					modelAttributeGet.setAccessible(true);
				}
				modelAttribute=(String) ReflectionUtils.invokeMethod(modelAttributeGet, parent);
			}
		} else {
			logger.warn("date selector tag for path '" + path + "' not inside a spring:form");
		}
		
	}

	private void resolveDateTime() {
		if (modelAttribute!=null) {
			model=pageContext.getAttribute(modelAttribute, PageContext.REQUEST_SCOPE);
			if (model!=null) {
				beanWrapper=new BeanWrapperImpl(model);
				logger.debug("modelAttribute '" + modelAttribute + "' found");
				Object o = beanWrapper.getPropertyValue(path);
				if (o!=null && o instanceof DateTime) {
					dateTime = (DateTime)o;
					logger.debug("date selector tag for path '" + path + "' in '"+ modelAttribute+"' found='"+dateTime+"'");					
				} else {
					logger.warn("date selector tag for path '" + path + "' in '"+ modelAttribute+"' not found or it is not a "+DateTime.class+" type");
				}
			} else {
				logger.debug("modelAttribute '" + modelAttribute + "' not found");
			}
		} else {
			logger.warn("date selector tag for path '" + path + "' in '"+ modelAttribute+"' not found in the models request");			
			dateTime=new DateTime();
		}
	}	
	
	@Override
	public void doCatch(Throwable throwable) throws Throwable {
		throw throwable;
	}

	@Override
	public void doFinally() {
		selector=Selector.DATE;
		modelAttribute=null;
		path=null;
		dateTime=null;
		minYear=null;
		maxYear=null;
		cssClass=null;
		cssClassSep=null;
		readonly=false;
		emptyopt=false;
	}

}
