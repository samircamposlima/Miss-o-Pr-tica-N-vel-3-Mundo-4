package com.example.doma;

import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;

public class AudioDeviceMonitor {
    private final AudioManager audioManager;

    public AudioDeviceMonitor(Context context) {
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        AudioHelper audioHelper = new AudioHelper(context);
    }

    public void startMonitoring() {
        audioManager.registerAudioDeviceCallback(new AudioDeviceCallback() {
            @Override
            public void onAudioDevicesAdded(AudioDeviceInfo[] addedDevices) {
                super.onAudioDevicesAdded(addedDevices);
                for (AudioDeviceInfo device : addedDevices) {
                    if (device.getType() != AudioDeviceInfo.TYPE_BLUETOOTH_A2DP) {
                        continue;
                    }

                }
            }

            @Override
            public void onAudioDevicesRemoved(AudioDeviceInfo[] removedDevices) {
                super.onAudioDevicesRemoved(removedDevices);
                for (AudioDeviceInfo device : removedDevices) {
                    if (device.getType() == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP) {
                        continue;
                    }

                }
            }
        }, null);
    }
}
