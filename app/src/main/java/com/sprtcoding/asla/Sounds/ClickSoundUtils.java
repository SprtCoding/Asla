package com.sprtcoding.asla.Sounds;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.sprtcoding.asla.R;

public class ClickSoundUtils {
    private SoundPool soundPool;
    private int clickSoundId;
    private Context context;

    public ClickSoundUtils(Context context) {
        this.context = context;
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
        clickSoundId = soundPool.load(context, R.raw.click_sound_2, 1);
    }

    public void playClickSound() {
        soundPool.play(clickSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
