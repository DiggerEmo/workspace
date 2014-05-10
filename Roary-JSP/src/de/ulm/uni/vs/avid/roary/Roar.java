package de.ulm.uni.vs.avid.roary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Roar {
	private String text_;
	private String author_;
	private Date creationDate_;
	private static Integer instances_ = 0;
	private final int instance_id_;
	

	public int getId() {
		return instance_id_;
	}

	public Roar(){
		creationDate_ = new Date();
		synchronized (instances_) {
			instances_ = instances_ + 1;
			instance_id_ =  instances_;
		}
	}

	public void setText(String text){
		text_ = text;
	}

	public String getText(){
		return text_;
	}

	public void setAuthor(String auth){
		author_ = auth;
	}

	public String getAuthor(){
		return author_;
	}

	public String getCreationTime(){
		//return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(creationDate_);
		return new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US).format(creationDate_);
	}
}
