package application;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for knowing activities and knowing parameters
 * It does not collaborate with any other classes
 * @author Cayde.Roothoff
 *
 */
public class OutdoorActivity {
	private Integer bestTemperatureMax;
	private Integer bestTemperatureMin;

	// day part
	private Integer bestPrecipChance;
	private Integer bestCloudCover;
	private Double bestQpf;
	private Double bestQpfSnow;
	private Integer bestTemperatureHeatIndex;
	private Integer bestTemperatureWindChill;
	private String bestWindPhrase;
	private String activityType;
	public ArrayList<String> outdoorActivityCandidateList = new ArrayList<String>();

	public OutdoorActivity() {

		this.bestTemperatureMax = 0;
		this.bestTemperatureMin = 0;

		// day part
		this.bestPrecipChance = 0;
		this.bestCloudCover = 0;
		this.bestQpf = 0.0;
		this.bestQpfSnow = 0.0;
		this.bestTemperatureHeatIndex = 0;
		this.bestTemperatureWindChill = 0;
		this.bestWindPhrase = "";
		this.outdoorActivityCandidateList.add("Sailing");
		this.outdoorActivityCandidateList.add("Hiking");
		this.outdoorActivityCandidateList.add("Climbing");
		this.outdoorActivityCandidateList.add("Skiing");
		this.outdoorActivityCandidateList.add("Biking");
	}
	
	public Integer getBestTemperatureMax() {
		return bestTemperatureMax;
	}

	public Integer getBestTemperatureMin() {
		return bestTemperatureMin;
	}

	public Integer getBestPrecipChance() {
		return bestPrecipChance;
	}

	public Integer getBestCloudCover() {
		return bestCloudCover;
	}

	public Double getBestQpf() {
		return bestQpf;
	}

	public Double getBestQpfSnow() {
		return bestQpfSnow;
	}

	public Integer getBestTemperatureHeatIndex() {
		return bestTemperatureHeatIndex;
	}

	public Integer getBestTemperatureWindChill() {
		return bestTemperatureWindChill;
	}

	public String getBestWindPhrase() {
		return bestWindPhrase;
	}
	public String getActivityType() {
		return activityType;
	}

	/**
	 * selectActivity sets the parameters for the chosen activity. This method does not return anything.
	 * @param activity
	 */
	public void selectActivity(String activity) {
		if (activity == "sailing") {
			this.bestTemperatureMax = 75;
			this.bestTemperatureMin = 65;

			// day part
			this.bestPrecipChance = 0;
			this.bestCloudCover = 3;
			this.bestQpf = 0.0;
			this.bestQpfSnow = 0.0;
			this.bestTemperatureHeatIndex = 78;
			this.bestTemperatureWindChill = 77;
			this.bestWindPhrase = "";
			this.activityType = "Sailing";
		}
		else if (activity == "hiking") {
			this.bestTemperatureMax = 68;
			this.bestTemperatureMin = 60;

			// day part
			this.bestPrecipChance = 0;
			this.bestCloudCover = 4;
			this.bestQpf = 0.0;
			this.bestQpfSnow = 0.0;
			this.bestTemperatureHeatIndex = 70;
			this.bestTemperatureWindChill = 70;
			this.bestWindPhrase = "";
			this.activityType = "Hiking";
		}
		else if (activity == "climbing") {
			this.bestTemperatureMax = 72;
			this.bestTemperatureMin = 65;

			// day part
			this.bestPrecipChance = 0;
			this.bestCloudCover = 1;
			this.bestQpf = 0.0;
			this.bestQpfSnow = 0.0;
			this.bestTemperatureHeatIndex = 74;
			this.bestTemperatureWindChill = 73;
			this.bestWindPhrase = "";
			this.activityType = "Climbing";
		}
		else if (activity == "skiing") {
			this.bestTemperatureMax = 25;
			this.bestTemperatureMin = 20;

			// day part
			this.bestPrecipChance = 0;
			this.bestCloudCover = 3;
			this.bestQpf = 0.0;
			this.bestQpfSnow = 0.25;
			this.bestTemperatureHeatIndex = 25;
			this.bestTemperatureWindChill = 25;
			this.bestWindPhrase = "";
			this.activityType = "Skiing";
		}
		else if (activity == "biking") {
			this.bestTemperatureMax = 72;
			this.bestTemperatureMin = 65;

			// day part
			this.bestPrecipChance = 0;
			this.bestCloudCover = 3;
			this.bestQpf = 0.0;
			this.bestQpfSnow = 0.0;
			this.bestTemperatureHeatIndex = 74;
			this.bestTemperatureWindChill = 72;
			this.bestWindPhrase = "";
			this.activityType = "Biking";
		}
	}
}
