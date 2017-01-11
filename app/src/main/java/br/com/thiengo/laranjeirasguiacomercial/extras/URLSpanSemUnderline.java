package br.com.thiengo.laranjeirasguiacomercial.extras;

import android.text.TextPaint;
import android.text.style.URLSpan;

/**
 * Created by viniciusthiengo on 11/01/17.
 */

public class URLSpanSemUnderline extends URLSpan {
    public URLSpanSemUnderline(String url) {
        super(url);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
}
