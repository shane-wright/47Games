package com.foursevengames.speaknews;

import android.app.ListActivity;
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
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RssListActivity extends ListActivity {
  public ArrayList titles = new ArrayList();
  public ArrayList urlStrings = new ArrayList();

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rss_list);
    getData();
    createRows();
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.rss_list_menu, menu);
    return true;
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Intent intent = new Intent(this, FeedViewActivity.class);
    intent.putExtra("urlString", urlStrings.get(position).toString());
    startActivity(intent);
  }

  private void getData() {
    titles.add("CNN");
    urlStrings.add("http://rss.cnn.com/rss/cnn_topstories.rss");
    titles.add("FOX");
    urlStrings.add("http://www.foxnews.com/about/rss/feedburner/foxnews/latest");
    titles.add("NBA");
    urlStrings.add("http://www.nba.com/rss/nba_rss.xml");
  }

  private void createRows() {
    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
    setListAdapter(adapter);
  }
}
