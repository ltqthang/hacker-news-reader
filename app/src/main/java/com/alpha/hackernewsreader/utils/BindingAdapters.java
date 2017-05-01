package com.alpha.hackernewsreader.utils;

import android.databinding.BindingAdapter;
import android.text.Html;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Thang
 * @since 6/5/17 11:19
 */
public class BindingAdapters {
    @BindingAdapter("android:htmlText")
    public static void setHtmlText(TextView textView, String htmlText) {
        if (htmlText != null) {
            textView.setText(Html.fromHtml(htmlText));
        }
    }

    @BindingAdapter("android:timeText")
    public static void setHtmlText(TextView textView, long time) {
        textView.setText(SimpleDateFormat.getDateInstance().format(new Date(time)));
    }
}
