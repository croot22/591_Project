package application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * This class is responsible for knowing forecasts, knowing activities, and ranking days/times by chosen activities
 * It collaborates with the Activity, WeatherUndergroundAPI, and NWSAPI classes
 * @author Cayde.Roothoff
 *
 */
public class RankForecast{
	private ArrayList<DailyForecast> weatherList = new ArrayList<DailyForecast>();
	private String chosenOutdoorActivity = new String();
	private OutdoorActivity activity = new OutdoorActivity();


	public RankForecast(ArrayList<DailyForecast> weatherList, String activity) {
		this.weatherList = weatherList;
		this.chosenOutdoorActivity = activity;
	}
	
	public ArrayList<DailyForecast> getRankedList() {
		return weatherList;
	}
	
	
	private void setActivityParameters() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = OutdoorActivity.class.getDeclaredMethod(chosenOutdoorActivity.toLowerCase());
		method.invoke(this.activity, null);
	}
	

	/**
	 * This method takes in the class array list and returns a new ranked list according to the activity parameters.
	 * @param null
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 *
	 */
	private void rankItems() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{

		ArrayList<DailyForecast> rankedList = new ArrayList<DailyForecast>();
		setActivityParameters();
		for(DailyForecast day: this.weatherList) {
			calculateAverage(day);
			rankedList.add(day);
		}
		rankedList.sort(null);
		Collections.copy(weatherList, rankedList);
	}

	/**
	 * This method calculates the average difference between weather variables from the chosen activity parameters
	 * @param day
	 */
	private void calculateAverage(DailyForecast day) {
		Integer diffTemperatureMax;
		Integer diffTemperatureMin;

		// day part
		int diffPrecipChance;
		int diffCloudCover;
		Double diffQpf;
		Double diffQpfSnow;
		int diffTemperatureHeatIndex;
		int diffTemperatureWindChill;
		String diffWindPhrase;

		Double dayAverage = 0.0;
		Double lowAverage = 0.0;


		diffTemperatureMax = Math.abs(day.getTemperatureMax() - activity.getBestTemperatureMax());
		diffTemperatureMin = Math.abs(day.getTemperatureMin() - activity.getBestTemperatureMin());

		// day part
		diffPrecipChance = Math.abs(day.getPrecipChanceD() - activity.getBestPrecipChance());
		diffCloudCover = Math.abs(day.getCloudCoverD() - activity.getBestCloudCover());
		diffQpf = Math.abs(day.getQpfD() - activity.getBestQpf()) * 10;
		diffQpfSnow = Math.abs(day.getQpfSnowD() - activity.getBestQpfSnow()) * 10;
		diffTemperatureHeatIndex = Math.abs(day.getTemperatureHeatIndexD() - activity.getBestTemperatureHeatIndex());
		diffTemperatureWindChill = Math.abs(day.getTemperatureWindChillD() - activity.getBestTemperatureWindChill());
		//diffWindPhrase = Math.abs(day.getWindPhraseD() - activity.getBestWindPhrase());

		dayAverage = (diffTemperatureMax + diffTemperatureMin + diffPrecipChance + diffCloudCover + diffQpf + diffQpfSnow + diffTemperatureHeatIndex + diffTemperatureWindChill) / 8;
		day.setAverage(dayAverage);

	}

	/**
	 * This method creates and returns a text variable that includes the weather information for a specific day
	 * @return Text
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public Text rankListPrint() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		rankItems();
		String rankedWeatherInfoString = new String();

		for (int i = 0; i < 3; i++) {
			int j = i + 1;
			rankedWeatherInfoString += j + ". \n" +
					weatherList.get(i).getDayOfWeek() + " " + weatherList.get(i).getDate() + "\n" +
					"There will be a high temperature of " + weatherList.get(i).getTemperatureMax() + "\n" +
					" and a low of " + weatherList.get(i).getTemperatureMin() + "\n" +
					"Rain: " + weatherList.get(i).getPrecipChanceN() +"% / Inches: " + weatherList.get(i).getQpfN() + "\n" +
					"Snow: " + weatherList.get(i).getQpfSnowN() + " inches \n" +
					"Cloud cover: " + weatherList.get(i).getCloudCoverN() + "% \n" +
					"Heat Index: " + weatherList.get(i).getTemperatureHeatIndexN() + "\n" +
					"F / Wind Chill: " + weatherList.get(i).getTemperatureWindChillN() + "F \n" +
					"Wind: " + weatherList.get(i).getWindPhraseN() + "\n";

		}
		Text rankedWeatherInfo = new Text(rankedWeatherInfoString);
		rankedWeatherInfo.setFont(Font.font("Bookman Old Style Bold",FontWeight.NORMAL, FontPosture.REGULAR, 10.0));
		return rankedWeatherInfo;
	}
}

