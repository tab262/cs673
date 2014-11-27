package com.bucs.virtualmuseumcurator.collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.bucs.virtualmuseumcurator.datamodel.CollectionPageData;
import com.bucs.virtualmuseumcurator.datamodel.EdocentSplashPageData;

public class JsonCollectionPage {
	
	
	private String JsonString;

	public JsonCollectionPage(String jsonString) {
		// TODO Auto-generated constructor stub
		JsonString = jsonString;
	}

    private static JSONObject getObject(String name,JSONObject jobj)
    		throws JSONException {
        JSONObject subObj = jobj.getJSONObject(name);
        return subObj;
    }
    private static String getString(String name, JSONObject jObj) throws JSONException {
    	Log.d("in fn String", "in fn String");
    	return (String)jObj.getString(name);
    }
     
    private static float  getFloat(String name, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(name);
    }
     
    private static int  getInt(String name, JSONObject jObj) throws JSONException {
        return (int)jObj.getInt(name);
    }
    
    
    public CollectionPageData JsonParse()
    {
    	CollectionPageData data=new CollectionPageData();
    	  
    	try {
    		
    		
			JSONArray jArr2= new JSONArray(this.JsonString);
			
			String[] museums=JsonString.split(",");
			
		     Log.d("json output", jArr2.toString());
			//Log.d("after fn String1",data.getMusuems().toString() );
			//Log.d("after fn String2",data.getPrKey().toString() );
			
			Log.d("after loop", "after loop");
	
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
    	

    }

}
