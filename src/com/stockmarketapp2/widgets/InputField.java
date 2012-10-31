package com.stockmarketapp2.widgets;

import javax.swing.JTextField;


/**
 * The input field that allows the user to enter stock symbols to query.
 * @author Sadruddin Junejo
 *
 */
public class InputField extends JTextField {

	/**
	 * A method used for checking that the symbol entered by the user follows the correct format,
	 * i.e. it only consists of capital letters, digits and periods.
	 * @return true if the symbol follows the correct symbol
	 */
	public boolean symbolIsValid(){
		String regex = "[A-Z[\\d[\\.]]]"; // the regex that every character in the input string must follow
		
		// Symbol can not be longer than 8 characters. Also, there must be some sort of input.
		if (this.getText().length() > 8 || this.getText().isEmpty()){ 
			return false;
		}
		else {
			 for (int i = 0; i < this.getText().length(); i++){
				 // checks each individual character comprising the symbol against the regular expression
				 if (!(this.getText().substring(i,i+1).matches(regex)))	return false; 
			 }
			 return true;
		}
	}

}
