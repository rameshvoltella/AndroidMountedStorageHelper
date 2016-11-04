/*** The MIT License (MIT)

 Copyright (c) 2016 Ramesh M Nair

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 ***/

package com.ramzi.mountlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.ramzi.mountlib.storageutils.control.StorageCallback;
import com.ramzi.mountlib.storageutils.control.StorageCallbackImp;
import com.ramzi.mountlib.storageutils.model.RootInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;



public class OnMediaMountedReceiver extends BroadcastReceiver implements StorageCallback.OnFinishChecking {
    private OnMountCallback mOnMountCallback;

    public OnMediaMountedReceiver(OnMountCallback mOnMountCallback) {
        this.mOnMountCallback = mOnMountCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {

                 /*Mount occurred*/
            if (intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED)) {
                String a = intent.getData().getPath();
                if (a != null && a.trim().length() != 0 && new File(a).exists() && new File(a).canExecute()) {
                    if (mOnMountCallback != null) {
                        getStorageDevice(context);
                    }
                }

            }
            /*UnMount occurred*/
            else if (intent.getAction().equals(Intent.ACTION_MEDIA_UNMOUNTED)) {
                if (mOnMountCallback != null) {
                    getStorageDevice(context);

                }
            }
        }
    }

    /*get Storage Details*/
    public void getStorageDevice(Context mContext) {
        if (mOnMountCallback != null) {

            /*Check for Storage which is mounted*/
            StorageCallbackImp mStorageImp = new StorageCallbackImp();
            mStorageImp.forceCheckStorage(this, mContext);
        }
    }

    @Override
    public void getStorageInfo(ArrayList<RootInfo> mRoots, HashMap<String, RootInfo> mIdToRoot, HashMap<String, File> mIdToPath) {
        if (mOnMountCallback != null) {
                 /*Pass detail of Storage to the registered class*/
            mOnMountCallback.onUpdateStorageData(mRoots, mIdToRoot, mIdToPath);
        }
    }
}
