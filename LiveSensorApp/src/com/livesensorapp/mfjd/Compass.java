/*
 * Compass
 * 
 * Da TYPE_ORIENTATION deprecated ist, soll zur Orientatierung die Funktion
 * getOrientation() verwendet werden, diese benötigt Daten aus den Sensoren:
 * - TYPE_ACCELEROMETER
 * - TYPE_MAGNETIC_FIELD
 */

package com.livesensorapp.mfjd;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
  

public class Compass extends Activity implements SensorEventListener, OnClickListener{

	private SensorManager mSensorManager;
	private Sensor mAcc, mComp;
   
	private float[] valuesAcc;
	private float[] valuesComp;
   
	private float[] matrixR;
	private float[] matrixI;
	private float[] matrixValues;
	
	private ImageButton btnBack;
	private TextView compAzi, compPitch, compRoll;
	private Compass_Drawing compVisual;
   
 	@Override
 	public void onCreate(Bundle savedInstanceState) 
 	{
 		super.onCreate(savedInstanceState);
 		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
 		setContentView(R.layout.compass_values);
	    
	    btnBack=(ImageButton) this.findViewById(R.id.btn_back_sensor);
	    btnBack.setOnClickListener(this);
 		
 		compAzi = (TextView)findViewById(R.id.comp_val_azi);
 		compPitch = (TextView)findViewById(R.id.comp_val_pitch);
 		compRoll = (TextView)findViewById(R.id.comp_val_roll);
 		compVisual = (Compass_Drawing)findViewById(R.id.compVisual);
      
 		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
 		mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
 		mComp = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
 		// TYPE_ORIENTATION ist deprecated, 
 		// außerdem zeigt der Azimuth bei der Visualisierung in die falsche Richtung
 		//mComp = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
 		
 		valuesAcc = new float[3];
 		valuesComp = new float[3];
  
 		matrixR = new float[9];
 		matrixI = new float[9];
 		matrixValues = new float[3];
 	}
  
 	@Override
 	protected void onResume() {
 		mSensorManager.registerListener(this, mAcc,
 				SensorManager.SENSOR_DELAY_NORMAL);
 		mSensorManager.registerListener(this, mComp,
 				SensorManager.SENSOR_DELAY_NORMAL);
 		super.onResume();
 	}
  
 	@Override
 	protected void onPause() {
 		mSensorManager.unregisterListener(this, mAcc);
 		mSensorManager.unregisterListener(this, mComp);
 		super.onPause();
 	}
  
 	@Override
 	public void onAccuracyChanged(Sensor arg0, int arg1) {
 		// TODO Auto-generated method stub 
 	}
  
 	@Override
 	public void onSensorChanged(SensorEvent event) {
 		// Auslesen der Sensoren in die value-Arrays
 		switch(event.sensor.getType()){
 			case Sensor.TYPE_ACCELEROMETER:
 				for(int i =0; i < 3; i++)
 					valuesAcc[i] = event.values[i];
 				break;
 			case Sensor.TYPE_MAGNETIC_FIELD:
 				for(int i =0; i < 3; i++)
 					valuesComp[i] = event.values[i];
 				break;
 		}
 		
 		// Rotationsmatrix aus den ausgelesenen Werten erstellen
 		if(SensorManager.getRotationMatrix(matrixR, matrixI, valuesAcc, valuesComp))
 		{
 			// Mit der Rotationsmatrix kann nun die Orientierung bestimmt werden 
 			SensorManager.getOrientation(matrixR, matrixValues);
     
 			double azimuth = Math.toDegrees(matrixValues[0]);
 			double pitch = Math.toDegrees(matrixValues[1]);
 			double roll = Math.toDegrees(matrixValues[2]);
 			
 			compAzi.setText(String.valueOf(azimuth));
 			compPitch.setText(String.valueOf(pitch));
 			compRoll.setText(String.valueOf(roll));
 			// Zur Bestimmung von Norden, wird nur der Azimuth Wert benötigt
 			compVisual.update(matrixValues[0]);
 		}
 	}
 
	public void onClick(View v) 
	{
		if(v==findViewById(R.id.btn_back_sensor)) 
		{
			finish();
		}
	}
}