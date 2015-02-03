import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PagePanel extends JPanel {

	private static simulator sim;
	private static ArrayList<PCB> PCBcopy;
	private JLabel text;
	private JButton temp;
	private ArrayList<JButton> procButtons;
	
	public PagePanel(simulator simTemp) {

		setLayout(new GridLayout(0,3));
		sim = simTemp;
		PCBcopy = sim.getPCBList();
		procButtons = new ArrayList();
		//add(text = new JLabel());
		//makePageTables();
		
		//initialize the procButtons
		for(int i = 0; i <= sim.getPCBList().size(); i++){
			//JButton temp = new JButton("fdsf");
			procButtons.add(new JButton());
			add(procButtons.get(i));
		}

	}

	public void addProcListener(ActionListener bAL){
		for(int i = 0; i < sim.getPCBList().size(); i++);
			//procButtons.get(i).addActionListener(bAL);
	}
	
	public void updatePCBList(){

		System.out.println("PCBlist size: " + sim.getPCBList().size());
		//System.out.println("PCBcopy P1: " + PCBcopy.get(5).getProcID());
		//PCBcopy.removeAll(procButtons);	//MAY remove all things from REAL PCBlist
		ArrayList<PCB> PCBcopy = sim.getPCBList();
		
		for(int i = 0; i < PCBcopy.size(); i++){
			JButton temp = new JButton("Process: " + Integer.toString(PCBcopy.get(i).getProcID()));
			procButtons.add(temp);
			add(procButtons.get(i));
			System.out.println("should have added a button");
			
		}
	}
	
	public void makePageTables(){
		

		ArrayList<PCB> PCBlist = sim.getPCBList();
		
			
		String output = "";
				for(int j = 0; j < PCBlist.size(); j++){
					for(int k = 0; k < PCBlist.get(j).getNumTextPages(); k++){
					output += "PID: " + PCBlist.get(j).getTextPT().getPageTable().get(k).getProcID() +
							"\n  :  Pg#: " + PCBlist.get(j).getTextPT().getPageTable().get(k).getPageNum() + 
							"\n  :  Typ: " + PCBlist.get(j).getTextPT().getPageTable().get(k).getPageType() + 
							"\n  :  Fr#: " + PCBlist.get(j).getTextPT().getPageTable().get(k).getFrameNum() + "\n\n";
					
					}
					
					for(int k = 0; k < PCBlist.get(j).getNumDataPages(); k++){
						output += "PID: " + PCBlist.get(j).getDataPT().getPageTable().get(k).getProcID() +
								"\n  :  Pg#: " + PCBlist.get(j).getDataPT().getPageTable().get(k).getPageNum() + 
								"\n  :  Typ: " + PCBlist.get(j).getDataPT().getPageTable().get(k).getPageType() + 
								"\n  :  Fr#: " + PCBlist.get(j).getDataPT().getPageTable().get(k).getFrameNum() + "\n\n";
					}
				}
				System.out.println("pages: " + output);
				text.setText(output);
	}
}
