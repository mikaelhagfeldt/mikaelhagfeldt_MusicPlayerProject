package com.example.mikael.mikaelhagfeldt_musicplayerproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
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






    }


}
