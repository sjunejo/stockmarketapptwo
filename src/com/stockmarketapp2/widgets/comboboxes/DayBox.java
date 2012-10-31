package com.stockmarketapp2.widgets.comboboxes;

import javax.swing.JComboBox;

/**
 * Combo box that allows the user to select a certain day of the month.
 * @author Sadruddin Junejo
 *
 */
public class DayBox extends JComboBox {
	
	/**
	 * Constructor class for day box. Sets default number of days as 31.
	 */
	public DayBox(){
		this.updateNumberOfDays(31);
	}
	
	/**
	 * Changes the number of days according to the input parameter.
	 * @param numberOfDays the number of days the month should have.
	 */
	 void updateNumberOfDays(int numberOfDays){
		this.removeAllItems(); // reset
		for (int i = 1; i <= numberOfDays; i++) this.addItem(new String("" + i));
	}
	
}
