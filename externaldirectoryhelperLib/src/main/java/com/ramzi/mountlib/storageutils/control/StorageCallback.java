package com.ramzi.mountlib.storageutils.control;

import android.content.Context;

import com.ramzi.mountlib.storageutils.model.RootInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by munnaz on 3/11/16.
 */

public interface StorageCallback {

    void forceCheckStorage(OnFinishChecking mStorageInfo, Context mContext);

        public interface OnFinishChecking
        {
            void getStorageInfo(ArrayList<RootInfo> mRoots, HashMap<String, RootInfo> mIdToRoot, HashMap<String, File> mIdToPath);

        }
}
