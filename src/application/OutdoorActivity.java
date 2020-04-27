package application;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for knowing activities and knowing parameters <p>
 * It does not collaborate with any other classes
 * @author Cayde.Roothoff
 *
 */
public class OutdoorActivity {
	public static Integer bestTemperatureMax = 0;
	public static Integer bestTemperatureMin = 0;

	// day part
	public static Integer bestPrecipChance = 0;
	public static Integer bestCloudCover = 0;
	public static Double bestQpf = 0.0;
	public static Double bestQpfSnow = 0.0;
	public static Integer bestTemperatureHeatIndex = 0;
	public static Integer bestTemperatureWindChill = 0;
	public static String bestWindPhrase = "";
	public static String activityType = "";
	
	

	
	public static Integer getBestTemperatureMax() {
		return bestTemperatureMax;
	}

	public static Integer getBestTemperatureMin() {
		return bestTemperatureMin;
	}

	public static Integer getBestPrecipChance() {
		return bestPrecipChance;
	}

	public static Integer getBestCloudCover() {
		return bestCloudCover;
	}

	public static Double getBestQpf() {
		return bestQpf;
	}

	public static Double getBestQpfSnow() {
		return bestQpfSnow;
	}

	public static Integer getBestTemperatureHeatIndex() {
		return bestTemperatureHeatIndex;
	}

	public static Integer getBestTemperatureWindChill() {
		return bestTemperatureWindChill;
	}

	public static String getBestWindPhrase() {
		return bestWindPhrase;
	}
	public static String getActivityType() {
		return activityType;
	}
	
	public static void setBestTemperatureMax(Integer bestTemperatureMax) {
		OutdoorActivity.bestTemperatureMax = bestTemperatureMax;
	}

	public static void setBestTemperatureMin(Integer bestTemperatureMin) {
		OutdoorActivity.bestTemperatureMin = bestTemperatureMin;
	}

	public static void setBestPrecipChance(Integer bestPrecipChance) {
		OutdoorActivity.bestPrecipChance = bestPrecipChance;
	}

	public static void setBestCloudCover(Integer bestCloudCover) {
		OutdoorActivity.bestCloudCover = bestCloudCover;
	}

	public static void setBestQpf(Double bestQpf) {
		OutdoorActivity.bestQpf = bestQpf;
	}

	public static void setBestQpfSnow(Double bestQpfSnow) {
		OutdoorActivity.bestQpfSnow = bestQpfSnow;
	}

	public static void setBestTemperatureHeatIndex(Integer bestTemperatureHeatIndex) {
		OutdoorActivity.bestTemperatureHeatIndex = bestTemperatureHeatIndex;
	}

	public static void setBestTemperatureWindChill(Integer bestTemperatureWindChill) {
		OutdoorActivity.bestTemperatureWindChill = bestTemperatureWindChill;
	}

	public static void setBestWindPhrase(String bestWindPhrase) {
		OutdoorActivity.bestWindPhrase = bestWindPhrase;
	}
	
	public static void setActivityType(String activityType) {
		OutdoorActivity.activityType = activityType;
	}
	
	public static void sailing() {
		setBestTemperatureMax(75);
		setBestTemperatureMin(65);
		setBestPrecipChance(0);
		setBestCloudCover(3);
		setBestQpf(0.0);
		setBestQpfSnow(0.0);
		setBestTemperatureHeatIndex(78);
		setBestTemperatureWindChill(77);
		setActivityType("Sailing");
	}

	public static void hiking() {
		setBestTemperatureMax(68);
		setBestTemperatureMin(60);
		setBestPrecipChance(0);
		setBestCloudCover(4);
		setBestQpf(0.0);
		setBestQpfSnow(0.0);
		setBestTemperatureHeatIndex(70);
		setBestTemperatureWindChill(70);
		setActivityType("Hiking");
	}
	
	public static void climbing() {
		setBestTemperatureMax(72);
		setBestTemperatureMin(65);
		setBestPrecipChance(0);
		setBestCloudCover(1);
		setBestQpf(0.0);
		setBestQpfSnow(0.0);
		setBestTemperatureHeatIndex(74);
		setBestTemperatureWindChill(73);
		setActivityType("Climbing");
	}

	public static void skiing() {
		setBestTemperatureMax(25);
		setBestTemperatureMin(20);
		setBestPrecipChance(0);
		setBestCloudCover(3);
		setBestQpf(0.0);
		setBestQpfSnow(0.25);
		setBestTemperatureHeatIndex(25);
		setBestTemperatureWindChill(25);
		setActivityType("Skiing");
	}

	public static void biking() {
		setBestTemperatureMax(72);
		setBestTemperatureMin(65);
		setBestPrecipChance(0);
		setBestCloudCover(3);
		setBestQpf(0.0);
		setBestQpfSnow(0.0);
		setBestTemperatureHeatIndex(74);
		setBestTemperatureWindChill(72);
		setActivityType("Biking");

	}
	

}
