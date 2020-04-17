package application;


import java.util.ArrayList;

/**
 * Outputs the weather information into txt file
 * @author Cayde.Roothoff
 *
 */
public class WeatherInfoOutput {
	
	private ArrayList<DailyForecast> weatherOutputList;
	OutdoorActivity chosenActivity;
	
	public WeatherInfoOutput(ArrayList<DailyForecast> weatherOutputList, OutdoorActivity chosenActivity) {
		this.weatherOutputList = weatherOutputList;
		this.chosenActivity = chosenActivity;
	}
	
	public void outputWeatherInfo() {
		RankForecast rank = new RankForecast(weatherOutputList, chosenActivity);
		this.weatherOutputList = rank.getRankedList();
		System.out.println("Here are the best days to go " + chosenActivity.getActivityType());
	}
	
}

