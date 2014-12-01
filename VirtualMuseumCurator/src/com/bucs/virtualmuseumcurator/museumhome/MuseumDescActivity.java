package com.bucs.virtualmuseumcurator.museumhome;




import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.datamodel.MuseumHomePageData;


public class MuseumDescActivity extends ActionBarActivity {
	
	
	private  com.bucs.virtualmuseumcurator.museumhome.MuseumHttpClient httprequest=new com.bucs.virtualmuseumcurator.museumhome.MuseumHttpClient();
	
	private TextView TextName;
	private TextView TextHour;
	private TextView TextLocation;
	private TextView TextDescription;
	private ImageView MuseumImage;
	
	private Bitmap bitmap;
	private String Address;
	private String Phone;
	private String membership;
	private String  website;
	private String parking;
	private String visitor_info;
	private ArrayList hours;
	private String lat;
	private String lng;
	private Activity context;
	private String title;
	private String ticketprices;
	private String primarykey;
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent){     
	      if(requestCode == 0)     
	      {         
	                        if(resultCode == RESULT_OK)         
	                        { 
	                                    String contents = intent.getStringExtra("SCAN_RESULT"); 
	                                     String format = intent.getStringExtra("SCAN_RESULT_FORMAT");           
	                                       Log.i("xZing", "contents: "+contents+" format: "+format);          
	                                           // Handle successful scan       
	                         }        
	                         else if(resultCode == RESULT_CANCELED)       
	                           {              // Handle cancel             
	                           Log.i("xZing", "Cancelled");        
	                          }    
	                        } 
	          }
	
	
	
	
	private  class URLtoImage extends AsyncTask<String, Void, Bitmap> {
		
			
			protected Bitmap doInBackground(String... params) {
				try {
					Log.d("Bit!!!!!!!!!!!!!!!!!!!!!!!", params[0]);
					URL url = new URL(params[0]);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoInput(true);
					connection.connect();
					InputStream input = connection.getInputStream();
					return BitmapFactory.decodeStream(input);
					 
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return null;
			}
		}
			
			protected void onPostExecute(Bitmap feed) {
		        // TODO: check this.exception 
		        // TODO: do something with the feed
				bitmap=feed;
				MuseumImage.setImageBitmap(bitmap);
			    		    
		    }
		
		}
	
	
	
	
	
	private class PerformMuseumSearch extends AsyncTask<String, Void, MuseumHomePageData> {
		
		private final String baseurl="https://s3.amazonaws.com/edocent/";
		   @Override
		   protected MuseumHomePageData doInBackground(String... params) {
			 try{ Log.d("backgroundThread","PerformMuseumSearch");
			  String url =params[0];  
			  MuseumHomePageData Text=httprequest.retrieve(url); 
		      Log.d("result", Text.toString());
		      
		        URL urlimage = new URL(baseurl+Text.getImage());
				HttpURLConnection connection = (HttpURLConnection) urlimage.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				Text.setMuseumImageinBit(BitmapFactory.decodeStream(input));
		      	      
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
		    	  
		    	  
		    	  bitmap=result.getMuseumImageinBit();
		          MuseumImage.setImageBitmap(bitmap);
		    	  TextName.setText(result.getMuseumName());
		    	  TextLocation.setText(result.getStreetAdress()+","+ result.getCity()+"-"+result.getZipcode());
		          TextDescription.setText(result.getDescription());
		          Address=(String) TextLocation.getText();
		          
		          
		          
		          Phone= "1234567811111";
		          title="I am here Thread";
		          
		          hours=result.getHours();
		          membership=result.getMembership();
		          parking=result.getParking();
		          visitor_info=result.getVisitor_info();
		          website=result.getWebsite();
		          ticketprices=result.getTicketprices();
		          lat=result.getLatitude();
		          lng=result.getLongitude();
		         // Log.d("###################", ticketprices);	             
		         // Log.d("Beforeeeeeeeeeeeeeee!!!!!!!!!!!!!!!!!!!!!!!", "###########");   
		          FragmentManager fm= getFragmentManager();
		          android.app.FragmentTransaction ft=fm.beginTransaction();
		          HomePageFragment frag=new HomePageFragment(Address,lat,lng,Phone,title,context,membership,website,ticketprices,hours,parking,visitor_info,primarykey);
		          ft.add(R.id.home_linearlayout, frag);
		          ft.commit();
		    	  
		          		
		      }
		       });
		   }
		      
		}
	
	
	
	 public Bitmap getBitmapFromURL(String src)
	    {
	    	try {
	    		Log.d("Bit!!!!!!!!!!!!!!!!!!!!!!!", src);
				URL url = new URL(src);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				Bitmap myBitmap = BitmapFactory.decodeStream(input);
				return myBitmap;
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
	    }
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_desc);
        TextName=(TextView)findViewById(R.id.museum_name);
        TextLocation=(TextView)findViewById(R.id.museum_location);
        this.MuseumImage=(ImageView)findViewById(R.id.Museum_Image);
       // TextHour=(TextView)findViewById(R.id.hours);
        TextDescription=(TextView)findViewById(R.id.museum_description);
        PerformMuseumSearch task= new PerformMuseumSearch(); 
        //task.execute(new String[] { "http://edocent.herokuapp.com/curator/1/"});
		Bundle extras=getIntent().getExtras();
		if(extras!=null){
		String primarykey=extras.getString("musuemprimarykey"); 
		this.primarykey=primarykey;
		Log.d("Primary key Passsssssssssssssssssssssss", primarykey);
        task.execute(new String[] {"http://edocent.herokuapp.com/curator/"+primarykey+"/"});
		}
        
        //lat= "42.339151000000001";
        //lng="-71.093853099999990";
        //Address=(String) TextLocation.getText();
        Address="143 Park Dr";
        Phone= "12345678000000";
        title="I am here on create";
	
        this.context=this;
     
        
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
