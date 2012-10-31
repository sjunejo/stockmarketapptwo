package com.stockmarketapp2.staticlogic;

import java.util.ArrayList;

import com.stockmarketapp2.errormessages.SymbolNotFoundErrorMessage;
import com.stockmarketapp2.gui.table.TableFrame;

/**
 * Contains various methods that are invoked after the user has pressed the lookup button.
 * @author Sadruddin Junejo
 *
 */
public class LookupButtonLogic {
	
	/*
	 * The following prefixes must be used in the URL that is used to query Yahoo's database.
	 */
	private static final String URLPREFIX = "http://ichart.yahoo.com/table.csv?s=";
	private static final String BMONTHPREFIX = "&a=";
	private static final String BDAYPREFIX = "&b=";
	private static final String BYEARPREFIX = "&c=";
	private static final String EMONTHPREFIX = "&d=";
	private static final String EDAYPREFIX = "&e=";
	private static final String EYEARPREFIX = "&f=";
	private static final String INTERVALPREFIX = "&g=";
	
	/**
	 * This array list keeps a record of the tables of data that are currently 'open', i.e. in the form
	 * of open windows. 
	 */
	public static ArrayList<TableFrame> tableFrameArray = new ArrayList<TableFrame>();
	
	/**
	 * Algorithm for checking that the start date chosen by the user lies before the end date
	 * @param beginYear 
	 * @param beginMonth
	 * @param beginDay
	 * @param endYear
	 * @param endMonth
	 * @param endDay
	 * @return true if the start date is before the end date, false if it is not
	 */
	public static boolean startDateBeforeEndDate(int beginYear, int beginMonth, int beginDay, int endYear, int endMonth, int endDay){
		if (beginYear < endYear){
			return true;
		}
		else {
			if (beginYear == endYear){
				if (beginMonth < endMonth){
					return true;
				}
				else {
					if (beginMonth == endMonth){
						if (beginDay < endDay){
							return true;
						}
						else {
							return false;
						}
					}
					else {
						return false;
					}
					
				}
			}
			else {
				return false;
			}
			
		}
	} // End of startDateBeforeEndDate() 
	
	/**
	 * The method that is executed if the input is successful.
	 * @param stockSymbol the stock symbol input by the user
	 * @param beginDate the start date selected by the user
	 * @param endDate the end date selected by the user
	 * @param interval the interval selected by the user
	 * @param checkBoxSelected whether the "Chronological Order" checkbox is selected. This determines whether data 
	 * is to be displayed in chronological order.
	 */
	public static void mainProcessing(String stockSymbol, int[] beginDate, int[] endDate, String interval, boolean checkBoxSelected){
		String URL = setupURL(stockSymbol, beginDate, endDate, interval);
		try {
			String dataString = URLReader.readURL(URL);
			String title = stockSymbol +  ": " 
					+ (beginDate[0]+1) + "/" + beginDate[1] + "/" + beginDate[2]
							+ " - " 
							+ (endDate[0]+1) + "/" + endDate[1] + "/" + endDate[2] 
									+ " "
									+ "(" + interval;
		
			if (checkBoxSelected){
				title = title + ", Chronological Order)";
			}
			else {
				title = title + ", Reverse Chronological Order)";
			}
			// DUPLICATE - check if title matches
			if (!windowAlreadyExists(title)) {
				TableFrame tblFrame = new TableFrame(dataString, title, checkBoxSelected);
				tableFrameArray.add(tblFrame);
			}
		}
		catch (NullPointerException e){
			new SymbolNotFoundErrorMessage().setVisible(true);
		}
	}
	
	/**
	 * Checks if a request by the user for a certain set of historical set data has also been fulfilled, i.e.
	 * whether a table displaying this data already exists
	 * @param title Contains the query that the user input (and may have previously input)
	 * @return true if there is already a table with the same data
	 */
	static boolean windowAlreadyExists(String title){
		if (tableFrameArray.size() == 0) {
			return false;
		}
		else {
			int i = 0;
			boolean found = false;
			while (i < tableFrameArray.size() && !found){
				if (tableFrameArray.get(i).getTitle().equals(title)){
					tableFrameArray.get(i).toFront();
					return true;
				}
				i++;
			}
			return false;
		}
	}
	
	/**
	 * Setups the URL for querying Yahoo!'s stock database
	 * @param stockSymbol the stock symbol specified by the user
	 * @param beginDate the start date specified
	 * @param endDate the end date specified
	 * @param interval the interval specified
	 * @return the URL in the form of a string
	 */
	static String setupURL(String stockSymbol, int[] beginDate, int[] endDate, String interval){
		String URL = URLPREFIX + stockSymbol;
		
		URL = URL + BMONTHPREFIX + beginDate[0];
		URL = URL + BDAYPREFIX + beginDate[1];
		URL = URL + BYEARPREFIX + beginDate[2];
		
		URL = URL + EMONTHPREFIX + endDate[0];
		URL = URL + EDAYPREFIX + endDate[1];
		URL = URL + EYEARPREFIX + endDate[2];
		
		URL = URL + INTERVALPREFIX;
		if (interval.equals("Daily")) URL = URL + "d";
		if (interval.equals("Weekly")) URL = URL + "w";
		if (interval.equals("Monthly")) URL = URL + "m";
		
		return URL;
	}

	/**
	 * Executes when a table window is closed. This is to make sure that the user is able to query the same set
	 * of data again.
	 * @param tbf The table frame to be removed from the array that contains the table frame data of all windows that currently 
	 * exist.
	 */
	public static void removeElementFromArray(TableFrame tbf){
		int i = 0;
		while (i < tableFrameArray.size()){
			if (tableFrameArray.get(i).equals(tbf)){
				tableFrameArray.remove(i);
				break;
			}
			i++;
		}
	} // end of removeElementFromArray() definition
	
} // End of LookupButtonLogic class definition
