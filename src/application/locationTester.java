package application;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

class locationTester {

	/**
	 * class that testse the getLocationCandidates method in Location
	 */
	@Test
	void getLocationCandidatesTest() {
		Location lone = new Location();
		Location ltwo = new Location();
		Location lthree = new Location();
		Location lfour = new Location();
		Location lfive = new Location();
		
		
		//5 locations to get information about known locations
		ArrayList<String> answer1 = lone.getLocationCandidates("11787");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> answer2 = ltwo.getLocationCandidates("Empire State Building");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> answer3 = lthree.getLocationCandidates("1600 Pennsylvania Ave NW");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> answer4 = lfour.getLocationCandidates("Denver, CO");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> answer5 =  lfive.getLocationCandidates("");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Assert the known size of each locations candidates
		assertEquals(5, answer1.size());
		assertEquals(2, answer2.size());
		assertEquals(5, answer3.size());
		assertEquals(5, answer4.size());
		//for answer5, I passed garbage and am confirming it reutrns null as expected
		assertEquals(null, answer5);
		
		//Parse a specific pre-determined address for each of the 4 valid locations
		lone.parseAddress("Hauppauge, Hauppauge, Suffolk County, New York, 11787, USA");
		ltwo.parseAddress("Empire State Building, 350, 5th Avenue, Korea Town, Manhattan, New York, New York County, New York, 10001, USA");
		lthree.parseAddress("The Oval Office, 1600, Pennsylvania Avenue Northwest, Golden Triangle, Penn Quarter, Washington D.C., Washington, Washington, D.C., 20006, USA");
		lfour.parseAddress("Denver County, Colorado, USA");
		
		//AsserEquals the values of each known location, name, late, long
		assertEquals("Hauppauge, Hauppauge, Suffolk County, New York, 11787, USA", lone.getDisplayName());
		assertEquals("40.844", lone.getLatitude());
		assertEquals("-73.20", lone.getLongitude());
		assertEquals("Empire State Building, 350, 5th Avenue, Korea Town, Manhattan, New York, New York County, New York, 10001, USA", ltwo.getDisplayName());
		assertEquals("-73.98", ltwo.getLongitude());
		assertEquals("40.748", ltwo.getLatitude());
		assertEquals("The Oval Office, 1600, Pennsylvania Avenue Northwest, Golden Triangle, Penn Quarter, Washington D.C., Washington, Washington, D.C., 20006, USA", lthree.getDisplayName());
		assertEquals("38.897", lthree.getLatitude());
		assertEquals("-77.03", lthree.getLongitude());
		assertEquals("Denver County, Colorado, USA", lfour.getDisplayName());
		assertEquals("-104.9", lfour.getLongitude());
		assertEquals("39.734", lfour.getLatitude());
		
		
	}


}
