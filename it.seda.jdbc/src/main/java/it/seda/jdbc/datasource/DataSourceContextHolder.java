/**
 * 
 */
package it.seda.jdbc.datasource;

import org.springframework.core.NamedInheritableThreadLocal;
import org.springframework.core.NamedThreadLocal;

/**
 * Holds the DataSourceContext with the current thread
 * 
 * @author f.ricci
 * @see it.seda.jdbc.datasource.DataSourceContext
 */
public class DataSourceContextHolder {

	private static final ThreadLocal<DataSourceContext> dataSourceContextHolder =
			new NamedThreadLocal<DataSourceContext>("DataSource context");

	private static final ThreadLocal<DataSourceContext> inheritableDataSourceContextHolder =
			new NamedInheritableThreadLocal<DataSourceContext>("DataSource context");


	/**
	 * Reset the DataSourceContext for the current thread.
	 */
	public static void resetDataSourceContext() {
		dataSourceContextHolder.remove();
		inheritableDataSourceContextHolder.remove();
	}

	/**
	 * Associate the given DataSourceContext with the current thread,
	 * <i>not</i> exposing it as inheritable for child threads.
	 * @param dataSourceContext the current DataSourceContext,
	 * or {@code null} to reset the thread-bound context
	 */
	public static void setDataSourceContext(DataSourceContext dataSourceContext) {
		setDataSourceContext(dataSourceContext, false);
	}

	/**
	 * Associate the given DataSourceContext with the current thread.
	 * @param dataSourceContext the current DataSourceContext,
	 * or {@code null} to reset the thread-bound context
	 * @param inheritable whether to expose the DataSourceContext as inheritable
	 * for child threads (using an {@link InheritableThreadLocal})
	 */
	public static void setDataSourceContext(DataSourceContext dataSourceContext, boolean inheritable) {
		if (dataSourceContext == null) {
			resetDataSourceContext();
		}
		else {
			if (inheritable) {
				inheritableDataSourceContextHolder.set(dataSourceContext);
				dataSourceContextHolder.remove();
			}
			else {
				dataSourceContextHolder.set(dataSourceContext);
				inheritableDataSourceContextHolder.remove();
			}
		}
	}

	/**
	 * Return the DataSourceContext associated with the current thread, if any.
	 * @return the current DataSourceContext, or {@code null} if none
	 */
	public static DataSourceContext getDataSourceContext() {
		DataSourceContext dataSourceContext = dataSourceContextHolder.get();
		if (dataSourceContext == null) {
			dataSourceContext = inheritableDataSourceContextHolder.get();
		}
		return dataSourceContext;
	}

	/**
	 * Associate the given data source ref name with the current thread.
	 * <p>Will implicitly create a DataSourceContext for the given dataSource,
	 * <i>not</i> exposing it as inheritable for child threads.
	 * @param dataSourceRef the current data source refernce, or {@code null} to reset
	 * the thread-bound context
	 * @see DataSourceContext#DataSourceContext(java.lang.String)
	 */
	public static void setDataSourceRef(String dataSourceRef) {
		setDataSourceRef(dataSourceRef, false);
	}

	/**
	 * Associate the given data source reference with the current thread.
	 * <p>Will implicitly create a DataSourceContext for the given reference.
	 * @param dataSourceRef the current reference, or {@code null} to reset
	 * the thread-bound context
	 * @param inheritable whether to expose the DataSourceContext as inheritable
	 * for child threads (using an {@link InheritableThreadLocal})
	 * @see DataSourceContext#DataSourceContext(java.lang.String)
	 */
	public static void setDataSourceRef(String dataSourceRef, boolean inheritable) {
		DataSourceContext dataSourceContext = (dataSourceRef != null ? new DataSourceContext(dataSourceRef) : null);
		setDataSourceContext(dataSourceContext, inheritable);
	}

	/**
	 * Return the data source reference associated with the current thread, if any,
	 * or the default reference else.
	 * @return the current data source reference, or the default reference if no
	 * specific reference has been associated with the current thread
	 * @see DataSourceContext#getDataSourceRef()
	 * @see java.lang.Locale#getDefault()
	 */
	public static String getDataSourceRef() {
		DataSourceContext localeContext = getDataSourceContext();
		return (localeContext != null ? localeContext.getDataSourceRef() : DataSourceContext.DEFAULT_REF);
	}
	
}
