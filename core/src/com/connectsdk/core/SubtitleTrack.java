/*
 * SubtitleTrack
 * Connect SDK
 *
 * Copyright (c) 2015 LG Electronics.
 * Created by Oleksii Frolov on 20 Jul 2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.connectsdk.core;

public class SubtitleTrack {

    final String url;

    final String mimeType;

    final String label;

    final String language;

    public static class Builder {
        // required fields
        String url;

        String mimeType;

        // optional fields

        String label;

        String language;

        /**
         * Constructor with required fields
         * @param url subtitle URL, for Chromecast a web server must have
         *            a header `Access-Control-Allow-Origin: *`
         * @param mimeType mime type of subtitles. It can be text/vtt, text/srt
         */
        public Builder(String url, String mimeType) {
            this.url = url;
            this.mimeType = mimeType;
        }

        public Builder setLabel(String label) {
            this.label = label;
            return this;
        }

        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        public SubtitleTrack build() {
            return new SubtitleTrack(this);
        }
    }

    private SubtitleTrack(SubtitleTrack.Builder builder) {
        url = builder.url;
        mimeType = builder.mimeType;
        label = builder.label;
        language = builder.language;
    }

    public String getUrl() {
        return url;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getLabel() {
        return label;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubtitleTrack that = (SubtitleTrack) o;

        if (getUrl() != null ? !getUrl().equals(that.getUrl()) : that.getUrl() != null)
            return false;
        return !(getMimeType() != null ? !getMimeType().equals(that.getMimeType()) : that.getMimeType() != null);

    }

    @Override
    public int hashCode() {
        int result = getUrl() != null ? getUrl().hashCode() : 0;
        result = 31 * result + (getMimeType() != null ? getMimeType().hashCode() : 0);
        return result;
    }
}
