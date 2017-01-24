package tdevm.muteme;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AudioManagerService extends Service implements SensorEventListener {


    public static final String TAG = "Mera Service bC sala";
    SensorManager sensorManager;
    AudioManager audioManager;
    Sensor accelSensor;
    Sensor proxSensor;


    public AudioManagerService() {
    }


    @Override
    public void onCreate() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "OnCreate Called");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "OnStartCommand  Called");


        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float accZ = sensorEvent.values[2];
        if (accZ < -8) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            Toast.makeText(getApplicationContext(), "Silent Mode ON!", Toast.LENGTH_SHORT).show();

        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (sensorEvent.values[0] <= 10) {

                Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "OnDestroy Called, Service was Destroyed");
        sensorManager.unregisterListener(this, accelSensor);
        sensorManager.unregisterListener(this, proxSensor);
        super.onDestroy();
    }


}
