package com.bucs.virtualmuseumcurator;



import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bucs.virtualmuseumcurator.datamodel.MuseumHomePageData;


public class MuseumDescActivity extends ActionBarActivity {
	
	
	private  com.bucs.virtualmuseumcurator.MuseumHttpClient httprequest=new com.bucs.virtualmuseumcurator.MuseumHttpClient();
	
	private TextView TextName;
	private TextView TextHour;
	private TextView TextLocation;
	
	
	private class PerformMuseumSearch extends AsyncTask<String, Void, MuseumHomePageData> {

		   @Override
		   protected MuseumHomePageData doInBackground(String... params) {
			 try{ Log.d("backgroundThread","PerformMuseumSearch");
			  String url =params[0];  
			  MuseumHomePageData Text=httprequest.retrieve(url);
		      Log.d("result", Text.toString());
		      return Text;
			 }
			 catch(Exception e)
			 {
				 Log.d("errorrrrrr",e.toString());
			 }
			return null;
		   }
		   
		   @Override
		   protected void onPostExecute(final MuseumHomePageData result) {         
		      runOnUiThread(new Runnable() {
		      @Override
		      public void run() {
		    	  
		    	  TextName.setText(result.getMuseumName());
		    	  TextLocation.setText(result.getStreetAdress()+ result.getCity()+"-"+result.getZipcode());
		          TextHour.setText(result.getHour_M());
		    	  
		          		
		      }
		       });
		   }
		      
		}
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_desc);
        TextName=(TextView)findViewById(R.id.museum_name);
        TextLocation=(TextView)findViewById(R.id.museum_location);
        TextHour=(TextView)findViewById(R.id.hours);
        PerformMuseumSearch task= new PerformMuseumSearch(); 
        task.execute(new String[] { "http://edocent.herokuapp.com/curator/1/"});
	
	
        
        FragmentManager fm= getFragmentManager();
        android.app.FragmentTransaction ft=fm.beginTransaction();
        HomePageFragment frag=new HomePageFragment();
        ft.add(R.id.home_linearlayout, frag);
        ft.commit();
        
    }
    
    @Override
    protected void onStart(){
    	super.onStart();
    	
    
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.museum_desc, menu);
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
}
