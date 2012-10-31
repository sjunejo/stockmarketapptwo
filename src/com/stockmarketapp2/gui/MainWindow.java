package com.stockmarketapp2.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.stockmarketapp2.errormessages.DateSelectionErrorMessage;
import com.stockmarketapp2.errormessages.InputErrorMessage;
import com.stockmarketapp2.staticlogic.LookupButtonLogic;
import com.stockmarketapp2.widgets.InputField;
import com.stockmarketapp2.widgets.LookupButton;
import com.stockmarketapp2.widgets.comboboxes.ComboBoxSet;
import com.stockmarketapp2.widgets.comboboxes.IntervalBox;

/**
 * The window that is responsible for allowing the user to input a stock symbol and a range of dates for querying 
 * historical data.
 * @author Sadruddin Junejo
 *
 */
public class MainWindow extends JFrame {
	
	private GridLayout gLayout;
	
	private JPanel mainPanel;
	private JPanel headingPanel;
	private JPanel widgetPanel;
	
	private InputField stockInputField;
	private IntervalBox intervalBox;
	
	private JCheckBox chronoCheckBox;
	
	private ComboBoxSet beginDateComboBoxSet;
	private ComboBoxSet endDateComboBoxSet;
	
	/**
	 * Constructor class for MainWindow. Contains all the methods used to construct the window.
	 */
	public MainWindow(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Stock Market App Pro");
		
		createMainPanel();
		setGridLayout();
		setupLabelHeadings();
		createWidgetPanel();
		addInputField();
		addBeginDateComboBoxes();
		addEndDateComboBoxes();
		addIntervalComboBox();
		addCheckBox();
		addLookupButton();
		
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Creates the main panel which utilises a border layout.
	 * The west side of the layout contains the headings for the input fields,
	 * while the center contains the actual input fields. 
	 */
	private void createMainPanel(){
		mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);
	}

	/**
	 * This method sets up the grid layout used by both the west and central parts of the border layout.
	 */
	private void setGridLayout(){
		gLayout = new GridLayout(6,1);
		gLayout.setVgap(5);
	}
	
	/**
	 * Sets up the label headings in the west part of the main panel
	 */
	private void setupLabelHeadings(){
		headingPanel = new JPanel(gLayout);
		String[] labelArray = {"Stock Symbol", "Begin", "End", "Interval", "Chronological Order"}; // the headings
		for (String labelName: labelArray)	headingPanel.add(new JLabel(labelName + ": "));
		mainPanel.add(headingPanel, BorderLayout.WEST);
	}
	
	/**
	 * Creates the panel that holds the input fields, namely:
	 * => The stock input field
	 * => The date checkboxes
	 * => The interval setting
	 * => The box that, when checked, lists the output data in chronological order
	 * => The lookup button that is used to output the required data when pressed.
	 */
	private void createWidgetPanel(){
		widgetPanel = new JPanel(gLayout);
		mainPanel.add(widgetPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Adds the stock input field.
	 * This field allows for the input of a stock symbol that is no longer than eight characters 
	 * and must follow a certain format. See "InputField" in com.stockmarketapp2.widgets for more info.
	 */
	private void addInputField(){
		stockInputField = new InputField();
		JPanel gridPanel = new JPanel(new GridLayout(1,2));
		gridPanel.add(stockInputField);
		gridPanel.add(new JLabel("")); // Using a grid layout and adding a blank JComponent decreases the size of the textfield.
		stockInputField.setPreferredSize(new Dimension(40,40));
		widgetPanel.add(gridPanel);
	}
	
	/**
	 * Adds the combo boxes that define the begin date.
	 * See ComboBoxSet for more info
	 */
	private void addBeginDateComboBoxes(){
		beginDateComboBoxSet = new ComboBoxSet(1);
		widgetPanel.add(beginDateComboBoxSet);
	}
	
	/**
	 * Adds the combo boxes that define the end date.
	 */
	private void addEndDateComboBoxes(){
		endDateComboBoxSet = new ComboBoxSet(2);
		widgetPanel.add(endDateComboBoxSet);
	}
	
	/**
	 * Adds combo box that determines whether the spread of requested data should be 
	 * daily, weekly or monthly.
	 */
	private void addIntervalComboBox(){
		intervalBox = new IntervalBox();
		widgetPanel.add(intervalBox);
	}
	
	/**
	 * Adds check box that, when ticked, displays results in chronological order.
	 */
	private void addCheckBox(){
	    chronoCheckBox = new JCheckBox();
		widgetPanel.add(chronoCheckBox);
	}
	
	/**
	 * Adds the button that 
	 * First, input validation is carried out using the input fields "symbolIsValid()" method.
	 * If the input follows the correct format, it is then checked to make sure that the specified start date
	 * lies before the end date.
	 * Finally, if the date is correct, the program then queries Yahoo!'s historical database 
	 * and returns data according to the requirements specified by the user.
	 */
	private void addLookupButton(){
		LookupButton lButton = new LookupButton("Lookup"){
			public void actionPerformed(ActionEvent e){
				if (stockInputField.symbolIsValid()){ // Checks that symbol entered by user is of the correct format
					// checks that the start date entered be the user lies before the end date
					if (LookupButtonLogic.startDateBeforeEndDate( 
							beginDateComboBoxSet.getYear(),
							beginDateComboBoxSet.getMonth(),
							beginDateComboBoxSet.getDay(),
							endDateComboBoxSet.getYear(),
							endDateComboBoxSet.getMonth(),
							endDateComboBoxSet.getDay()
							)){
								// See mainProcessing() method in LookupButtonLogic class for more info.
								LookupButtonLogic.mainProcessing(
									stockInputField.getText().toUpperCase(),
									beginDateComboBoxSet.getDate(), 
									endDateComboBoxSet.getDate(), 
									(String) intervalBox.getSelectedItem(),
									chronoCheckBox.isSelected()
								);
					}
					else { // Case where the user has used incorrect input
						new DateSelectionErrorMessage().setVisible(true); // display error message
					}
				}
				// Case where the user's stock input does not follow the required format
				else new InputErrorMessage().setVisible(true);  // display error message
			} // end of ActionPerformed definition
		};
		widgetPanel.add(lButton);
	} // end of addLookupButton definition
	
} // end of MainWindow class definition
