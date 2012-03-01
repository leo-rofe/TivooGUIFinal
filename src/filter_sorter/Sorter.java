package filter_sorter;

import java.util.Collections;

import calendar.XMLCal;



import event.Event;

public class Sorter {
	
	
	public void sortByTitle(XMLCal calendar, boolean reverse) {

			Collections.sort(calendar.getEvents(), Event.byTitle);
			if (reverse)
				Collections.reverse(calendar.getEvents());
	}

	public void sortStart(XMLCal calendar, boolean reverse) {

		Collections.sort(calendar.getEvents(), Event.byStart);
		if (reverse)
			Collections.reverse(calendar.getEvents());
}
	public void sortByEnd(XMLCal calendar, boolean reverse) {

		Collections.sort(calendar.getEvents(), Event.byEnd);
		if (reverse)
			Collections.reverse(calendar.getEvents());
}
		

}
