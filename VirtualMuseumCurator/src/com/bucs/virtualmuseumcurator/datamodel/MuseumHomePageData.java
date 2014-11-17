package com.bucs.virtualmuseumcurator.datamodel;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class MuseumHomePageData {

	
	private String Image;	
	private String MuseumName;
	private String Description;
	private String streetAdress;
	private String city;
	private String state;
	private String zipcode;
	private String VisitorsInfo;
	private String Ticketprices;
	private String parking;
	private String ticket_prices;
	private String visitor_info;
	private String membership;
	private String website;
	private Bitmap MuseumImageinBit;
	
	
	public Bitmap getMuseumImageinBit() {
		return MuseumImageinBit;
	}
	public void setMuseumImageinBit(Bitmap museumImageinBit) {
		MuseumImageinBit = museumImageinBit;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getTicket_prices() {
		return ticket_prices;
	}
	public void setTicket_prices(String ticket_prices) {
		this.ticket_prices = ticket_prices;
	}
	public String getVisitor_info() {
		return visitor_info;
	}
	public void setVisitor_info(String visitor_info) {
		this.visitor_info = visitor_info;
	}
	public String getMembership() {
		return membership;
	}
	public void setMembership(String membership) {
		this.membership = membership;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	private ArrayList Hours= new ArrayList();
	
	public String getVisitorsInfo() {
		return VisitorsInfo;
	}
	public void setVisitorsInfo(String visitorsInfo) {
		VisitorsInfo = visitorsInfo;
	}
	public String getTicketprices() {
		return Ticketprices;
	}
	public void setTicketprices(String ticketprices) {
		Ticketprices = ticketprices;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	private String latitude;
	private String longitude;
	
	
	private String hour_M;
	private String hour_T;
	private String hour_W;
	private String hour_R;
	private String hour_F;
	private String hour_ST;
	private String hour_SN;

	
	
	public String getStreetAdress() {
		return streetAdress;
	}
	public void setStreetAdress(String streetAdress) {
		this.streetAdress = streetAdress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String f) {
		this.latitude = f;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getHour_M() {
		return hour_M;
	}
	public void setHour_M(String hour_M) {
		this.hour_M = hour_M;
	}
	public String getHour_T() {
		return hour_T;
	}
	public void setHour_T(String hour_T) {
		this.hour_T = hour_T;
	}
	public String getHour_W() {
		return hour_W;
	}
	public void setHour_W(String hour_W) {
		this.hour_W = hour_W;
	}
	public String getHour_R() {
		return hour_R;
	}
	public void setHour_R(String hour_R) {
		this.hour_R = hour_R;
	}
	public String getHour_F() {
		return hour_F;
	}
	public void setHour_F(String hour_F) {
		this.hour_F = hour_F;
	}
	public String getHour_ST() {
		return hour_ST;
	}
	public void setHour_ST(String hour_ST) {
		this.hour_ST = hour_ST;
	}
	public String getHour_SN() {
		return hour_SN;
	}
	public void setHour_SN(String hour_SN) {
		this.hour_SN = hour_SN;
	}
	private String contact;
	
	private String Hour;
	private String Collections;
	private String location;
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	private String Events;
	private String  Maps;
	private String HighLights;
	
	
	public String getImage() {
		return Image;
	}
	public ArrayList getHours() {
		return Hours;
	}
	public void setHours(ArrayList hours) {
		Hours = hours;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getMuseumName() {
		return MuseumName;
	}
	public void setMuseumName(String museumName) {
		MuseumName = museumName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public String getHour() {
		return Hour;
	}
	public void setHour(String hour) {
		Hour = hour;
	}
	public String getCollections() {
		return Collections;
	}
	public void setCollections(String collections) {
		Collections = collections;
	}
	public String getEvents() {
		return Events;
	}
	public void setEvents(String events) {
		Events = events;
	}
	public String getMaps() {
		return Maps;
	}
	public void setMaps(String maps) {
		Maps = maps;
	}
	public String getHighLights() {
		return HighLights;
	}
	public void setHighLights(String highLights) {
		HighLights = highLights;
	}

}
