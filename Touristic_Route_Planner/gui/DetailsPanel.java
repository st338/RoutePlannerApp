package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import javax.swing.*;
import algorithm.Population;
import algorithm.RunGA;
import algorithm.Tour;
import data.City;
import data.Edge;
import data.EdgeTimeMap;

/**
 * Middle section of the interface.
 * Initializes parameters with default values.
 * Contains input fields, buttons for receiving user inputs.Positioned using GridbagLayout manager.
 * Contains actionListeners to listen to button events and act accordingly.
 * 
 * Creates initial population, saves fittest individual and runs algorithm for number of
 * generations specified when run button is clicked.
 * @author sadiatabassum
 *
 */
public class DetailsPanel extends JPanel implements ActionListener{
	private JButton addTimeBtn;
	private static JRadioButton noPrefButton,prefButton;
	private static ButtonGroup buttonGroup;
	private static JButton addPrefBtn,runBtn;
	private static JTextField popField, genField, mutationField, crossField, alphaField, betaField, seedTxtField, timeLimField; 
	private JLabel genLabel, popLabel, mutationLabel, crossLabel, prefLabel, alphaLabel,betaLabel, timeLimLabel, seedLabel;
	private static Integer populationSize = 50, generationNum = 10;
	private static double mutationRate = 0, crossoverRate = 0, paramAlpha = 0, paramBeta = 0,timeLimit;
	private double fitness = 0;
	private static double seedValue = 0;
	private boolean validity;
	private JTextArea  outputBox;
	private RunGA tour;
	private Tour tourObj = new Tour();
	private Tour valTour;
	private PrefTable prfDialog;
	private Time timeDialog;
	
	public DetailsPanel(){
		Dimension size = getPreferredSize();
		size.width = 250;
		size.height = 500;
		setPreferredSize(size);
		setBorder(BorderFactory.createTitledBorder("Settings"));
		
		 //input labels
		 genLabel = new JLabel("Generation:");
		 popLabel = new JLabel("Population:");
		 mutationLabel = new JLabel("Mutation:");
		 crossLabel = new JLabel("Crossover:");
		 timeLimLabel = new JLabel("Time limit:");
		 prefLabel = new JLabel("Preference:");
		 alphaLabel = new JLabel("Alpha:");
		 betaLabel = new JLabel("Beta:");
		 seedLabel = new JLabel("Seed");
		
		 //input text fields
		 popField = new JTextField("20",8);
		 genField = new JTextField("1000",8);
		 mutationField = new JTextField("0.015",8);
		 crossField = new JTextField("0.90",8);
		 timeLimField = new JTextField("30",8);
		 alphaField = new JTextField("0.1",8);
		 betaField = new JTextField("0.4",8);
	
		 seedTxtField = new JTextField("0");
		 seedTxtField.setColumns(10);
		 long sd = System.currentTimeMillis();
		// seedTxtField.setText(String.valueOf(sd));
		 
			
		 //Radio button
		 noPrefButton = new JRadioButton("No");
		 noPrefButton.setMnemonic(KeyEvent.VK_B);
		 noPrefButton.setActionCommand("No");
		 noPrefButton.setSelected(true);
			 
		 prefButton = new JRadioButton("Yes");
		 prefButton.setMnemonic(KeyEvent.VK_B);
		 prefButton.setActionCommand("Yes");
			 
		 buttonGroup = new ButtonGroup();
		 buttonGroup.add(noPrefButton);
		 buttonGroup.add(prefButton);
		
		
		//Time button
		addTimeBtn = new JButton("Add time");
		//Preference button
		addPrefBtn = new JButton("Add preference");
		//Run button
		runBtn = new JButton("Run");
		
		//Adds action listener to all the buttons
		noPrefButton.addActionListener(this);
		prefButton.addActionListener(this);
		addTimeBtn.addActionListener(this);
		addPrefBtn.addActionListener(this);
		runBtn.addActionListener(this);
		
		//Disables preference button if no-preference radio button is selected
		if(noPrefButton.isSelected()){
			addPrefBtn.setEnabled(false);
			alphaField.setEnabled(false);
			runBtn.setEnabled(true);
		}
		else{
			alphaField.setEnabled(true);
			runBtn.setEnabled(false);
		}
	
		//Layout Manager. Positions GUI components.
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//First column////////////////////
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty =0.1; 
		
		gc.gridx = 0;
		gc.gridy = 0;
		add(popLabel, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 1;
		add(genLabel, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 2;
		add(mutationLabel, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 3;
		add(crossLabel, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 4;
		add(timeLimLabel, gc);
		
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 5;
		add(prefLabel, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 7;
		add(alphaLabel, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 8;
		add(betaLabel, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 9;
		add(seedLabel, gc);
		
		
		//Second column////////////////////
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(popField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(genField, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(mutationField, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		add(crossField, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		add(timeLimField, gc);
		
	
		gc.gridx = 1;
		gc.gridy = 5;
		add(noPrefButton, gc);
		
		gc.gridx = 1;
		gc.gridy = 6;
		add(prefButton, gc);
		
	
		gc.gridx = 1;
		gc.gridy = 7;
		add(alphaField, gc);
		
		gc.gridx = 1;
		gc.gridy = 8;
		add(betaField, gc);
		
		gc.gridx = 1;
		gc.gridy = 9;
		add(seedTxtField, gc);
	
		
	//	gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 10;
		add(addTimeBtn, gc);
		
		gc.gridx = 1;
		gc.gridy = 11;
		add(addPrefBtn, gc);
		
		//Final row///
		gc.weighty = 1;	
		gc.gridx = 1;
		gc.gridy = 12;
		add(runBtn, gc);

	}

	
//	//get pop size
//	public static int getPopField(){
//		if(!popField.getText().isEmpty()){
//			populationSize = Integer.parseInt(popField.getText());
//		
//		}
//		else{
//			populationSize = null;
//		}
//		return populationSize;
//	}
	//get generation num
//	public static int getGenField(){
//		generationNum = Integer.parseInt(genField.getText());
//		return generationNum;
//	}
//	
	//getter methods for parameters
	public static double getMutation(){
		mutationRate = Double.parseDouble(mutationField.getText());
		return mutationRate;
	}
	
	public static double getCrossover(){
		crossoverRate = Double.parseDouble(crossField.getText());
		return crossoverRate;
	}
	
	public static double getAlpha(){
		paramAlpha = Double.parseDouble(alphaField.getText());
		return paramAlpha;
	}
	
	public static double getBeta(){
		paramBeta = Double.parseDouble(betaField.getText());
		return paramBeta;
	}
	
	public static double getTimeLimit(){
		timeLimit = Double.parseDouble(timeLimField.getText());
		return timeLimit;
	}
	
	public static double getSeed(){
		return seedValue;
	}
	
    public static String getSelectedButtonText() {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
    
    public static JButton getAddPrefBtn(){ 
    	return addPrefBtn; 
    }
    
    public static JRadioButton getPrefButton(){ 
    	return prefButton; 
    }
    
    public static JRadioButton getNoPrefButton(){ 
    	return noPrefButton; 
    }
    
    public static JButton getRunBtn(){ 
    	return runBtn; 
    }

    //Decides what to do when a button is clicked.
	public void actionPerformed(ActionEvent e) {
		 if(e.getActionCommand().equals("No")){
			 addPrefBtn.setEnabled(false);
			 alphaField.setEnabled(false);
			 runBtn.setEnabled(true);
		 }
		 
		 if(e.getActionCommand().equals("Yes")){
			 addPrefBtn.setEnabled(true);
			 alphaField.setEnabled(true);
			 runBtn.setEnabled(true);
		 }

		// Action for add time button
		 if( e.getActionCommand().equals("Add time")){
			 	int totalCity = 0;
				Edge.removeAll();
				MainFrame.getSelectedCities();
			 if(City.getTotalCities()>1){
				 
			    	//totalCity = City.getTotalCities();
				 	//System.out.println(totalCity);
			    	timeDialog = new Time();
			    	timeDialog.setModal(true);
			    	timeDialog.setVisible(true);
			    
				}
				else{
				
						JOptionPane.showMessageDialog(null,"Please select more cities");
				
				}	
		    }
			
			// Action for add preference button
			  if(e.getActionCommand().equals("Add preference")){
				  
				  	//MainFrame.getSelectedCities();
			    	//totalCity = City.getTotalCities();
					 prfDialog = new PrefTable();
					 prfDialog.setModal(true);
					 prfDialog.setVisible(true);
				
			  }
		
		/**
		 * Retrievs user inputs from the input fields.
		 * Creates initial population and 
		 * Runs the algorithm when run button is clicked.	  
		 */
		  if(e.getActionCommand().equals("Run")){
			
			 //Continue only if input fields have valid inputs 
			  if(checkValidity()){
				 // System.out.println("B"+popField.getText());
					populationSize = Integer.parseInt(popField.getText());
					generationNum = Integer.parseInt(genField.getText());
					mutationRate = Double.parseDouble(mutationField.getText());
					crossoverRate = Double.parseDouble(crossField.getText());
					paramAlpha = Double.parseDouble(alphaField.getText());
					paramBeta = Double.parseDouble(betaField.getText());
					timeLimit = Double.parseDouble(timeLimField.getText());
					seedValue = Long.parseLong(seedTxtField.getText());

			
				
				 if(City.getTotalCities()<=1){
					 JOptionPane.showMessageDialog(null,"Please select more cities");
					}
					else if(City.isEmptyPrefTable() && prefButton.isSelected()){
					  JOptionPane.showMessageDialog(null,"Please add preference");
							
					}
					 else{
					tour = new RunGA();
					Population pop = new Population(populationSize, true); 
				    //Save the fittest tour of the population here.
				    Tour.saveGenFittest(pop.getFittest());
				   // System.out.println("VISIT TIME:"+ pop.getFittest().getTotalVisitTime());
				    //Loop through generations and display outputs
				    outputBox = MainFrame.getOutput();
				    outputBox.removeAll();
				    outputBox.setText("Best possible routes:");
				    for (int i = 0; i <generationNum; i++) {
				    	outputBox.append("\n ..."+"Gen "+i+": "+tour.runAlg(pop));
				    }
				    
				    outputBox.revalidate();
			        outputBox.repaint();
	
					 }
				}
		  }
	}
	
	//Checks if input fields have valid inputs	
	private boolean checkValidity(){	 // check input 1
		validity=true;
	    try{
	        Integer.parseInt(popField.getText());
	    }catch (NumberFormatException e){
	    	JOptionPane.showMessageDialog(null,"Please enter a valid input"); // own implemented dialog
	    	validity=false;
	        return validity;
	    }
	    
	    try{
	        Integer.parseInt(genField.getText());
	    }catch (NumberFormatException e){
	    	JOptionPane.showMessageDialog(null,"Please enter a valid input"); // own implemented dialog
	    	validity=false;
	        return validity;
	    }
	    
	    try{
	        Double.parseDouble(mutationField.getText());
	    }catch (NumberFormatException e){
	    	JOptionPane.showMessageDialog(null,"Please enter a valid input"); // own implemented dialog
	    	validity=false;
	        return validity;
	    }
	    
	    try{
	    	Double.parseDouble(crossField.getText());
	    }catch (NumberFormatException e){
	    	JOptionPane.showMessageDialog(null,"Please enter a valid input"); // own implemented dialog
	    	validity=false;
	        return validity;
	    }
	    
	    try{
	    	Double.parseDouble(alphaField.getText());
	    }catch (NumberFormatException e){
	    	JOptionPane.showMessageDialog(null,"Please enter a valid input"); // own implemented dialog
	    	validity=false;
	        return validity;
	    }
	    
	    try{
	    	Double.parseDouble(betaField.getText());
	    }catch (NumberFormatException e){
	    	JOptionPane.showMessageDialog(null,"Please enter a valid input"); // own implemented dialog
	    	validity=false;
	        return validity;
	    }
		return validity;
	}
	

}
