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
import android.view.MenuItem;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class RssListActivity extends ListActivity {
  public ArrayList titles = new ArrayList();
  public ArrayList urlStrings = new ArrayList();
  public ArrayAdapter adapter;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rss_list);
    getData();
    registerForContextMenu(getListView());
    adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
    setListAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.rss_list_menu, menu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.new_feed_button:
        Intent intent = new Intent(this, NewFeedActivity.class);
        startActivity(intent);
        return true;
      default:
        return false;
    }
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.layout.context_menu, menu);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    String title = (String) titles.get(info.position);
    String url = (String) urlStrings.get(info.position);
    Feed feed = new Feed(title, url);
    DatabaseHandler db = new DatabaseHandler(this);

    switch (item.getItemId()) {
      case R.id.delete_rss_feed:
        db.deleteFeed(feed);
        getData();
        adapter.remove(title);
        adapter.notifyDataSetChanged();
        return true;
      case R.id.rename_rss_feed:
        return true;
      default:
        return super.onContextItemSelected(item);
    }
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Intent intent = new Intent(this, FeedViewActivity.class);
    intent.putExtra("urlString", urlStrings.get(position).toString());
    intent.putExtra("title", titles.get(position).toString());
    startActivity(intent);
  }

  private void getData() {
    DatabaseHandler db = new DatabaseHandler(this);
    ArrayList<Feed> feeds = db.getAllFeeds();

    for (Feed feed : feeds) {
      titles.clear();
      titles.add(feed.getName());
      urlStrings.clear();
      urlStrings.add(feed.getRssUrl());
    }
  }
}
