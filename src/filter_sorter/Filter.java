package filter_sorter;

import calendar.XMLCal;




public abstract class Filter {
	private XMLCal calendar;
	
	public Filter(XMLCal calendar) {
		this.calendar = calendar;
	}
	
	public XMLCal getCalendar() {
		return calendar;
	}
	
}
