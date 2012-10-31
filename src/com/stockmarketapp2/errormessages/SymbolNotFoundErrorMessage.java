package com.stockmarketapp2.errormessages;

import javax.swing.JOptionPane;

/**
 * The error message that is displayed if the user has either queried a stock symbol that does not exist,
 * or with invalid dates.
 * @author Sadruddin Junejo
 *
 */
public class SymbolNotFoundErrorMessage extends JOptionPane {

	/**
	 * Constructor method. Handles the display of the error message.
	 */
	public SymbolNotFoundErrorMessage(){
		this.showMessageDialog(null, 
				"Data for requested symbol not found. Please check your stock symbol and dates.",
				
				"Incorrect Input", 
				JOptionPane.ERROR_MESSAGE);
	}
}

