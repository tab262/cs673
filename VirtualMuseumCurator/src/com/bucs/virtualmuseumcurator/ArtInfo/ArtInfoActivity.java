package com.bucs.virtualmuseumcurator.ArtInfo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel;

public class ArtInfoActivity extends ActionBarActivity {
	
	private TextView artTitle;
	private TextView artDate;
	private TextView artDescription;
	private TextView artType;
	private ImageView artImage;
	private TextView shareFaceButton;
	private  SocialAuthAdapter adapter;
	private Activity context;
	
	
	
	
	private final class ResponseListener implements DialogListener{
		
		public void onComplete(Bundle values){
			adapter.updateStatus("SocialAuth App share",new MessageListener(),true);
		}

		@Override
		public void onBack() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onCancel() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	 class URLtoImage extends AsyncTask<String, Void, Bitmap> {
		protected Bitmap doInBackground(String... params) {
			try {
				Log.d("Individual image calling #########################", params[0]);
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
			Log.d("IMage assigned :)))))))))))))))))",":::))))))");
			artImage.setImageBitmap(feed);
		    //iv.setImageBitmap(bitmap);
		    		    
	    }
	
	}
	
	
	
	private final class MessageListener implements SocialAuthListener{
		
		public void onExecute(Integer i){
			Integer status = i;
		    if (status.intValue() == 200 || status.intValue() == 201 ||status.intValue() == 204)
		     Toast.makeText(context, "Message posted",Toast.LENGTH_LONG).show();
		    else
		    Toast.makeText(context, "Message not posted",Toast.LENGTH_LONG).show();
		   }

		@Override
		public void onError(SocialAuthError arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onExecute(String arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}
			
	}
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_art_info);
		 
	    Bundle bundle=getIntent().getExtras();
	    String index=bundle.getString("index");
	    ArtInfoDataModel artobj=(ArtInfoDataModel)bundle.getSerializable("artobj");
	    this.artTitle=(TextView)findViewById(R.id.art_title);
	    this.artDate=(TextView)findViewById(R.id.art_date);
	    this.artDescription=(TextView)findViewById(R.id.art_description);
	    this.artType=(TextView)findViewById(R.id.art_type);
	    this.artImage=(ImageView)findViewById(R.id.art_image);
	    this.context=this;
	    URLtoImage urlimage= new URLtoImage();
	    urlimage.execute(new String[]{"https://s3.amazonaws.com/edocent/"+artobj.getPictureurlpath()});	
	    artTitle.setText(artobj.getName());
	    artDate.setText(artobj.getDate());
	    artDescription.setText(artobj.getDescription());
	    artType.setText(artobj.getType());
	    Log.d("ARTIMAGE $$$$$$$$$$^^^^^^^^^^^^^^^&&&&&&&&&&","DDDDDDD");
	   // artImage.setImageBitmap(artobj.getPicturebitmap());
	    
	    
	    Button sharefacebook=(Button) findViewById(R.id.art_share);
	    adapter =new SocialAuthAdapter(new ResponseListener());
	    
	    adapter.addProvider(Provider.FACEBOOK,R.drawable.facebook);
	    //adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
	    adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);
	    adapter.addProvider(Provider.YAHOO, R.drawable.yahoo);
	    adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
	    adapter.addProvider(Provider.INSTAGRAM, R.drawable.instagram);
	    adapter.enable(sharefacebook);
	    
	   /* sharefacebook.setOnClickListener(new OnClickListener() 
	     {
	        public void onClick(View v) 
	        {
	            adapter.authorize(ProviderUI.this, Provider.FACEBOOK);
	        }
	    });*/
	    
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
