package com.livesensorapp.mfjd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class GPSPosition extends Activity implements OnClickListener, LocationListener
{

	private ImageButton btnBack;
	private LocationManager locManager;
	private TextView longitude, latitude, altitude,accuracy;
	
	@Override
	public final void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.gps_values);
	    
	    longitude=(TextView) this.findViewById(R.id.long_val);
	    latitude=(TextView) this.findViewById(R.id.lati_val);
	    altitude=(TextView) this.findViewById(R.id.alti_val);
	    accuracy=(TextView) this.findViewById(R.id.accu_val);
	    
	    btnBack=(ImageButton) this.findViewById(R.id.btn_back_sensor);
	    btnBack.setOnClickListener(this);
	    
	    locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    
	    if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
	    	AlertDialog.Builder noGPS = new AlertDialog.Builder(this);
	    	noGPS.setTitle("Standort konnte nicht ermittelt werden");
	    	noGPS.setCancelable(false);
	    	noGPS.setMessage("GPS (bzw. Standortermittlung) ist nicht eingeschaltet!");
	    	noGPS.setPositiveButton("Schlieﬂen", new DialogInterface.OnClickListener() {
	    		public void onClick(DialogInterface dialog, int which) {
	    			finish();
	    		}
	    	});
	        noGPS.show();
	     } else {
	    	locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
	    }
	    //locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	 }
	
	@Override
	public void onClick(View v) 
	{
		if(v==findViewById(R.id.btn_back_sensor)) 
		{
			finish();
		}
	}

	public void onPause() {
		super.onPause();
		locManager.removeUpdates(this);
		finish();
	}

	public void onLocationChanged(Location location) {
		if (location != null) {
			latitude.setText(Double.toString(location.getLatitude()));
			longitude.setText(Double.toString(location.getLongitude()));
			altitude.setText(Double.toString(location.getAltitude()));
			accuracy.setText(Double.toString(location.getAccuracy()));
		
			locManager.removeUpdates(this);
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
