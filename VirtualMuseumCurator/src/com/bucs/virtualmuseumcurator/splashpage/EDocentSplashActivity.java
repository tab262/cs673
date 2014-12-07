package com.bucs.virtualmuseumcurator.splashpage;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.museumhome.MuseumDescActivity;


public class EDocentSplashActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{
	
	
	private com.bucs.virtualmuseumcurator.splashpage.MuseumSplashHttpClient httprequestsplash= new com.bucs.virtualmuseumcurator.splashpage.MuseumSplashHttpClient();
	private Spinner spinnerState,spinnerCity,spinnerMuseum;
	private Button submit;
	private String selectedState;
	private String selectedCity;
	private String selectedMuseum;
	private int selectedStateIndex;
	private int selectedCityIndex;
	private int selectedMuseumIndex;
    private List<String> states;
    private List<String> city;
    private List<String> Museums;
    private Activity context;
    private JSONObject LocationJson;
    private JSONObject justcurrentcityJson;
    private String primarykey;
 
   
    
    
    
    
    private class PerformSplashpageSearch extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
          try{ Log.d("backgroundThread","PerformMuseumSearch");
           String url =params[0];  
           JSONObject  LocationJson=httprequestsplash.retrieve(url);
           Log.d("result", LocationJson.toString());
           return LocationJson;
          }
          catch(Exception e)
          {
              Log.d("errorrrrrr-PerformSplashpageSearch",e.toString());
          }
         return null;
        }
        
        @Override
        protected void onPostExecute(final JSONObject result) {         
           runOnUiThread(new Runnable() {
           @Override
           public void run() {
        	   
        	   Log.d("onPostExecute%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%", result.toString());
        	   //justcurrentcityJson=result;
        	   LocationJson=result;
        			   
          	 Log.d("I am here%%%%%%%%%%%%%%%%222222222222222222222", "I am here%%%%%%%%%%%%%%%%22222222222222222222222222");
         	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
         			android.R.layout.simple_spinner_item, states);
         	
         	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         	spinnerState.setAdapter(dataAdapter);
         	
         	spinnerState.setOnItemSelectedListener(new OnItemSelectedListener() {
         		
         		@Override
         	      public void onItemSelected(AdapterView<?> arg0, View arg1,
         	          int arg2, long arg3) {
         	        int index = arg0.getSelectedItemPosition();
         	        selectedState=states.get(index);
         	        
         	        Toast.makeText(getBaseContext(),
         	            "You have selected item : " + states.get(index),
         	            Toast.LENGTH_SHORT).show();	
         	        
         	        
         	        selectedState=states.get(index);
         	        selectedStateIndex=index;
         	        city=new ArrayList();
         	        try {
     					JSONArray cities=LocationJson.getJSONArray(selectedState);
     					Log.d("first@@@@@@@@@@@@@@@@@@@@@@@@@@",cities.toString());
     					for(int i=0;i<cities.length();i++){
     						city.add(cities.getJSONObject(i).getString("city"));
     						Log.d("Just City!!!!!!!!!!!!!!!!!!!",cities.getJSONObject(i).getString("city"));
     					}
     						Log.d("City LIST!!!!!!!!!!!!!!",city.toString());
     				} catch (JSONException e) {
     					// TODO Auto-generated catch block
     					e.printStackTrace();
     				}
         	         
         	        	
         	        

         	        
         	        // call  request to get list of cities and store it in this.cities 
         	        
         	        
         	        
         	        
         	   //------------------------------<INitalise the city inside the state on selected>------------------------------
         	    	Log.d("I am here%%%%%%%%%%%%%%%%333333333333333", "I am here%%%%%%%%%%%%%%%%33333333333333");
         	        spinnerCity=(Spinner)findViewById(R.id.city_spinner);
         	    	ArrayAdapter<String> dataAdaptercity = new ArrayAdapter<String>(context,
         	    			android.R.layout.simple_spinner_item, city);
         	    	
         	    	dataAdaptercity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         	    	spinnerCity.setAdapter(dataAdaptercity);
         	    	
         	    	spinnerCity.setOnItemSelectedListener(new OnItemSelectedListener() {
         	    		
         	    		@Override
         	    	      public void onItemSelected(AdapterView<?> arg0, View arg1,
         	    	          int arg2, long arg3) {
         	    	        int index = arg0.getSelectedItemPosition();
         	    	        
         	    	       selectedCity= city.get(index); 
         	    	       selectedCityIndex=index;
         	    	       Museums =new ArrayList(); 
         	    	       try {
         					JSONArray entry=LocationJson.getJSONArray(selectedState);
         					JSONObject entrymu =entry.getJSONObject(selectedCityIndex);
         					JSONArray allmuseums=entrymu.getJSONArray("museum");
         					Log.d("second@@@@@@@@@@@@@@@@@@@@@@@@@@",allmuseums.toString());
         					for(int j=0;j<allmuseums.length();j++){
         						Log.d(" Museums second@@@@@@@@@@@@@@@@@@@@@@@@@@",(String)allmuseums.get(j));
         						Museums.add((String)allmuseums.get(j));
         						Log.d("count+++++++++++++++++++++","count"+j);
         					}
         					
							Log.d("Museum LIST ####################", Museums.toString());
         				} catch (JSONException e) {
         					// TODO Auto-generated catch block
         					e.printStackTrace();
         				}
         	    	        
         	    	        
         	    	        
         	    	        Toast.makeText(getBaseContext(),
         	    	            "You have selected item : " + city.get(index),
         	    	            Toast.LENGTH_SHORT).show();
         	    	 
         	    	        
         	    	     //--------------------<intialise the museums inside the city selected>--------------------------
         	    	        
         	    	        spinnerMuseum=(Spinner)findViewById(R.id.museum_spinner);
         	    	     	ArrayAdapter<String> dataAdaptermusuem = new ArrayAdapter<String>(context,
         	    	     			android.R.layout.simple_spinner_item, Museums);
         	    	     	
         	    	     	dataAdaptermusuem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         	    	     	spinnerMuseum.setAdapter(dataAdaptermusuem);
         	    	     	
         	    	     	spinnerMuseum.setOnItemSelectedListener(new OnItemSelectedListener(){

         	    				@Override
         	    				public void onItemSelected(AdapterView<?> arg0, View arg1,
         	    		    	          int arg2, long arg3) {
         	    					// TODO Auto-generated method stub
         	    					
         	    			        int index = arg0.getSelectedItemPosition();
         	    			        selectedMuseumIndex=index;
         	    	    	        selectedMuseum=Museums.get(index);
         	    	    	        
         	    	    	        JSONArray entry;
         	    					try {
         	    						entry = LocationJson.getJSONArray(selectedState);
         	    						JSONObject entrymu =entry.getJSONObject(selectedCityIndex);
         	    						JSONArray allpk=entrymu.getJSONArray("pk");
         	    						
         	    						primarykey=allpk.get(selectedMuseumIndex).toString();
         	    						Log.d("primary key @@@@@@@@@@@@@@@",primarykey);
         	    						
         	    						
         	    					} catch (JSONException e) {
         	    						// TODO Auto-generated catch block
         	    						e.printStackTrace();
         	    					}
         	    					
         	    	    	        
         	    	    	        
         	    	    	        
         	    	    	        
         	    	    	        Toast.makeText(getBaseContext(),
         	    	    	            "You have selected item : " + states.get(index),
         	    	    	            Toast.LENGTH_SHORT).show();	
         	    					
         	    				}

         	    				@Override
         	    				public void onNothingSelected(AdapterView<?> parent) {
         	    					// TODO Auto-generated method stub
         	    					
         	    				}
         	    	     		
         	    	     	}); 
         	    	        
         	    	        
         	    	        
         	    	        
         	    	   
         	    	       
         	     	        
         	    	      }

         	    	      @Override
         	    	      public void onNothingSelected(AdapterView<?> arg0) {
         	    	      }
         	    	 
         	    		
         	    		
         			});
         	        
         	        
         	        
         	        
         	         
         	      }

         	      @Override
         	      public void onNothingSelected(AdapterView<?> arg0) {
         	      }
         	 
         		
         		
     		});
        	   
        	   
        	   
        	   
        	   
        	  /* List<String> museum=new ArrayList<String>();
        	  // Musuems=result.getMusuems();
        	   Log.d("result-onPostExecute", result.toString());
        	   spinnerMuseum=(Spinner)findViewById(R.id.museum_spinner);
        	   ArrayAdapter<String> dataAdaptermuseum = new ArrayAdapter<String>(context,
           			android.R.layout.simple_spinner_item, Museums);
           	
        	   dataAdaptermuseum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	   spinnerMuseum.setAdapter(dataAdaptermuseum);*/
        	   
           }
            });
        }
           
     }
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);
        this.context=this;
        this.spinnerState=(Spinner)findViewById(R.id.state_spinner);
        PerformSplashpageSearch request=new PerformSplashpageSearch();
        //request.execute("http://edocent.herokuapp.com/curator/?state=MA&city=Boston");
        request.execute("http://edocent.herokuapp.com/curator/locations/");
    	states=new ArrayList<String>();
    	states.add("MA");	
    	states.add("ME");
    	states.add("OK");
  
    	
    	
    	
    	
    	city=new ArrayList<String>();
    	city.add("Boston");
    	city.add("NY");
    	city.add("NJ");
    	city.add("RH");
    	city.add("NH");
    	city.add("CA");
    	city.add("MO");
    	
    	
    	
    	
    	//Musuems=new ArrayList<String>();
    
    	Log.d("I am here%%%%%%%%%%%%%%%%11111111111111111", "I am here%%%%%%%%%%%%%%%%11111111111111111");
    	 this.submit=(Button) findViewById(R.id.ButtonSendFeedback);
    	 this.submit.setOnClickListener(new Button.OnClickListener(){
         
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				//pass the appropriate key to select the museums,as args
				Intent intent =new Intent(context,MuseumDescActivity.class);
				Log.d("passingGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG", primarykey);
				intent.putExtra("musuemprimarykey",primarykey.toString());
				startActivity(intent);
				
			}
    		 
    	 });
    	

    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
      	

    	
	}
  

        
    
    public void onItemSelected(AdapterView<?> parent,View v, int position, long id) {
              
    	

    	
    	
}
    public void onNothingSelected(AdapterView<?> parent) {
        
      }
    	
    	
    	
    public void fillSpinnerCity(String state){
    	
    }
    
    
    
    
    public void fillSpinnermuseums(String city){
    	
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edocent_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    public void addListenerOnButton()
    {
           spinnerState=(Spinner) findViewById(R.id.state_spinner); 
           Toast.makeText(this, "splash page event triggered", Toast.LENGTH_LONG).show();
    }
    
    
      
}
