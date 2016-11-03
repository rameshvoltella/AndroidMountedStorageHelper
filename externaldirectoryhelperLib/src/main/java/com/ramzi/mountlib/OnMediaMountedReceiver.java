package com.ramzi.mountlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.ramzi.mountlib.storageutils.control.StorageCallback;
import com.ramzi.mountlib.storageutils.control.StorageCallbackImp;
import com.ramzi.mountlib.storageutils.model.RootInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by munnaz on 30/10/16.
 */

public  class OnMediaMountedReceiver extends BroadcastReceiver implements StorageCallback.OnFinishChecking {
private OnMountCallback mOnMountCallback;

    public OnMediaMountedReceiver(OnMountCallback mOnMountCallback)
    {
        this.mOnMountCallback=mOnMountCallback;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED)) {
                String a = intent.getData().getPath();
                if (a != null && a.trim().length() != 0 && new File(a).exists() && new File(a).canExecute()) {
                    if(mOnMountCallback!=null) {
                        getStorageDevice(context);
                    }
                }

            } else if (intent.getAction().equals(Intent.ACTION_MEDIA_UNMOUNTED)) {
                if(mOnMountCallback!=null) {
                    getStorageDevice(context);

                }
            }
        }
    }

    public void getStorageDevice(Context mContext)
    {
        if(mOnMountCallback!=null)
        {
            StorageCallbackImp mStorageImp=new StorageCallbackImp();
            mStorageImp.forceCheckStorage(this,mContext);
        }
    }

    @Override
    public void getStorageInfo(ArrayList<RootInfo> mRoots, HashMap<String, RootInfo> mIdToRoot, HashMap<String, File> mIdToPath) {
if(mOnMountCallback!=null)
{
    mOnMountCallback.onUpdateStorageData(mRoots,mIdToRoot,mIdToPath);
}
    }
}
