package pe.com.pacasmayo.sgcp.presentacion.gwt.util.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: Log4JInitServlet.java
 * Modificado: Feb 18, 2011 10:04:43 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class Log4JInitServlet extends HttpServlet {

	private static final long serialVersionUID = -9008829547075453156L;
	static final Logger logger = Logger.getLogger(Log4JInitServlet.class);

	public void init() throws ServletException {
		String log4jfile = getInitParameter("log4j-properties");
		if (log4jfile != null) {
			String propertiesFilename = getServletContext().getRealPath(log4jfile);
			PropertyConfigurator.configure(propertiesFilename);
			logger.info("logger configured.");
		}
	}
}
