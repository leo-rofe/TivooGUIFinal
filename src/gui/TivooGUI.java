package gui;


import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import calendar.XMLCal;


import filter_sorter.CalendarFilter;
import filter_sorter.ConflictFilter;
import filter_sorter.KeywordFilter;
import filter_sorter.NativeFilter;
import filter_sorter.Sorter;


public class TivooGUI {
	public final static Object[] options = { "Keyword", "Date", "Conflict", "No Filter" };
	public final static Object[] keyOptions = { "Title", "Author", "Description",
	"Actor (only for TV Calendar)"};
	public final static Object[] calOptions = { "Day", "Week", "Month",
	"Time Period"};
	public final static Object[] sortOptions = { "Start", "End", "Title", "No Sort"};
	
	public String[] loadFiles() {
		JFileChooser fc = new JFileChooser();
		// to select multiple files, hold the apple command key.
		fc.setMultiSelectionEnabled(true);
		fc.showOpenDialog(null);
		File[]filesList = fc.getSelectedFiles();
		String[] fileString = new String[filesList.length];
		for (int i = 0; i < filesList.length; i++) {
			fileString[i] = (filesList[i].toString());
		}
		return fileString;
	}
	
	public void filterOptions(XMLCal calendar) throws ParseException{
		JFrame frame = new JFrame();

		String input = (String) JOptionPane.showInputDialog(frame,
				"Pick Your Filter:", "Customized Dialog",
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (input.equals("Keyword"))
		{
			KeywordFilter filter = new KeywordFilter(calendar);
			NativeFilter actorFilter = new NativeFilter(calendar);
			String keyInput = (String) JOptionPane.showInputDialog(frame,
					"Pick Your Keyword Filter:", "Customized Dialog",
					JOptionPane.PLAIN_MESSAGE, null, keyOptions, keyOptions[0]);
			String filterString = JOptionPane.showInputDialog(null,
					"Enter Filter Words for " + keyInput + ":\n"
							+ "(separate words with a space):");
			String [] filterArray = filterString.split(" ");
			if (keyInput.equals("Title")) filter.filterTitle(filterArray);
			if (keyInput.equals("Author")) filter.filterAuthor(filterArray);
			if (keyInput.equals("Description")) filter.filterDescription(filterArray);
			if (keyInput.equals("Actor (only for TV Calendar)")) actorFilter.filterActor(filterArray);		
		}
		else if (input.equals("Conflict"))
		{
			ConflictFilter filter = new ConflictFilter(calendar);
			filter.filterConflicts();
		}
		else if (input.equals("Date"))
		{
			JFrame frame1 = new JFrame();
			CalendarFilter filter = new CalendarFilter(calendar);
			String calInput = (String) JOptionPane.showInputDialog(frame1,
					"Pick Your Date Filter:", "Customized Dialog",
					JOptionPane.PLAIN_MESSAGE, null, calOptions, calOptions[0]);
			if (calInput.equals("Time Period"))
			{
				String filterString = JOptionPane.showInputDialog(null,
						"Enter StartDate then EndDate separated by a space \n"
								+ "(Format: MM.DD.YYYY MM.DD.YYYY):");
				String[] filters = filterString.split(" ");
				ArrayList<Calendar> befAf = new ArrayList<Calendar>();
				for (int i = 0; i < filters.length; i++) {
					String[] date = filters[i].split("\\.");
					Calendar temp = new GregorianCalendar(
							Integer.parseInt(date[2]), Integer.parseInt(date[0]),
							Integer.parseInt(date[1]));
					befAf.add(temp);
				}
				filter.filterTime(befAf.get(0), befAf.get(1));
			}
			else 
			{
				String filterString = JOptionPane.showInputDialog(null,
						"Enter Date: \n"
								+ "(Format: MM.DD.YYYY):");
				String[] temp = filterString.split("\\.");
				Calendar date = new GregorianCalendar(
						Integer.parseInt(temp[2]), Integer.parseInt(temp[0]),
						Integer.parseInt(temp[1]));
				if (calInput.equals("Day")) filter.filterDay(date);
				if (calInput.equals("Week")) filter.filterWeek(date);
				if (calInput.equals("Month")) filter.filterMonth(date);
			}
		}
		
		JFrame frame2 = new JFrame();
		Sorter sorter = new Sorter();
		String sortInput = (String) JOptionPane.showInputDialog(frame2,
				"Sort Options:", "Customized Dialog",
				JOptionPane.PLAIN_MESSAGE, null, sortOptions, sortOptions[3]);
		if (sortInput.equals("End")) sorter.sortByEnd(calendar, false);
		if (sortInput.equals("Start")) sorter.sortStart(calendar, false);
		if (sortInput.equals("Title")) sorter.sortByTitle(calendar, false);
	}

	}
