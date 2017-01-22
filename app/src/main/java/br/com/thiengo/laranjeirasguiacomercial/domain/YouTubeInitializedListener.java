package br.com.thiengo.laranjeirasguiacomercial.domain;

import android.app.Activity;
import android.content.Context;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import java.lang.ref.WeakReference;

import br.com.thiengo.laranjeirasguiacomercial.ComercioActivity;

/**
 * Created by viniciusthiengo on 18/01/17.
 */

public class YouTubeInitializedListener implements YouTubePlayer.OnInitializedListener {
    private WeakReference<Comercio> weakActivity;

    public YouTubeInitializedListener( Comercio comercio ){
        weakActivity = new WeakReference<Comercio>(comercio);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {
        if( !foiRestaurado ) {
            youTubePlayer.loadVideo( weakActivity.get().getYouTubeCode() );
            //youTubePlayer.play();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult resultado) {
        // SHOW A DIALOG ASKING TO INSTALL YOUTUBE APP
        /*resultado
            .getErrorDialog( weakActivity.get(), 1)
            .show();*/
    }
}
