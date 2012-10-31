package com.stockmarketapp2.widgets.comboboxes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

/**
 * The 'Combo Box Set' is a set of one day box, one month box and one year box.
 * Two of these exist in the main window of the program; one for the start date of data 
 * and one for the end date.
 * This class is responsible for methods involving the manipulation of dates,
 * including accounting for leap years and the number of days of different months 
 * of the year.
 * @author Sadruddin Junejo
 *
 */
public class ComboBoxSet extends JPanel {
	private GridLayout gLayout; // the grid layout for the three combo boxes
	
	private int boxSetIndex;
	
	private DayBox dayBox;
	private MonthBox monthBox;
	private YearBox yearBox;
	
	/**
	 * Constructor method for combo box set.
	 * @param bsIndex Determines which one of the two combo box sets this object is.
	 * A value of 1 relates to the set responsible for the 'start' date
	 * and a value of 2 relates to the 'end' date.
	 */
	public ComboBoxSet(int bsIndex){
		gLayout = new GridLayout(1,3);
		gLayout.setHgap(5);
		
		setLayout(gLayout);
		
		boxSetIndex = bsIndex;
		
		dayBox = new DayBox();
		monthBox = new MonthBox();
		yearBox = new YearBox();
		setDefaultDate();
		
		monthBox.addActionListener(new MonthChangeListener());
		yearBox.addActionListener(new YearChangeListener());
		
		add(dayBox);
		add(monthBox);
		add(yearBox);
		
	} // End of ComboBoxSet constructor definition
	
	/**
	 * Sets default date for the combo box sets.
	 */
	void setDefaultDate(){
		if (boxSetIndex == 1){ // Begin date
			yearBox.setSelectedItem("2000"); // 1 January is already default entry
		}
		else { // End date
			Date date = new Date();
			SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			// Sets end date as the current date
			String currentDate = dFormat.format(date);
			dayBox.setSelectedIndex(Integer.parseInt(currentDate.substring(0,2))-1);
			monthBox.setSelectedIndex(Integer.parseInt(currentDate.substring(3,5))-1);
			yearBox.setSelectedItem(currentDate.substring(6,10));
		}
	} // End of setDefaultDate() definition

	/**
	 * Determines whether the year selected for the set is a leap year.
	 * @return true if the year is a leap year, false if it is not.
	 */
	boolean isLeapYear(){
		int year = Integer.parseInt((String) yearBox.getSelectedItem());
		if (year % 4 == 0){
			if (year % 100 == 0){
				if (year % 400 == 0){
					return true;	
				}
				else {
					return false;
				}
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	} // End of isLeapYear() definition
	
	/**
	 * Returns the date specified by the combo box set.
	 * @return the date in the form of a one-dimensional array.
	 */
	public int[] getDate(){
		int[] dateArray = new int[3];
		dateArray[0] = monthBox.getSelectedIndex();
		dateArray[1] = Integer.parseInt((String) dayBox.getSelectedItem());
		dateArray[2] = Integer.parseInt((String) yearBox.getSelectedItem());
		return dateArray;
	}
	
	/**
	 * Returns the year selected through the set's year box.
	 * @return the INDEX of the year selected 
	 */
	public int getYear(){
		return yearBox.getSelectedIndex();
	}
	
	/**
	 * Returns the month selected through this set's month box.
	 * @return the month selected
	 */
	public int getMonth(){
		return monthBox.getSelectedIndex()+1;
	}
	
	/**
	 * Returns the selected through this set's day box.
	 * @return the day selected
	 */
	public int getDay(){
		return dayBox.getSelectedIndex();
	}
	
	/**
	 * An action listener that determines how many days to use in this set's day box
	 * depending on what month is selected.
	 * For instance, January has 31 days while March has 30 days.
	 * Also, in case the month of February is selected the program is instructed to check if the year
	 * is a leap year before updating the number of days in the day box.
	 * @author Sadruddin Junejo
	 *
	 */
	public class MonthChangeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int selectedMonth = monthBox.getSelectedIndex();
			switch (selectedMonth){
				case 0: case 2: case 4: case 6: case 7: case 9: case 11: dayBox.updateNumberOfDays(31); break;
				case 3: case 5: case 8: case 10: dayBox.updateNumberOfDays(30); break;
				case 1: if(isLeapYear()) dayBox.updateNumberOfDays(29); else dayBox.updateNumberOfDays(28); break;
				default: dayBox.updateNumberOfDays(31); break;
			}
		}
	} // End of MonthChangeListener definition
	
	/**
	 * Action listener than handles what occurs when the year is changed.
	 * Necessary in case a leap year is selected.
	 * @author Sadruddin Junejo
	 *
	 */
	public class YearChangeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int monthIndex = monthBox.getSelectedIndex();
			if (monthIndex == 1){
				if (isLeapYear()) 			dayBox.updateNumberOfDays(29);
				else 						dayBox.updateNumberOfDays(28);
			}
			
		}
		
	} // End of YearChangeListener definition
	
} // End of ComboBoxSet definition
