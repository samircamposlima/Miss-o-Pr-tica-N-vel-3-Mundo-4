package com.example.doma;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.content.pm.PackageManager;

public class AudioHelper {
    private final AudioManager audioManager;
    private final Context context;

    public AudioHelper(Context context) {
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.context = context;
    }

    public boolean isAudioOutputAvailable(int type) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_AUDIO_OUTPUT)) {
            return false;
        }
        AudioDeviceInfo[] devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
        for (AudioDeviceInfo device : devices) {
            if (device.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
