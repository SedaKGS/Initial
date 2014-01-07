/**
 * 
 */
package it.seda.jdbc.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic Implementation of DataPage 
 * @author f.ricci
 *
 * @param <T> the page item data type
 * 
 * @see it.seda.jdbc.commons.DataPage
 */
public class DefaultDataPage<T extends Serializable> implements DataPage<T> {
	
	private int firstRow;
	private int lastRow;
	private int totalPages;
	private int totalRows;
	private int pageSize;
	private int pageNumber;		
	
	private List<T> rows;
	
	/**
	 * This is a representation of an empty data page 
	 */
	public static <E extends Serializable> DefaultDataPage<E> emptyPage(Class<E> e) {
		return new DefaultDataPage<E>();
	}

	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public DefaultDataPage() {}
	
	public DefaultDataPage (List<T> rows) {
		this.rows = new ArrayList<T>(rows);
	}
	
	/**
	 * Appends the specified row to the end of this page 
	 * @param row row to be appended to this page
	 */
	public final void addRow(T row) {
		getRows().add(row);
	}
	/**
	 * Inserts the specified row at the specified position in this page. 
	 * Shifts the row currently at that position (if any) and any subsequent rows to the right 
	 * (adds one to their indices).
	 * 
	 * @param index index at which the specified row is to be inserted
	 * @param row row to be inserted
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size()).
	 */
	public final void addRow(int index, T row) {
		getRows().add(index, row);
	}
	
	/**
	 * Returns the row at the specified position in this page
	 * @param index index of row to return
	 * @return the row at the specified position in this page
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 */
	public final T getRow(int index) { 
		return rows.get(index);
	}
	/**
	 * Returns the number of elements in this page
	 * @return this page size
	 */
	public final int getSize() { 
		return rows.size(); 
	}		
	
	/**
	 * Return the list containing all rows in this page 
	 * @return <code>List</code> all rows in this page
	 */
	public final List<T> getRows() {
		if (rows==null) {
			rows=new ArrayList<T>();
		}
		return this.rows;
	}
}