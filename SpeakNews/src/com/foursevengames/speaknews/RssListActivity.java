package com.foursevengames.speaknews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class RssListActivity extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rss_list);
    createRows();
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.rss_list_menu, menu);
    return true;
  }

  public void onFeedClicked(View view) {
    Intent intent = new Intent(this, FeedViewActivity.class);
    //intent.putExtra();
    startActivity(intent);
  }

  private void createRows() {
    String[] names = { "CNN", "FOX", "NBA" };
    int[] images = { R.drawable.cnn_icon, R.drawable.fox_icon, R.drawable.nba_icon };
    TableLayout rssList = (TableLayout) findViewById(R.id.rss_list);
    int i = 0;
    for (String name : names) {
      TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.tablerow_template, null);
      ImageView view = (ImageView) getLayoutInflater().inflate(R.layout.icon_template, null);
      view.setImageResource(images[i]);
      TextView text = (TextView) getLayoutInflater().inflate(R.layout.text_template, null);
      text.setText(name);
      row.addView(view);
      row.addView(text);
      row.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          onFeedClicked(view);
        }
      });
      rssList.addView(row);
      i++;
    }
  }
}
