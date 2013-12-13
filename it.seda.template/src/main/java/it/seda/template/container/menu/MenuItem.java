/**
 * 
 */
package it.seda.template.container.menu;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author f.ricci
 *
 */
public class MenuItem implements Comparable<MenuItem> {

	private int order;
	private String id;
	private String path;
	private boolean selected;
	
	private Set<MenuItem> children;

	public int getOrder() {
		return order;
	}
	
	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public boolean getHasChildren() {
		return getChildren().size()>0;
	}
	
	public boolean getHasLink() {
		return path!=null;
	}	
	
	public boolean isSelected() {
		return selected;
	}

	public MenuItem(Menu menu, boolean selected) {
		this(menu.getId(), menu.getPath(), menu.getOrder(), selected);
	}
	
	public MenuItem(String id, String path, int order, boolean selected) {
		super();
		this.id = id;
		this.path = path;
		this.order = order;
		this.selected = selected;
	}

	public void addChild(MenuItem child) {
		getChildren().add(child);
	}

	public Set<MenuItem> getChildren() {
		if (children==null) {
			children=new TreeSet<MenuItem>();
		}
		return children;
	}

	@Override
	public String toString() {
		return "MenuItem [order=" + order + ", id=" + id + ", path=" + path
				+ ", selected=" + selected + ", children=" + children + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MenuItem)) return false;
		
		MenuItem other = (MenuItem) obj;
		return this.id.equals(other.getId());
	}

	@Override
	public int compareTo(MenuItem o) {
		int compare = order-o.getOrder();
		if (compare==0) {
			return id.compareTo(o.getId());	
		}
		return compare;
	}

}
