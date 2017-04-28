package com.alpha.hackernewsreader.list;

import android.util.Log;
import com.alpha.hackernewsreader.api.ItemDetailsAPI;
import com.alpha.hackernewsreader.api.TopStoriesAPI;
import com.alpha.hackernewsreader.model.ItemDetails;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Thang
 * @since 25/4/17 21:20
 */
public class NewsListPresenter {
    private NewsListView mNewsListView;
    private TopStoriesAPI mTopStoriesAPI;
    private ItemDetailsAPI mItemDetailsAPI;

    private Disposable subscribe;

    @Inject
    public NewsListPresenter(TopStoriesAPI topStoriesAPI, ItemDetailsAPI itemDetailsAPI) {
        mTopStoriesAPI = topStoriesAPI;
        mItemDetailsAPI = itemDetailsAPI;
    }

    public void setNewsListView(NewsListView newsListView) {
        mNewsListView = newsListView;
    }

    public void fetchTopStories() {
        subscribe = mTopStoriesAPI.getTopStories()
                .flatMap(new Function<List<Long>, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(List<Long> ids) throws Exception {
                        return Observable.fromIterable(ids);
                    }
                })
                .take(10)
                .concatMap(new Function<Long, ObservableSource<ItemDetails>>() {
                    @Override
                    public ObservableSource<ItemDetails> apply(Long id) throws Exception {
                        return mItemDetailsAPI.getItemDetails(id);
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ItemDetails>>() {
                    @Override
                    public void accept(List<ItemDetails> itemDetails) throws Exception {
                        Log.d("TAG", "" + itemDetails.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    public void destroy() {
        subscribe.dispose();
    }
}
