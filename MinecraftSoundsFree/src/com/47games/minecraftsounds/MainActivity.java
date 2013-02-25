package com.foursevengames.minecraftsounds;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
 
public class MainActivity extends TabActivity {
 
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
 
    Resources resources = getResources(); 
    TabHost tabHost = getTabHost(); 
 
    // Mobs tab
    Intent intentMob = new Intent().setClass(this, MobListActivity.class);
    TabSpec tabSpecMob = tabHost
      .newTabSpec("Mobs")
      .setIndicator("", resources.getDrawable(R.drawable.icon_mob_config))
      .setContent(intentMob);
 
    // Environment tab
    Intent intentEnv = new Intent().setClass(this, EnvListActivity.class);
    TabSpec tabSpecEnv = tabHost
      .newTabSpec("Environment")
      .setIndicator("", resources.getDrawable(R.drawable.icon_env_config))
      .setContent(intentEnv);
 
    // add all tabs 
    tabHost.addTab(tabSpecMob);
    tabHost.addTab(tabSpecEnv);
 
    //set Mobs tab as default (zero based)
    tabHost.setCurrentTab(0);
  }
 
}
