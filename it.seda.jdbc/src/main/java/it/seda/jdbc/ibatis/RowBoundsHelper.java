/**
 * 
 */
package it.seda.jdbc.ibatis;

import org.apache.ibatis.session.RowBounds;

import it.seda.jdbc.commons.DataPage;
/**
 * Facility to handle mybatis and base jdbc data page.
 * 
 * @author f.ricci
 * @see it.seda.template.taglib.DatagridTag
 *
 */
public class RowBoundsHelper {

	private int offset;
	private int limit;
	
	private int rowsxpage;
	private int pageno;

	public int getOffset() {
		return offset;
	}
	public int getLimit() {
		return limit;
	}
	
	public RowBoundsHelper(int rowsxpage, int pageno) {
		
		this.rowsxpage=rowsxpage;
		this.pageno=pageno;
		
		limit = rowsxpage;
		offset = (pageno*rowsxpage)-rowsxpage;
		
	}

	public RowBounds buildRowBounds() {
		return new RowBounds(offset, limit);
	}
	
	public void decorate(DataPage<?> page, int totalRows) {
		int size = page.getRows().size();

		page.setFirstRow(totalRows>0?offset+1:0);
		page.setLastRow(totalRows>0?offset+size:0);
		page.setPageNumber(totalRows>0?pageno:0);
		page.setTotalPages(totalRows>0?(totalRows/rowsxpage)+(totalRows%rowsxpage==0?0:1):0);
		
		page.setPageSize(rowsxpage);
		page.setTotalRows(totalRows);
		
	}	
	
}
