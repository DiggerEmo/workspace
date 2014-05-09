/*
 * Live Sensor App
 * Version: 1.2
 * Date: 	09.05.2014
 * Authors: Johanna Ditzig, Manuel Fiedler
 * 
 * Menü mit 4 Buttons, die je in eine Activity führen, 
 * welche verschiedene Sensoren auslesen. 
 */
package com.livesensorapp.mfjd;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
	private Button illuminanceBtn;
	private Button accelerometerBtn;
	private Button compassBtn;
	private Button gpsBtn;


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
        this.initElements();
	}


	/**
	 * initializes Button-Elements and sets click-listeners
	 */
	private void initElements() 
	{
    	this.illuminanceBtn = (Button)findViewById(R.id.btn_illuminance);
    	this.accelerometerBtn = (Button)findViewById(R.id.btn_touchLocations);
    	this.compassBtn = (Button)findViewById(R.id.btn_compass);
    	this.gpsBtn = (Button)findViewById(R.id.btn_gps);

    	this.illuminanceBtn.setOnClickListener(this);
    	this.accelerometerBtn.setOnClickListener(this);
    	this.compassBtn.setOnClickListener(this);
    	this.gpsBtn.setOnClickListener(this);
	}

	
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
    	{
    	case R.id.btn_illuminance:
    		Intent sensorValuesIntent = new Intent(MainActivity.this, Illuminance.class);
    		startActivity(sensorValuesIntent);
    		break;
    		
    	case R.id.btn_touchLocations:
    		Intent touchLocationsIntent = new Intent(MainActivity.this, Accelerometer.class);
    		startActivity(touchLocationsIntent);
    		break;
    		
        case R.id.btn_compass:
        	Intent compassIntent = new Intent(MainActivity.this, Compass.class);
        	startActivity(compassIntent);
        	break;
    		
    	case R.id.btn_gps:
    		Intent mapIntent = new Intent(MainActivity.this, GPSPosition.class);
        	startActivity(mapIntent);
        	break;
    		

    	}
	}

}
