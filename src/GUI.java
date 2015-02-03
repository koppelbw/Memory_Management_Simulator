
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;    
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class GUI {
	
	public static ArrayList<String> setFrameInfo(FrameTable frameTable){
		ArrayList<String> info = new ArrayList();
		
		for(int j = 0; j < frameTable.frameTableSize(); j++){
			
			String temp0 = Integer.toString(frameTable.getFrame(j).getProcID());
			char temp1 = frameTable.getFrame(j).getPageType();
			String temp2 = Integer.toString(frameTable.getFrame(j).getPageNum());
			
			String ret = " ";
			if((frameTable.getFrame(j).getProcID() != -1) && (frameTable.getFrame(j).getPageType() != '-'))
				ret = "PID: " + temp0 + "  |  Type: " + temp1 + "  |  Pg#: " + temp2;
				
			info.add(ret);
		}
		
		return info;
	}
	
	private static JFrame createFrameTableWindow(ArrayList<String> frameInfo){
		
		//Create and set up the FrameTable window
        JFrame frameFrameTable = new JFrame("Frame Table");
        frameFrameTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameFrameTable.setSize(350, 500);
        GridLayout frameLayout = new GridLayout(0,2);
        frameFrameTable.setLayout(frameLayout);
        
		 //Add lables to FrameTable
        JLabel label1 = new JLabel("Physical Memory");
        JLabel label2 = new JLabel(" ");
        JLabel l0 = new JLabel("    Frame 0");
        JLabel l1 = new JLabel("    Frame 1");
        JLabel l2 = new JLabel("    Frame 2");
        JLabel l3 = new JLabel("    Frame 3");
        JLabel l4 = new JLabel("    Frame 4");
        JLabel l5 = new JLabel("    Frame 5");
        JLabel l6 = new JLabel("    Frame 6");
        JLabel l7 = new JLabel("    Frame 7");
        
        //Frame info labels
        JLabel f0 = new JLabel(frameInfo.get(0));
        JLabel f1 = new JLabel(frameInfo.get(1));
        JLabel f2 = new JLabel(frameInfo.get(2));
        JLabel f3 = new JLabel(frameInfo.get(3));
        JLabel f4 = new JLabel(frameInfo.get(4));
        JLabel f5 = new JLabel(frameInfo.get(5));
        JLabel f6 = new JLabel(frameInfo.get(6));
        JLabel f7 = new JLabel(frameInfo.get(7));

        
        //add FrameTbale labels
        frameFrameTable.getContentPane().add(label2);
        frameFrameTable.getContentPane().add(label1);
        frameFrameTable.getContentPane().add(l0);
        frameFrameTable.getContentPane().add(f0);
        frameFrameTable.getContentPane().add(l1);
        frameFrameTable.getContentPane().add(f1);
        frameFrameTable.getContentPane().add(l2);
        frameFrameTable.getContentPane().add(f2);
        frameFrameTable.getContentPane().add(l3);
        frameFrameTable.getContentPane().add(f3);
        frameFrameTable.getContentPane().add(l4);
        frameFrameTable.getContentPane().add(f4);
        frameFrameTable.getContentPane().add(l5);
        frameFrameTable.getContentPane().add(f5);
        frameFrameTable.getContentPane().add(l6);
        frameFrameTable.getContentPane().add(f6);
        frameFrameTable.getContentPane().add(l7);
        frameFrameTable.getContentPane().add(f7);
        
        return frameFrameTable;
	}
	
	private static JFrame getFrameTableFrame(JFrame frameFrame){
		return frameFrame;
	}
	
	public static void updateFrameTableGUI(){
	//	JFrame frameFrame = getFrameTableFrame()
	}
	
	private static JFrame createPageTableWindow(){
		JFrame pageWindow = new JFrame("Page Table");
		pageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pageWindow.setSize(300, 300);
		pageWindow.setLayout(new FlowLayout());
		
		return pageWindow;
	}
	
	
	
	static void createAndShowGUI(FrameTable frameTable) {
		
        //Create and set up the FrameTable window
        JFrame frameFrame = createFrameTableWindow(setFrameInfo(frameTable));
        frameFrame.setVisible(true);
       
        
        //Create and set up the Page Table window
//        JFrame pageTableList;
//        JFrame pageTemp = createPageTableWindow();
//        JLabel labelTemp = new JLabel("PageTable here");
//        pageTemp.getContentPane().add(labelTemp);
//        pageTemp.setVisible(true);
        
        
 
        
    }
 
}
