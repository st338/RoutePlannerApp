package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import algorithm.Population;
import algorithm.RunGA;
import algorithm.Tour;
import data.City;
import data.Edge;
/**
 * This is the main class that creates the main GUI window.
 * Creates list of cities on the left, details panel in the middle and output area 
 * on the right side of the window. 
 * @author sadiatabassum
 *
 */
public class MainFrame extends JFrame  {
	private DetailsPanel detailsPanel;
	private DefaultListModel<City> cityList;
	private Object buttonActionCommand;
	private static JTextArea outputArea;
	private static JList listCity;
	private static double mutationRate = 0, crossoverRate = 0, paramAlpha = 0, paramBeta = 0,timeLimit = 0;
	private  Integer populationSize,generationNum;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame("Touristic Route Planner");
					frame.setResizable(false);
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MainFrame() {
		
	}
	
	public MainFrame(String title){
		super(title);
		setBounds(100, 100, 850, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set layout manager
		setLayout(new BorderLayout());
		
		//create swing components///////////////////////////
		//Left container
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(120, 30));
		//Add city list to scrollpane
		cityList = new DefaultListModel<City>();
		initCityList(cityList);
		listCity = new JList();
		listCity.setForeground(Color.BLUE);
		listCity.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, null));
		listCity.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);    
		listCity.setModel(cityList);
		scrollPane.setViewportView(listCity);
		
		//Middle container
		detailsPanel = new DetailsPanel();
		
		//Right container
		JPanel panel = new JPanel();
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setColumns(40);
		outputArea.setRows(27);
		JScrollPane scroll = new JScrollPane(outputArea); //place the JTextArea in a scroll pane
		Dimension size = getPreferredSize();
		size.width = 480;
		size.height = 470;
		scroll.setPreferredSize(size);
		panel.add(scroll);
		
		//Add components to swing panel
		Container c = getContentPane();
		c.add(scrollPane,BorderLayout.WEST);
		c.add(detailsPanel, BorderLayout.CENTER);
		c.add(panel,BorderLayout.EAST);
		setVisible(true);
	
	}
	
	//Create initial list of cities
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initCityList(DefaultListModel cityList) {
		cityList.addElement(new City("A"));
		cityList.addElement(new City("B"));
		cityList.addElement(new City("C"));
		cityList.addElement(new City("D"));
		cityList.addElement(new City("E"));
		cityList.addElement(new City("F"));
		cityList.addElement(new City("G"));
		cityList.addElement(new City("H"));
		cityList.addElement(new City("I"));
		cityList.addElement(new City("J"));
		cityList.addElement(new City("K"));
		cityList.addElement(new City("L"));
		cityList.addElement(new City("M"));
	}
	
	@SuppressWarnings("deprecation")
	public static void getSelectedCities(){
		City.removeAllCity();
		Object[] values = listCity.getSelectedValues();
		for (Object selecCity : values) {
    	   //  System.out.println(selecCity);
    	     City newCity = new City(selecCity.toString(),40,100,0);
    	     if(!City.containsCity(newCity)){
    	    	 City.addCityToCityList(newCity);
    	     }
    	}
	}

    public static JTextArea getOutput(){ 
    	return outputArea; 
    }

}
