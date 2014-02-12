/**
 * 
 */
package it.seda.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * This class provides an application-wide access to the
 * Spring ApplicationContext! The ApplicationContext is
 * injected in a static method of the class "AppContext".
 *
 * Use AppContext.getApplicationContext() to get access
 * to all Spring Beans.
 *
 * @author f.ricci
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        // Wiring the ApplicationContext into a static method
    	ApplicationContextHolder.setContext(ctx);
    }
    
    /**
     * This class provides application-wide access to the Spring ApplicationContext.
     * The ApplicationContext is injected by the class "ApplicationContextProvider".
     *
     * @author f.ricci
     */
    public static class ApplicationContextHolder {

        private static ApplicationContext ctx;

        /**
         * Injected from the class "ApplicationContextProvider" which is automatically
         * loaded during Spring-Initialization.
         */
        private static void setContext(ApplicationContext applicationContext) {
            ctx = applicationContext;
        }


        /**
         * Get access to the Spring ApplicationContext from everywhere in your Application.
         *
         * @return
         */
        public static ApplicationContext getContext() {
            return ctx;
        }
    }
} 