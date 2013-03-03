package com.foursevengames.speaknews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.content.Intent;

public class RssListActivity extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rss_list);
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.rss_list_menu, menu);
    return true;
  }

  public void onFeedClicked(View view) {
    Intent intent = new Intent(this, FeedListActivity.class);
    startActivity(intent);
  }

}
