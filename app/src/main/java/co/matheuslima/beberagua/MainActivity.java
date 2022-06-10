package co.matheuslima.beberagua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private TimePicker timePicker;
    private EditText editMinutes;

    private int hour;
    private int minute;
    private int interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = findViewById(R.id.btn_notify);
        timePicker = findViewById(R.id.time_picker);
        editMinutes = findViewById(R.id.edit_txt_number_interval);

        timePicker.setIs24HourView(true);

        btnNotify.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String sInterval = editMinutes.getText().toString();

                        hour = timePicker.getCurrentHour();
                        minute = timePicker.getCurrentMinute();
                        interval = Integer.parseInt(sInterval);

                        Log.d("Hour", hour + ":" + minute);
                        Log.d("Interval", Integer.toString(interval));
                    }
                }
        );

    }

//    public View.OnClickListener notifyClick = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            String sInterval = editMinutes.getText().toString();
//
//            hour = timePicker.getCurrentHour();
//            minute = timePicker.getCurrentMinute();
//            interval = Integer.parseInt(sInterval);
//
//            Log.d("Hour", Integer.toString(hour) + ":" + Integer.toString(minute));
//            Log.d("Interval", Integer.toString(interval));
//        }
//    };


//    public void notifyClick(View view) {
//        String sInterval = editMinutes.getText().toString();
//
//        hour = timePicker.getCurrentHour();
//        minute = timePicker.getCurrentMinute();
//        interval = Integer.parseInt(sInterval);
//
//        Log.d("Hour", Integer.toString(hour) + ":" + Integer.toString(minute));
//        Log.d("Interval", Integer.toString(interval));
//    }

}