package it.seda.sem.jdbc;

import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DefaultRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return "java:comp/env/jdbc/SEMDataSource";
	}
	
	//---------------------------------------------------------------------
	// Implementation of JDBC 4.1's getParentLogger method
	//---------------------------------------------------------------------
	@Override
	public Logger getParentLogger() {
		return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	}
}
