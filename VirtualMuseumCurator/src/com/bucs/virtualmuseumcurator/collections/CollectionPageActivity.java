package com.bucs.virtualmuseumcurator.collections;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.collections.CollectionPageAdapter.ViewHolder.ImageArtobj;
import com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel;
import com.bucs.virtualmuseumcurator.datamodel.CollectionRowContent;



public class CollectionPageActivity extends ActionBarActivity {
	
	
	private LinearLayout collections;
	private MuseumCollectionHttpClient collectionClient;
	private CollectionPageActivity context;
	
	public class WrapperCollecRowArtObj{
		public ArrayList<CollectionRowContent> rowcollectionlist=new ArrayList<CollectionRowContent>();
		public  ArrayList<ArtInfoDataModel> individualartobjectlist=new ArrayList<ArtInfoDataModel>();
		public JSONArray jsoncollectionarray=new JSONArray();
		}
	
	
    private class RetrieveCollection extends AsyncTask<String, Void, WrapperCollecRowArtObj> {

        @Override
        protected WrapperCollecRowArtObj doInBackground(String... params) {
          try{ 
        	       Log.d("RetrieveCollectionbackgroundThread","RetrieveCollection");
			           String url =params[0];  
			   		/* 
			   		 * Get the Json response from the server for the list of collection of art pieces 
			   		 * which is in JSONArray format
			   		 * */
			        
			        WrapperCollecRowArtObj wrappobj=new WrapperCollecRowArtObj();
			           
			   		MuseumCollectionHttpClient collectionClient=new MuseumCollectionHttpClient();
			   		JSONArray exhibitionArray=collectionClient.retrieve(url);
			   		wrappobj.jsoncollectionarray=exhibitionArray;
			   		
			   		
			   		//for the collection list view
			   		ArrayList<CollectionRowContent> rowvalues=new ArrayList<CollectionRowContent>();
			   		rowvalues=CollectionRowContent.fromJSON(exhibitionArray);
			   		wrappobj.rowcollectionlist=rowvalues;
			   		//for the individual art display
			   		ArrayList<ArtInfoDataModel> artlist= new ArrayList<ArtInfoDataModel>();   		
			   		artlist=ArtInfoDataModel.fromJSONArray(exhibitionArray);
			   		wrappobj.individualartobjectlist=artlist;
			   		
			   		for(int index=0; index< rowvalues.size();index++){
			   			URL imageurl=new URL("https://s3.amazonaws.com/edocent/"+artlist.get(index).getPictureurlpath());
			   			Log.d("URL^^^^^^^^^^^^^^^^^^^^^^^", imageurl.toString());
			   			HttpURLConnection connection;
			   			connection = (HttpURLConnection) imageurl.openConnection();
			   			connection.setDoInput(true);
			   			connection.connect();
			   			InputStream input = connection.getInputStream();
			   			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			   			//artlist.get(index).setPicturebitmap(myBitmap);
			   			rowvalues.get(index).setBitmap(myBitmap);		   			
			   			//holder.image.setImageBitmap(myBitmap);*/
   			
			   		}
			   		 		
			   		
			        Log.d("RetrieveCollectionresult", exhibitionArray.toString());
			           return wrappobj;
			          }
			          catch(Exception e)
			          {
			              Log.d("errorrrrrr-RetrieveCollectionpageSearch",e.toString());
			          }
			         return null;
        }
        
        @Override
        protected void onPostExecute(final WrapperCollecRowArtObj wrapperexhibition) {         
           runOnUiThread(new Runnable() {
           @Override
				           public void run() {
				        	   
				        	   /*
						   		 * Pass the json response to .fromJSON() function to construct a list of  ArrayList<CollectionRowContent> to 
						   		 * be given the adapter
						   		 * */
        	   
        	    			
						   		
        	   					ArrayList<ArtInfoDataModel> copyartlist= new ArrayList<ArtInfoDataModel>(); 
        	   					copyartlist=wrapperexhibition.individualartobjectlist;
						        FragmentManager fm= getFragmentManager();
						        android.app.FragmentTransaction ft=fm.beginTransaction();
						        CollectionPageFragment frag=new CollectionPageFragment(copyartlist,context);
						        ft.add(R.id.collection_list_view, frag);
						        Log.d("result-onPostExecute1",wrapperexhibition.toString());
						       // Log.d("result-onPostExecute2",rowvalues.get(1).getString("description"));
						   	    com.bucs.virtualmuseumcurator.collections.CollectionPageAdapter adapter= new CollectionPageAdapter(context,R.layout.rowlayoutcollections,wrapperexhibition.rowcollectionlist);
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
		Bundle extras=getIntent().getExtras();
		String primarykey=extras.getString("primarykey");
        //task.execute(new String[] { "http://edocent.herokuapp.com/curator/1/exhibitions/"});
        task.execute(new String[] {"http://edocent.herokuapp.com/curator/"+primarykey+"/collection/1/"});
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
