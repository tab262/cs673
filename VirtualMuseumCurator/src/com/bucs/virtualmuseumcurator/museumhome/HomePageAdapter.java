package com.bucs.virtualmuseumcurator.museumhome;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.R.array;
import com.bucs.virtualmuseumcurator.R.id;
import com.bucs.virtualmuseumcurator.R.layout;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePageAdapter extends ArrayAdapter<String>{
	
	private final Activity  context;
	private final String[] names;
	
	static class ViewHolder
	{
		public ImageView getImage() {
			return image;
		}
		public void setImage(ImageView image) {
			this.image = image;
		}
		public TextView text;
		public ImageView image;
	}
	
	public HomePageAdapter(Activity context,String[] names)
	{  
		super(context,R.layout.rowlayouthomepage,names);
		this.context=context;
		this.names=names;
	}
	
	public View getView(int position,View convertView,ViewGroup parent )
	{
		View rowView=convertView;
		
		//reuse views
		if(rowView==null)
		{
			LayoutInflater inflater=context.getLayoutInflater();
			rowView=inflater.inflate(R.layout.rowlayouthomepage, null);
			
			//configure view holder
			ViewHolder viewholder = new ViewHolder();
			viewholder.text=(TextView) rowView.findViewById(R.id.frag_text);
			viewholder.image=(ImageView) rowView.findViewById(R.id.frag_image);
			rowView.setTag(viewholder);
		}
		

		
		//fill data 
		TypedArray pictures=context.getResources().obtainTypedArray(R.array.home_page_pictures);
		ViewHolder holder=(ViewHolder) rowView.getTag();
		String s=names[position];
		
		holder.text.setText(s);
		//image
		holder.image.setImageDrawable(pictures.getDrawable(position));
		
		
	
		
		return rowView;
		
	}
	
	
	

}
