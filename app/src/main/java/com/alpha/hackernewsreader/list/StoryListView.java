package com.alpha.hackernewsreader.list;

import com.alpha.hackernewsreader.model.Story;
import java.util.List;

/**
 * @author Thang
 * @since 25/4/17 21:20
 */
public interface StoryListView {
    void onLoading();

    void onError();

    void onStoryListFetched(List<Story> stories);
}
