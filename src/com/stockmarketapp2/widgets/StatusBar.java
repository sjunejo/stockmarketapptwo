package com.stockmarketapp2.widgets;

import javax.swing.JLabel;

/**
 * Widget that depicts  the average adjacent close price and maximum drawdown with respect to the requested data.
 * @author Sadruddin Junejo
 *
 */
public class StatusBar extends JLabel {
	
	private double[] adjCloseArray;
	private boolean chronologicalOrder;
	
	private double peak;
	private double drawdown;
	private double maxDrawdown;
	
	/**
	 * Constructor method for status bar.
	 * @param adjCloseArray an array containing the adjacent close values
	 * @param chronologicalOrder whether the data is displayed in chronological order
	 */
	public StatusBar(double[] adjCloseArray, boolean chronologicalOrder){
		this.chronologicalOrder = chronologicalOrder;
		this.adjCloseArray  = adjCloseArray.clone();
		this.setText("Average Adjusted Close Price: " + this.calculateAverageAdjustedClose());
		this.setText(this.getText() + " | Maximum Drawdown: " + this.calculateMaxDrawdown());
		
	}
	
	/**
	 * Calculates the average adjusted close price.
	 * @return the average adjusted close price
	 */
	 double calculateAverageAdjustedClose(){
		double average = 0;
		for (double element: adjCloseArray){
			average = average + element;
		}
		average = average / adjCloseArray.length;
		
		return round(average);
	}
	 
	 /**
	  * Calculates the maximal drawdown of the displayed data.
	  * @return the maximal drawdown
	  */
	double calculateMaxDrawdown(){
		 peak = -99999999; 
		 drawdown = 0;
		 maxDrawdown = -59589538;

		if (chronologicalOrder){
			for (int i = 0; i < adjCloseArray.length; i++){
				performActualCalculations(i);
			}
		}
		else {
			for (int i = adjCloseArray.length-1; i >= 0; i--){
				performActualCalculations(i);
			}
		}

		return round(maxDrawdown);
	}
	
	/**
	 * Separate method for performing actual calculations.
	 * Necessary in order to avoid code repetition in the above calculateMaxDrawdown() method.
	 * @param i
	 */
	private void performActualCalculations(int i){
		if (adjCloseArray[i] > peak){
			peak = adjCloseArray[i];
		}
		else {
			drawdown = peak - adjCloseArray[i];
		}
		if (drawdown > maxDrawdown){
			maxDrawdown = drawdown;
		}
	}
	
	/**
	 * Simple rounding method, used for both the maximum drawdown and average adjacent close.
	 * @param number the number to be rounded
	 * @return the number, rounded to 2 decimal places
	 */
	static double round(double number){
		double rounded = number * 100;
		rounded = Math.round(rounded);
		rounded = rounded / 100;
		return rounded;
	}

}
