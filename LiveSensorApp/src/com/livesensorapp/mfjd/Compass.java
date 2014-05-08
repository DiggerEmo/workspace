package com.livesensorapp.mfjd;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Compass extends Activity implements OnClickListener, SensorEventListener
{
	private ImageButton btnBack;
	private SensorManager mSensorManager;
	private Sensor mComp;
	private TextView compX,compY,compZ;
	
	@Override
	public final void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.compass_values);
	    
	    btnBack=(ImageButton) this.findViewById(R.id.btn_back_sensor);
	    btnBack.setOnClickListener(this);
	    
	    Float[] results = new Float[3];
	    compX=(TextView) this.findViewById(R.id.comp_val_x);
	    compY=(TextView) this.findViewById(R.id.comp_val_y);
	    compZ=(TextView) this.findViewById(R.id.comp_val_z);
	    
	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mComp = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	    
	 }
	
	@Override
	public void onClick(View v) 
	{
		if(v==findViewById(R.id.btn_back_sensor)) 
		{
			finish();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		
		if(event.sensor ==mComp)
		{
			compX.setText(Float.toString(event.values[0]));
			compY.setText(Float.toString(event.values[1]));
			compZ.setText(Float.toString(event.values[2]));
			Log.e(SENSOR_SERVICE, "Test--------------------------------------");
		}
		
		
	}
	
	@Override
	protected void onResume() 
	{
	    super.onResume();
	    mSensorManager.registerListener(this, mComp, SensorManager.SENSOR_DELAY_NORMAL);
	}

	  
	@Override
	protected void onPause() 
	{
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	}
	

}
