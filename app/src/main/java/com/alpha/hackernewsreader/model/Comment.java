package com.alpha.hackernewsreader.model;

import java.util.List;

/**
 * @author Thang
 * @since 25/4/17 21:10
 */
public class Comment {
    private int id;
    private String by;
    private List<Long> kids;
    private String parent;
    private String text;
    private Long time;
    private String type;

    private Comment(Builder builder) {
        id = builder.id;
        by = builder.by;
        kids = builder.kids;
        parent = builder.parent;
        text = builder.text;
        time = builder.time;
        type = builder.type;
    }

    public int getId() {
        return id;
    }

    public String getBy() {
        return by;
    }

    public List<Long> getKids() {
        return kids;
    }

    public String getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }

    public Long getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private int id;
        private String by;
        private List<Long> kids;
        private String parent;
        private String text;
        private Long time;
        private String type;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder by(String val) {
            by = val;
            return this;
        }

        public Builder kids(List<Long> val) {
            kids = val;
            return this;
        }

        public Builder parent(String val) {
            parent = val;
            return this;
        }

        public Builder text(String val) {
            text = val;
            return this;
        }

        public Builder time(Long val) {
            time = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }
}
