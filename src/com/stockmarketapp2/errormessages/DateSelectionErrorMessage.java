package com.stockmarketapp2.errormessages;

import javax.swing.JOptionPane;

/**
 * The error message that appears if the user has not entered a start date chronologically before the end date.
 * @author Sadruddin Junejo
 *
 */
public class DateSelectionErrorMessage extends JOptionPane {

	/**
	 * Constructor method. Handles the display of the error message.
	 */
	public DateSelectionErrorMessage(){
		this.showMessageDialog(null, 
				"Start date must be before the end date!",
				
				"Date Selection Error", 
				JOptionPane.ERROR_MESSAGE);
	}
}
