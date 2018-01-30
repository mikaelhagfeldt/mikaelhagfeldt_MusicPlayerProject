package com.example.mikael.mikaelhagfeldt_musicplayerproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private MediaPlayer fieldMediaPlayer;
    private ImageView fieldImageView;
    private Button fieldButtonPrev;
    private Button fieldButtonPlay;
    private Button fieldButtonNext;
    private TextView fieldTextViewLeft;
    private TextView fieldTextViewRight;
    private SeekBar fieldSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fieldImageView = findViewById(R.id.id_ma1_imageView);
        this.fieldButtonPrev = findViewById(R.id.id_ma1_previousButton);
        this.fieldButtonPlay = findViewById(R.id.id_ma1_playButton);
        this.fieldButtonNext = findViewById(R.id.id_ma1_nextButton);
        this.fieldTextViewLeft = findViewById(R.id.id_ma1_tw_start);
        this.fieldTextViewRight = findViewById(R.id.id_ma1_tw_end);
        this.fieldSeekBar = findViewById(R.id.id_ma1_seekBar);
        this.fieldButtonNext.setOnClickListener(this);
        this.fieldButtonPlay.setOnClickListener(this);
        this.fieldButtonPrev.setOnClickListener(this);
        this.fieldMediaPlayer = new MediaPlayer();
        this.fieldMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.goinghome);



    }

    public void playSong()
    {
        if (this.fieldMediaPlayer != null)
        {
            this.fieldMediaPlayer.start();
            this.fieldButtonPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    public void pauseSong()
    {
        if (this.fieldMediaPlayer != null)
        {
            this.fieldMediaPlayer.pause();
            this.fieldButtonPlay.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.id_ma1_previousButton:
                break;
            case R.id.id_ma1_playButton:
                if (fieldMediaPlayer.isPlaying())
                {
                    pauseSong();
                }
                else
                {
                    playSong();
                }
                break;
            case R.id.id_ma1_nextButton:
                break;
        }
    }


}
