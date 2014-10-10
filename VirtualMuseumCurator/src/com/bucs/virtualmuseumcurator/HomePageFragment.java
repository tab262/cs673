package com.bucs.virtualmuseumcurator;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class HomePageFragment  extends ListFragment{
	
	public void onActivityCreated(Bundle saveInstanceState)
	{
		super.onActivityCreated(saveInstanceState);
		String[] values = new String[] { "Tour", "Upcoming Events", "Spotlight of the week",
		        "Collection","Location","Maps","Register for notification" };
		    /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_list_item_1, values);*/
		com.bucs.virtualmuseumcurator.HomePageAdapter adapter= new HomePageAdapter(getActivity(),values);
		    setListAdapter(adapter);
	}


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
  // do something with the data
    	
    	String item= (String) getListAdapter().getItem(position);
    	Toast.makeText(getActivity(), item+" event triggered", Toast.LENGTH_LONG).show();
    }

}
