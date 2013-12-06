package it.seda.sem.jdbc.datasource;

/**
 * Holds the data source reference
 * @author f.ricci
 *
 */
public class DataSourceContext {
	
	private String dataSourceRef;
	
	public String getDataSourceRef() {
		return dataSourceRef;
	}
	
	public DataSourceContext(String dataSourceRef) {
		this.dataSourceRef=dataSourceRef;
	}
	
}
