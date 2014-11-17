package com.bucs.virtualmuseumcurator.datamodel;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;

public class CollectionRowContent {
	
	public String getArtlink() {
		return artlink;
	}

	public void setArtlink(String artlink) {
		this.artlink = artlink;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	private String artName;
	private String artDuration;
	private String artLocation;
	private String artstartDate;
	private String artendDate;
	private String artDescription;
	private String artlink;
	private Bitmap bitmap;
	
	
	public CollectionRowContent(JSONObject jobj){
		try{
			this.artName=jobj.getString("title");
			this.artDescription=jobj.getString("description");
			this.artstartDate=jobj.getString("startDate");
			this.artendDate=jobj.getString("endDate");
			JSONArray jarr=jobj.getJSONArray("art_objects");
			JSONObject jsonartobj=jarr.getJSONObject(0);
			artlink=jsonartobj.getString("image");
			
			
		}
		catch (JSONException e){
			
			e.printStackTrace();
		}
		
	}
	
	public String getArtstartDate() {
		return artstartDate;
	}

	public void setArtstartDate(String artstartDate) {
		this.artstartDate = artstartDate;
	}

	public String getArtendDate() {
		return artendDate;
	}

	public void setArtendDate(String artendDate) {
		this.artendDate = artendDate;
	}

	public String getArtDescription() {
		return artDescription;
	}

	public void setArtDescription(String artDescription) {
		this.artDescription = artDescription;
	}

	private String artImage;
	
	public CollectionRowContent(String name,String duration,String location,String image){
		this.artName=name;
		this.artDuration=duration;
		this.artLocation=location;
		this.artImage=image;
	}

	public String getArtName() {
		return artName;
	}

	public void setArtName(String artName) {
		this.artName = artName;
	}

	public String getArtDuration() {
		return artDuration;
	}

	public void setArtDuration(String artDuration) {
		this.artDuration = artDuration;
	}

	public String getArtLocation() {
		return artLocation;
	}

	public void setArtLocation(String artLocation) {
		this.artLocation = artLocation;
	}

	public String getArtImage() {
		return artImage;
	}

	public void setArtImage(String artImage) {
		this.artImage = artImage;
	}
	
	public static ArrayList<CollectionRowContent> fromJSON(JSONArray jsonresponse){
		
		ArrayList<CollectionRowContent> artpieces=new ArrayList<CollectionRowContent>();
		
		for (int i=0; i<jsonresponse.length();i++){
			try {
				Log.d("content!!!!!!!", jsonresponse.getJSONObject(i).toString());
				artpieces.add(new CollectionRowContent(jsonresponse.getJSONObject(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return artpieces;
	}

}
