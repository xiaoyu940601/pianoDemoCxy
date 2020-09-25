package com.cxy.pianodemo;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import tech.oom.library.keyBoard.Key;
import tech.oom.library.keyBoard.PianoKeyBoard;
import tech.oom.library.sound.SoundPlayUtils;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private PianoKeyBoard keyBoard;
    /**
     * 按键的listener
     */
    PianoKeyBoard.KeyListener listener = new PianoKeyBoard.KeyListener() {
        @Override
        public void onKeyPressed(Key key) {
//某个键被按下的回调

        }

        @Override
        public void onKeyUp(Key key) {
//某个键被松开的回调
        }

        @Override
        public void currentFirstKeyPosition(int position) {
//            键盘显示的第一个键的index/position更新回调
            seekBar.setMax(keyBoard.getMaxMovePosition());
            seekBar.setProgress(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);//
        setContentView(R.layout.activity_main);
        SoundPlayUtils.init(this);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        seekBar = (SeekBar) findViewById(R.id.activity_play_seek_bar);
        keyBoard = (PianoKeyBoard) findViewById(R.id.keyboard);
        keyBoard.setPronuncTextColor(Color.argb(1,1,0,0));
        keyBoard.setC();
//        keyBoard.getKeyByKeycode(60).setPressed(true,false);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                keyBoard.moveToPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setMax(keyBoard.getMaxMovePosition());

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        keyBoard.setKeyCount(8);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyBoard.showPrevious();
                keyBoard.setPronuncTextDimension(12 * getResources().getDisplayMetrics().scaledDensity);
//                keyBoard.invalidate();
//                keyBoard.getKeyByKeycode(31).setPressed(false,false);
//                keyBoard.getKeyByKeycode(35).setPressed(true,false);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyBoard.showNext();
//                keyBoard.invalidate();
//                keyBoard.getKeyByKeycode(35).setPressed(false,false);
//                keyBoard.getKeyByKeycode(31).setPressed(true,false);
            }
        });

        keyBoard.setKeyListener(listener);

    }
}