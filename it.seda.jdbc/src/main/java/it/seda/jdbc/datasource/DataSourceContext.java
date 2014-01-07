package it.seda.jdbc.datasource;

/**
 * Holds the data source reference lookup key used by routing data source
 * @author f.ricci
 * @see it.seda.jdbc.datasource.RoutingDataSource
 *
 */
public class DataSourceContext {
	
	public static final String DEFAULT_REF = "dataSourceRef";
	
	private String dataSourceRef;
	
	public String getDataSourceRef() {
		return dataSourceRef;
	}
	
	public DataSourceContext(String dataSourceRef) {
		this.dataSourceRef=dataSourceRef;
	}
	
}
