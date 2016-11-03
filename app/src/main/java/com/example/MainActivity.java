package com.example;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ramzi.mountlib.OnMediaMountedReceiver;
import com.ramzi.mountlib.OnMountCallback;
import com.ramzi.mountlib.storageutils.model.RootInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnMountCallback {
    OnMediaMountedReceiver mOnMediaMountedReceiver=new OnMediaMountedReceiver(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOnMediaMountedReceiver.getStorageDevice(this);

    }

    @Override
    public void onUpdateStorageData(ArrayList<RootInfo> mRoots, HashMap<String, RootInfo> mIdToRoot, HashMap<String, File> mIdToPath) {
        Toast.makeText(getApplicationContext(),"call sucessss"+mRoots.size(),1).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter newFilter = new IntentFilter();
        newFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        newFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        newFilter.addDataScheme(ContentResolver.SCHEME_FILE);
       registerReceiver(mOnMediaMountedReceiver, newFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(mOnMediaMountedReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mOnMediaMountedReceiver);
    }
}
