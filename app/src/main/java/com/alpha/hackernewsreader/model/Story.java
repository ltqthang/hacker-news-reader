package com.alpha.hackernewsreader.model;

import java.util.ArrayList;

/**
 * Created by ILoveCoding on 5/1/17.
 */

public class Story {
    private String by;
    private String dhouston;
    private Long descendants;
    private int id;
    private ArrayList<Integer> kids;
    private Integer score;
    private Long time;
    private String title;
    private String type;
    private String url;

    public String getBy() {
        return by;
    }

    public String getDhouston() {
        return dhouston;
    }

    public Long getDescendants() {
        return descendants;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getKids() {
        return kids;
    }

    public Integer getScore() {
        return score;
    }

    public Long getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    private Story(Builder builder) {
        by = builder.by;
        dhouston = builder.dhouston;
        descendants = builder.descendants;
        id = builder.id;
        kids = builder.kids;
        score = builder.score;
        time = builder.time;
        title = builder.title;
        type = builder.type;
        url = builder.url;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String by;
        private String dhouston;
        private Long descendants;
        private int id;
        private ArrayList<Integer> kids;
        private Integer score;
        private Long time;
        private String title;
        private String type;
        private String url;

        private Builder() {
        }

        public Builder by(String val) {
            by = val;
            return this;
        }

        public Builder dhouston(String val) {
            dhouston = val;
            return this;
        }

        public Builder descendants(Long val) {
            descendants = val;
            return this;
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder kids(ArrayList<Integer> val) {
            kids = val;
            return this;
        }

        public Builder score(Integer val) {
            score = val;
            return this;
        }

        public Builder time(Long val) {
            time = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Story build() {
            return new Story(this);
        }
    }
}
