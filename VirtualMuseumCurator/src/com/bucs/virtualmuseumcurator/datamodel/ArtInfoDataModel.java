package com.bucs.virtualmuseumcurator.datamodel;

import android.media.MediaPlayer;

public class ArtInfoDataModel {
	
	private String Name;
	private String Date;
	private String location;
	
	private String Description;
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
	
}
