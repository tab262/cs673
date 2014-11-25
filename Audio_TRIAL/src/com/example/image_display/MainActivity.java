package com.example.image_display;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
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
	
	MediaPlayer mediaPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button play = (Button)findViewById(R.id.play);
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Uri path = Uri.parse("https://s3.amazonaws.com/edocent/audio_files/example_audio.mp3");
				try {
			          mediaPlayer = new MediaPlayer();
			          mediaPlayer.setDataSource(MainActivity.this, path);
			          mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			          mediaPlayer.prepare(); 
			      } catch (IOException e) {           
			          e.printStackTrace();
			      } 
				
				mediaPlayer.start();
				
			}
		});
		
		
		Button stop = (Button)findViewById(R.id.stop);
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(mediaPlayer.isPlaying())
				{
					mediaPlayer.stop();
					mediaPlayer.release();
				}
				
			}
		});
	}
	
	protected void onDestroy()
	{
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
			
		super.onDestroy();
	}
	
}
