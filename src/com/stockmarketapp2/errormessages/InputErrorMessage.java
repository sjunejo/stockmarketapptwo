package com.stockmarketapp2.errormessages;

import javax.swing.JOptionPane;

/**
 * The error message that is displayed if the user has entered an invalid format for the stock symbol.
 * @author Sadruddin Junejo
 *
 */
public class InputErrorMessage extends JOptionPane {
	
	/**
	 * Constructor method. Handles the display of the error message.
	 */
	public InputErrorMessage(){
		this.showMessageDialog(null, 
				"Input cannot be more than 8 characters in length and must only contain uppercase characters, digits or periods." +
				"\nAlso, at least one character must be input.", 
				
				"Input Error", 
				JOptionPane.ERROR_MESSAGE);
		
	}

}