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


package com.ramzi.mountlib.storageutils;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;


public final class DocumentsContract {

    public final static class Root {
        private Root() {
        }


        public static final int FLAG_SUPPORTS_CREATE = 1;

        /**
         * Flag indicating that this root offers content that is strictly local
         * on the device. That is, no network requests are made for the content.
         **/
        public static final int FLAG_LOCAL_ONLY = 1 << 1;


        public static final int FLAG_SUPPORTS_SEARCH = 1 << 3;

        /**
         * Flag indicating that this root supports testing parent child
         * relationships.
         */
        public static final int FLAG_SUPPORTS_IS_CHILD = 1 << 4;

        public static final int FLAG_ADVANCED = 1 << 17;

        public static final int FLAG_SUPPORTS_EDIT = 1 << 18;

    }
}
