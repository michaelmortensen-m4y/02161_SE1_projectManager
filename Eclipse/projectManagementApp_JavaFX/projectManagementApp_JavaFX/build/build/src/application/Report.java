package application;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Report {
	
	private Project project; // The project that the report is about
	private Calendar dateGenerated; // The date that the report was generated
	
	public Report(Project project, Calendar dateGenerated) {
		this.project = project;
		this.dateGenerated = dateGenerated;
	}


}
