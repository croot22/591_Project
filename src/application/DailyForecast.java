package application;

/**
 * Class to hold the weather forecast data in a
 * DailyForecast object.
 * The first 6 instance variables are for data points that apply
 * to an entire day's forecast.
 * The remaining 18 instance variables are to hold 9 additional
 * data points that are divided between Day and Night; to match
 * the format of how the data is provided by the APIs.
 */

public class DailyForecast implements Comparable{
	private String weatherService; // added to distinguish the weatherService from which data was obtained.
    private String date;
	private String dayOfWeek;
	private String narrative;
	private Integer temperatureMax;
	private Integer temperatureMin;

	// Data points provided twice per day
	// D - Day; N - Night
	private String daypartNameD;
	private String daypartNameN;
	private String narrativeD;
	private String narrativeN;
	private Integer precipChanceD;
	private Integer precipChanceN;
	private Integer cloudCoverD;
	private Integer cloudCoverN;
	private Double qpfD;
	private Double qpfN;
	private Double qpfSnowD;
	private Double qpfSnowN;
	private Integer temperatureHeatIndexD;
	private Integer temperatureHeatIndexN;
	private Integer temperatureWindChillD;
	private Integer temperatureWindChillN;
	private String windPhraseD;
	private String windPhraseN;
	
	// Variable to help with the compareTo override method
	private Double average;

	// No parameter constructor for easier calling of methods in this class.
	public DailyForecast() {
	}

	/**
	 * Full constructor method for creating DailyForecast objects.    
	 * @param weatherService - The provider of the weather forecast - ex. National Weather Service
	 * @param date - Date of forecast
	 * @param dayOfWeek - The day of week name - ex. Monday, Tuesday, etc
	 * @param narrative - A human readable narrative of the days forecast
	 * @param temperatureMax - Daily maximum temperature
	 * @param temperatureMin - Daily minimum temperature
	 * @param daypartNameD - Day part name (for "Day" portion) ex. Today, Tomorrow
	 * @param daypartNameN - Night part name (for "Night" portion) ex. Tonight, Tomorrow Night
	 * @param narrativeD - Day part - human readable narrative for partial day
	 * @param narrativeN - Night part - human readable narrative for partial day
	 * @param precipChanceD - Day part - integer type, percentage value for probability of precipitation
	 * @param precipChanceN - Night part - integer type, percentage value for probability of precipitation
	 * @param cloudCoverD - Day part - average cloud cover expressed as a percentage
	 * @param cloudCoverN - Night part - average cloud cover expressed as a percentage
	 * @param precipTypeD - Day part - string of precipitation type ex. rain, snow
	 * @param precipTypeN - Night part - string of precipitation type ex. rain, snow
	 * @param qpfD - Day part - forecasted measurable precipitation (liquid or liquid equivalent)
	 * @param qpfN - Night part - forecasted measurable precipitation (liquid or liquid equivalent)
	 * @param qpfSnowD - Day part - forecasted measurable precipitation as snow
	 * @param qpfSnowN - Night part - forecasted measurable precipitation as snow
	 * @param temperatureHeatIndexD - Day part - air temperature feel on human skin
	 * @param temperatureHeatIndexN - Night part - air temperature feel on human skin
	 * @param temperatureWindChillD - Day part - - air temperature feel on human skin
	 * @param temperatureWindChillN - Night part - air temperature feel on human skin
	 * @param windPhraseD - Day part - human readable phrase that describes the wind direction and speed
	 * @param windPhraseN - Night part - human readable phrase that describes the wind direction and speed
	 */
	public DailyForecast(String weatherService, String date, String dayOfWeek, String narrative, Integer temperatureMax, 
			Integer temperatureMin, String daypartNameD, String daypartNameN, String narrativeD, 
			String narrativeN, Integer precipChanceD, Integer precipChanceN, Integer cloudCoverD, 
			Integer cloudCoverN, String precipTypeD, String precipTypeN, Double qpfD, Double qpfN,
			Double qpfSnowD, Double qpfSnowN, Integer temperatureHeatIndexD, Integer temperatureHeatIndexN,
			Integer temperatureWindChillD, Integer temperatureWindChillN, String windPhraseD, String windPhraseN) {


		this.weatherService = weatherService;
	    this.date = date;
		this.dayOfWeek = dayOfWeek;
		this.narrative = narrative;
		this.temperatureMax = temperatureMax;
		this.temperatureMin = temperatureMin;

		this.daypartNameD = daypartNameD;
		this.daypartNameN = daypartNameN;
		this.narrativeD = narrativeD;
		this.narrativeN = narrativeN;
		this.precipChanceD = precipChanceD;
		this.precipChanceN = precipChanceN;
		this.cloudCoverD = cloudCoverD;
		this.cloudCoverN = cloudCoverN;
		this.qpfD = qpfD;
		this.qpfN = qpfN;
		this.qpfSnowD = qpfSnowD;
		this.qpfSnowN = qpfSnowN;
		this.temperatureHeatIndexD = temperatureHeatIndexD;
		this.temperatureHeatIndexN = temperatureHeatIndexN;
		this.temperatureWindChillD = temperatureWindChillD;
		this.temperatureWindChillN = temperatureWindChillN;
		this.windPhraseD = windPhraseD;
		this.windPhraseN = windPhraseN;
		this.average = 0.0;
	}


    public void setAverage(Double average) {
        this.average = average;
    }
    
    @Override
    public int compareTo(Object otherDay) {
        DailyForecast myOtherDay = (DailyForecast) otherDay;
        if (myOtherDay.getAverage() == average) return 0;
        if (average - myOtherDay.getAverage() < 0) return -1;
        return 1;
    }

	
	public String getWeatherService() {
	    return weatherService;
	}

	public String getDate() {
		return date;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public String getNarrative() {
		return narrative;
	}

	public Integer getTemperatureMax() {
		return temperatureMax;
	}

	public Integer getTemperatureMin() {
		return temperatureMin;
	}

	public String getDaypartNameD() {
		return daypartNameD;
	}

	public String getDaypartNameN() {
		return daypartNameN;
	}

	public String getNarrativeD() {
		return narrativeD;
	}

	public String getNarrativeN() {
		return narrativeN;
	}

	public Integer getPrecipChanceD() {
		return precipChanceD;
	}

	public Integer getPrecipChanceN() {
		return precipChanceN;
	}

	public Integer getCloudCoverD() {
		return cloudCoverD;
	}

	public Integer getCloudCoverN() {
		return cloudCoverN;
	}

	public Double getQpfD() {
		return qpfD;
	}

	public Double getQpfN() {
		return qpfN;
	}

	public Double getQpfSnowD() {
		return qpfSnowD;
	}

	public Double getQpfSnowN() {
		return qpfSnowN;
	}

	public Integer getTemperatureHeatIndexD() {
		return temperatureHeatIndexD;
	}

	public Integer getTemperatureHeatIndexN() {
		return temperatureHeatIndexN;
	}

	public Integer getTemperatureWindChillD() {
		return temperatureWindChillD;
	}

	public Integer getTemperatureWindChillN() {
		return temperatureWindChillN;
	}

	public String getWindPhraseD() {
		return windPhraseD;
	}

	public String getWindPhraseN() {
		return windPhraseN;
	}

	public Double getAverage() {
		return average;
	}


	
}

