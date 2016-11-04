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

package com.example;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ramzi.mountlib.OnMediaMountedReceiver;
import com.ramzi.mountlib.OnMountCallback;
import com.ramzi.mountlib.storageutils.model.RootInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnMountCallback {

    /*Declare the OnMediaMountedReceiver before on create*/
    OnMediaMountedReceiver mOnMediaMountedReceiver=new OnMediaMountedReceiver(this);

    private ArrayList<RootInfo> mRoots=new ArrayList<>();
    RecyclerView mRecylerView;
    StorageListAdapter mAdaptor;
    Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecylerView=(RecyclerView)findViewById(R.id.recycler_view);
        refreshButton=(Button)findViewById(R.id.refresh_button);

        /*Call for mounted devices*/
        mOnMediaMountedReceiver.getStorageDevice(this);




        mAdaptor=new StorageListAdapter(getApplicationContext(),mRoots);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mAdaptor);

        /*To refresh the Storage*/
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 /*Call for mounted devices*/

                mOnMediaMountedReceiver.getStorageDevice(MainActivity.this);

            }
        });



/*Get the clicked path of storage*/
        mRecylerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click

              Toast.makeText(getApplicationContext(),"Clicked Storage path is "+mRoots.get(position).path,Toast.LENGTH_LONG).show();

                    }
                })
        );




    }

    @Override
    public void onUpdateStorageData(ArrayList<RootInfo> mRootsData, HashMap<String, RootInfo> mIdToRootData, HashMap<String, File> mIdToPathData) {
        /*Get the list of mounted devices here
        *
        * will get call back automatically if a mount or un mount occur
        * */

        mRoots.clear();
        mRoots.addAll(mRootsData);
        mAdaptor.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"Storage Refreshed New Storage Count Is : "+mRoots.size(),Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*Register the mOnMediaMountedReceiver with
        * Intent.ACTION_MEDIA_MOUNTED
        * Intent.ACTION_MEDIA_UNMOUNTED
        * Scheme:-ContentResolver.SCHEME_FILE
        * * */

        IntentFilter newFilter = new IntentFilter();
        newFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        newFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        newFilter.addDataScheme(ContentResolver.SCHEME_FILE);
        registerReceiver(mOnMediaMountedReceiver, newFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*UnRegister mOnMediaMountedReceiver
        *
        * */
        unregisterReceiver(mOnMediaMountedReceiver);
    }
}
