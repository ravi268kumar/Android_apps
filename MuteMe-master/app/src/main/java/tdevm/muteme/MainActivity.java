package tdevm.muteme;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ImageView mute, unmute;
    SensorManager sensorManager;
    AudioManager audioManager;
    Button startService, stopService;
    ToggleButton toggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Do Not Disturb");
        }
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        startService = (Button) findViewById(R.id.btn_start);
        stopService = (Button) findViewById(R.id.btn_stop);
        mute = (ImageView) findViewById(R.id.mute);
        unmute = (ImageView) findViewById(R.id.unmute);

        final Intent serviceIntent = new Intent(MainActivity.this, AudioManagerService.class);

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
                    Toast.makeText(MainActivity.this, "Vibrate Mode ON!", Toast.LENGTH_SHORT).show();
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                } else {
                    Toast.makeText(MainActivity.this, "Already in Vibrate Mode!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        unmute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE) {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Toast.makeText(MainActivity.this, "Normal Mode ON!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Already in Normal Mode!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    startService(serviceIntent);

                } else {
                    stopService(serviceIntent);
                }

            }
        });






    }


//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//        // float accX = sensorEvent.values[0];
//        //float accY = sensorEvent.values[1];
//        float accZ = sensorEvent.values[2];
//        if (accZ < -8) {
//            //Toast.makeText(MainActivity.this, "Working", Toast.LENGTH_SHORT).show();
//            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
//        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
//            if (sensorEvent.values[0] <= 10) {
//
//                Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }


    @Override
    protected void onStop() {
        //  sensorManager.unregisterListener(this);
        super.onStop();

    }

}
