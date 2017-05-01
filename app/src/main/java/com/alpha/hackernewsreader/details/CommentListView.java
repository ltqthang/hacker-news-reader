package com.alpha.hackernewsreader.details;

import com.alpha.hackernewsreader.model.Comment;
import java.util.List;

/**
 * @author Thang
 * @since 6/5/17 10:49
 */
public interface CommentListView {
    void onLoading();

    void onError();

    void onCommentListFetched(List<Comment> comments);
}
