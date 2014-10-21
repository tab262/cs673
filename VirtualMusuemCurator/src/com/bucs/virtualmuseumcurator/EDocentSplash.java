package com.bucs.virtualmuseumcurator;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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

import com.bucs.virtualmuseumcurator.datamodel.EdocentSplashPageData;


public class EDocentSplash extends ActionBarActivity implements AdapterView.OnItemSelectedListener{
	
	
	private com.bucs.virtualmuseumcurator.MuseumSplashHttpClient httprequestsplash= new com.bucs.virtualmuseumcurator.MuseumSplashHttpClient();
	private Spinner spinnerState,spinnerCity,spinnerMuseum;
	private Button submit;
	private String selectedState;
	private String selectedCity; 
    private List<String> states;
    private List<String> city;
    private List<String> Musuems;
    private Activity context;
    
    
    
    
    private class PerformSplashpageSearch extends AsyncTask<String, Void, EdocentSplashPageData> {

        @Override
        protected EdocentSplashPageData doInBackground(String... params) {
          try{ Log.d("backgroundThread","PerformMuseumSearch");
           String url =params[0];  
           EdocentSplashPageData Text=httprequestsplash.retrieve(url);
           Log.d("result", Text.toString());
           return Text;
          }
          catch(Exception e)
          {
              Log.d("errorrrrrr-PerformSplashpageSearch",e.toString());
          }
         return null;
        }
        
        @Override
        protected void onPostExecute(final EdocentSplashPageData result) {         
           runOnUiThread(new Runnable() {
           @Override
           public void run() {
               
        	   List<String> museum=new ArrayList<String>();
        	   Musuems=result.getMusuems();
        	   Log.d("result-onPostExecute", result.toString());
        	   spinnerMuseum=(Spinner)findViewById(R.id.museum_spinner);
        	   ArrayAdapter<String> dataAdaptermuseum = new ArrayAdapter<String>(context,
           			android.R.layout.simple_spinner_item, Musuems);
           	
        	   dataAdaptermuseum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	   spinnerMuseum.setAdapter(dataAdaptermuseum);
        	   
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
    	states=new ArrayList<String>();
    	states.add("MA");
    	states.add("NY");
    	states.add("NJ");
    	states.add("RH");
    	states.add("NH");
    	states.add("CA");
    	states.add("MO");
    	
    	
    	
    	
    	city=new ArrayList<String>();
    	city.add("Boston");
    	city.add("NY");
    	city.add("NJ");
    	city.add("RH");
    	city.add("NH");
    	city.add("CA");
    	city.add("MO");
    	
    	
    	
    	
    	Musuems=new ArrayList<String>();
    
    	
    	
    	
    	
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
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
    	        
    	        
    	        /*
    	         * 
    	         * 
    	         * call http request to get list of cities and store it in this.cities
    	         * 
    	         * */
    	      }

    	      @Override
    	      public void onNothingSelected(AdapterView<?> arg0) {
    	      }
    	 
    		
    		
		});
    	
    	
    	
    	 this.spinnerCity=(Spinner)findViewById(R.id.city_spinner);
    	ArrayAdapter<String> dataAdaptercity = new ArrayAdapter<String>(this,
    			android.R.layout.simple_spinner_item, city);
    	
    	dataAdaptercity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinnerCity.setAdapter(dataAdaptercity);
    	
    	spinnerCity.setOnItemSelectedListener(new OnItemSelectedListener() {
    		
    		@Override
    	      public void onItemSelected(AdapterView<?> arg0, View arg1,
    	          int arg2, long arg3) {
    	        int index = arg0.getSelectedItemPosition();
    	        
    	       selectedCity= city.get(index);   
    	        
    	        
    	        
    	        
    	        
    	        Toast.makeText(getBaseContext(),
    	            "You have selected item : " + city.get(index),
    	            Toast.LENGTH_SHORT).show();
    	        
    	        
    	        PerformSplashpageSearch request=new PerformSplashpageSearch();
    	        request.execute("http://edocent.herokuapp.com/curator/?state=MA&city=Boston");
    	       
     	        
    	      }

    	      @Override
    	      public void onNothingSelected(AdapterView<?> arg0) {
    	      }
    	 
    		
    		
		});
    	
    	

    	
	}
  

        
    
    public void onItemSelected(AdapterView<?> parent,View v, int position, long id) {
              
    	

    	
    	
}
    public void onNothingSelected(AdapterView<?> parent) {
        
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
    }
}
