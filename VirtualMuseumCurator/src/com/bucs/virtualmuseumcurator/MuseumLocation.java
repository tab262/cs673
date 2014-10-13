package com.bucs.virtualmuseumcurator;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MuseumLocation extends ActionBarActivity implements LocationListener {
	
	
	private GoogleMap map;
	private  LatLng musuemLocation;
	private  String phoneNumber;
	private  String address;
	private LocationManager locationManager;
	private float currlat;
	private float currlng;
	
	private TextView Direction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_museum_location);
		
		Intent intent=getIntent();	
		if(intent!=null){
			float lat= intent.getFloatExtra("Lat",(float) 0.002323);
			float lng=intent.getFloatExtra("lng", (float)0.002323);
			this.musuemLocation=new LatLng(lat,lng);
			this.phoneNumber=intent.getStringExtra("phone");
			this.address=intent.getStringExtra("Address");
		}
		
		
		Direction=(TextView)findViewById(R.id.museum_directions);
		
		
		locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
		
		map =((MapFragment) getFragmentManager().findFragmentById(R.id.museum_map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		map.addMarker(new MarkerOptions().position(musuemLocation).title("I am here!"));
	    CameraUpdate update= CameraUpdateFactory.newLatLngZoom(musuemLocation,16);
	    map.animateCamera(update);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.museum_location, menu);
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

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Log.d("CURRENT_LOCATION",("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude()));
		currlat=(float) location.getLatitude();
		currlng=(float) location.getLatitude();
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		Log.d("Latitude","status");
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Log.d("Latitude","enable");
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Log.d("Latitude","disable");
		
	}
	
	public void onClickDirection(View v){
		
		//Intent intent =new Intent(android.content.Intent.ACTION_VIEW,
				//Uri.parse("http://maps.google.com/maps?saddr="+this.cu));
		
	}
}
