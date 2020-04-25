package application;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Class meant for printing error/debug messages to a txt file vs. the console
 * 2 methods, one to print just to txt file other to print to console.
 * @author Bryan Rogers
 *
 */
public class PrintDebug {

	/**
	 * Method that prints out the debug messages to a log file
	 * @param message
	 */
	public static void printDebug(String message) {
		String directoryPath = System.getProperty("user.dir") + "/log/";
		
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdir();
		}
		
		File f = new File(directoryPath + "syslog.txt");
		try {
			FileWriter fw = new FileWriter(f, true);
			fw.write(LocalDateTime.now() + ": " + message + "\n");
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		catch (JSONException e) {
			//e.printStackTrace();
		}
	}
	/**
	 * overloaded method to print debug messages to log file AND to console
	 * if a second paramter is passed.
	 * @param message
	 * @param i - flag that denotes if message should print to console as well as syslog file
	 */
	public static void printDebug(String message, int i) {
		System.out.println(message);
		printDebug(message);
	}
	
}
