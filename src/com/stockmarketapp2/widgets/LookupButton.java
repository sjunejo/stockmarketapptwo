package com.stockmarketapp2.widgets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Button, that, when clicked, displays historical stock data if input validation is successful.
 * @author Sadruddin Junejo
 *
 */
public class LookupButton extends JButton implements ActionListener {
	
	/**
	 * Constructor class for look up button.
	 * @param name The text displayed on the button.
	 */
	public LookupButton(String name){
		super(name);
		this.addActionListener(this);
	}

	/**
	 * This method is overidden in the MainWindow class. 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
