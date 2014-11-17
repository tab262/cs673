package com.bucs.virtualmuseumcurator.information;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bucs.virtualmuseumcurator.R;

public class MuseumInformation extends ActionBarActivity {
   
	private ArrayList<String> hours=new ArrayList();
	private String ticketprices;
	private String visitorsinfo;
	private String parking;
	private String membership;
	private String website;
	
	
	private TextView tktp;
	private TextView visInfo;
	private TextView memb;
	private TextView webs;
	private TextView pk;
	
	private TextView mon;
	private TextView tue;
	private TextView wed;
	private TextView th;
	private TextView fr;
	private TextView st;
	private TextView sn;
	private ImageView MuseumPicture;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_museum_information);
		Bundle extras=getIntent().getExtras();
		
		this.mon=(TextView)findViewById(R.id.hoursmon);
		this.tue=(TextView)findViewById(R.id.hourstue);
		this.wed=(TextView)findViewById(R.id.hourswed);
		this.th=(TextView)findViewById(R.id.hoursthur);
		this.fr=(TextView)findViewById(R.id.hoursfri);
		this.st=(TextView)findViewById(R.id.hourssat);
		this.sn=(TextView)findViewById(R.id.hourssun);
		
		this.webs=(TextView)findViewById(R.id.websiteinfo);
		this.visInfo=(TextView)findViewById(R.id.visitorinfo);
		this.memb=(TextView)findViewById(R.id.membershipinfo);
	    this.tktp=(TextView)findViewById(R.id.ticketprices);	
		
		
		
		if(extras!=null){
			 this.hours=extras.getStringArrayList("hours");
			 this.membership=extras.getString("membership");
			 this.parking=extras.getString("parking");
			 this.ticketprices=extras.getString("ticketprice");
			 this.visitorsinfo=extras.getString("visitorsinfo");
			 this.website=extras.getString("website");
			 
			 
			    Log.d("list##########", this.hours.toString());
				this.mon.setText( "Monday :"+this.hours.get(0));
				this.tue.setText("Tuesday :"+this.hours.get(1));
				this.wed.setText( "Wednesday :"+this.hours.get(2));
				this.th.setText( "Thursday :"+this.hours.get(3));
				this.fr.setText( "Friday :"+this.hours.get(4));
				this.st.setText( "Saturday :"+this.hours.get(5));
				this.sn.setText( "Sunday :"+this.hours.get(6));
				
				Log.d("%%%%%%%%%%%%%%%%%%", this.membership+this.parking+this.ticketprices+this.website);
				
				/*this.memb.setText(this.membership);
				this.pk.setText(this.parking);*/
				this.tktp.setText(this.ticketprices);
				this.webs.setText(this.website);
				
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.museum_information, menu);
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
