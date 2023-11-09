package com.sprtcoding.asla.Sounds;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.sprtcoding.asla.R;

public class NextLevelSoundUtils {
    private SoundPool soundPool;
    private int soundId;
    private Context context;

    public NextLevelSoundUtils(Context context) {
        this.context = context;
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
        soundId = soundPool.load(context, R.raw.open_new_level, 1);
    }

    public void playNextSound() {
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
