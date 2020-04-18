package application;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		String weatherInfo = "Here are the best days to go " + chosenActivity.getActivityType() + "\n";
		for (int i = 1; i <= 5; i++) {
			weatherInfo += weatherOutputList.get(i).getDayOfWeek() + weatherOutputList.get(i).getDate() + 
					"There will be a high temperature of " + weatherOutputList.get(i).getTemperatureMax() + 
					"and a low of " + weatherOutputList.get(i).getTemperatureMin() + 
					"Rain: " + weatherOutputList.get(i).getPrecipChanceN() +"% / Inches: " + weatherOutputList.get(i).getQpfN() +
					"Snow: " + weatherOutputList.get(i).getQpfSnowN() + " inches." +
					"Cloud cover: " + weatherOutputList.get(i).getCloudCoverN() + "%" +
					"Heat Index: " + weatherOutputList.get(i).getTemperatureHeatIndexN() +
					"F / Wind Chill: " + weatherOutputList.get(i).getTemperatureWindChillN() + "F" +
					"Wind: " + weatherOutputList.get(i).getWindPhraseN();          

		}
		writeWeatherInfoToFile(weatherInfo);
	}

	private void writeWeatherInfoToFile(String weatherInfo){


		File f = new File("WeatherInfo.txt");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("File not created.");
			e1.printStackTrace();
		}
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(weatherInfo);
			pw.flush();

			pw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

