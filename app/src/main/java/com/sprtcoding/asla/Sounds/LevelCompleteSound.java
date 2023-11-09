package com.sprtcoding.asla.Sounds;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.sprtcoding.asla.R;

public class LevelCompleteSound {
    private SoundPool soundPool;
    private int soundId;
    private Context context;

    public LevelCompleteSound(Context context) {
        this.context = context;
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
        soundId = soundPool.load(context, R.raw.game_level_complete, 1);
    }

    public void playSound() {
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
