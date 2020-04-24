package application;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class jsonIOtester {

	/**
	 * Test case for fileWriter and FileReader methods
	 * Effectively tests that data written is the same as data read
	 */
	@Test
	void fileReadWriteTest() {
		JSONInputOutput jio = new JSONInputOutput();
		ArrayList<Location> writeLocations = new ArrayList<Location>();
		ArrayList<Location> readLocations = new ArrayList<Location>();
		String testFile = "testfile.json";
		
		//Instantiate 10 locations
		for (int i = 0; i < 10; i++) {
			writeLocations.add(new Location());
			writeLocations.get(i).setDisplayName("Display Name " + i);
			writeLocations.get(i).setLatitude(Integer.toString(i));
			writeLocations.get(i).setLongitude(Integer.toString(i));
			
		}
		
		//Write those 10 locations to a file and then read them back
		jio.fileWriter(writeLocations, testFile);
		readLocations = jio.fileReader(testFile);
		
		//Confirm that the 10 locations written are the same as teh 10 locations read
		for (int j = 0; j < 10; j++) {
			
			assertEquals(writeLocations.get(j).getDisplayName(), readLocations.get(j).getDisplayName());
			assertEquals(writeLocations.get(j).getLatitude(), readLocations.get(j).getLatitude());
			assertEquals(writeLocations.get(j).getLongitude(), readLocations.get(j).getLongitude());
		}
		
		//Test the append function by adding the locations again to the file
		jio.fileWriter(writeLocations, testFile, false);
		readLocations = jio.fileReader(testFile);
		
		//confirm that the locations were written again at the end of the file
		for (int j = 10; j < 20; j++) {
			assertEquals(writeLocations.get(j-10).getDisplayName(), readLocations.get(j).getDisplayName());
			assertEquals(writeLocations.get(j-10).getLatitude(), readLocations.get(j).getLatitude());
			assertEquals(writeLocations.get(j-10).getLongitude(), readLocations.get(j).getLongitude());
		}
		
		//Test that readLocaitons returns null for a missing file
		readLocations = jio.fileReader("missingfile.json");
		assertEquals(null, readLocations);
	}
	
	/**
	 * Test parseGPS method by passing in static data and comparing the compute method against
	 * known data
	 */
	@Test
	void parseGPSTest() {
		//Passing in a raw API response with known values

		String directoryPath = System.getProperty("user.dir") + "/TestCases/";
		String responseBody = "";
		String responseBody2 = "";
		File f = new File(directoryPath + "testoneresponseone.json");
		File f2 = new File(directoryPath + "testoneresponsetwo.json");
		try {
			Scanner fileReader = new Scanner(f);
			Scanner fileReader2 = new Scanner(f2);
			while (fileReader.hasNext()) {
				responseBody += fileReader.nextLine();
			}
			while (fileReader2.hasNext()) {
				responseBody2 += fileReader2.nextLine();
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		
		JSONInputOutput jio = new JSONInputOutput();
		
		ArrayList<String> urls = jio.parseGPS(responseBody);
		ArrayList<String> urls2 = jio.parseGPS(responseBody2);
		
		//enter urls from text and make sure they are shown properly in return
		assertEquals("https://api.weather.gov/gridpoints/TOP/31,80/forecast", urls.get(0));
		assertEquals("https://api.weather.gov/gridpoints/TOP/31,80", urls.get(1));
		assertEquals("https://api.weather.gov/gridpoints/BOU/96,58/forecast", urls2.get(0));
		assertEquals("https://api.weather.gov/gridpoints/BOU/96,58", urls2.get(1));
	
	}
	
	/**
	 * test the parseWeather method by using a known dataset with known values adn comparing
	 * what the method returns to what is computed manually
	 */
	@Test
	void parseWeatherTest() {
		
					
		String forecastResponseBody = "";
		String forecastGridDataResponseBody = "";
		String directoryPath = System.getProperty("user.dir") + "/TestCases/";
		File f = new File(directoryPath + "forecastResponse.json");
		File f2 = new File(directoryPath + "forecastGridData.json");
		try {
			Scanner fileReader = new Scanner(f);
			Scanner fileReader2 = new Scanner(f2);
			while (fileReader.hasNext()) {
				forecastResponseBody += fileReader.nextLine();
			}
			while (fileReader2.hasNext()) {
				forecastGridDataResponseBody += fileReader2.nextLine();
			}
			
			fileReader.close();
			fileReader2.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		
		
		forecastResponseBody = forecastResponseBody.replace("+", "");
		forecastResponseBody = forecastResponseBody.replace("\\n", "\n");
		forecastResponseBody = forecastResponseBody.replace("\\r", "\n");
		forecastResponseBody = forecastResponseBody.replace("\"", "");
		forecastResponseBody = forecastResponseBody.replace("\\", "\"");
		
		forecastGridDataResponseBody = forecastGridDataResponseBody.replace("\\n", "");
		forecastGridDataResponseBody = forecastGridDataResponseBody.replace("\\r", "");
		forecastGridDataResponseBody = forecastGridDataResponseBody.replace("+", "");
		forecastGridDataResponseBody = forecastGridDataResponseBody.replace("\"", "");
		forecastGridDataResponseBody = forecastGridDataResponseBody.replace("\\", "\"");
		
		//System.out.println(forecastResponseBody);
		//get the weather from known data
		JSONInputOutput jio = new JSONInputOutput();
		ArrayList<DailyForecast> fivedays = jio.parseNWSForecast(forecastResponseBody, forecastGridDataResponseBody);
		
		//Test that the day of the week is computed correct
		assertEquals(fivedays.get(0).getDayOfWeek(),"WEDNESDAY");
		assertEquals(fivedays.get(1).getDayOfWeek(),"THURSDAY");
		assertEquals(fivedays.get(2).getDayOfWeek(),"FRIDAY");
		assertEquals(fivedays.get(3).getDayOfWeek(),"SATURDAY");
		assertEquals(fivedays.get(4).getDayOfWeek(),"SUNDAY");
		
		//cehck max temp is computed correct
		assertEquals(fivedays.get(0).getTemperatureMax(),72);
		assertEquals(fivedays.get(1).getTemperatureMax(),38);
		assertEquals(fivedays.get(2).getTemperatureMax(),41);
		assertEquals(fivedays.get(3).getTemperatureMax(),59);
		assertEquals(fivedays.get(4).getTemperatureMax(),69);
		assertEquals(fivedays.get(5).getTemperatureMax(),71);
	
		//check min temp is computed correct
		assertEquals(fivedays.get(0).getTemperatureMin(),30);
		assertEquals(fivedays.get(1).getTemperatureMin(),16);
		assertEquals(fivedays.get(2).getTemperatureMin(),26);
		assertEquals(fivedays.get(3).getTemperatureMin(),33);
		assertEquals(fivedays.get(4).getTemperatureMin(),36);
		assertEquals(fivedays.get(5).getTemperatureMin(),39);
		
		//check precipChance is computed correct
		assertEquals(6, fivedays.get(0).getPrecipChanceN());
		assertEquals(40, fivedays.get(1).getPrecipChanceN());
		assertEquals(8, fivedays.get(2).getPrecipChanceN());
		assertEquals(7, fivedays.get(3).getPrecipChanceN());
		assertEquals(3, fivedays.get(4).getPrecipChanceN());
		assertEquals(1, fivedays.get(5).getPrecipChanceN());
		assertEquals(8, fivedays.get(1).getPrecipChanceD());
		assertEquals(24, fivedays.get(2).getPrecipChanceD());
		assertEquals(6, fivedays.get(3).getPrecipChanceD());
		assertEquals(1, fivedays.get(4).getPrecipChanceD());
		assertEquals(1, fivedays.get(5).getPrecipChanceD());
		assertEquals(989, fivedays.get(0).getPrecipChanceD());
		
		//check cloudCover is computed correct
		assertEquals(989, fivedays.get(0).getCloudCoverD());
		assertEquals(74, fivedays.get(1).getCloudCoverD());
		assertEquals(71, fivedays.get(2).getCloudCoverD());
		assertEquals(41, fivedays.get(3).getCloudCoverD());
		assertEquals(38, fivedays.get(4).getCloudCoverD());
		
		assertEquals(61, fivedays.get(0).getCloudCoverN());
		assertEquals(85, fivedays.get(1).getCloudCoverN());
		assertEquals(49, fivedays.get(2).getCloudCoverN());
		assertEquals(48, fivedays.get(3).getCloudCoverN());
		assertEquals(41, fivedays.get(4).getCloudCoverN());

		//check qpf is computed correct
		assertEquals(0, fivedays.get(0).getQpfN());
		assertEquals(.16999999999999998, fivedays.get(1).getQpfN());
		assertEquals(0, fivedays.get(2).getQpfN());
		assertEquals(0, fivedays.get(3).getQpfN());
		assertEquals(0, fivedays.get(4).getQpfN());
		assertEquals(0, fivedays.get(5).getQpfN());
		
		assertEquals(989, fivedays.get(0).getQpfD());
		assertEquals(0, fivedays.get(1).getQpfD());
		assertEquals(.01, fivedays.get(2).getQpfD());
		assertEquals(0, fivedays.get(3).getQpfD());
		assertEquals(0, fivedays.get(4).getQpfD());
		assertEquals(0, fivedays.get(5).getQpfD());
	}

}
