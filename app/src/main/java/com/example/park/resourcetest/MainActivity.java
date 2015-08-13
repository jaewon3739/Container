package com.example.park.resourcetest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(new Intent("com.rsupport.server.helper"), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);

        Resources resource = null;
        String packageName = null;

        try {
            for (ResolveInfo resolveInfo : list) {
                packageName = resolveInfo.activityInfo.packageName;
                resource = pm.getResourcesForApplication(packageName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap originalHourBitmap = BitmapFactory.decodeResource(resource, resource.getIdentifier("tutorial_1", "drawable", packageName));

        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), originalHourBitmap);

        findViewById(R.id.test).setBackground(bitmapDrawable);
    }
}
