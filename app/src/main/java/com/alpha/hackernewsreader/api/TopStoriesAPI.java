package com.alpha.hackernewsreader.api;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;

/**
 * @author Thang
 * @since 25/4/17 21:08
 */
public interface TopStoriesAPI {
    @GET("topstories.json")
    Observable<List<Long>> getTopStories();
}
