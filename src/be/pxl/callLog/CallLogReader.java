package be.pxl.callLog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;

public class CallLogReader extends Thread {
	private HashSet<CallLog> callLogs;
	private String callLogFileName;

	public CallLogReader(String callLogFileName) {
		this.callLogFileName = callLogFileName;
		callLogs = new HashSet<CallLog>();
	}

	@Override
	public void run() {
		try (BufferedReader bufferedReader = new BufferedReader(
				new FileReader("resources\\in\\" + callLogFileName + ".csv"))) {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (!line.contains("IGNORE")) {
					callLogs.add(new CallLog(line));
				}
			}
			bufferedReader.close();
			Files.move(Paths.get("resources\\in\\" + callLogFileName + ".csv"),
					Paths.get("resources\\processed\\" + callLogFileName + ".csv"),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashSet<CallLog> getCallLogs() {
		return callLogs;
	}
}
