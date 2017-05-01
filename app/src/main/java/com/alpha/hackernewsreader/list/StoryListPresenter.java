package com.alpha.hackernewsreader.list;

import com.alpha.hackernewsreader.api.StoryDetailsAPI;
import com.alpha.hackernewsreader.api.TopStoriesAPI;
import com.alpha.hackernewsreader.model.Story;
import com.alpha.hackernewsreader.utils.Logger;
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
public class StoryListPresenter {
    private static final int LIMIT = 10;

    private StoryListView mStoryListView;
    private TopStoriesAPI mTopStoriesAPI;
    private StoryDetailsAPI mStoryDetailsAPI;

    private Disposable subscribe;

    @Inject
    StoryListPresenter(TopStoriesAPI topStoriesAPI, StoryDetailsAPI storyDetailsAPI) {
        mTopStoriesAPI = topStoriesAPI;
        mStoryDetailsAPI = storyDetailsAPI;
    }

    void setStoryListView(StoryListView storyListView) {
        mStoryListView = storyListView;
    }

    public void fetchTopStories(boolean isRefreshing) {
        if (!isRefreshing) {
            mStoryListView.onLoading();
        }

        subscribe = mTopStoriesAPI.getTopStories()
                .flatMap(new Function<List<Long>, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(List<Long> ids) throws Exception {
                        return Observable.fromIterable(ids);
                    }
                })
                .take(LIMIT)
                .concatMap(new Function<Long, ObservableSource<Story>>() {
                    @Override
                    public ObservableSource<Story> apply(Long id) throws Exception {
                        return mStoryDetailsAPI.getStoryDetails(id);
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Story>>() {
                    @Override
                    public void accept(List<Story> itemDetails) throws Exception {
                        mStoryListView.onStoryListFetched(itemDetails);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.log(throwable);
                        mStoryListView.onError();
                    }
                });
    }

    void destroy() {
        subscribe.dispose();
    }
}
