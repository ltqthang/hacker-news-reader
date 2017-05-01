package com.alpha.hackernewsreader.api;

import com.alpha.hackernewsreader.model.Story;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Thang
 * @since 25/4/17 21:09
 */
public interface StoryDetailsAPI {
    @GET("item/{itemId}.json")
    Observable<Story> getStoryDetails(@Path("itemId") Long itemId);
}
