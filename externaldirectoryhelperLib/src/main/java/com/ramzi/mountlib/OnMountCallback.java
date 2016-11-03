package com.ramzi.mountlib;

import com.ramzi.mountlib.storageutils.model.RootInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by munnaz on 3/11/16.
 */

public interface OnMountCallback {
    void onUpdateStorageData(ArrayList<RootInfo> mRoots,HashMap<String, RootInfo> mIdToRoot,HashMap<String, File> mIdToPath);

}
