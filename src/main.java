import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class main {
	
	public static void startGUI(GUI myGUI, final FrameTable frameTable){
		
		//RUN GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	GUI.createAndShowGUI(frameTable);
		    }
	    });
		
		
	}
	
	public static void updateGUI(GUI myGUI, final FrameTable frameTable){
		
		//RUN GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	GUI.createAndShowGUI(frameTable);
		    }
	    });
		
		//BREAK AND WAIT FOR NEXT BUTTON TO BE PRESSED
	}
	
	public static void textOutput(FrameTable frameTable, ArrayList<PCB> PCBlist){			
		
		//proves PCBlist works
		System.out.println("\n\n*******************************\n     PCB LIST\n---------------------");
		for(int j = 0; j < PCBlist.size(); j++)
			System.out.println("PID: " + PCBlist.get(j).getProcID() + "  :  #Tp: " + PCBlist.get(j).getNumTextPages() +
				"  :  #Dp: " + PCBlist.get(j).getNumDataPages());
		
		//proves PCBlist pointers to PageTables works
		System.out.println("\n\n    PageTable\n---------------------");
		for(int j = 0; j < PCBlist.size(); j++){
			for(int k = 0; k < PCBlist.get(j).getNumTextPages(); k++)
			System.out.println("PID: " + PCBlist.get(j).getTextPT().getPageTable().get(k).getProcID() +
				"  :  Pg#: " + PCBlist.get(j).getTextPT().getPageTable().get(k).getPageNum() + 
				"  :  Typ: " + PCBlist.get(j).getTextPT().getPageTable().get(k).getPageType() + 
				"  :  Fr#: " + PCBlist.get(j).getTextPT().getPageTable().get(k).getFrameNum());
			
			for(int k = 0; k < PCBlist.get(j).getNumDataPages(); k++)
				System.out.println("PID: " + PCBlist.get(j).getDataPT().getPageTable().get(k).getProcID() +
					"  :  Pg#: " + PCBlist.get(j).getDataPT().getPageTable().get(k).getPageNum() + 
					"  :  Typ: " + PCBlist.get(j).getDataPT().getPageTable().get(k).getPageType() + 
					"  :  Fr#: " + PCBlist.get(j).getDataPT().getPageTable().get(k).getFrameNum());
		}
		
		//prove frameTable works
		System.out.println("\n\n    Frame Table\n---------------------");
		for(int j = 0; j < frameTable.frameTableSize(); j++){
			System.out.println("FID: " + frameTable.getFrame(j).getFrameNum() + 
					"  :  PID: " + frameTable.getFrame(j).getProcID() + 
					"  :  Typ: " + frameTable.getFrame(j).getPageType() + 
					"  :  Pg#: " + frameTable.getFrame(j).getPageNum());
		}
	}
	
	public static void freeFrames(FrameTable fTable, PCB proc){
		fTable.freeFrames(proc.getProcID());
	}
	
	public static void fillFrames(FrameTable fTable, PCB proc){
		
		//text pages
		for(int i = 0; i < proc.getNumTextPages(); i++){
			Frame temp = fTable.getFreeFrame();
			proc.getTextPT().getPageTable().get(i).setFrameNum(temp.getFrameNum());
			temp.setProcID(proc.getProcID());
			temp.setPageNum(proc.getTextPT().getPageTable().get(i).getPageNum());
			temp.setPageType(proc.getTextPT().getPageTable().get(i).getPageType());
		}
		
		//data pages
		for(int i = 0; i < proc.getNumDataPages(); i++){
			Frame temp = fTable.getFreeFrame();
			proc.getDataPT().getPageTable().get(i).setFrameNum(temp.getFrameNum());
			temp.setProcID(proc.getProcID());
			temp.setPageNum(proc.getDataPT().getPageTable().get(i).getPageNum());
			temp.setPageType(proc.getDataPT().getPageTable().get(i).getPageType());
		}
	}
	
	public static void createPageTables(PCB proc, FrameTable fTable){
		PageTable textTable = new PageTable();
		PageTable dataTable = new PageTable();
		
		//make pages and fill pagetables respectively
		for(int i = 0; i < proc.getNumTextPages(); i++){
			Page temp = new Page();
			temp.setProcID(proc.getProcID());
			temp.setPageNum(i);
			temp.setPageType('T');
			
			textTable.getPageTable().add(temp);
			
		}
		
		for(int i = 0; i < proc.getNumDataPages(); i++){
			Page temp = new Page();
			temp.setProcID(proc.getProcID());
			temp.setPageNum(i);
			temp.setPageType('D');

			dataTable.getPageTable().add(temp);
			
		}
		
		
		//add pointers in PCB to their pagetables
		proc.setTextPT(textTable);
		proc.setDataPT(dataTable);
		
		fillFrames(fTable, proc);
		
	}
	
	public static void main(String[] args) {
		
		
		
		
		int PAGE_SIZE = 512;	//Bytes
		
		//create FrameTable
		final FrameTable frameTable = new FrameTable();
		
		//
		ArrayList<String[]> data = new ArrayList<String[]>();
		ArrayList<PCB> PCBlist = new ArrayList();
		
		//Arraylists that keep a History of the execution
		ArrayList<FrameTable> fTableList = new ArrayList();
		ArrayList<ArrayList<PCB>> PCBpcbList = new ArrayList();
		
		//Start GUI
		GUI myGUI = new GUI();
		startGUI(myGUI, frameTable);
		
		//Read in from data.txt
		BufferedReader br = null;
		try {
			String sCurrentLine;
			//br = new BufferedReader(new FileReader("C:/Users/Bill/Dropbox/452_Proj3/src/data.txt"));
			br = new BufferedReader(new FileReader("C:/Users/Bill/Dropbox/452_Proj3/src/oneProc.txt"));
			
			//read from data.txt and store info into the data arraylist
			int index = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] tokens = sCurrentLine.split(" ");
				data.add(tokens);
				index++;
			}
			
			
			/* Fills procID arraylist
			 * Calculates the number of pages for Text and Data segments.
			 * Adds them to arraylists that indices correspond correctly */
			for(int i = 0; i < data.size(); i++){
				
				if(!data.get(i)[1].equals("Halt")){
					PCB tempPCB = new PCB();
					PCBlist.add(tempPCB);
					tempPCB.setProcID(Integer.parseInt(data.get(i)[0]));
					tempPCB.setNumTextPages(((Integer.parseInt(data.get(i)[1])) / PAGE_SIZE) + 1);
					tempPCB.setNumDataPages(((Integer.parseInt(data.get(i)[2])) / PAGE_SIZE) + 1);
					
					//create PageTables
					createPageTables(tempPCB, frameTable);
					
					//textoutput
					textOutput(frameTable, PCBlist);
					
					updateGUI(myGUI, frameTable);
					
					//FrameTable temp = new FrameTable();
					//COPY OBJECT somehow0-----------------------
					
				}else{
					//HALT
					System.out.println("\nHALT: " + data.get(i)[0]);

					//remove PCB from PCBlist
					for(int j = 0; j < PCBlist.size(); j++){
						if(Integer.parseInt(data.get(i)[0]) == PCBlist.get(j).getProcID()){
							freeFrames(frameTable, PCBlist.get(Integer.parseInt(data.get(j)[0])));
							PCBlist.remove(j);
							
						}
					
					}
					textOutput(frameTable, PCBlist);
					
					updateGUI(myGUI, frameTable);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
		
		
	}
}