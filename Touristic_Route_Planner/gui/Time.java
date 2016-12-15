package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.border.LineBorder;

import data.City;
import data.EdgeTimeMap;
import data.Edge;

/**
 * Creates the time window after add time button is clicked.
 * 
 */
public class Time extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JComboBox comboBox; 
	private JButton seedButton;
	private String[][] tableData;
	private ArrayList<City> cityData;
	private int rowSize = 0;
	private int totalCity = 0;
	private Edge cpd;
	private JTable table;
	int index = 0;
	private Random generator;
	private boolean setSeed = false;
	private static int seedCount = 0;
	
	//private CityPairsData cpd;

	public static void main(String[] args) {
		try {
			Time dialog = new Time();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Time() {
		setTitle("Add walking time");
		initComponents();
		createEvents();
	}

	private void initComponents() {
	
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JPanel infoPanel = new JPanel();	
		infoPanel.add(new JLabel("Add walking time"));
		getContentPane().add(infoPanel);
		
		// Create Table/////////////////////////////////////////////
		cityData = City.getAllCities();
		totalCity = City.getTotalCities();
		computeRowSize(totalCity);
		
		table = new JTable(rowSize,3){
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
			  return !( columnIndex == 0 && columnIndex == 1 );
			}
			@Override
			public Component prepareRenderer(TableCellRenderer r, int tableData, int columns){
				Component c = super.prepareRenderer(r, tableData, columns);
				if(isCellSelected(tableData, columns)){
					c.setBackground(Color.YELLOW);
					c.setForeground(Color.BLACK);
				}
				else{
					c.setBackground(Color.WHITE);
				}
				return c;
			}
		
		};
		
		// create pair of cities and save them as Edge class object in an array list.
		for(int k=0; k<totalCity; k++){
			for(int l=0; l<totalCity; l++){
				if(k!=l){
					City firstCity = City.getCityFromCityList(k);
					City secondCity = City.getCityFromCityList(l);
					cpd = new Edge(firstCity,secondCity);
					Edge.addEdge(cpd);
					//System.out.println(k+" "+l+"CPD"+firstCity+" "+secondCity);
				}
			   
			}
		}
		
		/** Gets all first cities and second cities from Edge and save in separate arraylists so that 
		 * tables first and second column can be filled with correct cities.
		 */
		ArrayList<City> firstColumn = cpd.getFirstColumn();
		ArrayList<City> secondColumn = cpd.getSecondColumn();

		//Add cities in first column of table
		for(int i=0; i<firstColumn.size(); i++){
			table.setValueAt(firstColumn.get(i), i, 0);
		}
		//Add cities in second column of table
		for(int i=0; i<secondColumn.size(); i++){
			table.setValueAt(secondColumn.get(i), i, 1);
		}
	
		//generate random value from the seed
		if(DetailsPanel.getSeed()!=0){
			generator = new Random((long) DetailsPanel.getSeed());
		}
		else{
			//Set seed value to current system time
			Long seed = System.currentTimeMillis();
			generator = new Random(seed);
		}
		double[] doubles = new double[secondColumn.size()];
		//set default random values in time.
		for(int i=0; i<secondColumn.size(); i++){
			double dd = generator.nextDouble() * (100);
			doubles[i] = dd;
			table.setValueAt(dd, i, 2);
			//generator.setSeed(1);
			//table.setValueAt((double)(Math.random() * 100), i, 2);
		
		}
		//System.out.println(Arrays.toString(doubles));
		//System.out.println(generator.nextDouble());
		
		//Get time and pair of cities from the table and put in a map
		for(int i=0; i<secondColumn.size(); i++){
			double userTime2 =  Double.parseDouble((table.getValueAt(i, 2)).toString());
		    String cit1 = table.getValueAt(i, 0).toString();
	        City frmCity = City.getCityFromName(cit1);
	        String cit2 = table.getValueAt(i, 1).toString();
	        City toCity = City.getCityFromName(cit2);
	        Edge cpd = new Edge(frmCity,toCity);
	        EdgeTimeMap cmp = new EdgeTimeMap(cpd,userTime2);
			//System.out.println("Time: "+userTime2);
		}
		
		table.setBackground(Color.WHITE);
		table.setShowGrid(false);
		table.setForeground(new Color(0, 0, 0));
		table.setPreferredScrollableViewportSize(new Dimension(450,63));
		table.setFillsViewportHeight(true);
		table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("From");	
		table.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("To");
		table.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("Walking time");
		
		
		TableCellListener tcl = new TableCellListener(table, new AbstractAction(){
			public void actionPerformed(ActionEvent e)
		    {   
				
		        TableCellListener tcl = (TableCellListener)e.getSource();
		        int row = table.getSelectedRow();
		        int col = table.getSelectedColumn();
		        
		        //Get from and to city
		        String cit1 = table.getValueAt(row, 0).toString();
		        City frmCity = City.getCityFromName(cit1);
		        String cit2 = table.getValueAt(row, 1).toString();
		        City toCity = City.getCityFromName(cit2);
		       
		        double userTime =  Double.parseDouble((table.getValueAt(row, 2)).toString());
			
		        Edge cpd = new Edge(frmCity,toCity);
		        System.out.println("Time: "+userTime);
		        if(!EdgeTimeMap.containsPair(cpd)){
		        	
		        	EdgeTimeMap cmp = new EdgeTimeMap(cpd,userTime);
		        //	System.out.println("Not Contains "+userTime);
		        }
		        else{
		        	EdgeTimeMap.replaceTime(cpd,userTime);
		        //	System.out.println("Contains "+userTime);
		        }
		        
		        
		    }
		});

		JScrollPane jps = new JScrollPane(table);
		getContentPane().add(jps);
		
		//Layout manager for group layout
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 440, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 229, Short.MAX_VALUE)
		);
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
		}

	}

	private void computeRowSize(int totalCity) {

	    for(int i=0;i<totalCity;i++){
	        for(int j=0;j<totalCity;j++){
	            if(i!=j){
	            	rowSize++; 
	             //   System.out.println(i+","+j);
	            }
	        }
	    }
		
	}

	private void createEvents() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("OK")){
					setVisible(false);
				}
				
			}
		});
		
	
	}
	

}
