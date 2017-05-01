package com.alpha.hackernewsreader;

import android.content.Context;
import com.alpha.hackernewsreader.details.CommentListActivity;
import com.alpha.hackernewsreader.model.Story;
import javax.inject.Inject;

/**
 * @author Thang
 * @since 3/5/17 22:07
 */
public class Navigator {
    private Context mContext;

    @Inject
    public Navigator(Context context) {
        mContext = context;
    }

    public void goToStoryDetails(Story story) {
        CommentListActivity.start(mContext, story.getKids());
    }
}
