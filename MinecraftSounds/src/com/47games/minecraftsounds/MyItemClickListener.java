package com.foursevengames.minecraftsounds;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.media.AudioManager;

public class MyItemClickListener implements OnItemClickListener {
  Context context;
  SoundPool sp;
  AudioManager manager;

  @Override
  public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    float actualVolume = (float) manager.getStreamVolume(AudioManager.STREAM_MUSIC);
    float maxVolume = (float) manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    float volume = actualVolume / maxVolume;
    int soundID = Integer.parseInt(v.getTag().toString());
    sp.play(soundID, volume, volume, 1, 0, 1f);
  }
 
  public void giveSoundPool(SoundPool sp) {
    this.sp = sp;
  }
  
  public void setContext(Context context) {
    this.context = context;
  }
  
  public void giveAudioManager(AudioManager manager) {
    this.manager = manager;
  }
}
