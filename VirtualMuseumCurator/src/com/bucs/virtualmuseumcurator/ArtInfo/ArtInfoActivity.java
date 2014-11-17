package com.bucs.virtualmuseumcurator.ArtInfo;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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
	    ArtInfoDataModel artobj=(ArtInfoDataModel)bundle.getSerializable("artobj");
	    this.artTitle=(TextView)findViewById(R.id.art_title);
	    this.artDate=(TextView)findViewById(R.id.art_date);
	    this.artDescription=(TextView)findViewById(R.id.art_description);
	    this.artType=(TextView)findViewById(R.id.art_type);
	    this.artImage=(ImageView)findViewById(R.id.art_image);
	    this.context=this;
	    //this.shareFaceButton
	    	
	    artTitle.setText(artobj.getName());
	    artDate.setText(artobj.getDate());
	    artDescription.setText(artobj.getDescription());
	    artType.setText(artobj.getType());
	    //artImage.setImageBitmap(artobj.get);
	    
	    
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
