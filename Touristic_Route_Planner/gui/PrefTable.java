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
import javax.swing.table.TableCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data.City;
import data.Edge;
import data.EdgeTimeMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
/**
 * Creates preference table
 * @author sadiatabassum
 *
 */

public class PrefTable extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private double prefScore;

	private Random scgenerator;

	public static void main(String[] args) {
		try {
			PrefTable dialog = new PrefTable();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param actionListener 
	 */
	public PrefTable() {
		setTitle("Add preference score between 1 to 10");
		initComponents();
		createEvents();

		}

	private void initComponents() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JPanel infoPanel = new JPanel();	
		infoPanel.add(new JLabel("Add preference"));
		getContentPane().add(infoPanel);
		
		// Creates Table/////////////////////////////////////////////
		JTable table = new JTable(City.getTotalCities(),2){
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
			  return !( columnIndex == 0);
			}
			@Override
			public Component prepareRenderer(TableCellRenderer r, int tableData, int columns){
				Component c = super.prepareRenderer(r, tableData, columns);
				/*if(tableData%2==0){
					c.setBackground(Color.WHITE);
				}
				else{
					c.setBackground(Color.LIGHT_GRAY);
				}*/
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
	
		//generate random value from the seed
				if(DetailsPanel.getSeed()!=0){
					scgenerator = new Random((long) DetailsPanel.getSeed());
				//	System.out.println("AA"+seed);
				}
				else{
					//Set seed value
					Long seed = System.currentTimeMillis();
					scgenerator = new Random(seed);
				//	System.out.println("AA"+seed);
				}

		// set preference score
		for(int k=0; k<City.getTotalCities(); k++){
			table.setValueAt(City.getCityFromCityList(k), k, 0);
			//generates random numbers in a range of 1 to 10
			prefScore = scgenerator.nextDouble()*(10 - 1+1) + 1;
			table.setValueAt((prefScore), k, 1);
			
			City.setPreference(prefScore, City.getCityFromCityList(k));
		
		}
		
		
		table.setBackground(Color.WHITE);
		table.setShowGrid(false);
		table.setForeground(new Color(0, 0, 0));
		table.setPreferredScrollableViewportSize(new Dimension(450,63));
		table.setFillsViewportHeight(true);
		table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("City");	
		table.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Score");
		
		
		
		Action action = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        TableCellListener tcl = (TableCellListener)e.getSource();
		        int row = table.getSelectedRow();
		        int col = table.getSelectedColumn();
		        //Get from and to city
		        String cit1 = table.getValueAt(row, 0).toString();
		        City selectedCity = City.getCityFromName(cit1);
		      
		        double prefScore = Double.parseDouble((tcl.getNewValue().toString()));
		        
		        City.setPreference(prefScore, selectedCity);
		       
		     //  System.out.println(CityPair.getTimePairFromList(frmToTime));
		    }
		};
		
		TableCellListener tcl = new TableCellListener(table, action);

		JScrollPane jps = new JScrollPane(table);
		getContentPane().add(jps);
	
		
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


	private void createEvents() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 setVisible(false);

			}
		});
		
	}
	
}
