package com.bucs.virtualmuseumcurator;

import org.json.JSONException;
import org.json.JSONObject;

import com.bucs.virtualmuseumcurator.datamodel.MuseumHomePageData;

/**
 * @author chethan
 *
 */
public class JsonMuseumHomePage {
	private String JsonString;

	public JsonMuseumHomePage(String jsonString) {
		// TODO Auto-generated constructor stub
		JsonString = jsonString;
	}

    private static JSONObject getObject(String name,JSONObject jobj)
    		throws JSONException {
        JSONObject subObj = jobj.getJSONObject(name);
        return subObj;
    }
    private static String getString(String name, JSONObject jObj) throws JSONException {
        return jObj.getString(name);
    }
     
    private static float  getFloat(String name, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(name);
    }
     
    private static int  getInt(String name, JSONObject jObj) throws JSONException {
        return jObj.getInt(name);
    }
    
    
    public MuseumHomePageData JsonParse()
    {
    	
    	
    	try {
    		
    		MuseumHomePageData frontpage=new MuseumHomePageData();
			JSONObject jObj = new JSONObject(this.JsonString);
			frontpage.setMuseumName(getString("name",jObj));
			frontpage.setLocation(getString("location",jObj));
			frontpage.setHour(getString("hours",jObj));
			return frontpage;
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    	
    	/*List loc = new Location();
    	 
    	JSONObject coordObj = getObject("coord", jObj);
    	loc.setLatitude(getFloat("lat", coordObj));
    	loc.setLongitude(getFloat("lon", coordObj));
    	 
    	JSONObject sysObj = getObject("sys", jObj);
    	loc.setCountry(getString("country", sysObj));
    	loc.setSunrise(getInt("sunrise", sysObj));
    	loc.setSunset(getInt("sunset", sysObj));
    	loc.setCity(getString("name", jObj));
    	weather.location = loc;
    	
    	
    	
    	
    	
    	// We use only the first value
    	JSONObject JSONWeather = jArr.getJSONObject(0);
    	weather.currentCondition.setWeatherId(getInt("id", JSONWeather));
    	weather.currentCondition.setDescr(getString("description", JSONWeather));
    	weather.currentCondition.setCondition(getString("main", JSONWeather));
    	weather.currentCondition.setIcon(getString("icon", JSONWeather));
    	 
    	JSONObject mainObj = getObject("main", jObj);
    	weather.currentCondition.setHumidity(getInt("humidity", mainObj));
    	weather.currentCondition.setPressure(getInt("pressure", mainObj));
    	weather.temperature.setMaxTemp(getFloat("temp_max", mainObj));
    	weather.temperature.setMinTemp(getFloat("temp_min", mainObj));
    	weather.temperature.setTemp(getFloat("temp", mainObj));
    	 
    	// Wind
    	JSONObject wObj = getObject("wind", jObj);
    	weather.wind.setSpeed(getFloat("speed", wObj));
    	weather.wind.setDeg(getFloat("deg", wObj));
    	 
    	// Clouds
    	JSONObject cObj = getObject("clouds", jObj);
    	weather.clouds.setPerc(getInt("all", cObj));
    	
    	*{
  "coord":{"lon":12.4958,"lat":41.903},
  "sys":{"country":"Italy","sunrise":1369107818,"sunset":1369160979},
  "weather":[{
        "id":802,"main":"Clouds","description":"scattered clouds",
         "icon":"03d"}],
   "base":"global stations",
   "main":{
           "temp":290.38,
           "humidity":68,
           "pressure":1015,
           "temp_min":287.04,
           "temp_max":293.71},
    "wind":{ 
           "speed":1.75,
           "deg":290.002},
    "clouds":{"all":32},
    "dt":1369122932,
    "id":3169070,
    "name":"Rome",
    "cod":200
}
    	*
    	*
    	*
    	*
    	*
    	*/
    }
    


}
