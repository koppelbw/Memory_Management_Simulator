import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;

/********************************************************************
 * File: simulator.java 
 * Author: Bill Koppelberger 
 * Due Date: 4/3/14 
 * CIS 452: Project 3 
 * Prof. Wolffe
 * 
 * This file is the Model of the MVC. This file is the backbone
 * of the application.
 *******************************************************************/
public class simulator {

	/* Keeps track of what line in data.txt to execute next */
	private static int procIndex;

	/* Page size in Bytes */
	private static int PAGE_SIZE;

	/* Stores all data read from file */
	private static ArrayList<String[]> data;

	/* Stores all of the active PCBs into this list */
	private static ArrayList<PCB> PCBlist;

	/* Holds the 8 frames of this simulator */
	private static FrameTable frameTable;

	/* The file to be read */
	private String file;

	/* Constructor: instantiates the FrameTable & reads in the file */
	public simulator() {

		// Initialize globals
		frameTable = new FrameTable();
		PAGE_SIZE = 512;
		data = new ArrayList<String[]>();
		PCBlist = new ArrayList();
		procIndex = 0;

		// Read in from data.txt
		BufferedReader br = null;
		try {
			file = JOptionPane
					.showInputDialog("Enter direct path to data file");
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file));

			// read from data.txt and store info into the data arraylist
			int index = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] tokens = sCurrentLine.split(" ");
				data.add(tokens);
				index++;

			}

		} catch (IOException e) {
			//Exits if File not found
			JOptionPane.showMessageDialog(null, "File not found.");
			System.exit(0);
			e.printStackTrace();
		} catch (NullPointerException n) {
			// Exits if user presses cancel on the Enter File Prompt
			JOptionPane.showMessageDialog(null, "GoodBye.");
			System.exit(0);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/* Advances the program one line */
	public static void doNextProcess() {

		int i = procIndex;

		// Creates and Stores PCBs. Calculates the number of pages for Text and
		// Data segments.
		if (data.size() <= procIndex) {
			JOptionPane.showMessageDialog(null, "End of file.");
			
		} else if (!data.get(i)[1].equals("Halt")) {
			PCB tempPCB = new PCB();
			PCBlist.add(tempPCB);
			tempPCB.setProcID(Integer.parseInt(data.get(i)[0]));
			
			
			//////
			/*
			 * if((Integer.parseInt(data.get(i)[1]) / PAGE_SIZE) == 1)
				tempPCB.setNumTextPages(((Integer.parseInt(data.get(i)[1])) / PAGE_SIZE));
			else
				tempPCB.setNumTextPages(((Integer.parseInt(data.get(i)[1])) / PAGE_SIZE) + 1);
			

			if((Integer.parseInt(data.get(i)[2]) / PAGE_SIZE) == 1)
				tempPCB.setNumDataPages(((Integer.parseInt(data.get(i)[2])) / PAGE_SIZE));
			else
				tempPCB.setNumDataPages(((Integer.parseInt(data.get(i)[2])) / PAGE_SIZE) + 1);
			*/
			if((Integer.parseInt(data.get(i)[1]) % PAGE_SIZE == 0))
				tempPCB.setNumTextPages(((Integer.parseInt(data.get(i)[1])) / PAGE_SIZE));
			else
				tempPCB.setNumTextPages(((Integer.parseInt(data.get(i)[1])) / PAGE_SIZE) + 1);
			

			if((Integer.parseInt(data.get(i)[2]) % PAGE_SIZE == 0))
				tempPCB.setNumDataPages(((Integer.parseInt(data.get(i)[2])) / PAGE_SIZE));
			else
				tempPCB.setNumDataPages(((Integer.parseInt(data.get(i)[2])) / PAGE_SIZE) + 1);
			

			// create PageTables
			createPageTables(tempPCB, frameTable);

		} else {
			// HALT

			// remove PCB from PCBlist
			for (int j = 0; j < PCBlist.size(); j++) {
				if (Integer.parseInt(data.get(i)[0]) == PCBlist.get(j)
						.getProcID()) {
					freeFrames(frameTable,
							PCBlist.get(Integer.parseInt(data.get(j)[0])));
					PCBlist.remove(j);
				}
			}
		}
		
		//Increment global process index
		procIndex++;
	}

	/* Free's the frames that the specified Process holds */
	public static void freeFrames(FrameTable fTable, PCB proc) {
		fTable.freeFrames(proc.getProcID());
	}

	/* Fills frames of specified Process into the FrameTable*/
	public static void fillFrames(FrameTable fTable, PCB proc) {
		
		// Fills all frames of the Text type
		for (int i = 0; i < proc.getNumTextPages(); i++) {
			Frame temp = fTable.getFreeFrame();
			proc.getTextPT().getPageTable().get(i)
					.setFrameNum(temp.getFrameNum());
			temp.setProcID(proc.getProcID());
			temp.setPageNum(proc.getTextPT().getPageTable().get(i).getPageNum());
			temp.setPageType(proc.getTextPT().getPageTable().get(i)
					.getPageType());
			temp.setPcb(proc);
		}

		// Fills all frames of the Data type
		for (int i = 0; i < proc.getNumDataPages(); i++) {
			Frame temp = fTable.getFreeFrame();
			proc.getDataPT().getPageTable().get(i)
					.setFrameNum(temp.getFrameNum());
			temp.setProcID(proc.getProcID());
			temp.setPageNum(proc.getDataPT().getPageTable().get(i).getPageNum());
			temp.setPageType(proc.getDataPT().getPageTable().get(i)
					.getPageType());
			temp.setPcb(proc);
		}
	}

	/* Creates two page Tables(Text and Data) for the process proc */
	public static void createPageTables(PCB proc, FrameTable fTable) {
		
		PageTable textTable = new PageTable();
		PageTable dataTable = new PageTable();

		// Make and fill Text page tables
		for (int i = 0; i < proc.getNumTextPages(); i++) {
			Page temp = new Page();
			temp.setProcID(proc.getProcID());
			temp.setPageNum(i);
			temp.setPageType('T');

			textTable.getPageTable().add(temp);

		}

		// Make and fill Data page tables
		for (int i = 0; i < proc.getNumDataPages(); i++) {
			Page temp = new Page();
			temp.setProcID(proc.getProcID());
			temp.setPageNum(i);
			temp.setPageType('D');

			dataTable.getPageTable().add(temp);

		}

		// add pointers in PCB to their pagetables
		proc.setTextPT(textTable);
		proc.setDataPT(dataTable);

		//Fill frames
		fillFrames(fTable, proc);

	}

	/* Global get methods */
	public FrameTable getFrameTable() {
		return frameTable;
	}
	public ArrayList<PCB> getPCBList() {
		return PCBlist;
	}

	public ArrayList<String[]> getData() {
		return data;
	}
	public int getProcIndex() {
		return procIndex;
	}
}
