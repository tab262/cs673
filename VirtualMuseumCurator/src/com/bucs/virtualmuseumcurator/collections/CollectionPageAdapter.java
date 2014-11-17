package com.bucs.virtualmuseumcurator.collections;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bucs.virtualmuseumcurator.R;
import com.bucs.virtualmuseumcurator.collections.CollectionPageAdapter.ViewHolder.ImageArtobj;
import com.bucs.virtualmuseumcurator.datamodel.CollectionRowContent;

public class CollectionPageAdapter extends ArrayAdapter<ArrayList>{
	
	public CollectionPageAdapter(Context context, int resource,
			ArrayList objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context=(Activity) context;
		this.exhibits=objects;
	}
	
	
	private ArrayList<Bitmap> Bitmapobjects=new  ArrayList<Bitmap>();
	private int counter=0;






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
		public class ImageArtobj extends AsyncTask<String, Void, Bitmap> {

			   @Override
			   protected Bitmap doInBackground(String... params) {
				 try{ Log.d("ImagebackgroundThread","ImageSearch");

				      Log.d("Beforeeeeeeeeeeeeeee!!!!!!!!!!!!!!!!!!!!!!!", "###########");
			          Bitmap bitmap = getBitmapFromURL("https://s3.amazonaws.com/edocent/museum_images/mfa.jpg");
			          
			          return bitmap; 
				  
			     
				 }
				 catch(Exception e)
				 {
					 Log.d("errorrrrrr",e.toString());
				 }
				return null;
			   }
			   
			   @Override
			   protected void onPostExecute(final Bitmap result) { 
				   Log.d("got bit map in list!!!!!!!!!!", "got bit map in list");
				   image.setImageBitmap(result);
				   
			   }



				 public Bitmap getBitmapFromURL(String src)
				    {
				    	try {
				    		Log.d("Bit!!!!!!!!!!!!!!!!!!!!!!!", src);
							URL url = new URL(src);
							HttpURLConnection connection = (HttpURLConnection) url.openConnection();
							connection.setDoInput(true);
							connection.connect();
							InputStream input = connection.getInputStream();
							Bitmap myBitmap = BitmapFactory.decodeStream(input);
							return myBitmap;
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							return null;
						}
				    }

			      
			}
			
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
		
		ViewHolder holder=(ViewHolder) rowView.getTag();
		com.bucs.virtualmuseumcurator.datamodel.CollectionRowContent row=(CollectionRowContent) this.exhibits.get(position);
		Log.d("fill data !!!!!!!!!",row.getArtName());
		holder.Name.setText(row.getArtName());
		holder.enddate.setText(row.getArtendDate());
		holder.startdate.setText(row.getArtstartDate());
		holder.description.setText(row.getArtDescription());
		
		//image
		CollectionPageAdapter.ViewHolder.ImageArtobj task= holder.new ImageArtobj(); 
        task.execute(new String[] {"https://s3.amazonaws.com/edocent/"+row.getArtlink()});
	    //holder.image.setImageBitmap(bitmap);

		return rowView;
	}
	
	

}
