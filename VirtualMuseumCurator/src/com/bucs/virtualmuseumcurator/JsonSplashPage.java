package com.bucs.virtualmuseumcurator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.bucs.virtualmuseumcurator.datamodel.EdocentSplashPageData;


public class JsonSplashPage {
	private String JsonString;

	public JsonSplashPage(String jsonString) {
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
    
    
    public EdocentSplashPageData JsonParse()
    {
    	EdocentSplashPageData data=new EdocentSplashPageData();
    	String mN;
    	String[] pK;
    	  
    	try {
    		
    		
			JSONArray jArr2= new JSONArray(this.JsonString);
			
			String[] museums=JsonString.split(",");
			
			for( int i=0; i< museums.length;i++)
			{
				Log.d("before String", "before String");
				mN=museums[i];
				pK=mN.split(":");
				int startindex=pK[0].indexOf("'");
				int endindex=pK[0].length();
				data.getMusuems().add(pK[0].substring(startindex,endindex));
				data.getPrKey().add(pK[1]);
				
				
			}
			Log.d("after fn String1",data.getMusuems().toString() );
			Log.d("after fn String2",data.getPrKey().toString() );
			
			Log.d("after loop", "after loop");
	
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
    	

    }
    


}
