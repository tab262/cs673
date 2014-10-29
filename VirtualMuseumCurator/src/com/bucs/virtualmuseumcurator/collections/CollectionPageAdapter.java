package com.bucs.virtualmuseumcurator.collections;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.datamodel.ArtInfoDataModel;
import com.bucs.virtualmuseumcurator.datamodel.CollectionRowContent;

public class CollectionPageAdapter extends ArrayAdapter<ArrayList>{
	
	public CollectionPageAdapter(Context context, int resource,
			ArrayList objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context=(Activity) context;
		this.exhibits=objects;
	}




	private final Activity  context;
	private final ArrayList<CollectionRowContent> exhibits; 
	
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
		public TextView getEndDate() {
			return enddate;
		}
		public void setEndDate(TextView endDate) {
			enddate = endDate;
		}
		
		public TextView getStartdate() {
			return startdate;
		}
		public void setStartdate(TextView startdate) {
			this.startdate = startdate;
		}
		
		public TextView getDescription() {
			return description;
		}
		public void setDescription(TextView description) {
			this.description = description;
		}
		
		
		public TextView Name;
		public TextView startdate;
		public TextView enddate;
		public TextView description;
		public ImageView image;
			
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
			Log.d("fill data nulllllll !!!!!!!!!","!!!!!!!!!!!!!!");
			viewholder.startdate=(TextView) rowView.findViewById(R.id.startdate_art);
			viewholder.image=(ImageView) rowView.findViewById(R.id.art_image);
			viewholder.enddate=(TextView) rowView.findViewById(R.id.enddate_art);
			viewholder.description=(TextView) rowView.findViewById(R.id.description_art);
			viewholder.Name=(TextView)rowView.findViewById(R.id.name_art);
			rowView.setTag(viewholder);
		}
		

		
		//fill data 
		
		
		//TypedArray pictures=context.getResources().obtainTypedArray(R.array.home_page_pictures);
		ViewHolder holder=(ViewHolder) rowView.getTag();
		com.bucs.virtualmuseumcurator.datamodel.CollectionRowContent row=(CollectionRowContent) this.exhibits.get(position);
		Log.d("fill data !!!!!!!!!",row.getArtName());
		holder.Name.setText(row.getArtName());
		holder.enddate.setText(row.getArtendDate());
		holder.startdate.setText(row.getArtstartDate());
		holder.description.setText(row.getArtDescription());
		//holder.image.setImageBitmap(row.getArtImage());
		
		//image
		//holder.image.setImageResource(R.drawable.upcoming);*/
		
		return rowView;
	}
	
	

}
