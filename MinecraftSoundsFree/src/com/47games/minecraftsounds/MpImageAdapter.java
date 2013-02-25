package com.foursevengames.minecraftsounds;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.GridView;
import android.view.ViewGroup.LayoutParams;
import android.util.TypedValue;
import android.media.MediaPlayer;
import java.io.IOException;
import android.content.res.Resources;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;

public class MpImageAdapter extends BaseAdapter {
  private Context context;
  private Integer[] imageIds;
  private int[] soundIds;

  public MpImageAdapter(Context c) {
    context = c;
  }

  public int getCount() {
    return imageIds.length;
  }

  public Object getItem(int position) {
    return null;
  }

  public long getItemId(int position) {
    return 0;
  }

  // create a new ImageView for each item referenced by the Adapter
  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;
    if (convertView == null) {  // if it's not recycled, initialize some attributes
      imageView = new ImageView(context);
      //convert to dp
      int jello = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 85, context.getResources().getDisplayMetrics());
      int pudding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics());
      imageView.setLayoutParams(new GridView.LayoutParams(jello, jello));
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageView.setPadding(pudding, pudding, pudding, pudding);
    } else {
      imageView = (ImageView) convertView;
    }
    imageView.setImageResource(imageIds[position]);
    MediaPlayer mp = new MediaPlayer();

    Resources res = context.getResources();
    AssetFileDescriptor afd = res.openRawResourceFd(soundIds[position]);

    try {
      mp.reset();
      mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
      mp.setLooping(true);
      mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
      mp.prepare();
      imageView.setTag(mp);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return imageView;
  }

  public void giveImageIds(Integer[] imageIds) {
    this.imageIds = imageIds;
  }
  public void giveSoundIds(int[] soundIds) {
    this.soundIds = soundIds;
  }
}
