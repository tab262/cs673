package com.bucs.virtualmuseumcurator.collections;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bucs.virtualmuseumcurator.datamodel.CollectionRowContent;

public class CollectionPageFragment extends ListFragment{
	
	public void onActivityCreated(Bundle saveInstanceState)
	{
		super.onActivityCreated(saveInstanceState);
		
		
	}


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
  // do something with the data
    	
    	String item= (String) getListAdapter().getItem(position);
    	Toast.makeText(getActivity(), item+" event triggered", Toast.LENGTH_LONG).show();
    }

	


}
