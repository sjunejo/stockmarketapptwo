package com.stockmarketapp2.widgets.comboboxes;

import javax.swing.JComboBox;

/**
 * Combo box that specifies the interval between individual pieces of historical stock data;
 * daily, weekly or monthly.
 * @author Sadruddin Junejo
 *
 */
public class IntervalBox extends JComboBox {
	
	/**
	 * Constructor class for interval box
	 */
	public IntervalBox(){
		this.addItem(new String("Daily"));
		this.addItem(new String("Weekly"));
		this.addItem(new String("Monthly"));
		
		this.setSelectedIndex(2); // Sets the default value as "Weekly".
	} // End of constructor definition
	

} // End of class definition
