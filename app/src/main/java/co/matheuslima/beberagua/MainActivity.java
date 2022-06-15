package co.matheuslima.beberagua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private TimePicker timePicker;
    private EditText editMinutes;

    private boolean isRun = false;

    private int hour;
    private int minute;
    private int interval;

    private SharedPreferences preferences;
    // Banco para poucos dados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = findViewById(R.id.btn_notify);
        timePicker = findViewById(R.id.time_picker);
        editMinutes = findViewById(R.id.edit_txt_number_interval);

        preferences = getSharedPreferences("db", Context.MODE_PRIVATE);

        isRun = preferences.getBoolean("activated", false);

        if (isRun) {
            btnNotify.setText(R.string.pause);
            btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_red_light));

            int savedHours = preferences.getInt("hour", timePicker.getCurrentHour());
            int savedMinutes = preferences.getInt("minute", timePicker.getCurrentMinute());
            int savedInterval = preferences.getInt("interval", 0);

            timePicker.setCurrentHour(savedHours);
            timePicker.setCurrentMinute(savedMinutes);
            editMinutes.setText(Integer.toString(savedInterval));
        }

        timePicker.setIs24HourView(true);
        getSupportActionBar().hide();

    }

    public void notifyClick(View view) {
        String sInterval = editMinutes.getText().toString();

        if (sInterval.isEmpty()) {
            Toast.makeText(this, R.string.error_msg, Toast.LENGTH_LONG).show();
            return;
        }

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
        interval = Integer.parseInt(sInterval);

        if (!isRun) {
            btnNotify.setText(R.string.pause);
            btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_red_light));
            isRun = true;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", true);
            editor.putInt("interval", interval);
            editor.putInt("hour", hour);
            editor.putInt("minute", minute);
            editor.apply();

        } else {
            btnNotify.setText(R.string.notify);
            btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.teal_200));
            isRun = false;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", false);
            editor.remove("interval");
            editor.remove("hour");
            editor.remove("minute");
            editor.apply();

        }

    }

}