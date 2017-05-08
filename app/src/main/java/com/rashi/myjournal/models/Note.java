package com.rashi.myjournal.models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by rashi on 27/4/17.
 */

public class Note extends RealmObject {
    public String title;
    public String content;

    public Note() {
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;

    }
}
