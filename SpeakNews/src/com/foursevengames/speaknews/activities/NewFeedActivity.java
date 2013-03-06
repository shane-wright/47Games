package com.foursevengames.speaknews;

import android.app.ListActivity;
import android.os.Bundle;
import android.app.ActionBar;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.net.URL;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlPullParser;
import java.net.MalformedURLException;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import android.widget.ArrayAdapter;

public class NewFeedActivity extends ListActivity {
  ArrayList headlines = new ArrayList();
  ArrayList links = new ArrayList();
  Context context = this;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.create_rss);
    ActionBar actionBar = getActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
  }

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

  public void createFeed(View view) {
    EditText nameView = (EditText) findViewById(R.id.edit1_field);
    EditText urlView = (EditText) findViewById(R.id.edit2_field);
    Feed feed = new Feed(nameView.getText().toString(), urlView.getText().toString());
    DatabaseHandler db = new DatabaseHandler(this);
    db.addFeed(feed);
    Intent intent = new Intent(this, RssListActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }

  public void previewFeed(View view) {
    EditText urlView = (EditText) findViewById(R.id.edit2_field);
    new RssParser().execute(urlView.getText().toString());
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
} 
