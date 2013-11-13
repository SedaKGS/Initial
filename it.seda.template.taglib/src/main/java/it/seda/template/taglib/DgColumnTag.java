package it.seda.template.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class DgColumnTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	DgColumn dgColumn = new DgColumn();
	
	public void setAsc(String asc) {
		dgColumn.setAsc(asc);
	}

	public void setDesc(String desc) {
		dgColumn.setDesc(desc);
	}

	public void setLabel(String label) {
		dgColumn.setLabel(label);
	}

	public void setTitle(String title) {
		dgColumn.setTitle(title);
	}

	public void setCssClass(String cssClass) {
		dgColumn.setCssClass(cssClass);
	}

	public void setCssType(String cssType) {
		dgColumn.setCssType(cssType);
	}	
	
	public int doStartTag() throws JspTagException {
		DatagridTag parent = (DatagridTag) findAncestorWithClass(this, DatagridTag.class);
        if (parent == null) {
            throw new JspTagException("DgColumnTag: invalid parent");
        }
		
        if (parent.getLoopStatus().isFirst()) {
            parent.addDgColumn(dgColumn.clone());        	
        }
        
        parent.notifyColumnTraversal();
        
		return EVAL_BODY_BUFFERED;
	}	
	
	public int doEndTag() throws JspTagException {
		DatagridTag parent = (DatagridTag) findAncestorWithClass(this, DatagridTag.class);
		
		BodyContent bodyContent = getBodyContent();
		try {
			if (bodyContent != null) {
				String content = bodyContent.getString().replaceAll("\t", "").replaceAll("\n", "").replace("\r", "");
				
				if (content.trim().length() == 0) {
					content="&nbsp;";
				}
				String sHtml = " <td class=\"seda-ui-datagridcell " 
							+ (parent.getLoopStatus().isLast() ? "seda-ui-datagridcellfooter " : " ") 
							+ " seda-ui-datatype-"
							+ parent.getCurrentDgColumn().getCssType()
							+ parent.getCurrentDgColumn().getCssClass()
							+ "\">"
							+ content
							+ "</td>";

				parent.smartWriter(sHtml);
			}
		} catch (IOException x) {
			throw new JspTagException(x.getMessage());
		}

		return EVAL_PAGE;
	}
	
	public static class DgColumn {

		private String asc;
		private String desc;
		private String label;
		private String title;
		private String cssClass;
		private String cssType;

		public String getHeader() {
			if (title!=null) {
				return title;
			} else if (label!=null) {
				return label;
			}
			return "Header";
		}
		
		public String getAsc() {
			return asc;
		}

		public void setAsc(String asc) {
			this.asc = asc;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getCssClass() {
			if (cssClass != null && cssClass.trim().length()>0) {
				return " " + cssClass;
			} else {
				return "";
			}
		}

		public void setCssClass(String cssClass) {
			this.cssClass = cssClass;
		}

		public String getCssType() {
			if (cssType != null && cssType.trim().length()>0) {
				return cssType;
			} else {
				return "";
			}
		}

		public void setCssType(String cssType) {
			this.cssType = cssType;
		}

		@Override
		protected DgColumn clone() {
			DgColumn column = new DgColumn();
			column.setAsc(asc);
			column.setDesc(desc);
			column.setLabel(label);
			column.setTitle(title);
			column.setCssClass(cssClass);
			column.setCssType(cssType);
			return column;
		}

		@Override
		public String toString() {
			return "DgColumn [title=" + title + ", label=" + label
					+ ", cssType=" + cssType + ", cssClass=" + cssClass
					+ ", asc=" + asc + ", desc=" + desc + "]";
		}	
		
	}
	
}
