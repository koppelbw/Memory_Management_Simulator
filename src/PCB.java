/********************************************************************
 * File: PCB.java 
 * Author: Bill Koppelberger 
 * Due Date: 4/3/14 
 * CIS 452: Project 3 
 * Prof. Wolffe
 * 
 * This file is a struct that acts as a PCB. It keeps track of the
 * ProcessID, NumberTextPages, NumberDataPages, Text Page Table, 
 * and the Data Page Table.
 *******************************************************************/
public class PCB {

	/* Global Variables */
	int procID;
	int numTextPages;
	int numDataPages;
	PageTable textPT;
	PageTable dataPT;

	/* Getter and Setter methods */
	public PageTable getTextPT() {
		return textPT;
	}

	public void setTextPT(PageTable textPT) {
		this.textPT = textPT;
	}

	public PageTable getDataPT() {
		return dataPT;
	}

	public void setDataPT(PageTable dataPT) {
		this.dataPT = dataPT;
	}

	public int getProcID() {
		return procID;
	}

	public void setProcID(int procID) {
		this.procID = procID;
	}

	public int getNumTextPages() {
		return numTextPages;
	}

	public void setNumTextPages(int numTextPages) {
		this.numTextPages = numTextPages;
	}

	public int getNumDataPages() {
		return numDataPages;
	}

	public void setNumDataPages(int numDataPages) {
		this.numDataPages = numDataPages;
	}
}