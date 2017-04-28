package com.alpha.hackernewsreader.api;

import com.alpha.hackernewsreader.model.ItemDetails;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Thang
 * @since 25/4/17 21:09
 */
public interface ItemDetailsAPI {
    @GET("item/{itemId}.json")
    Observable<ItemDetails> getItemDetails(@Path("itemId") Long itemId);
}
