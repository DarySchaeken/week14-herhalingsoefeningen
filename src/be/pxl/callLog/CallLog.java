package be.pxl.callLog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CallLog {
	private int id;
	private String name;
	private LocalDate date;
	private LocalTime time;
	private String company;
	private String description;
	private int priority;
	private CallLogStatus status;

	private static final DateTimeFormatter DATE_INPUT = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private static final DateTimeFormatter TIME_INPUT = DateTimeFormatter.ofPattern("HH:mm:ss");
	private static final DateTimeFormatter DATE_OUTPUT = DateTimeFormatter.ofPattern("uuuuMMdd");
	private static final DateTimeFormatter TIME_OUTPUT = DateTimeFormatter.ofPattern("HHmm");

	public CallLog(String input) {
		String[] inputs = input.split(";");
		id = Integer.parseInt(inputs[0]);
		name = inputs[1];
		date = LocalDate.parse(inputs[2], DATE_INPUT);
		time = LocalTime.parse(inputs[3], TIME_INPUT);
		company = inputs[4];
		description = inputs[5];
		priority = Integer.parseInt(inputs[6]);
		status = determineCallLogStatus(inputs[7]);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public String getCompany() {
		return company;
	}

	public String getDescription() {
		return description;
	}

	public int getPriority() {
		return priority;
	}

	public CallLogStatus getStatus() {
		return status;
	}

	public int compareToDateTime(CallLog callLog) {
		LocalDateTime localDateTime1 = LocalDateTime.of(this.getDate(), this.getTime());
		LocalDateTime localDateTime2 = LocalDateTime.of(callLog.getDate(), callLog.getTime());
		
		return localDateTime1.compareTo(localDateTime2);
	}

	public int compareToPriorityAlphabet(CallLog callLog) {
		int result = this.getPriority() - callLog.getPriority();
		if(result == 0){
			return this.getCompany().compareToIgnoreCase(callLog.getCompany());
		} 
		return result;	
	}
	
	public int compareToPriorityDateTime(CallLog callLog) {
		int result = this.getPriority() - callLog.getPriority();
		if(result == 0){
			return this.compareToDateTime(callLog);
		} 
		return result;
	}

	@Override
	public String toString() {
		return String.format("%d;%s;%s;%s;%s;%s;%d;%s", id, name, date.format(DATE_INPUT), time.format(TIME_INPUT),
				company, description, priority, status.name().replaceAll("_", " "));
	}
	
	public String dateTimeFileName() {
		return String.format("%s%s", date.format(DATE_OUTPUT), time.format(TIME_OUTPUT));
	}

	private CallLogStatus determineCallLogStatus(String statusString) {
		switch (statusString.toUpperCase()) {
		case "OPEN":
			return CallLogStatus.OPEN;
		case "CLOSED":
			return CallLogStatus.CLOSED;
		case "IN PROGRESS":
			return CallLogStatus.IN_PROGRESS;
		}
		return status;
	}

}
