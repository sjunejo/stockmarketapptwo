package com.stockmarketapp2.widgets.comboboxes;

import javax.swing.JComboBox;

/**
 * Combo box that allows the user to choose a month of the year.
 * @author Sadruddin Junejo
 *
 */
public class MonthBox extends JComboBox  {
	
	/**
	 * Constructor method for month box class
	 */
	public MonthBox(){
		String[] monthsOfTheYear = {"January", "February", "March",	"April", "May", "June",	
				"July",	"August", "September", "October", "November", "December"};
		for (String month: monthsOfTheYear) this.addItem(new String(month)); // adds months of year to month box
	}
	
}
