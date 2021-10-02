package com.alvesgleibson.youtubeapp;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;



import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private static final String GOOGLE_API_KEY = "AIzaSyBCaDI1XX9sazs30tNPNMc8vdVe20uCRo8";
    private YouTubePlayer.PlaybackEventListener playbackEventListener;
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.viewPlay);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);
        playBacktListener();
        playStateListener();

    }



    private void playStateListener() {
        playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {
                Toast.makeText(MainActivity.this, "Video Carregando ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoaded(String s) {
                Toast.makeText(MainActivity.this, "Video Carregado ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdStarted() {
                Toast.makeText(MainActivity.this, "Propaganda Iniciou" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoStarted() {
                Toast.makeText(MainActivity.this, "Video Começando ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoEnded() {
                Toast.makeText(MainActivity.this, "Video Fnalizado ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {
                Toast.makeText(MainActivity.this, "Erro ao carregar o video "+errorReason, Toast.LENGTH_SHORT).show();
            }
        };

    }

    private void playBacktListener() {

        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                Toast.makeText(MainActivity.this, "Video Execultando ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPaused() {
                Toast.makeText(MainActivity.this, "Video Pausado ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopped() {
                Toast.makeText(MainActivity.this, "Video Parado ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBuffering(boolean b) {
                Toast.makeText(MainActivity.this, "Video Pré-carregando ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSeekTo(int i) {
                Toast.makeText(MainActivity.this, " movimentando a SeekBar ", Toast.LENGTH_SHORT).show();
            }
        };

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {

        //youTubePlayer.loadVideo("DhGUEf_apPc");
        Log.d("Estado_play", "Foi restaurado: "+foiRestaurado);
        //youTubePlayer.setPlaybackEventListener( playbackEventListener );
        youTubePlayer.setPlayerStateChangeListener( playerStateChangeListener );
        if (!foiRestaurado){
            //youTubePlayer.cueVideo("DhGUEf_apPc");
            youTubePlayer.cuePlaylist("PLWz5rJ2EKKc8OENfLdh3zM5T6IRdlVYKj");
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Erro ao iniciar o Player "+youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }
}