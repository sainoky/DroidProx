package com.example.app.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class ProximitySensorActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mProxSensor;

    private TextView mTextName;
    private TextView mTextVendor;
    private TextView mTextResolution;
    private TextView mTextMaxRange;
    private TextView mTextValue;
    private TextView mTextFace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        mTextName = (TextView)findViewById(R.id.textSensorName);
        mTextVendor = (TextView)findViewById(R.id.textSensorVendor);
        mTextResolution = (TextView)findViewById(R.id.textSensorResolution);
        mTextMaxRange = (TextView)findViewById(R.id.textSensorMaxRange);
        mTextValue = (TextView)findViewById(R.id.textSensorValue);
        mTextFace = (TextView)findViewById(R.id.textSensorExpression);
    }

    private void ShowSensorValues() {
        mTextName.setText("Sensor name: " + mProxSensor.getName());
        mTextVendor.setText("Sensor vendor: " + mProxSensor.getVendor());
        mTextResolution.setText("Sensor resolution: " + mProxSensor.getResolution());
        mTextMaxRange.setText("Sensor maximum range: " + mProxSensor.getMaximumRange());
    }

    @Override
    protected void onResume() {
        super.onResume();

        mProxSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorManager.registerListener(this, mProxSensor,
                                        SensorManager.SENSOR_DELAY_NORMAL);
        ShowSensorValues();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not implemented
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        mTextValue.setText("Sensor value: " + event.values[0]);
        if (event.values[0] == 0)
            mTextFace.setText("|ω・`)");
        else
            mTextFace.setText("|(*´∀｀)");
    }
}
