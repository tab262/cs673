package com.bucs.virtualmuseumcurator.museumhome;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bucs.virtualmuseumcurator.codescanner.ScannerAcivity;
import com.bucs.virtualmuseumcurator.collections.CollectionPageActivity;
import com.bucs.virtualmuseumcurator.information.MuseumInformation;
import com.bucs.virtualmuseumcurator.location.MuseumLocationActivity;
import com.google.android.gms.maps.model.LatLng;

public class HomePageFragment  extends ListFragment{
	
	private String Address;
	private LatLng latlng;
	private String phone;
	private String title;
	private Activity context;
	private String lat;
	private String lng;
	private ArrayList hours;
	private String parking;
	private String tickteprice;
	private  String membership;
	private String website;
	private String visitorsinfo;
	
	
	
	
   public HomePageFragment(String Address, String lat,String lng,String phone,String title,Activity context,String membership,String website,String ticketprice,ArrayList hours,String parking,String VisitorInfo )
   { 
	   this.Address=Address;
       this.phone=phone;
       this.title=title;
       this.context=context;
       this.lat=lat;
       this.lng=lng;
       this.membership= membership;
       this.website=website;
       this.tickteprice=ticketprice;
       this.hours=hours;
       this.parking=parking;
       this.visitorsinfo=VisitorInfo;
       
	   
   }
	
	public void onActivityCreated(Bundle saveInstanceState)
	{
		   super.onActivityCreated(saveInstanceState);
		    String[] values = new String[] { "information","Tour", "Upcoming Events", "Spotlight of the week",
		        "Collection","Events","Maps","Scan for information" };
		    /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_list_item_1, values);*/
		    com.bucs.virtualmuseumcurator.museumhome.HomePageAdapter adapter= new HomePageAdapter(getActivity(),values);
		    setListAdapter(adapter);
	}


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
  // do something with the data
    	
    	String item= (String) getListAdapter().getItem(position);
    	
    	switch(position){
    	
    	case 0:
    		Intent intentInfo=new Intent(this.context,MuseumInformation.class);
    		intentInfo.putStringArrayListExtra("hours", this.hours);
    		intentInfo.putExtra("membership",this.membership);
    		intentInfo.putExtra("parking",this.parking);
    		intentInfo.putExtra("ticketprice",this.tickteprice);
    		intentInfo.putExtra("website",this.website);
    		intentInfo.putExtra("visitorsinfo", this.visitorsinfo);
    		startActivity(intentInfo);
    		break;
    	
    	  
    	case 4:
    		Intent intentC=new Intent(this.context,CollectionPageActivity.class);
    		//intent.putExtra("", value);
    		Log.d("Before CollectionPageActivity activity","********");
    		startActivity(intentC);
    		Toast.makeText(getActivity(), item+" Collection triggered", Toast.LENGTH_LONG).show();
    		break;
    	
    	case 6:
    		Intent intent =new Intent(this.context,MuseumLocationActivity.class);
    		intent.putExtra("Address", this.Address);
    		intent.putExtra("phone", this.phone);
    		intent.putExtra("title", this.title);
    		intent.putExtra("lat", this.lat);
    		intent.putExtra("lng", this.lng);
    		Log.d("Before start activity",this.Address+this.title+this.phone+this.lat +this.lng);
    		startActivity(intent);
    		Toast.makeText(getActivity(), item+" Map triggered", Toast.LENGTH_LONG).show();
    		break;
    		
    	case 7:
    		//Intent intentScan=new Intent("com.google.zxing.client.SCAN");
    		Intent intentScan =new Intent(this.context,ScannerAcivity.class);
    		startActivity(intentScan);
    		
    		
    		
    	default:
    		    Log.d("No activity","********");
    			Toast.makeText(getActivity(), item+" event triggered default", Toast.LENGTH_LONG).show();
    	
    	}
    	
    	//Toast.makeText(getActivity(), item+" event triggered", Toast.LENGTH_LONG).show();
    }

}
