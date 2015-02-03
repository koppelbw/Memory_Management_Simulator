import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/********************************************************************
 * File: FramePanel.java 
 * Author: Bill Koppelberger 
 * Due Date: 4/3/14 
 * CIS 452: Project 3 
 * Prof. Wolffe
 * 
 * This file is the View in the MVC model. This file creates the 
 * GUI that is seen by the user. This program uses Java Swing.
 *******************************************************************/
public class FramePanel extends JPanel {

	/* The main simulator object */
	private static simulator sim;

	/* Initialize lables & buttons to FramePanel */
	private static JLabel label1 = new JLabel("Physical Memory");
	private static JLabel label2 = new JLabel("     What's Next?");
	private static JLabel l0, l1, l2, l3, l4, l5, l6, l7;
	private static JButton f0, f1, f2, f3, f4, f5, f6, f7;
	private JButton next;

	/* Constructor for FramePanel */
	public FramePanel(simulator simTemp) {
		
		setLayout(new GridLayout(0, 2));
		
		//Initialize globals
		sim = simTemp;
		f0 = new JButton();
		f1 = new JButton();
		f2 = new JButton();
		f3 = new JButton();
		f4 = new JButton();
		f5 = new JButton();
		f6 = new JButton();
		f7 = new JButton();

		// Initialize FrameTable GUI
		ArrayList<String> frameInfo = setFrameInfo(sim.getFrameTable());
		add(l0 = new JLabel("      Frame 0"));
		add(f0 = new JButton(frameInfo.get(0)));
		add(l1 = new JLabel("      Frame 1"));
		add(f1 = new JButton(frameInfo.get(1)));
		add(l2 = new JLabel("      Frame 2"));
		add(f2 = new JButton(frameInfo.get(2)));
		add(l3 = new JLabel("      Frame 3"));
		add(f3 = new JButton(frameInfo.get(3)));
		add(l4 = new JLabel("      Frame 4"));
		add(f4 = new JButton(frameInfo.get(4)));
		add(l5 = new JLabel("      Frame 5"));
		add(f5 = new JButton(frameInfo.get(5)));
		add(l6 = new JLabel("      Frame 6"));
		add(f6 = new JButton(frameInfo.get(6)));
		add(l7 = new JLabel("      Frame 7"));
		add(f7 = new JButton(frameInfo.get(7)));

		// Initially disable all buttons
		f0.setEnabled(false);
		f1.setEnabled(false);
		f2.setEnabled(false);
		f3.setEnabled(false);
		f4.setEnabled(false);
		f5.setEnabled(false);
		f6.setEnabled(false);
		f7.setEnabled(false);

		// set button listener up
		addFrameInfoListener(new FrameInfoListener());
		next = new JButton("Next");
		add(label2);
		add(next);

	}

	/* Creates a new JFrame when the user wants to see a Page Table */
	private void createPageTableFrame(int n) {
		
		//Set up Pagetable JFrame
		JFrame frame = new JFrame("Process " + sim.getFrameTable()
				.getFrame(n).getProcID() + "'s Page Table");
		frame.setLayout(new GridLayout(0, 4));
		frame.setSize(425, 175);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(new JLabel("Process ID"));
		frame.add(new JLabel("Page Type"));
		frame.add(new JLabel("Page Number"));
		frame.add(new JLabel("Frame Number"));

		// Generate and add JLabels for Text Page Table
		for (int i = 0; i < sim.getFrameTable().getFrame(n).getPcb()
				.getNumTextPages(); i++) {
			frame.add(new JLabel(Integer.toString(sim.getFrameTable()
					.getFrame(n).getPcb().getTextPT().getPageTable().get(i)
					.getProcID())));
			frame.add(new JLabel(""
					+ sim.getFrameTable().getFrame(n).getPcb().getTextPT()
							.getPageTable().get(i).getPageType()));
			frame.add(new JLabel(Integer.toString(sim.getFrameTable()
					.getFrame(n).getPcb().getTextPT().getPageTable().get(i)
					.getPageNum())));
			frame.add(new JLabel(Integer.toString(sim.getFrameTable()
					.getFrame(n).getPcb().getTextPT().getPageTable().get(i)
					.getFrameNum())));
		}

		// Generate and add JLabels for Data Page Table
		for (int i = 0; i < sim.getFrameTable().getFrame(n).getPcb()
				.getNumDataPages(); i++) {
			frame.add(new JLabel(Integer.toString(sim.getFrameTable()
					.getFrame(n).getPcb().getDataPT().getPageTable().get(i)
					.getProcID())));
			frame.add(new JLabel(""
					+ sim.getFrameTable().getFrame(n).getPcb().getDataPT()
							.getPageTable().get(i).getPageType()));
			frame.add(new JLabel(Integer.toString(sim.getFrameTable()
					.getFrame(n).getPcb().getDataPT().getPageTable().get(i)
					.getPageNum())));
			frame.add(new JLabel(Integer.toString(sim.getFrameTable()
					.getFrame(n).getPcb().getDataPT().getPageTable().get(i)
					.getFrameNum())));
		}

	}

	/* An ActionListener that will listen for the PageTable Buttons */
	class FrameInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == f0) {
				createPageTableFrame(0);
			}
			if (e.getSource() == f1) {
				createPageTableFrame(1);
			}
			if (e.getSource() == f2) {
				createPageTableFrame(2);
			}
			if (e.getSource() == f3) {
				createPageTableFrame(3);
			}
			if (e.getSource() == f4) {
				createPageTableFrame(4);
			}
			if (e.getSource() == f5) {
				createPageTableFrame(5);
			}
			if (e.getSource() == f6) {
				createPageTableFrame(6);
			}
			if (e.getSource() == f7) {
				createPageTableFrame(7);
			}
		}
	}

	/* Adds the ActionListener to all of the Page Table Buttons */
	public void addFrameInfoListener(ActionListener fAL) {
		f0.addActionListener(fAL);
		f1.addActionListener(fAL);
		f2.addActionListener(fAL);
		f3.addActionListener(fAL);
		f4.addActionListener(fAL);
		f5.addActionListener(fAL);
		f6.addActionListener(fAL);
		f7.addActionListener(fAL);
	}

	/* ActionListener for the 'Next' Button */
	public void addNextListener(ActionListener bAL) {
		next.addActionListener(bAL);
	}

	/* Updates the Frame Info JLabels and the "Next" JLabel */
	public static void updateFrameInfo() {

		// updates the framInfo JLabels
		ArrayList<String> frameInfo = setFrameInfo(sim.getFrameTable());
		f0.setText(frameInfo.get(0));
		f1.setText(frameInfo.get(1));
		f2.setText(frameInfo.get(2));
		f3.setText(frameInfo.get(3));
		f4.setText(frameInfo.get(4));
		f5.setText(frameInfo.get(5));
		f6.setText(frameInfo.get(6));
		f7.setText(frameInfo.get(7));

		// updates the what's "Next" JLabel
		if (sim.getData().size() > sim.getProcIndex())
			if (!sim.getData().get(sim.getProcIndex())[1].equals("Halt"))
				label2.setText("     Next:    "
						+ sim.getData().get(sim.getProcIndex())[0] + " "
						+ sim.getData().get(sim.getProcIndex())[1] + " "
						+ sim.getData().get(sim.getProcIndex())[2]);
			else
				label2.setText("     Next:    "
						+ sim.getData().get(sim.getProcIndex())[0] + " "
						+ sim.getData().get(sim.getProcIndex())[1]);
		else
			label2.setText("     End of File!");
	}

	/* Sets the information needed for the FrameInfo JLabels */
	public static ArrayList<String> setFrameInfo(FrameTable frameTable) {

		ArrayList<String> info = new ArrayList();

		//Loops through the entire frameTable
		for (int j = 0; j < frameTable.frameTableSize(); j++) {

			String temp0 = Integer.toString(frameTable.getFrame(j).getProcID());
			char temp1 = frameTable.getFrame(j).getPageType();
			String temp2 = Integer
					.toString(frameTable.getFrame(j).getPageNum());

			String ret = " ";
			if ((frameTable.getFrame(j).getProcID() != -1)
					&& (frameTable.getFrame(j).getPageType() != '-'))
				ret = "PID: " + temp0 + "  |  Type: " + temp1 + "  |  Pg#: "
						+ temp2;

			info.add(ret);

			//Enables and Disables the FrameInfo Buttons
			switch (j) {
			case 0:
				if (frameTable.getFrame(j).getProcID() != -1
						&& frameTable.getFrame(j).getPcb() != null)
					f0.setEnabled(true);
				else
					f0.setEnabled(false);
				break;
			case 1:
				if (frameTable.getFrame(j).getProcID() != -1
						&& frameTable.getFrame(j).getPcb() != null)
					f1.setEnabled(true);
				else
					f1.setEnabled(false);
				break;
			case 2:
				if (frameTable.getFrame(j).getProcID() != -1
						&& frameTable.getFrame(j).getPcb() != null)
					f2.setEnabled(true);
				else
					f2.setEnabled(false);
				break;
			case 3:
				if (frameTable.getFrame(j).getProcID() != -1
						&& frameTable.getFrame(j).getPcb() != null)
					f3.setEnabled(true);
				else
					f3.setEnabled(false);
				break;
			case 4:
				if (frameTable.getFrame(j).getProcID() != -1
						&& frameTable.getFrame(j).getPcb() != null)
					f4.setEnabled(true);
				else
					f4.setEnabled(false);
				break;
			case 5:
				if (frameTable.getFrame(j).getProcID() != -1
						&& frameTable.getFrame(j).getPcb() != null)
					f5.setEnabled(true);
				else
					f5.setEnabled(false);
				break;
			case 6:
				if (frameTable.getFrame(j).getProcID() != -1
						&& frameTable.getFrame(j).getPcb() != null)
					f6.setEnabled(true);
				else
					f6.setEnabled(false);
				break;
			case 7:
				if (frameTable.getFrame(j).getProcID() != -1
						&& frameTable.getFrame(j).getPcb() != null)
					f7.setEnabled(true);
				else
					f7.setEnabled(false);
				break;

			}
		}

		return info;
	}
}
