package com.bucs.virtualmuseumcurator;

import com.bucs.virtualmuseumcurator.HomePageAdapter.ViewHolder;
import com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExhibitionPageAdapter extends ArrayAdapter<ArtInfoDataModel>{
	
	private final Activity  context;
	private final com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel[] exhibits; 
	
	static class ViewHolder
	{
		public ImageView getImage() {
			return image;
		}
		public void setImage(ImageView image) {
			this.image = image;
		}
		
		public TextView getName() {
			return Name;
		}
		public void setName(TextView name) {
			Name = name;
		}
		public TextView getDuration() {
			return Duration;
		}
		public void setDuration(TextView duration) {
			Duration = duration;
		}
		
		
		public TextView getLocation() {
			return location;
		}
		public void setLocation(TextView location) {
			this.location = location;
		}
		
		
		public TextView Name;
		public TextView Duration;
		public TextView location;
		public ImageView image;
			
	}
	
	public ExhibitionPageAdapter(Activity context,com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel[] Exhibits)
	{  
		super(context,R.layout.rowlayoutcollections,Exhibits);
		this.context=context;
		this.exhibits=Exhibits;
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
			viewholder.Duration=(TextView) rowView.findViewById(R.id.duration_art);
			viewholder.image=(ImageView) rowView.findViewById(R.id.art_image);
			viewholder.location=(TextView) rowView.findViewById(R.id.location_art);
			viewholder.Name=(TextView)rowView.findViewById(R.id.art_name);
			rowView.setTag(viewholder);
		}
		

		
		//fill data 
		
		
		/*TypedArray pictures=context.getResources().obtainTypedArray(R.array.home_page_pictures);
		ViewHolder holder=(ViewHolder) rowView.getTag();
		String s=names[position];
		
		holder.text.setText(s);
		holder.image.setImageDrawable(pictures.getDrawable(position));
		
		//image
		//holder.image.setImageResource(R.drawable.upcoming);*/
		
		return rowView;
	}
	
	

}
