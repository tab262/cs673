package com.bucs.virtualmuseumcurator;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ExhibitionPageFragment extends ListFragment{
	
	public void onActivityCreated(Bundle saveInstanceState)
	{
		super.onActivityCreated(saveInstanceState);
		
		
		/*
		 * 
		 * Get the Json object art and construct ArtInfoDataModel
		 * */
		
		
		String[] values = new String[] { "Tour", "Upcoming Events", "Spotlight of the week",
		        "Collection","Register for notification" };
	/*	com.bucs.virtualmuseumcurator.ExhibitionPageAdapter adapter= new ExhibitionPageAdapter(getActivity(),values);
		setListAdapter(adapter);*/
	}


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
  // do something with the data
    	
    	String item= (String) getListAdapter().getItem(position);
    	Toast.makeText(getActivity(), item+" event triggered", Toast.LENGTH_LONG).show();
    }

	


}
