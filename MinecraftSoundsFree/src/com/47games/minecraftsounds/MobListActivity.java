package com.foursevengames.minecraftsounds;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.ViewGroup.LayoutParams;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Toast;

public class MobListActivity extends Activity {
  Context context = this;
  final SoundPool sp = new SoundPool(21, AudioManager.STREAM_MUSIC, 0);
  int[] sounds = {
    R.raw.mob_bat, 
    R.raw.mob_blaze,
    R.raw.mob_cat,
    R.raw.mob_chicken,
    R.raw.mob_cow,
    R.raw.mob_creeper,
    R.raw.mob_enderdragon,
    R.raw.mob_enderman,
    R.raw.mob_ghast,
    R.raw.mob_irongolem, 
    R.raw.mob_magmacube,
    R.raw.mob_pig,
    R.raw.mob_sheep,
    R.raw.mob_silverfish,
    R.raw.mob_skeleton,
    R.raw.mob_slime,
    R.raw.mob_spider, 
    R.raw.mob_wither,
    R.raw.mob_wolf, 
    R.raw.mob_zombie,
    R.raw.mob_zombiepigman
  };
  Integer[] images = {
    R.drawable.mob_bat, 
    R.drawable.mob_blaze,
    R.drawable.mob_cat,
    R.drawable.mob_chicken,
    R.drawable.mob_cow,
    R.drawable.mob_creeper,
    R.drawable.mob_enderdragon,
    R.drawable.mob_enderman,
    R.drawable.mob_ghast,
    R.drawable.mob_irongolem,
    R.drawable.mob_magmacube,
    R.drawable.mob_pig,
    R.drawable.mob_sheep,
    R.drawable.mob_silverfish,
    R.drawable.mob_skeleton,
    R.drawable.mob_slime,
    R.drawable.mob_spider,
    R.drawable.mob_wither,
    R.drawable.mob_wolf,
    R.drawable.mob_zombie, 
    R.drawable.mob_zombiepigman 
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.mobs);
    GridView main = (GridView) findViewById(R.id.mob_grid);
    ImageAdapter adapt = new ImageAdapter(context);
    adapt.giveImageIds(images);
    adapt.giveSoundIds(sounds);
    adapt.giveSoundPool(sp);
    main.setAdapter(adapt);


    this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    // Getting the user sound settings
    AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


    int soundID = sp.load(context, sounds[0], 1);
    MyItemClickListener click = new MyItemClickListener();
    click.giveSoundPool(sp);
    click.giveAudioManager(audioManager);
    click.setContext(context);
    main.setOnItemClickListener(click);
  }
}
