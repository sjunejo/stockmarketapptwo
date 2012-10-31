package com.stockmarketapp2.gui.table;

import java.util.Arrays;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * The Table Model that holds the data requested by the user.
 * @author Sadruddin Junejo
 *
 */
public class DataTableModel extends DefaultTableModel implements TableModel{

	private double previousValue;
	private double currentValue;
	private String colouredString;

	/**
	 * Constructor
	 * @param dataArray the two-dimensional array containing the historical stock data queried by the user
	 * @param columnNames the column headings
	 */
	public DataTableModel(String[][] dataArray, String[] columnNames){
		super(dataArray, columnNames);
	}
	
	/**
	 * Sets up loop for colouring adjusted close data depending on rise/fall.
	 * This method deals with data in reversed chronological order.
	 */
	public void colourize(){
		for (int i = 0; i < this.getRowCount()-1; i++){
			previousValue = Double.parseDouble((String) this.getValueAt(i+1, 6));
			currentValue = Double.parseDouble((String) this.getValueAt(i,6));
			determineAndSetColour(i);
		}
	}
	
	/**
	 * Sets up loop for colouring adjusted close data depending on rise/fall.
	 * This method deals with data in chronological order.
	 */
	public void reversedTableColourize(){
		for (int i = this.getRowCount()-1; i>=1; i--){
			previousValue = Double.parseDouble((String) this.getValueAt(i-1, 6));
			currentValue = Double.parseDouble((String) this.getValueAt(i,6));
			determineAndSetColour(i);
		}
	} 
	
	/**
	 * Method that colours adjusted close values depending on whether they have risen or fallen.
	 * This separate method is needed to avoid excess code repetition in the above methods.
	 * @param i the row index of the string to be coloured.
	 * NOTE: The column index is 6 as it is only the column with index 6 (the last column) that needs to be coloured.
	 */
	void determineAndSetColour(int i){
		colouredString = (String) this.getValueAt(i, 6);
		if (currentValue < previousValue){ // if the price has fallen
			colouredString = "<html><font color = '#CC0000'>" + colouredString + "</font></html>"; // sets price red
			this.setValueAt(colouredString, i, 6);
			
		}
		if (currentValue > previousValue){ // if the price has risen
			colouredString = "<html><font color = '#339900'>" + colouredString + "</font></html>"; // sets price green 
			this.setValueAt(colouredString, i, 6);
			
		}
	} // End of determineAndSetColour() definition
	
} // End of DataTableModel class definition
