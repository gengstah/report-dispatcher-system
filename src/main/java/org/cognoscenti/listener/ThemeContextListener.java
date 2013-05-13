package org.cognoscenti.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ThemeContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		ctx.setAttribute("theme", ctx.getInitParameter("defaultTheme"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) { }
}