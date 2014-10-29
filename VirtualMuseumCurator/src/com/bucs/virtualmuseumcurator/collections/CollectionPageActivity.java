package com.bucs.virtualmuseumcurator.collections;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.datamodel.CollectionPageData;
import com.bucs.virtualmuseumcurator.datamodel.CollectionRowContent;
import com.bucs.virtualmuseumcurator.museumhome.HomePageFragment;



public class CollectionPageActivity extends ActionBarActivity {
	
	
	private LinearLayout collections;
	private MuseumCollectionHttpClient collectionClient;
	private CollectionPageActivity context;
	
    private class RetrieveCollection extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(String... params) {
          try{ 
        	       Log.d("RetrieveCollectionbackgroundThread","RetrieveCollection");
			           String url =params[0];  
			   		/* 
			   		 * Get the Json response from the server for the list of collection of art pieces 
			   		 * which is in JSONArray format
			   		 * */
			   		MuseumCollectionHttpClient collectionClient=new MuseumCollectionHttpClient();
			   		JSONArray exhibitionArray=collectionClient.retrieve(url);
			        Log.d("RetrieveCollectionresult", exhibitionArray.toString());
			           return exhibitionArray;
			          }
			          catch(Exception e)
			          {
			              Log.d("errorrrrrr-RetrieveCollectionpageSearch",e.toString());
			          }
			         return null;
        }
        
        @Override
        protected void onPostExecute(final JSONArray exhibitionArray) {         
           runOnUiThread(new Runnable() {
           @Override
				           public void run() {
				        	   
				        	   /*
						   		 * Pass the json response to .fromJSON() function to construct a list of  ArrayList<CollectionRowContent> to 
						   		 * be given the adapter
						   		 * */
						   		ArrayList rowvalues=new ArrayList<CollectionRowContent>();
						   		rowvalues=CollectionRowContent.fromJSON(exhibitionArray);
						   		
						   		
						        FragmentManager fm= getFragmentManager();
						        android.app.FragmentTransaction ft=fm.beginTransaction();
						        CollectionPageFragment frag=new CollectionPageFragment();
						        ft.add(R.id.collection_list_view, frag);
						        Log.d("result-onPostExecute1",exhibitionArray.toString());
						       // Log.d("result-onPostExecute2",rowvalues.get(1).getString("description"));
						   	    com.bucs.virtualmuseumcurator.collections.CollectionPageAdapter adapter= new CollectionPageAdapter(context,R.layout.rowlayoutcollections,rowvalues);
						   	    Log.d("before Adapter!!!!!!!!!!","");
						   	    frag.setListAdapter(adapter);
						   	    Log.d("after Adapter!!!!!!!!!","");
						        ft.commit();
						        Log.d("after commit!!!!!!!!!!!!","");
						   		
				        	   
				           }
				            });
        }
           
     }
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection_page);
		this.context=this;
		RetrieveCollection task= new RetrieveCollection(); 
        task.execute(new String[] { "http://edocent.herokuapp.com/curator/1/exhibitions/"});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_page, menu);
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
