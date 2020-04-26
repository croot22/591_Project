package application;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Test cases for NWSApiHandler. Meant to stress different methods and features of the calss.
 * @author Bryan Rogers
 *
 */
class NWS_APIHandlerTester {

	/**
	 * Test the calls to NWS API by calling known GPS coordinates and confirming basic information
	 * 
	 */
	@Test
	void NWSCalltest() {
		NWS_APIHandler nws = new NWS_APIHandler();
		LocalDate today = LocalDate.now();
		
		//setup some known GPS coordinates and get the forecast for them
		String[] coordinates = {"39.734,-104.904561045", "40.748,-73.98", "40.843,-73.20", ""};
		
		
		ArrayList<DailyForecast> forecast1 = nws.getNWSForecast(coordinates[0]);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<DailyForecast> forecast2 = nws.getNWSForecast(coordinates[1]);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<DailyForecast> forecast3 = nws.getNWSForecast(coordinates[2]);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<DailyForecast> forecast4 = nws.getNWSForecast(coordinates[3]);
		
		//Check that date for each forecast matches today's date
		assertEquals(today.toString(), forecast1.get(0).getDate());
		assertEquals(today.toString(), forecast2.get(0).getDate());
		assertEquals(today.toString(), forecast3.get(0).getDate());
		
		//Check that the forecast for tomorrow's date matches tomorrows date
		assertEquals(today.plusDays(1).toString(), forecast1.get(1).getDate());
		assertEquals(today.plusDays(1).toString(), forecast2.get(1).getDate());
		assertEquals(today.plusDays(1).toString(), forecast3.get(1).getDate());
		
		System.out.println(forecast1.get(0).getNarrativeN());
		
		//check forecast4 returns null as expected
		assertEquals(null, forecast4);
		
	}

}
