package com.foursevengames.minecraftsounds;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.view.View.OnClickListener;

public class MyClickListener implements OnClickListener {
  int soundID;
  Context context;
  float volume;
  SoundPool sp;

  @Override
  public void onClick(View view) {
    sp.play(soundID, volume, volume, 1, 0, 1f);
  }
 
  public void setSoundID(int soundID) {
    this.soundID = soundID;
  }

  public void setVolume(float volume) {
    this.volume = volume;
  }

  public void giveSoundPool(SoundPool sp) {
    this.sp = sp;
  }
}
