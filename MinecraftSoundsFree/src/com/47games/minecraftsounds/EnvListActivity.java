package com.foursevengames.minecraftsounds;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.widget.GridView;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.Toast;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;

public class EnvListActivity extends Activity {
  Context context = this;
  final SoundPool sp = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
  int[] sounds = {
    R.raw.env_chest_open,
    R.raw.env_cloth,
    R.raw.env_door,
    R.raw.env_fire,
    R.raw.env_firework_launch,
    R.raw.env_glass,
    R.raw.env_grass,
    R.raw.env_gravel,
    R.raw.env_lava,
    R.raw.env_minecart,
    R.raw.env_piston,
    R.raw.env_portal,
    R.raw.env_sand,
    R.raw.env_snow,
    R.raw.env_stone,
    R.raw.env_tnt,
    R.raw.env_water,
    R.raw.env_wood
  };
  Integer[] images = {
    R.drawable.env_chest,
    R.drawable.env_cloth,
    R.drawable.env_door,
    R.drawable.env_fire,
    R.drawable.env_firework,
    R.drawable.env_glass,
    R.drawable.env_grass,
    R.drawable.env_gravel,
    R.drawable.env_lava,
    R.drawable.env_minecart,
    R.drawable.env_piston,
    R.drawable.env_portal,
    R.drawable.env_sand,
    R.drawable.env_snow,
    R.drawable.env_stone,
    R.drawable.env_tnt,
    R.drawable.env_water,
    R.drawable.env_wood
  };
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.env);
    GridView main = (GridView) findViewById(R.id.env_grid);
    ImageAdapter adapt = new ImageAdapter(context);
    registerForContextMenu(main);
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

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.layout.context_menu, menu);
  }

/*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) { 
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.layout.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
    case R.id.menu_about:
      Toast.makeText(context, "About was Selected, env", Toast.LENGTH_SHORT).show();
      return true;

    case R.id.menu_help:
      Toast.makeText(context, "Help was Selected, env", Toast.LENGTH_SHORT).show();
      return true;

    case R.id.menu_report_bug:
      Toast.makeText(context, "Report a bug was Selected, env", Toast.LENGTH_SHORT).show();
      return true;

    default:
      return super.onOptionsItemSelected(item);
    }
  }*/
}
