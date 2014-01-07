package it.seda.jdbc.commons;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * Holds a single data page
 * 
 * @author f.ricci
 *
 * @param <T> the page item data type
 */
public interface DataPage<T extends Serializable> {
	
	/**
	 * 
	 * @return the first row index
	 */
	public int getFirstRow();
	/**
	 * sets the first row index
	 */
	public void setFirstRow(int firstRow);
	/**
	 * 
	 * @return the last row index
	 */
	public int getLastRow();
	/**
	 * 
	 * sets the last row index
	 */
	public void setLastRow(int lastRow);
	/**
	 * 
	 * @return the total pages
	 */
	public int getTotalPages();
	/**
	 * 
	 * sets the total pages
	 */
	public void setTotalPages(int totalPages);
	/**
	 * 
	 * @return the total rows
	 */
	public int getTotalRows();
	/**
	 * 
	 * sets the total rows
	 */
	public void setTotalRows(int totalRows);
	/**
	 * 
	 * @return the page size in number of rows
	 */
	public int getPageSize();
	/**
	 * 
	 * sets the page size in number of rows
	 */
	public void setPageSize(int pageSize);
	/**
	 * 
	 * @return the current page number
	 */
	public int getPageNumber();
	/**
	 * 
	 * sets the current page number
	 */
	public void setPageNumber(int pageNumber);

	/**
	 * Appends the specified row to the end of this page 
	 * @param row row to be appended to this page
	 */
	public void addRow(T row);
	/**
	 * Inserts the specified row at the specified position in this page. 
	 * Shifts the row currently at that position (if any) and any subsequent rows to the right 
	 * (adds one to their indices).
	 * 
	 * @param index index at which the specified row is to be inserted
	 * @param row row to be inserted
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size()).
	 */
	public void addRow(int index, T row) ;
	
	/**
	 * Returns the row at the specified position in this page
	 * @param index index of row to return
	 * @return the row at the specified position in this page
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 */
	public T getRow(int index);
	/**
	 * Returns the number of elements in this page
	 * @return this page size
	 */
	public int getSize();
	
	/**
	 * Return the list containing all rows in this page 
	 * @return <code>List</code> all rows in this page
	 */
	public List<T> getRows();
}