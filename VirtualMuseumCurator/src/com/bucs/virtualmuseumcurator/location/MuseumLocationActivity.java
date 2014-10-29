package com.bucs.virtualmuseumcurator.location;

import java.util.Locale;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.R.id;
import com.bucs.virtualmuseumcurator.R.layout;
import com.bucs.virtualmuseumcurator.R.menu;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MuseumLocationActivity extends FragmentActivity implements LocationListener {
	
	
	private GoogleMap map=null;
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
		Log.d("at MuseumLocation", "MuseumLocation");
		Intent intent=getIntent();	
		float lat=0;
		float lng=0;
		if(intent!=null){
			 lat= intent.getFloatExtra("Lat",(float)42.339151000000001);
			 lng=intent.getFloatExtra("lng", (float)-71.093853099999990);
			this.musuemLocation=new LatLng(lat,lng);
			this.phoneNumber=intent.getStringExtra("phone");
			this.address=intent.getStringExtra("Address");
		}
		Log.d("before Direction",lat+","+lng);
		
		//Direction=(TextView)findViewById(R.id.museum_directions);
		
		
		locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
		Log.d(" before museum_map", " before museum_map");
		
		MapFragment fragment = ( MapFragment) getFragmentManager().findFragmentById(R.id.museum_map);
		map = fragment.getMap();
		Log.d("after museum_map", "after museum_map");
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		map.addMarker(new MarkerOptions().position(musuemLocation).title("I am here!"));
	    CameraUpdate update= CameraUpdateFactory.newLatLngZoom(musuemLocation,16);
	    map.animateCamera(update);
	    Log.d("after museum_map", "after museum_map");
	    
	    Direction=(TextView) findViewById(R.id.text_directions);

	    
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
		String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)", 42.341516, -71.098307, "Home Sweet Home", 42.339151000000001, -71.093853099999990, "Where the party is at");
		Intent intent =new Intent(android.content.Intent.ACTION_VIEW,Uri.parse(uri));
		intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		startActivity(intent);
		
	}
	
	
	public void onClickContact(View v){
		Intent intent=new Intent();
		intent.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.DialtactsContactsEntryActivity"));
		startActivity(intent);
	}
	
}
