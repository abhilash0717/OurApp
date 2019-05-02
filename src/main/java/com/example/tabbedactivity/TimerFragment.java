package com.example.tabbedactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.security.PublicKey;

public class TimerFragment extends Fragment {

    TextView timerTextview;
    Button button;
    SeekBar timerseekbar;
    Boolean counterActivity = false;
    CountDownTimer countDownTimer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timer_fragment,container,false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timerseekbar= view.findViewById(R.id.timerseekBar);
        timerTextview = view.findViewById(R.id.timertextview);
        button = view.findViewById(R.id.gobutton);

        timerseekbar.setMax(1500);
        timerseekbar.setProgress(1500);

        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                UpdateTimer (progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(counterActivity){

                    timerTextview.setText("25:00");
                    timerseekbar.setProgress(1500);
                    timerseekbar.setEnabled(true);
                    countDownTimer.cancel();
                    button.setText("go!");
                    counterActivity = false;

                } else {

                    counterActivity = true;
                    timerseekbar.setEnabled(false);

                    countDownTimer = new CountDownTimer(timerseekbar.getProgress() * 1000, 1000) {

                        @Override
                        public void onTick(long l) {

                            UpdateTimer((int) l / 1000);
                            button.setText("stop");


                        }

                        @Override
                        public void onFinish() {

                            Log.i("finished", "timer all done!");
                            timerTextview.setText("25:00");
                            button.setText("go");
                            timerseekbar.setProgress(1500);
                            timerseekbar.setEnabled(true);
                            countDownTimer.cancel();
                            counterActivity = false;


                        }
                    }.start();
                }

            }
        });

 }
    public void UpdateTimer ( int Secondsleft){
        int minutes = Secondsleft / 60;
        int seconds = Secondsleft - (minutes * 60);

        String SecondString = Integer.toString(seconds);

        if (seconds <= 9) {
            SecondString = "0" + SecondString ;
        }
        String minuteString = Integer.toString(minutes);
        if(minutes<=9){

            minuteString = "0" + minuteString;

        }

        timerTextview.setText( minuteString + ":" + (SecondString));

    }
}





