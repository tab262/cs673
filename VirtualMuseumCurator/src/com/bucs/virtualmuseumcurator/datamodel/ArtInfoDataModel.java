package com.bucs.virtualmuseumcurator.datamodel;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.util.Log;


public class ArtInfoDataModel implements Serializable {
	
	private String Name;
	private String Date;
	private String location;
	private String Type;
	private String Description;
	private Bitmap Picturebitmap;
	private String pictureurlpath;
	private String mediaurlpath;
	private String Artid;
	private String Collection;
	
	public String getArtid() {
		return Artid;
	}

	public void setArtid(String artid) {
		Artid = artid;
	}

	public String getCollection() {
		return Collection;
	}

	public void setCollection(String collection) {
		Collection = collection;
	}

	public String getArtistimageurl() {
		return Artistimageurl;
	}

	public void setArtistimageurl(String artistimageurl) {
		Artistimageurl = artistimageurl;
	}

	public Bitmap getArtistimageBitmap() {
		return ArtistimageBitmap;
	}

	public void setArtistimageBitmap(Bitmap artistimageBitmap) {
		ArtistimageBitmap = artistimageBitmap;
	}

	private String Artistname;
	private String Artistnationality;
	private String Artistbiography;
	private String ArtistdateOfBirth;
	private String ArtistdateOfDeath;
	private String Artistimageurl;
	private Bitmap ArtistimageBitmap;
	private String Artistowner;
	
	
	
	
	
	
		
	public Bitmap getPicturebitmap() {
		return Picturebitmap;
	}

	public void setPicturebitmap(Bitmap picture) {
		Picturebitmap = picture;
	}

	public String getPictureurlpath() {
		return pictureurlpath;
	}

	public void setPictureurlpath(String pictureurlpath) {
		this.pictureurlpath = pictureurlpath;
	}

	public String getMediaurlpath() {
		return mediaurlpath;
	}

	public void setMediaurlpath(String mediaurlpath) {
		this.mediaurlpath = mediaurlpath;
	}

	public String getArtistname() {
		return Artistname;
	}

	public void setArtistname(String artistname) {
		Artistname = artistname;
	}

	public String getArtistnationality() {
		return Artistnationality;
	}

	public void setArtistnationality(String artistnationality) {
		Artistnationality = artistnationality;
	}

	public String getArtistbiography() {
		return Artistbiography;
	}

	public void setArtistbiography(String artistbiography) {
		Artistbiography = artistbiography;
	}

	public String getArtistdateOfBirth() {
		return ArtistdateOfBirth;
	}

	public void setArtistdateOfBirth(String artistdateOfBirth) {
		ArtistdateOfBirth = artistdateOfBirth;
	}

	public String getArtistdateOfDeath() {
		return ArtistdateOfDeath;
	}

	public void setArtistdateOfDeath(String artistdateOfDeath) {
		ArtistdateOfDeath = artistdateOfDeath;
	}

	public String getArtistimage() {
		return Artistimageurl;
	}

	public void setArtistimage(String artistimage) {
		Artistimageurl = artistimage;
	}

	public String getArtistowner() {
		return Artistowner;
	}

	public void setArtistowner(String artistowner) {
		Artistowner = artistowner;
	}

	public ArtInfoDataModel(JSONObject obj){
		
		
		try {
			this.Name=obj.getString("title");
			this.Date=obj.getString("creationDate");
			this.Description=obj.getString("description");
			this.pictureurlpath=obj.getString("image");
			this.mediaurlpath=obj.getString("audio");
			this.Collection=obj.getString("collection");
			this.Artid=obj.getString("id");
			
			//for the artist
			JSONObject Artistobject=obj.getJSONObject("artist");
			this.Artistname=Artistobject.getString("name");
			this.ArtistdateOfBirth=Artistobject.getString("dateOfBirth");
			this.ArtistdateOfDeath=Artistobject.getString("dateOfDeath");
			this.Artistimageurl=Artistobject.getString("image");
			this.Artistowner=Artistobject.getString("owner");
			//this.Type=obj.getString("artType");
			
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
				JSONObject entry=arry.getJSONObject(i);
				Log.d("artobj!!!!!!!!!!!!!", entry.toString());
				list.add(new ArtInfoDataModel((JSONObject) entry ));
				Log.d("listart!!!!!!!!!!!!!", list.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return list;
		
	}


	
}
