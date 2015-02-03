/********************************************************************
 * File: Frame.java 
 * Author: Bill Koppelberger 
 * Due Date: 4/3/14 
 * CIS 452: Project 3 
 * Prof. Wolffe
 * 
 * This file is a class that simulates a Frame that will be part
 * of the Frame Table. It keeps track of the FrameNum, ProcID,
 * PageType, PageNum, Free status, and the PCB of the Frame.
 *******************************************************************/
public class Frame {

	/* Global variables */
	int frameNum;
	int procID;
	char pageType;
	int pageNum;
	int free; // 0 = free ; 1 = not free
	PCB pcb;

	/* Getter and Setter methods of the Global variables */
	public PCB getPcb() {
		return pcb;
	}

	public void setPcb(PCB pcb) {
		this.pcb = pcb;
	}

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}

	public int getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}

	public int getProcID() {
		return procID;
	}

	public void setProcID(int procID) {
		this.procID = procID;
	}

	public char getPageType() {
		return pageType;
	}

	public void setPageType(char pageType) {
		this.pageType = pageType;
	}

	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
