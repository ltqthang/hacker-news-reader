package com.alpha.hackernewsreader.api;

import com.alpha.hackernewsreader.model.Comment;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Thang
 * @since 25/4/17 21:09
 */
public interface CommentDetailsAPI {
    @GET("item/{itemId}.json")
    Observable<Comment> getCommentDetails(@Path("itemId") int itemId);
}
