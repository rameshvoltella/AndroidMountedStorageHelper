/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
         *
         * @see #
         * @see Intent#EXTRA_LOCAL_ONLY
         */
        public static final int FLAG_LOCAL_ONLY = 1 << 1;


        public static final int FLAG_SUPPORTS_SEARCH = 1 << 3;

        /**
         * Flag indicating that this root supports testing parent child
         * relationships.
         *
         * @see #
         * @see android.provider.DocumentsProvider#isChildDocument(String, String)
         */
        public static final int FLAG_SUPPORTS_IS_CHILD = 1 << 4;

        public static final int FLAG_ADVANCED = 1 << 17;

        public static final int FLAG_SUPPORTS_EDIT = 1 << 18;

    }
}
