package com.bucs.virtualmuseumcurator;

import org.json.JSONException;
import org.json.JSONObject;

import com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel;

public class JsonArtPage {
	private String JsonString;

	public JsonArtPage(String jsonString) {
		JsonString = jsonString;
	}

    /*private static JSONObject getObject(String name,JSONObject jobj)
    		throws JSONException {
        JSONObject subObj = jobj.getJSONObject(name);
        return subObj;
    }*/
    private static String getString(String name, JSONObject jObj) throws JSONException {
        return jObj.getString(name);
    }
     
    /*private static float  getFloat(String name, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(name);
    }
     
    private static int  getInt(String name, JSONObject jObj) throws JSONException {
        return jObj.getInt(name);
    }*/
    
    
    public ArtInfoDataModel JsonParse()
    {
    	
    	
    	try {
    		
    		ArtInfoDataModel artpage=new ArtInfoDataModel();
			JSONObject jObj = new JSONObject(this.JsonString);
			artpage.setArtistName(getString("artist",jObj));
			artpage.setArtType(getString("artType",jObj));
			artpage.setMuseumName(getString("museumName",jObj));
			artpage.setDescription(getString("description",jObj));
			artpage.setTitle(getString("title",jObj));
			artpage.setCreationDate(getString("creationDate",jObj));
			return artpage;
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
    	
    }
    


}
