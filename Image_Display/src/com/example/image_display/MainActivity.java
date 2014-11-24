package com.example.image_display;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

	
	private ImageView iv;
	private Bitmap bitmap;
	private MediaPlayer player = new MediaPlayer();
	private Button play;
	private Button stop;
	
	 class URLtoImage extends AsyncTask<String, Void, Bitmap> {
		
		/*public URLtoImage(String src) {
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
		}*/
		protected Bitmap doInBackground(String... params) {
			try {
				Log.d("Bit!!!!!!!!!!!!!!!!!!!!!!!", params[0]);
				URL url = new URL(params[0]);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				
				
				try {
					player.setDataSource(params[1]);
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				
				
				
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
		    iv.setImageBitmap(bitmap);
		    //Button for playing the audio
	        play=(Button)findViewById(R.id.play);
	        play.setOnClickListener(new OnClickListener(){
	        	
	        	public void onClick(View v){
	        		player= new MediaPlayer();
	        		player.setAudioStreamType(AudioManager.STREAM_MUSIC);
	        		
	        		
	        		try {
	        			
	        			player.reset();
	        			
						player.prepare();
						Log.d("HIII","HELLOO" );
						//player.start();
						
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		//player.start();
	        		
	        		
	        		player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						
						@Override
						public void onPrepared(MediaPlayer mp) {
							mp.start(); 
							
						}
					});
	        		//player.prepareAsync();
	        	}
	        });
		    		    
	    }

	

	
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView1);
        
       
        
        stop=(Button)findViewById(R.id.stop);
        stop.setOnClickListener( new OnClickListener(){
        	
        	public void onClick(View v){
        		if(player!=null && player.isPlaying()){
        			player.stop();
        		}
        	}
        });
        
        Log.d("Beforeeeeeeeeeeeeeee!!!!!!!!!!!!!!!!!!!!!!!", "###########");
        URLtoImage url=new URLtoImage();
        url.execute(new String []{"https://s3.amazonaws.com/edocent/art_images/painting.jpg ","https://s3.amazonaws.com/edocent/audio_files/example_audio.mp3"});
        //bitmap = getBitmapFromURL("http://icons.iconarchive.com/icons/flat-icons.com/flat/128/Satellite-icon.png");
       
    }	
   


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /*public Bitmap getBitmapFromURL(String src)
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
    }*/

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
