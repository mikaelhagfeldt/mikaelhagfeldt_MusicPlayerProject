package com.example.mikael.mikaelhagfeldt_musicplayerproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Thread fieldThread;

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
        this.fieldSeekBar.setMax(this.fieldMediaPlayer.getDuration());

        this.fieldSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                if (b)
                {
                    fieldMediaPlayer.seekTo(i);
                }
                SimpleDateFormat localDateFormat = new SimpleDateFormat("mm:ss");
                int localCurrentPosition = fieldMediaPlayer.getCurrentPosition();
                int localCurrentDuration = fieldMediaPlayer.getDuration();
                fieldTextViewLeft.setText(localDateFormat.format(new Date(localCurrentPosition)));
                fieldTextViewRight.setText(localDateFormat.format(new Date(localCurrentDuration)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    /*
        Skapar processor som körs samtidigt som applikationen kör, och som uppdaterar texten
        nedanför SeekBar i realtid. Koden nedanför körs medan låten spelas, dvs medan
        SeekBar rör sig framåt. Thread sleep på minst 40, annars hänger sig applikationen.
     */

    public void updater()
    {
        this.fieldThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    while ((fieldMediaPlayer.isPlaying()) && (fieldMediaPlayer != null))
                    {
                        Thread.sleep(40);
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                int localCurrentPositionNew = fieldMediaPlayer.getCurrentPosition();
                                fieldSeekBar.setProgress(localCurrentPositionNew);
                                fieldTextViewLeft.setText(String.valueOf(new SimpleDateFormat("mm:ss").format(new Date(fieldMediaPlayer.getCurrentPosition()))));
                            }
                        });
                    }
                }
                catch (Exception e)
                {
                    Log.d("Error.", "Something wrong with Thread.");
                }
            }
        };
        this.fieldThread.start();
    }

    public void playSong()
    {
        if (this.fieldMediaPlayer != null)
        {
            this.fieldMediaPlayer.start();
            updater();
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
                previousSong();
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
                nextSong();
                break;
        }
    }

    public void nextSong()
    {
        if (this.fieldMediaPlayer != null)
        {
            this.fieldMediaPlayer.seekTo(this.fieldMediaPlayer.getDuration() - 500); // hoppar till slutet av aktuell låt
        }
    }

    public void previousSong()
    {
        if (this.fieldMediaPlayer.isPlaying())
        {
            this.fieldMediaPlayer.seekTo(0-1); // fungerar inte, seekTo() fel metod?
        }
    }


}
