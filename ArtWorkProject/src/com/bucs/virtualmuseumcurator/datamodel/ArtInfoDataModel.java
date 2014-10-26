package com.bucs.virtualmuseumcurator.datamodel;

import android.graphics.Bitmap;
import android.media.MediaPlayer;

public class ArtInfoDataModel {
	
	private String Artist_Name;
	private String Museum_Name;
	private String Art_Type;	
	private String Creation_Date;
	private String Description;
	private String Title;
	private MediaPlayer Art_Audio;
	private Bitmap ArtImage;

	public MediaPlayer getArtaudio() {
		return Art_Audio;
	}

	public void setArtAudio(MediaPlayer audio) {
		this.Art_Audio = audio;
	}

	public String getArtistName() {
		return Artist_Name;
	}

	public void setArtistName(String artist) {
		Artist_Name = artist;
	}

	public String getMuseumName() {
		return Museum_Name;
	}

	public void setMuseumName(String museum) {
		Museum_Name = museum;
	}

	public String getArtType() {
		return Art_Type;
	}

	public void setArtType(String artType) {
		this.Art_Type = artType;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
	
	public String getCreationDate() {
		return Creation_Date;
	}

	public void setCreationDate(String creationDate) {
		Creation_Date = creationDate;
	}
	
	public Bitmap getImage() {
		return ArtImage;
	}
	public void setImage(Bitmap image) {
		ArtImage = image;
	}
	
}
