package com.alpha.hackernewsreader.model;

import java.util.List;

/**
 * @author Thang
 * @since 25/4/17 21:10
 */
public class ItemDetails {
    public final long id;
    public final String by;
    public final List<Long> kids;
    public final String parent;
    public final String text;
    public final Long time;
    public final String type;

    public ItemDetails(long id, String by, List<Long> kids, String parent, String text, Long time, String type) {
        this.id = id;
        this.by = by;
        this.kids = kids;
        this.parent = parent;
        this.text = text;
        this.time = time;
        this.type = type;
    }
}
