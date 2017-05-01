package com.alpha.hackernewsreader.details;

import com.alpha.hackernewsreader.api.CommentDetailsAPI;
import com.alpha.hackernewsreader.model.Comment;
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
 * @since 6/5/17 10:48
 */
public class CommentListPresenter {
    private CommentListView mView;
    private CommentDetailsAPI mAPI;

    private Disposable subscribe;

    @Inject
    CommentListPresenter(CommentDetailsAPI API) {
        mAPI = API;
    }

    public void setView(CommentListView view) {
        mView = view;
    }

    void fetchCommentList(List<Integer> commentIds) {
        mView.onLoading();

        subscribe = Observable.fromIterable(commentIds)
                .concatMap(new Function<Integer, ObservableSource<Comment>>() {
                    @Override
                    public ObservableSource<Comment> apply(Integer id) throws Exception {
                        return mAPI.getCommentDetails(id);
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Comment>>() {
                    @Override
                    public void accept(List<Comment> comments) throws Exception {
                        mView.onCommentListFetched(comments);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.log(throwable);
                        mView.onError();
                    }
                });
    }

    void destroy() {
        subscribe.dispose();
    }
}
