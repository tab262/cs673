package com.bucs.virtualmuseumcurator.ArtInfo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel;

public class ArtInfoActivity extends ActionBarActivity {
	
	private TextView artTitle;
	private TextView artDate;
	private TextView artDescription;
	private TextView artType;
	private ImageView artImage;

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
	    
	    	
	    artTitle.setText(artobj.getName());
	    artDate.setText(artobj.getDate());
	    artDescription.setText(artobj.getDescription());
	    artType.setText(artobj.getType());
	    //artImage.setImageBitmap(artobj.get);
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
