

package com.ramzi.mountlib.storageutils;


import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import android.os.storage.StorageManager;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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

public final class StorageUtils {


    //private static final String TAG = "StorageUtils";
	private StorageManager mStorageManager;
    private Context mContext;
    public StorageUtils(Context context){
        mContext = context;
        mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
    }

    public List<StorageVolume> getStorageMounts() {
        List<StorageVolume> mounts = new ArrayList<StorageVolume>();
        boolean first = false;
        try {
			Method getVolumeList = StorageManager.class.getDeclaredMethod("getVolumeList");
			Object[] sv = (Object[])getVolumeList.invoke(mStorageManager);
            if(null == sv){
                return mounts;
            }
			for (Object object : sv) {
				String filePath = getPath(object);
				boolean emulated = getEmulated(object);
				boolean primary = false;
                if(Utils.hasJellyBeanMR1()){
                    primary = getPrimary(object);
                }
                else{
                    if(!first){
                        first = true;
                        primary = true;
                    }
                }

                String description = Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT ? getUserLabel(object) : getDescription(object);
				StorageVolume mount = new StorageVolume(description, filePath);
				mount.isEmulated = emulated;
				mount.isPrimary = primary;
				mounts.add(mount);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
        return mounts;
    }

    private String getPath(Object object) throws IllegalAccessException, NoSuchFieldException {
        String path = "";
        Field mPath = object.getClass().getDeclaredField("mPath");
        mPath.setAccessible(true);
        Object pathObj = mPath.get(object);
        if(Utils.hasJellyBeanMR1()){
            path = ((File)pathObj).toString();
        }
        else{
            path = (String)pathObj;
        }
        return path;
    }

    private String getDescription(Object object) throws IllegalAccessException, NoSuchFieldException {
        String description = "";
        if(Utils.hasMarshmallow()){
            description = getDescription(object, false);
        }
        else if(Utils.hasJellyBean()){
            try {
                description = getDescription(object, true);
            }
            catch (Resources.NotFoundException e){
                description = getDescription(object, false);
            }
        }
        else{
            description = getDescription(object, false);
        }
        return description;
    }

    private String getDescription(Object object, boolean hasId) throws IllegalAccessException, NoSuchFieldException {
        String description = "";
        if (hasId) {
            Field mDescription = object.getClass().getDeclaredField("mDescriptionId");
            mDescription.setAccessible(true);
            int mDescriptionInt = mDescription.getInt(object);
            description = mContext.getResources().getString(mDescriptionInt);
        } else {
            Field mDescription = object.getClass().getDeclaredField("mDescription");
            mDescription.setAccessible(true);
            description = (String) mDescription.get(object);
        }
        return description;
    }

    private String getUserLabel(Object object) throws IllegalAccessException, NoSuchFieldException {
        String userLabel = "";
        if(Utils.hasKitKat()) {
            Field mDescription = object.getClass().getDeclaredField("mUserLabel");
            mDescription.setAccessible(true);
            userLabel = (String) mDescription.get(object);
            if(TextUtils.isEmpty(userLabel)){
                userLabel = getDescription(object);
            }
        }
        return userLabel;
    }

    private boolean getPrimary(Object object) throws IllegalAccessException, NoSuchFieldException {
        boolean primary = false;
        Field mPrimary = object.getClass().getDeclaredField("mPrimary");
        mPrimary.setAccessible(true);
        primary = mPrimary.getBoolean(object);
        return primary;
    }

    private boolean getEmulated(Object object) throws IllegalAccessException, NoSuchFieldException {
        boolean emulated = false;
        if(Utils.hasJellyBeanMR1()){
            Field mEmulated = object.getClass().getDeclaredField("mEmulated");
            mEmulated.setAccessible(true);
            emulated = mEmulated.getBoolean(object);
        }

        return emulated;
    }



}