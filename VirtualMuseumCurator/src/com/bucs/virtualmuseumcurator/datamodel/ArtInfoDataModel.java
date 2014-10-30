package com.bucs.virtualmuseumcurator.datamodel;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class ArtInfoDataModel implements Serializable {
	
	private String Name;
	private String Date;
	private String location;
	private String Type;
	private String Description;
	
	public ArtInfoDataModel(JSONObject obj){
		
		
		try {
			this.Name=obj.getString("title");
			this.Date=obj.getString("creationDate");
			this.Description=obj.getString("description");
			this.Type=obj.getString("artType");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	private MediaPlayer art_audio;
	private MediaPlayer art_video;
	

	public MediaPlayer getArt_audio() {
		return art_audio;
	}

	public void setArt_audio(MediaPlayer art_audio) {
		this.art_audio = art_audio;
	}

	public MediaPlayer getArt_video() {
		return art_video;
	}

	public void setArt_video(MediaPlayer art_video) {
		this.art_video = art_video;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	
	
	public static ArrayList<ArtInfoDataModel> fromJSONArray(JSONArray arry ){
		
		ArrayList<ArtInfoDataModel> list=new ArrayList<ArtInfoDataModel>();
		
		
		for (int i =0; i<arry.length();i++){
			try {
				JSONObject firstentry=arry.getJSONObject(i);
				Log.d("artobj!!!!!!!!!!!!!", firstentry.toString());
				JSONArray art= firstentry.getJSONArray("art_objects");
				list.add(new ArtInfoDataModel((JSONObject) art.get(0) ));
				Log.d("listart!!!!!!!!!!!!!", art.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return list;
		
	}


	
}
