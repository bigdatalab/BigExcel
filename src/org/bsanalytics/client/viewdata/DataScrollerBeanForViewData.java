package org.bsanalytics.client.viewdata;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class DataScrollerBeanForViewData {
	
	private int rows = 10; //change this number to scrolling requirements
	private int scrollerPage=1;
	private int size;
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public List<SelectItem> getPagesToScroll() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (int i = 1; i <= Math.ceil(size / getRows()); i++) {
			if (Math.abs(i - scrollerPage) < 5) {
				SelectItem item = new SelectItem(i);
				list.add(item);
			}
		}
		return null;
	}

	public int getScrollerPage() {
		return scrollerPage;
	}

	public void setScrollerPage(int scrollerPage) {
		this.scrollerPage = scrollerPage;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
