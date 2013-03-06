package com.foursevengames.speaknews;

import android.app.ListActivity;
import android.os.Bundle;
import android.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import java.util.ArrayList;
import java.net.URL;
import java.io.InputStream;
import android.widget.ListView;
import android.view.View;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlPullParser;
import java.net.MalformedURLException;
import java.io.IOException;
import android.widget.ArrayAdapter;
import android.net.Uri;
import org.xmlpull.v1.XmlPullParserException;
import android.os.AsyncTask;
import android.content.Context;
import android.os.Bundle;

public class FeedViewActivity extends ListActivity {
  Context context = this;
  ArrayList headlines = new ArrayList();
  ArrayList links = new ArrayList();
  Bundle extras = new Bundle();

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.feed_list);
    extras = getIntent().getExtras();
    getActionBar().setDisplayHomeAsUpEnabled(true);
    this.setTitle(extras.getString("title"));
    new RssParser().execute(extras.getString("urlString"));
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // app icon in action bar clicked; go home
        Intent intent = new Intent(this, RssListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  public class RssParser extends AsyncTask<String, Void, ArrayList> {
    protected ArrayList doInBackground(String... urlString) {
      try {
        URL url = new URL(urlString[0]);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(false);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(getInputStream(url), "UTF_8");
        boolean insideItem = false;

        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
          if (eventType == XmlPullParser.START_TAG) {
            if (xpp.getName().equalsIgnoreCase("item")) {
              insideItem = true;
            } else if (xpp.getName().equalsIgnoreCase("title")) {
              if (insideItem)
                headlines.add(xpp.nextText()); //extract the headline
            } else if (xpp.getName().equalsIgnoreCase("link")) {
              if (insideItem)
                links.add(xpp.nextText()); //extract the link of article
            }
          }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
            insideItem=false;
          }
            eventType = xpp.next(); //move to next element
          }
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (XmlPullParserException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return headlines;
    }

    protected void onPostExecute(ArrayList headlines) {
      ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, headlines);
      setListAdapter(adapter);
    
    }
  }

  public InputStream getInputStream(URL url) {
    try {
      return url.openConnection().getInputStream();
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Uri uri = Uri.parse((String) links.get(position));
    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    startActivity(intent);
  }
}
