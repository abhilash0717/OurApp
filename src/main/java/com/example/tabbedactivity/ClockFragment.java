package com.example.tabbedactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;


public class ClockFragment extends Fragment implements View.OnClickListener {


    private int notificationId = 1;
    TimePicker timePicker;
    AlarmManager alarm;
    PendingIntent alarmIntent;
    Switch alarmSwitch;
    EditText editText;
    TextView statusTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clock_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        statusTextView = view.findViewById(R.id.statusTextView);

        alarmSwitch = view.findViewById(R.id.alarmSwitch);

        editText = view.findViewById(R.id.labelEditText);
        timePicker =  view.findViewById(R.id.timePicker);
        alarm = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);


        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                Intent intent = new Intent(getActivity(),AlarmReciever.class);

                intent.putExtra("notificationID",notificationId);
                intent.putExtra("toDO",editText.getText().toString());

                editText = view.findViewById(R.id.labelEditText);
                timePicker =  view.findViewById(R.id.timePicker);
                alarm = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);


                alarmIntent = PendingIntent.getBroadcast(getActivity(),0,intent,PendingIntent.FLAG_CANCEL_CURRENT);




                if(alarmSwitch.isChecked()){

                    int hour = timePicker.getCurrentHour();
                    int minute = timePicker.getCurrentMinute();

                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY,hour);
                    startTime.set(Calendar.MINUTE,minute);
                    startTime.set(Calendar.SECOND,0);
                    long alarmStartTime = startTime.getTimeInMillis();

                    alarm.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);

                    Toast.makeText(getActivity(), "Alarm is set", Toast.LENGTH_SHORT).show();
                    statusTextView.setText("Alarm Status: on");


                } else {

                    alarm.cancel(alarmIntent);
                    Toast.makeText(getActivity(), "Alarm is cancelled", Toast.LENGTH_SHORT).show();
                    statusTextView.setText("Alarm Status: off");
                }
            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}