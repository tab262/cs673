package com.bucs.virtualmuseumcurator.collections;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.bucs.virtualmuseumcurator.ArtInfo.ArtInfoActivity;
import com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel;

public class CollectionPageFragment extends ListFragment{
	
	
	private ArrayList<ArtInfoDataModel> artobjects=new ArrayList<ArtInfoDataModel>();
	private Activity context;
	
	
	public CollectionPageFragment(ArrayList<ArtInfoDataModel> artlist, Activity context)
	{
		this.artobjects=artlist;
		this.context=context;
	}
	
	public void onActivityCreated(Bundle saveInstanceState)
	{
		super.onActivityCreated(saveInstanceState);
		
		
	}


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
         // do something with the data
        //Pass the data to the Art Activity
    	Intent intent=new Intent();
    	intent.setClass(this.context, ArtInfoActivity.class);
    	intent.putExtra("index",position);
    	Bundle bundle=new Bundle();
    	bundle.putSerializable("artobj", this.artobjects.get(position));
    	intent.putExtras(bundle);
    	startActivity(intent);
    }

	


}
