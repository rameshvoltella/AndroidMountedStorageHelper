# README #

### Build status

**develop** [![Build Status](https://api.travis-ci.org/rameshvoltella/AndroidMountedStorageHelper.svg?branch=master)](https://travis-ci.org/rameshvoltella/AndroidMountedStorageHelper)

externaldirectoryhelperLib: how to use
------------------------

1. add library externaldirectoryhelperLib to gradle
  
    ```java
     compile project(':externaldirectoryhelperLib')
```

2. implement OnMountCallback on Activity or Fragment which need to get storage info
  
    ```java

         implements OnMountCallback
```

3. Declare the OnMediaMountedReceiver before on create
  
    ```java

         OnMediaMountedReceiver mOnMediaMountedReceiver=new OnMediaMountedReceiver(this);
```


4. Register and un register the OnMediaMountedReceiver in your fragment or activity
  
    ```java

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
```


5. Call for getting mounted devices
  
    ```java

         mOnMediaMountedReceiver.getStorageDevice(this);
```

6. Getting the callback of Strorage info
  
    ```java

          @Override
        public void onUpdateStorageData(ArrayList<RootInfo> mRootsData, HashMap<String, RootInfo> mIdToRootData, HashMap<String, File> mIdToPathData) {
        /*Get the list of mounted devices here
        *
        * will get call back automatically if a mount or un mount occur
        * */

        }
```




## License

    The MIT License (MIT)

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


        







