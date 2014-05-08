package com.livesensorapp.mfjd;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Accelerometer extends Activity implements OnClickListener, SensorEventListener
{
	private ImageButton btnBack;
	private TextView accX,accY,accZ;
	private SensorManager mSensorManager;
	private Sensor mAcc;
	
	@Override
	public final void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.accelerometer_values);
	    
	    accX=(TextView) this.findViewById(R.id.acc_val_x);
	    accY=(TextView) this.findViewById(R.id.acc_val_y);
	    accZ=(TextView) this.findViewById(R.id.acc_val_z);
	    
	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    
	    btnBack=(ImageButton) this.findViewById(R.id.btn_back_sensor);
	    btnBack.setOnClickListener(this);
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
	public void onAccuracyChanged(Sensor sensor, int accuracy) 
	{
		// TODO Auto-generated method stub
	}


	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		if(event.sensor ==mAcc)
		{
			accX.setText(""+event.values[0]);
			accY.setText(""+event.values[1]);
			accZ.setText(""+event.values[2]);
		}
		
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
	    mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
	}
	  
	@Override
	protected void onPause() 
	{
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	}

}
