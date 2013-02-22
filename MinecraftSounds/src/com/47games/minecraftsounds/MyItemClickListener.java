package com.foursevengames.minecraftsounds;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;

public class MyItemClickListener implements OnItemClickListener {
  Context context;
  float volume;
  SoundPool sp;

  @Override
  public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    int soundID = Integer.parseInt(v.getTag().toString());
    sp.play(soundID, volume, volume, 1, 0, 1f);
  }
 
  public void setVolume(float volume) {
    this.volume = volume;
  }

  public void giveSoundPool(SoundPool sp) {
    this.sp = sp;
  }
  
  public void setContext(Context context) {
    this.context = context;
  }
}
