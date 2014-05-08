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

public class Illuminance extends Activity implements OnClickListener, SensorEventListener
	{
		private ImageButton btnBack;
		private Sensor mLight;
		private SensorManager mSensorManager;
		private TextView light;
		
		@Override
		public final void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
		    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		    setContentView(R.layout.illuminance_values);
		    
		    light=(TextView) this.findViewById(R.id.light_val);
		    
		    btnBack=(ImageButton) this.findViewById(R.id.btn_back_sensor);
		    btnBack.setOnClickListener(this);
		    
		    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		    mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
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
			if(event.sensor ==mLight)
			{
				light.setText(""+event.values[0]);
			}
		}
		
		@Override
		protected void onResume() 
		{
		    super.onResume();
		    mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
		}

		  
		@Override
		protected void onPause() 
		{
		    super.onPause();
		    mSensorManager.unregisterListener(this);
		}
		
}
