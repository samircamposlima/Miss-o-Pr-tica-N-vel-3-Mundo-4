package com.example.doma;

import android.app.Activity;
import android.media.AudioDeviceInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private AudioHelper audioHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioHelper = new AudioHelper(this);
        AudioDeviceMonitor audioDeviceMonitor = new AudioDeviceMonitor(this);

        Button checkAudioButton = findViewById(R.id.check_audio_button);
        checkAudioButton.setOnClickListener(v -> {
            boolean isSpeakerAvailable = audioHelper.isAudioOutputAvailable(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER);
            boolean isBluetoothHeadsetConnected = audioHelper.isAudioOutputAvailable(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP);

            Toast.makeText(MainActivity.this, "Speaker Available: " + isSpeakerAvailable, Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "Bluetooth Headset Connected: " + isBluetoothHeadsetConnected, Toast.LENGTH_SHORT).show();
        });

        Button bluetoothSettingsButton = findViewById(R.id.bluetooth_settings_button);
        bluetoothSettingsButton.setOnClickListener(v -> BluetoothHelper.openBluetoothSettings(MainActivity.this));

        audioDeviceMonitor.startMonitoring();
    }
}
