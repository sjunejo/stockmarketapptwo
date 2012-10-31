package com.stockmarketapp2.gui.table;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.stockmarketapp2.staticlogic.LookupButtonLogic;
import com.stockmarketapp2.widgets.StatusBar;

/**
 * The window in which historical stock data is displayed.
 * Also depicts the average adjacent close price via a status bar.
 * @author Sadruddin Junejo
 *
 */
public class TableFrame extends JFrame  {
	
	private JTable dataTable;
	private DataTableModel dataTableModel;
	
	private String stockSymbol;
	
	private String[] rawDataArray;
	private String[][] rawDataArray2;
	private String[] rowArray;
	
	private JPanel mainPanel;
	
	/**
	 * Constructor method for table frame.
	 * @param dataString The string that contains the historical stock data queried from Yahoo!'s database
	 * @param title The title of the window. Contains the stock symbol, start and end dates,
	 * 		  interval between pieces of data and whether the data is displayed in chronological or
	 * 		  reverse chronological order. 
	 * @param chronologicalOrder Whether the data is displayed in chronological order or not
	 */
	public TableFrame(String dataString, String title, boolean chronologicalOrder){
		this.stockSymbol = stockSymbol;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // The close operation is handled by the window listener 
																   // implemented later
		this.setTitle(title);
		
		// The window utilises a border layout - the central part is devoted to the data table while 
		// the southern part is taken by the status bar.
		mainPanel = new JPanel(new BorderLayout());
		
		// the names of the columns of the table
		String[] columnNames = {"Date","Open","High","Low","Close","Volume","Adj. Close"};
		
	    dataTableModel = new DataTableModel(formatData(dataString, chronologicalOrder), columnNames);
		dataTable = new JTable(dataTableModel);
		
		mainPanel.add(new StatusBar(this.getAdjustedCloseValues(), chronologicalOrder), BorderLayout.SOUTH);
		
		if (!chronologicalOrder) dataTableModel.colourize(); else dataTableModel.reversedTableColourize();
		
		JScrollPane scrollPane = new JScrollPane(dataTable);
		dataTable.setFillsViewportHeight(true);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
		// This handles what occurs when the window closes.
		this.addWindowListener( 
			    new WindowAdapter() { 
			        public void windowClosing(WindowEvent e) { 
			        	// the below line is necessary so that if the same query is used after the window's closed,
			        	// the window can be re-created.
			        	LookupButtonLogic.removeElementFromArray((TableFrame) e.getSource());
			        	dispose(); // closes this window
			        } 
			    } 
			); // end of window listener definition
		
		this.add(mainPanel);
		this.pack();
		this.setVisible(true);
	} // End of TableFrame constructor definition
	
	/**
	 * Converts the string containing historical stock data into a 2-dimensional array for display in a table.
	 * @param dataString the string containing historical stock data
	 * @param chronologicalOrder whether the data is to be displayed in chronological order
	 * @return a 2-d array containing the data, with column headings specified in the "columnNames" array in the constructor
	 * method of this class.
	 * 			
	 */
	public String[][] formatData(String dataString, Boolean chronologicalOrder){
		
		/* The data supplied by Yahoo! is a string with data for each date separated with a newline character (\n).
		 The below line of code takes advantage of that and creates an array with each row representing data for 
		 a certain date. */
		rawDataArray = dataString.split("\n"); 
		
		/* Each row of data has commas separating the separate pieces of data. The below block of code splits each row into
		 * 7 columns, and as a result all of the data from Yahoo! ends up in a 2-d array.
		 */
		rawDataArray2 = new String[rawDataArray.length-1][7];
		for (int i = 1; i < rawDataArray.length; i++){
			rowArray = rawDataArray[i].split(",");
			for (int j = 0; j < 7; j++){
				rawDataArray2[i-1][j] = rowArray[j];
			}
		}
		
		// CHRONOLOGICAL ORDER 
		if (chronologicalOrder){ // reverses the entire data array if the chronological order checkbox was ticked earlier.
			String[][] reversedArray = new String[rawDataArray2.length][7];
			for (int i = 0; i < rawDataArray2.length; i++){
				for (int j = 0; j < 7; j++){
					reversedArray[i][j] = rawDataArray2[rawDataArray2.length-i-1][j];
				}
			}
			rawDataArray2 = reversedArray;
		}
		
		return rawDataArray2;
	} // End of formatData() definition
	
	/**
	 * Method for accessing the adjusted close value data
	 * @return the adjusted close value data in the form of an array.
	 */
	double[] getAdjustedCloseValues(){
		double[] adjCloseArray = new double[dataTableModel.getRowCount()];
		for (int i = 0; i < dataTableModel.getRowCount(); i++){
			// adds each value in the adjusted close column to the array
			adjCloseArray[i] = Double.parseDouble((String) dataTableModel.getValueAt(i, 6)); 
		}
		return adjCloseArray;
	} // End of getAdjustedCloseValues() definition

} // End of TableFrame definition
