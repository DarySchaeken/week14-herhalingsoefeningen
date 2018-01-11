package be.pxl.callLog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class CallLogWriter extends Thread {
	private HashSet<CallLog> callLogs;
	private String fileName;
	
	public CallLogWriter(HashSet<CallLog> callLogs, String fileName) {
		this.callLogs = callLogs;
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources\\output\\" + fileName + ".csv"))){
			for(CallLog callLog: callLogs) {
				bufferedWriter.write(callLog.toString() + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
