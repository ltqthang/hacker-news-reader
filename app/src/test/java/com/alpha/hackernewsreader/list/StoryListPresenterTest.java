package com.alpha.hackernewsreader.list;

import com.alpha.hackernewsreader.RxTestSetup;
import com.alpha.hackernewsreader.api.StoryDetailsAPI;
import com.alpha.hackernewsreader.api.TopStoriesAPI;
import com.alpha.hackernewsreader.model.Story;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Thang
 * @since 3/5/17 20:57
 */
@RunWith(RobolectricTestRunner.class)
public class StoryListPresenterTest {
    private StoryListPresenter mPresenter;

    @Mock
    StoryListView mStoryListView;

    @Mock
    TopStoriesAPI mTopStoriesAPI;

    @Mock
    StoryDetailsAPI mStoryDetailsAPI;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        RxTestSetup.setup();

        mPresenter = new StoryListPresenter(mTopStoriesAPI, mStoryDetailsAPI);
        mPresenter.setStoryListView(mStoryListView);
    }

    @After
    public void tearDown() throws Exception {
        RxTestSetup.reset();
    }

    @Test
    public void fetchSuccess() throws Exception {
        Story story0 = Story.newBuilder().title("story0").build();
        Story story1 = Story.newBuilder().title("story1").build();
        List<Story> items = Arrays.asList(story0, story1);
        when(mTopStoriesAPI.getTopStories()).thenReturn(Observable.just(Arrays.asList(1L, 2L)));
        when(mStoryDetailsAPI.getStoryDetails(1L)).thenReturn(Observable.just(story0));
        when(mStoryDetailsAPI.getStoryDetails(2L)).thenReturn(Observable.just(story1));

        mPresenter.fetchTopStories(false);

        verify(mStoryListView).onStoryListFetched(items);
    }

    @Test
    public void showLoadingView() throws Exception {
        when(mTopStoriesAPI.getTopStories()).thenReturn(Observable.<List<Long>>empty());

        mPresenter.fetchTopStories(false);

        verify(mStoryListView).onLoading();
    }

    @Test
    public void notShowLoadingIfRefresh() throws Exception {
        when(mTopStoriesAPI.getTopStories()).thenReturn(Observable.<List<Long>>empty());

        mPresenter.fetchTopStories(true);

        verify(mStoryListView, never()).onLoading();
    }

    @Test
    public void showErrorView_0() throws Exception {
        when(mTopStoriesAPI.getTopStories()).thenReturn(Observable.<List<Long>>error(new Throwable()));
        when(mStoryDetailsAPI.getStoryDetails(anyLong())).thenReturn(Observable.<Story>empty());

        mPresenter.fetchTopStories(false);

        verify(mStoryListView).onError();
    }

    @Test
    public void showErrorView_1() throws Exception {
        when(mTopStoriesAPI.getTopStories()).thenReturn(Observable.just(Arrays.asList(1L, 2L)));
        when(mStoryDetailsAPI.getStoryDetails(anyLong())).thenReturn(Observable.<Story>error(new Throwable()));

        mPresenter.fetchTopStories(false);

        verify(mStoryListView).onError();
    }
}