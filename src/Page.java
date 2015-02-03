/********************************************************************
 * File: Page.java 
 * Author: Bill Koppelberger 
 * Due Date: 4/3/14 
 * CIS 452: Project 3 
 * Prof. Wolffe
 * 
 * This file is a class that acts as a Page. It keeps track of 
 * the ProcessID, PageNumber, PageType, and the FrameNumber of a
 * Page.
 *******************************************************************/
public class Page {

	/* Global Variables */
	int procID;
	int pageNum;
	char pageType;
	int frameNum;

	/* Getter and Setter methods */
	public int getProcID() {
		return procID;
	}

	public void setProcID(int procID) {
		this.procID = procID;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public char getPageType() {
		return pageType;
	}

	public void setPageType(char pageType) {
		this.pageType = pageType;
	}

	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}
}