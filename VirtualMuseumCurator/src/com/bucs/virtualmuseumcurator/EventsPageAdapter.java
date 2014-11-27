package com.bucs.virtualmuseumcurator;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bucs.virtualmuseumcurator.datamodel.CollectionRowContent;

public class EventsPageAdapter extends ArrayAdapter<CollectionRowContent>{
	private final Activity  context;
	private final CollectionRowContent[] eventContent;
	
	static class ViewHolder
	{
		public ImageView getImage() {
			return image;
		}
		public void setImage(ImageView image) {
			this.image = image;
		}
		public TextView Name;
		public TextView Duration;
		public TextView Location;
		public ImageView image;
	}
	
	public EventsPageAdapter(Activity context,CollectionRowContent[] eventContent)
	{  
		super(context,R.layout.rowlayoutcollections,eventContent);
		this.context=context;
		this.eventContent=eventContent;
	}
	
	public View getView(int position,View convertView,ViewGroup parent )
	{
		View rowView=convertView;
		
		//reuse views
		if(rowView==null)
		{
			LayoutInflater inflater=context.getLayoutInflater();
			rowView=inflater.inflate(R.layout.rowlayoutcollections, null);
			
			//configure view holder
			ViewHolder viewholder = new ViewHolder();
			viewholder.Name=(TextView) rowView.findViewById(R.id.name_art);
			//viewholder.Duration=(TextView) rowView.findViewById(R.id.duration_art);
			//viewholder.Location=(TextView) rowView.findViewById(R.id.location_art);
			viewholder.image=(ImageView) rowView.findViewById(R.id.art_image);
			rowView.setTag(viewholder);
		}
		

		
		//fill data 
		TypedArray pictures=context.getResources().obtainTypedArray(R.array.home_page_pictures);
		ViewHolder holder=(ViewHolder) rowView.getTag();
		CollectionRowContent s=eventContent[position];
		
		holder.Name.setText( s.getArtName());
		holder.Duration.setText(s.getArtDuration());
		holder.Location.setText(s.getArtLocation());
		holder.image.setImageDrawable(pictures.getDrawable(position));
		
		
		//image
		//holder.image.setImageResource(R.drawable.upcoming);
		
		return rowView;
		
	}

}
