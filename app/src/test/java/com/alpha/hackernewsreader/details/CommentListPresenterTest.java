package com.alpha.hackernewsreader.details;

import com.alpha.hackernewsreader.RxTestSetup;
import com.alpha.hackernewsreader.api.CommentDetailsAPI;
import com.alpha.hackernewsreader.model.Comment;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Thang
 * @since 6/5/17 11:35
 */
public class CommentListPresenterTest {
    private CommentListPresenter mPresenter;

    @Mock
    CommentListView mView;

    @Mock
    CommentDetailsAPI mAPI;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        RxTestSetup.setup();

        mPresenter = new CommentListPresenter(mAPI);
        mPresenter.setView(mView);
    }

    @After
    public void tearDown() throws Exception {
        RxTestSetup.reset();
    }

    @Test
    public void fetchSuccess() throws Exception {
        when(mAPI.getCommentDetails(anyInt())).thenReturn(Observable.just(Comment.newBuilder().build()));

        mPresenter.fetchCommentList(Arrays.asList(1, 2, 3, 4));

        verify(mView).onCommentListFetched(anyListOf(Comment.class));
    }

    @Test
    public void showLoadingView() throws Exception {
        when(mAPI.getCommentDetails(anyInt())).thenReturn(Observable.just(Comment.newBuilder().build()));

        mPresenter.fetchCommentList(Collections.<Integer>emptyList());

        verify(mView).onLoading();
    }

    @Test
    public void showErrorView() throws Exception {
        when(mAPI.getCommentDetails(anyInt())).thenReturn(Observable.<Comment>error(new Throwable()));

        mPresenter.fetchCommentList(Arrays.asList(1));

        verify(mView).onError();
    }
}