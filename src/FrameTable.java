/********************************************************************
 * File: FrameTable.java 
 * Author: Bill Koppelberger 
 * Due Date: 4/3/14 
 * CIS 452: Project 3 
 * Prof. Wolffe
 * 
 * This file is a class that acts as a FrameTable. It keeps track
 * of the 8 Frames that are in Physical memory.
 *******************************************************************/
public class FrameTable {
	
	/* The 8 Frames that hold pages */
	Frame fTable[] = new Frame[8];
	
	/* Constructor that initializes the FrameTable */
	public FrameTable() {
		for(int i = 0; i < fTable.length; i++){
			Frame temp = new Frame();
			fTable[i] = temp;
			fTable[i].setFrameNum(i);
			fTable[i].setPageType('-');
		}
	}
	
	/* Returns the location of the first available frame */
	public Frame getFreeFrame(){
		for(int i = 0; i < 8; i++){
			if(fTable[i].getFree() == 0){
				fTable[i].setFree(1);
				return fTable[i];
			}
		}
		return null;
	}
	
	/* Returns the length of the FrameTable */
	public int frameTableSize(){
		return fTable.length;
	}
	
	/* Returns the specified Frame */
	public Frame getFrame(int index){
		return fTable[index];
	}
	
	/* Free all frames for specified PCB (allow frames to get overwritten) */
	public void freeFrames(int procID){
		for(int i = 0; i < fTable.length; i++){
			if(fTable[i].getProcID() == procID){
				fTable[i].setFree(0);
				fTable[i].setProcID(-1);
				fTable[i].setPageType(' ');
				fTable[i].setPageNum(-1);
				fTable[i].setFree(0);		//0 = free  ; 1 = not free
			}
		}
	}
}
