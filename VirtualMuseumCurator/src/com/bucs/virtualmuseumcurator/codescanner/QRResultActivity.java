package com.bucs.virtualmuseumcurator.codescanner;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.json.JSONObject;

import android.app.Activity;
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
import android.widget.Toast;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.codescanner.QRResultActivity.ArtQRObject.QRResult;
import com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel;

public class QRResultActivity extends ActionBarActivity {
	
	
	private ArtInfoDataModel artObject;
	private String artlink;
	
	private TextView artTitle;
	private TextView artDate;
	private TextView artDescription;
	private TextView artistName;
	private ImageView artImage;
	private TextView shareFaceButton;
	private  SocialAuthAdapter adapter;
	private Activity context;
	
	
	 class ArtQRObject extends AsyncTask<String, Void, QRResult> {
		 
		 
		 public class QRResult{
			 public  ArtInfoDataModel artObject;
			 public  Bitmap artimag;
			 
		 }
		
		 protected QRResult doInBackground(String... params) {
			try {
				Log.d("Individual QR art object calling #########################", params[0]);
				//Toast.makeText(context, "Individual QR art object calling 2#########################",Toast.LENGTH_LONG).show();
				//Toast.makeText(getActivity(), item+" Map triggered", Toast.LENGTH_LONG).show();
				URL url = new URL(params[0]);
				QRResult result=new QRResult();
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				Log.d("input streammmmmmmmmmmmmmmmmm", input.toString());
				String theString = IOUtils.toString(input, "UTF-8");
				JSONObject artobj=new JSONObject(theString);
				result.artObject=new ArtInfoDataModel(artobj);
				
				Log.d("artonbject urllllllllllllllllllllllll", "https://s3.amazonaws.com/edocent/"+result.artObject.getPictureurlpath());
				URL url2 = new URL("https://s3.amazonaws.com/edocent/"+result.artObject.getPictureurlpath());
				HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
				connection2.setDoInput(true);
				connection2.connect();
				InputStream input2 = connection2.getInputStream();
				//result.artimag=new Bitmap(); 
				/*BitmapFactory.Options options =new BitmapFactory.Options();
				options.inJustDecodeBounds=true;
				BitmapFactory.decodeStream(input2,null,options);
				//options.inSampleSize = calculateInSampleSize(options, 768, 1280);
				options.inJustDecodeBounds = false;
				
				BufferedInputStream bis = new BufferedInputStream(input2);
				Bitmap bm = BitmapFactory.decodeStream(bis);
				bis.close();
				input2.close();*/
				
				result.artimag=BitmapFactory.decodeStream(input2);
				return result;
				 		
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
		}
	}
		
		protected void onPostExecute(QRResult feed) {
	        // TODO: check this.exception 
	        // TODO: do something with the feed
			Log.d("called QR object :)))))))))))))))))",":::))))))");
			//Toast.makeText(context, "onPostExecute 3 artTitle #########################",Toast.LENGTH_LONG).show();
			artTitle.setText(feed.artObject.getName());
			//Toast.makeText(context, "onPostExecute 4 artDate #########################",Toast.LENGTH_LONG).show();
			artDate.setText(feed.artObject.getDate());
			//Toast.makeText(context, "onPostExecute 6 artDescription #########################",Toast.LENGTH_LONG).show();
			artDescription.setText(feed.artObject.getDescription());
			//Toast.makeText(context, "onPostExecute 7######################### artistName ",Toast.LENGTH_LONG).show();
			artistName.setText(feed.artObject.getArtistname());
			//Toast.makeText(context, "onPostExecute 8 end #########################",Toast.LENGTH_LONG).show();
			artImage.setImageBitmap(feed.artimag);
			    		    
	    }
	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_art_info);
		Bundle extras=getIntent().getExtras();
		this.artlink=extras.getString("artlink");
		 this.artTitle=(TextView)findViewById(R.id.art_title);
		 this.artDate=(TextView)findViewById(R.id.art_date);
		 this.artDescription=(TextView)findViewById(R.id.art_description);
		 this.artistName=(TextView)findViewById(R.id.art_type);
		 this.artImage=(ImageView)findViewById(R.id.art_image);
		 this.context=this;
		
		
		ArtQRObject qrthread= new ArtQRObject();
		Log.d("Calling QR linkkkkkkkkkkkkkkkkkkkkkkkkkk ", "http://edocent.herokuapp.com"+this.artlink);
		Log.d("art Likkkkkkkkkkkkkkkkkkk", this.artlink);
		qrthread.execute(new String[]{"http://edocent.herokuapp.com"+this.artlink});
		
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.art_info, menu);
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
