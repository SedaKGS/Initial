package it.seda.template.taglib;

import it.seda.template.taglib.DgColumnTag.DgColumn;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.LoopTagStatus;
import javax.servlet.jsp.jstl.core.LoopTagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

public class DatagridTag extends LoopTagSupport implements TryCatchFinally {

	private static final long serialVersionUID = 1L;

	protected ActionResolver actionResolver;
	
	private Page<?> pageset;
	private Iterator<?> iterator;
	private boolean headerPrinted=false;
	private StringBuilder rowCellBuffer;
	private int count=0;
	
	private int currentColumn=0;

	private List<DgColumn> columns;
	private String rowperPage;
	private String cssClass;
	private String border;
	private String cellPadding;
	private String cellSpacing;
	private String viewstate;
	private String context=null;
	private String action;
	private String order;

	public boolean pagingbar=true;
	
	public boolean showfirst=true;	
	public boolean showlast=true;
	public boolean showrowindex=true;
	public boolean showrows=true;	
	public boolean showpagesize=true;
	public String modifier="";
	
	public void setPageset(Page<?> pageset) {
		this.pageset=pageset;
	}

	public void addDgColumn(DgColumn dgh) {
		if (getLoopStatus().isFirst()) {
			columns.add(dgh);	
		}
	}

	public void setCssclass(String cssClass) {
		this.cssClass=cssClass;
	}

	public String getCssClass() {
		if (cssClass != null && cssClass.trim() != "") {
			return " " + cssClass;
		} else {
			return "";
		}
	}

	public void setRowperpage(String rowperPage) {
		this.rowperPage=rowperPage;
	}

	public void setBorder(String border) {
		this.border=border;
	}

	public String getBorder() {
		return border==null?"":" border=\"" + border + "\"";
	}

	public void setCellpadding(String cellPadding) {
		this.cellPadding=cellPadding;
	}

	public String getCellpadding() {
		return cellPadding==null?"":" cellpadding=\"" + cellPadding + "\"";
	}

	public void setCellspacing(String cellSpacing) {
		this.cellSpacing=cellSpacing;
	}

	public String getCellspacing() {
		return cellSpacing==null?"":" cellspacing=\"" + cellSpacing + "\"";
	}

	public void setViewstate(String viewstate) {
		this.viewstate=viewstate;
	}

	public void setAction(String action) {
		this.action=action;
	}
	public void setContext(String context) {
		this.context=context;
	}
	public void setPagingbar(boolean pagingbar) {
		this.pagingbar=pagingbar;
	}
	
	public void setShowfirst(boolean showfirst) {
		this.showfirst = showfirst;
	}
	public void setShowlast(boolean showlast) {
		this.showlast=showlast;
	}

	public void setShowrowindex(boolean showrowindex) {
		this.showrowindex=showrowindex;
	}
	
	public void setShowrows(boolean showrows) {
		this.showrows=showrows;
	}	

	public void setShowpagesize(boolean showpagesize) {
		this.showpagesize=showpagesize;
	}		
	public void setModifier(String modifier) {
		this.modifier=modifier;
	}	
	
	public String hasOrder() {
		return (order == null ? "" : "&amp;order=" + order);
	}

	@Override
	public int doStartTag() throws JspException {
		columns=new ArrayList<DgColumn>();
		
		headerPrinted=false;
		rowCellBuffer=new StringBuilder();
		count=0;
		
		action = actionResolver().resolveAction(action,context);
		
		Object orderobject = pageContext.getAttribute("order");
		if (orderobject==null) {
			orderobject = pageContext.getRequest().getParameter("order");
		}
		if (orderobject!=null && orderobject instanceof java.lang.String) {
			order=String.valueOf(orderobject);
		}		

		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	protected void prepare() throws JspTagException {
		if (pageset!=null && pageset.getRows()!=null) {
			iterator=pageset.getRows().iterator();
		}
	}

	@Override
	protected Object next() throws JspTagException {
		Object object = iterator.next();
		return object;
	}	

	public void notifyColumnTraversal() {
		currentColumn++;
	}

	public DgColumn getCurrentDgColumn() {
		return columns.get(currentColumn-1);
	}
	
	@Override
	protected boolean hasNext() throws JspTagException {
		LoopTagStatus loopStatus = getLoopStatus();
 		if (iterator==null) {
			return false;
		} else {
			boolean hasNext = iterator.hasNext();
			if (!loopStatus.isFirst() || (loopStatus.isFirst() && loopStatus.isLast())) {
				if (headerPrinted==false) {
					// Stampa la testata
					printTableOpen();
					
					headerPrinted=true;
				}
				
				printBodyRow();				
			} 
			
			if (hasNext==false && loopStatus.isLast()) {
				printBodyRow();
				// close table
				printTableClose();
			}
			
			return hasNext;
		}
	}
	private void printBodyRow() {
		// se non mi trovo in analisi della superclasse loop
		// ed ho eseguito un loop sul body
		if (rowCellBuffer.length()>0) {
			// apri la riga
			printBodyRowOpen();
			// scarica il buffer della riga
			printBodyRowCells();
			// chiudi la riga
			printBodyRowClose();
			
			currentColumn=0;
		}

	}
	private void printTableClose() {

		try {
			pageContext.getOut().write("</tbody></table>");			
		} catch(IOException x) {
			x.printStackTrace();
		}
		
	}

	private void printBodyRowCells() {
		try {
			pageContext.getOut().write(rowCellBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			rowCellBuffer.delete(0, rowCellBuffer.length());
		}
		
	}

	private void printBodyRowOpen() {
		count++;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<tr class=\"seda-ui-datagridrow");
		stringBuilder.append((count % 2 == 0)?"pari":"dispari");
		stringBuilder.append(getLoopStatus().isLast()?" seda-ui-datagridrowfooter":"");
		stringBuilder.append("\">");
		
		try {
			pageContext.getOut().write(stringBuilder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void printBodyRowClose() {
		
		try {
			pageContext.getOut().write("</tr>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void smartWriter(String html) throws IOException {
//		if (getLoopStatus().isFirst()) {
			rowCellBuffer.append(html);
//		} else {
//			pageContext.getOut().write(html);
//		}
	}
	
	public String normalizeHref(String sHref) {

		sHref = sHref.replaceAll("&amp;", "&");
		sHref = sHref.replaceAll("&", "&amp;");

		return sHref;

	}

	public void printTableOpen() {
		StringBuilder html = new StringBuilder();
		html.append("<table class=\"seda-ui-datagrid");
		html.append(getCssClass());
		html.append("\"");
		html.append(getBorder());
		html.append(getCellpadding());
		html.append(getCellspacing());
		html.append(">");

		buildHeader(html);

		try {
			pageContext.getOut().write(html.toString());			
		} catch(IOException x) {
			x.printStackTrace();
		}
		
	}

	
	private void buildHeader(StringBuilder html) {

		html.append("<thead>");
		
		int iFrow = pageset.getFirstRow();
		int iLrow = pageset.getLastRow();
		int iNumRow = pageset.getTotalRows();
		int iNumPages = pageset.getTotalPages();
		int iCurrentPage = pageset.getPageNumber();
		int iRowperPages = pageset.getPageSize();
		String sViewstate="";

		if (action.contains("?")) {
			sViewstate = "&amp;viewstate=" + viewstate;
		} else {
			sViewstate = "?viewstate=" + viewstate;
		}

		int iPageLeft = iCurrentPage + 1;
		int iPageRight = iCurrentPage - 1;
		if (iCurrentPage == 1) {
			iPageRight = 1;
		}
		String[] rowperPages = null;
		String sClasspageInfo = "";
		if (iNumPages == 1) {
			sClasspageInfo = " seda-ui-disable";
		}

		if (rowperPage != null && rowperPage.trim().length()>0) {
			rowperPages = rowperPage.split(";");
		} else {
			rowperPages= new String[iNumRow];
			for (int i = 0; i < iNumRow; i++) {
				rowperPages[i]=String.valueOf(i+1);
			}
		}

		if (iCurrentPage == iNumPages) {
			iPageLeft = iNumPages;
		}
		
		if (pagingbar) {
			html.append(
					"<tr> <th class=\"seda-ui-controlheader\" colspan=\""
					+ (columns.size())
					+ "\"> <form action=\""
					+ action
					+ "\" class=\"seda-ui-formdg"
					+ sClasspageInfo
					+ "\" onsubmit=\"return false;\"> ");
			
			if (showrowindex) {
				html.append( "<span class=\"seda-ui-spandgrow\">Righe   "
				+ iFrow + " - " + iLrow + " su " + iNumRow + "</span>");					
			}
			if (showfirst) {
				html.append(
						  "<a class=\"seda-ui-paginglnk seda-ui-leftleftarrow\" "
						+ "href=\""
						+ action
						+ sViewstate
						+ "&amp;" + modifier + "pageNumber="
						+ 1
						+ "&amp;" + modifier + "rowsPerPage="
						+ iRowperPages
						+ hasOrder()
						+ "\" ><span class=\"seda-ui-spanfirst\">First</span> </a>"
						);
			}
			html.append(
					  "<a class=\"seda-ui-paginglnk  seda-ui-leftarrow\"  "
					+ "  href=\""
					+ action
					+ sViewstate
					+ "&amp;" + modifier + "pageNumber="
					+ (iPageRight)
					+ "&amp;" + modifier + "rowsPerPage="
					+ iRowperPages
					+ hasOrder()
					+ "\" ><span class=\"seda-ui-spanprev\">Prev</span> </a>"
					+ ""
					
					+ "<span class=\"seda-ui-spandgpage\"> pagina "
					+ iCurrentPage
					+ " di "
					+ iNumPages
					+ " </span>"
					
					+ "<a class=\"seda-ui-paginglnk seda-ui-rightarrow\""
					+ "  href=\""
					+ action
					+ sViewstate
					+ "&#38;" + modifier + "pageNumber="
					+ iPageLeft
					+ "&#38;" + modifier + "rowsPerPage="
					+ iRowperPages
					+ hasOrder()
					+ "\" ><span class=\"seda-ui-spannext\">Next</span> </a>");
			if (showlast) {
				html.append("<a class=\"seda-ui-paginglnk  seda-ui-rightrightarrow\" "
					+ " href=\""
					+ action
					+ sViewstate
					+ "&amp;" + modifier + "pageNumber="
					+ iNumPages
					+ "&amp;" + modifier + "rowsPerPage="
					+ iRowperPages
					+ hasOrder()
					+ " \" ><span class=\"seda-ui-spanlast\">Last</span> </a>"
					+ "");
			}

			if (showpagesize) {
				html.append("<label id=\"sedauilabeldgrow\" class=\"seda-ui-labeldgrow\" for=\"sedauiddlrow\"> Righe per Pagina: </label>");
				html.append("<select id=\"sedauiddlrow\" class=\"seda-ui-ddlrow\" name=\"sedauiddlrow\"  size=\"1\">");

				if (rowperPages.length > 0) {
					for (int i = 0; i < rowperPages.length; i++) {
						html.append("<option value=\""
								+ action
								+ sViewstate
								+ "&amp;" + modifier + "pageNumber=1&amp;" + modifier + "rowsPerPage="
								+ rowperPages[i] + hasOrder()
								+ "\"");

						if (Integer.valueOf(rowperPages[i]).intValue() == iRowperPages) {
							html.append(" selected=\"selected\"");
						}
						html.append( " >" + rowperPages[i] + "</option>");
					}
				}

				html.append("</select><input name=\"sedauidgbuttonrow\" id=\"sedauidgbuttonrow\" type=\"button\" class=\"seda-ui-pagingbtn seda-ui-dgbuttonrow\" value=\"Vai\" onclick=\"location = this.form.sedauiddlrow.value\" /> ");
				html.append("<label id=\"sedauilabeldgpage\" class=\"seda-ui-labeldgpage\" for=\"sedauiddlpage\"> Pagina: </label>");
				html.append("<select id=\"sedauiddlpage\" name=\"sedauiddlpage\" class=\"seda-ui-ddlpage\"  size=\"1\">");
				for (int i = 1; i <= iNumPages; i++) {
					html.append("<option value=\"" + action
							+ sViewstate + "&amp;" + modifier + "pageNumber=" + i
							+ "&amp;" + modifier + "rowsPerPage=" + iRowperPages
							+ hasOrder() + "\"");
					if (i == iCurrentPage) {
						html.append(" selected=\"selected\"");
					}
					html.append(" >" + i + "</option>");
				}
				html.append("</select><input name=\"sedauidgbuttonpage\" id=\"sedauidgbuttonpage\" type=\"button\" class=\"seda-ui-pagingbtn seda-ui-dgbuttonpage\" value=\"Vai\" onclick=\"location = this.form.sedauiddlpage.value\" /> ");
			}
			if (showrows && !showrowindex) {
				html.append("<span class=\"seda-ui-spandgrow\">Righe:"+iNumRow+"</span>");
			}
			html.append("</form>");
			html.append("</th> </tr> ");
		}

		html.append("<tr> ");		
		if (columns.size()>0) {
			for (int k = 0; k < columns.size(); k++) {
				DgColumn dg = columns.get(k);

				html.append(" <th class=\"seda-ui-datagridheadercell"
						+ dg.getCssClass() + "\" > ");
				// Titolo e Funzioni di Ordinamento delle colonne
				if (dg.getAsc() != null && dg.getAsc().trim().length()>0)
					html.append(
							"<a class=\"seda-ui-btnup\"  id=\"dgbtnup"
							+ k
							+ "\" name =\"dgbtnup"
							+ k
							+ " \"  href=\""
							+ action
							+ sViewstate
							+ "&amp;order="
							+ dg.getAsc()
							+ "&amp;" + modifier + "pageNumber=1" // + iCurrentPage
							+ "&amp;" + modifier + "rowsPerPage="
							+ iRowperPages
							+ "\" ><span class=\"seda-ui-spanasc\">Asc</span></a>");

				html.append(dg.getHeader() + "&nbsp;");

				if (dg.getDesc() != null && dg.getDesc().trim().length()>0)
					html.append(
							"<a class=\"seda-ui-btndown\"  id=\"dgbtndwb"
							+ k
							+ "\" name =\"dgbtndwn"
							+ k
							+ " \"  href=\""
							+ action
							+ sViewstate
							+ "&amp;order="
							+ dg.getDesc()
							+ "&amp;" + modifier + "pageNumber=1" // + iCurrentPage
							+ "&amp;" + modifier + "rowsPerPage="
							+ iRowperPages
							+ "\" ><span class=\"seda-ui-spandesc\">Desc</span></a>");
				html.append("</th> ");

			}
		} 
		html.append("</tr> ");

		html.append("</thead> <tbody> ");
	}
	
	protected ActionResolver actionResolver() throws JspTagException {
		if (actionResolver==null) {
			actionResolver=new ActionResolver(pageContext);
		}
		return actionResolver;
	}
	
	@Override
	public void doCatch(Throwable throwable) throws Throwable {
		throw throwable;
	}

	@Override
	public void doFinally() {
		super.doFinally();
		
		pageset=null;
		iterator=null;
		headerPrinted=false;
		rowCellBuffer=new StringBuilder();
		count=0;
		
		currentColumn=0;

		columns=new ArrayList<DgColumn>();
		rowperPage=null;
		cssClass=null;
		border=null;
		cellPadding=null;
		cellSpacing=null;
		viewstate=null;
		context=null;
		action=null;
		order=null;

		pagingbar=true;
		
		showfirst=true;	
		showlast=true;
		showrowindex=true;
		showrows=true;	
		showpagesize=true;
		modifier="";

	}	
	
	public static class Page<T extends Serializable> {
		
		
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
		public static <E extends Serializable> Page<E> emptyPage(Class<E> e) {
			return new Page<E>();
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

		public Page() {}
		
		public Page (List<T> rows) {
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
		protected final T getRow(int index) { 
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
}
