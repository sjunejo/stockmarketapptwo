package com.stockmarketapp2.widgets.comboboxes;

import javax.swing.JComboBox;

/**
 * A combo box which allows the user to select a year.
 * Two of these exist in the main window of the program; one for the start date and one for the beginning date. 
 * @author Sadruddin Junejo
 *
 */
public class YearBox extends JComboBox {

	/**
	 * Constructor method for year box.
	 */
	public YearBox(){	
		// Adds selectable years to the year combo box
		for (int i = 1970; i <= 2012; i++){
			this.addItem(new String("" + i));
		}
	}

}
