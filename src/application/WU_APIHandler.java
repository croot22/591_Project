package application;
//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WU_APIHandler {
    
    /**
     * Methods sets up the URL needed to make a request to the WeatherUnderground
     * API for the 5-day forecast for a specified location.
     * @param coordinates (String) - Takes in a specified format of the coordinates
     * for a location that a forecast is requested.
     * @return (String) JSON style string returned by API with the forecast data
     * @throws IOException
     */
    public String makeAPICall(String coordinates) throws IOException {
        String endPoint = "https://api.weather.com";
        String path = "/v3/wx/forecast/daily/5day";
        //String geocode = "39.717,-104.9";
        String geocode = coordinates;
        String apiKey = "a5951eae4c0f4fb5951eae4c0f7fb544";
        String queryParams = "?geocode=" + geocode + "&units=e&language=en-US&format=json&apiKey=" + apiKey; 
        // Puts all the necessary parts of the URL together to make the API call.
        String fiveDayForecastURL = endPoint + path + queryParams;  
        // Passed the URL to the 'makeRequest' method and returns the forecast string.
        String WUndergroundURLResponse = GetResponseFromURL.makeRequest(fiveDayForecastURL);
        
        return WUndergroundURLResponse; 
        
    }
    
//    public ArrayList<String> jArrayHelper (JSONArray jsonArray) {
//        ArrayList<String> arrName = new ArrayList<String>();
//        
//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                arrName.add(jsonArray.get(i).toString());
//                //System.out.println(jsonArray.get(i).toString());
//                
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            
//        }
//        
//        return arrName;
//        
//    }
    
    
    /**
     * Method to parse the Weather Underground JSON response string into the DailyForecast Class.
     * @param jsonResponse (String) - Takes in the JSON String returned from the API call
     * @return Array of DailyForecast objects.
     * @throws JSONException
     */
    public ArrayList<DailyForecast> parseWUndergroundJSONForecast(String jsonResponse) throws JSONException {
        //create a JSON object with the String response
        JSONObject jObj = new JSONObject(jsonResponse);

        // These data points are one per day in the JSON object
        JSONArray dateJA = jObj.getJSONArray("validTimeLocal");  // collecting to strip out the date
        JSONArray dayOfWeekJA = jObj.getJSONArray("dayOfWeek");
        JSONArray narrativeJA = jObj.getJSONArray("narrative");
        JSONArray maxTempJA = jObj.getJSONArray("temperatureMax");
        JSONArray minTempJA = jObj.getJSONArray("temperatureMin");

        // These data points are twice per day, Day and Night.
        // They are also within a nested JSON object. Creating this variable to shorten code below
        JSONObject daypartObj = jObj.getJSONArray("daypart").getJSONObject(0); 

        JSONArray dayPartNameJA = daypartObj.getJSONArray("daypartName");
        JSONArray narrativePartDayJA = daypartObj.getJSONArray("narrative");
        JSONArray precipChanceJA = daypartObj.getJSONArray("precipChance");
        JSONArray cloudCoverJA = daypartObj.getJSONArray("cloudCover");  
        JSONArray precipTypeJA = daypartObj.getJSONArray("precipType");
        JSONArray qpfRainJA = daypartObj.getJSONArray("qpf"); 
        JSONArray qpfSnowJA = daypartObj.getJSONArray("qpfSnow"); 
        JSONArray heatIndexJA = daypartObj.getJSONArray("temperatureHeatIndex"); 
        JSONArray windChillJA = daypartObj.getJSONArray("temperatureWindChill"); 
        JSONArray windPhraseJA = daypartObj.getJSONArray("windPhrase"); 

        // ArrayList to hold the 'DailyForecast' objects
        ArrayList<DailyForecast> forecastsArray = new ArrayList<DailyForecast>();

        /*
         * Loop to set each day's forecast returned in the JSON
         * to a 'DailyForecast' object. Each data point is assigned to a 
         * variable, then the variables are passed into the 'DailyForecast'
         * constructor to create a new object for each day.
         */
        int j = 0; // needed for day part data points.
        int k = 1;
        for(int i = 0; i < dayOfWeekJA.length(); i++) {

            // There is no separate date field, so stripping date from the 'validTimeLocal' data point
            String tempDate = dateJA.getString(i); 
            String date = tempDate.substring(0, 10); 

            String dayOfWeek = dayOfWeekJA.getString(i);
            String narrative = narrativeJA.getString(i);
            String weatherService = "WUnderground";

            /*
             * Temperature max becomes 'null' after 3pm local time.
             * This is at a different time then the split between 
             * Day and Night, so needs to be handled separately from the
             * day parts. 
             * 
             * NULL HANDLING:
             * To avoid errors with a null value in the JSON data, a specific 
             * "non-sense" value is assigned to the variable instead of 'null'
             * These "non-sense" values are then dealt with later by catching these
             * specific non-sense values and then not presenting that to the user. 
             * 
             * For int/Integer the "non-sense" values is 989.
             * For String - the "non-sense" value is "XX"  
             */

            Integer tMax = 989;
            if (maxTempJA.isNull(i)) {
                tMax = 989;
            } else {tMax = maxTempJA.getInt(i);}

            Integer tMin = minTempJA.getInt(i);

            /*
             * For data points split between Day and Night
             * two variable are created to catch each value
             */
            String dayPartNameDay = "XX";
            String dayPartNameNight = "XX";
            String narrativeDay = "XX";
            String narrativeNight = "XX";
            Integer precipChanceDay = 989;
            Integer precipChanceNight = 989;  
            Integer cloudCoverDay = 989;
            Integer cloudCoverNight = 989;
            String precipTypeDay = "";
            String precipTypeNight = "";
            Double qpfRainDay = 989.0;
            Double qpfRainNight = 989.0;
            Double qpfSnowDay = 989.0;
            Double qpfSnowNight = 989.0;
            Integer heatIndexDay = 989;
            Integer heatIndexNight = 989;
            Integer windChillDay = 989;
            Integer windChillNight = 989;
            String windPhraseDay = "XX";
            String windPhraseNight = "XX";

            /*
             * Due to the data points have a Day and Night
             * value, a regular For Loop does not work properly.
             * To get the data into the right days, this series of 
             * If/Else statement were used to get the data into the 
             * correct days 'DailyForecast' object
             */
            // j = Day
            // k = Night
            // If the "day part name" is null, all Day part values will remain set at default value.
            if (dayPartNameJA.isNull(j)) { 

            }
            else {
                dayPartNameDay = dayPartNameJA.getString(j);
                narrativeDay = narrativePartDayJA.getString(j);
                precipChanceDay = precipChanceJA.getInt(j);
                cloudCoverDay = cloudCoverJA.getInt(j);
                precipTypeDay = precipTypeJA.getString(j);
                qpfRainDay = qpfRainJA.getDouble(j);
                qpfSnowDay = qpfSnowJA.getDouble(j);
                heatIndexDay = heatIndexJA.getInt(j);
                windPhraseDay = windPhraseJA.getString(j);
            }
            
            dayPartNameNight = dayPartNameJA.getString(k);
            narrativeNight = narrativePartDayJA.getString(k);
            precipChanceNight = precipChanceJA.getInt(k);
            cloudCoverNight = cloudCoverJA.getInt(k);
            precipTypeNight = precipTypeJA.getString(k);
            qpfRainNight = qpfRainJA.getDouble(k);
            qpfSnowNight = qpfSnowJA.getDouble(k);
            heatIndexNight = heatIndexJA.getInt(k);
            windChillNight = windChillJA.getInt(k);
            windPhraseNight = windPhraseJA.getString(k);

            j = j + 2;
            k = k + 2;
            /* 
             * Because there a once a day factors and twice a day factors, the looping needs three
             * iteration variables to get that right day in the correct object. 
             * Explained: For the first day the all day variable is (i=0), the Day part variable is (j=0), the Night part variable is (k=1), 
             * then for second day forecast the all day variable increase by 1 so (i=1), the Day part is (j=2), and the Night part is (k=3).
             * This continues as for each day, the all day variable has arrays of 5 data points while the "day part" arrays have 10 data points.
             * [i=2 , j=4, k=5], [i=3, j=6, k=7], [i=4 , j=8 , k=9], [i=5, j=10, k=11]
             */

            
            // Sets each day's data to a DailyForecast object using it constructor.
            DailyForecast dailyForecast = new DailyForecast(weatherService, date, dayOfWeek, narrative, tMax, tMin,
                    dayPartNameDay, dayPartNameNight, narrativeDay, narrativeNight, precipChanceDay, precipChanceNight, cloudCoverDay, cloudCoverNight,
                    precipTypeDay, precipTypeNight, qpfRainDay, qpfRainNight, qpfSnowDay, qpfSnowNight, 
                    heatIndexDay, heatIndexNight, windChillDay, windChillNight, windPhraseDay, windPhraseNight);
            
            // Adds each days DailyForecast object to an ArrayList
            forecastsArray.add(dailyForecast);
        }
        return forecastsArray; 
    }
}
