package be.pxl.callLog;

import java.util.HashSet;
import java.util.stream.Collectors;

public class CallLogApplication {

	public static void main(String[] args) {
		// Place all testdata files in resources\in folder before running application.
		CallLogReader[] readers = new CallLogReader[5];
		readers[0] = new CallLogReader("testdata1");
		readers[1] = new CallLogReader("testdata2");
		readers[2] = new CallLogReader("testdata3");
		readers[3] = new CallLogReader("testdata4");
		readers[4] = new CallLogReader("testdata5");

		for (CallLogReader reader : readers) {
			reader.start();
		}
		
		for (CallLogReader reader : readers) {
			try {
				reader.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		HashSet<CallLog> allLogs = new HashSet<CallLog>();
		for (CallLogReader reader : readers) {
			allLogs.addAll(reader.getCallLogs());
		}

		//System.out.println(allLogs.size());
		
		HashSet<CallLog> closedLogs = allLogs.stream().filter(cL -> cL.getStatus().equals(CallLogStatus.CLOSED))
				.sorted((cl1, cl2) -> cl1.compareToDateTime(cl2))
				.collect(Collectors.toCollection(HashSet<CallLog>::new));
		new CallLogWriter(closedLogs,
				String.format("%s_closed", closedLogs.stream().findFirst().get().dateTimeFileName())).start();

		HashSet<CallLog> inProgressLogs = allLogs.stream()
				.filter(cL -> cL.getStatus().equals(CallLogStatus.IN_PROGRESS))
				.sorted((cl1, cl2) -> cl1.compareToPriorityAlphabet(cl2))
				.collect(Collectors.toCollection(HashSet<CallLog>::new));
		new CallLogWriter(inProgressLogs,
				String.format("%s_in_progress", inProgressLogs.stream().findFirst().get().dateTimeFileName())).start();
		
		HashSet<CallLog> openLogs = allLogs.stream()
				.filter(cL -> cL.getStatus().equals(CallLogStatus.OPEN))
				.sorted((cl1, cl2) -> cl1.compareToPriorityDateTime(cl2))
				.collect(Collectors.toCollection(HashSet<CallLog>::new));
		new CallLogWriter(openLogs,
				String.format("%s_open", openLogs.stream().findFirst().get().dateTimeFileName())).start();
	}

}
