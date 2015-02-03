import java.util.ArrayList;

/********************************************************************
 * File: PageTable.java 
 * Author: Bill Koppelberger 
 * Due Date: 4/3/14 
 * CIS 452: Project 3 
 * Prof. Wolffe
 * 
 * This file is a struct that acts as a Page Table. It keeps an
 * ArrayList of Pages.
 *******************************************************************/
public class PageTable {

	/* List of Pages */
	ArrayList<Page> pageTable = new ArrayList();

	/* Getter and Setter methods */
	public ArrayList<Page> getPageTable() {
		return pageTable;
	}

	public void setPageTable(ArrayList<Page> pageTable) {
		this.pageTable = pageTable;
	}	
}