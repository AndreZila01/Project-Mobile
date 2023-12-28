package pt.iade.andre.diogo.cartrackapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.errorprone.annotations.Var;

import java.time.DateTimeException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PlusActivity extends AppCompatActivity {
    GregorianCalendar dateWhatever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    private void background_doWork() {
        (findViewById(R.id.btnAddInspction)).setOnClickListener(this::onclick);
        (findViewById(R.id.btnAddCar)).setOnClickListener(this::onclick);
        (findViewById(R.id.btnAddCoime)).setOnClickListener(this::onclick);
        (findViewById(R.id.btnSaveAddValue)).setOnClickListener(this::onclick);
        (findViewById(R.id.txtPlusAnoMes)).setOnClickListener(this::onclick);

        findViewById(R.id.BackActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onclick(View v) {

        int s = v.getId();

        if(s == R.id.txtPlusAnoMes){
            InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.

            View view = this.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(this);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            final int hour = c.get(Calendar.HOUR_OF_DAY);
            final int minute = c.get(Calendar.MINUTE);

            DatePickerDialog datepick = new DatePickerDialog(PlusActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    ((TextView)findViewById(R.id.txtPlusAnoMes)).setText(dayOfMonth+"/"+month+"/"+year);
                    dateWhatever = new GregorianCalendar(year, month, dayOfMonth);

                    TimePickerDialog timepick = new TimePickerDialog(PlusActivity.this, new TimePickerDialog.OnTimeSetListener() {


                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            //rt1.setText(hourOfDay + ":" + minute);
                            String value = ((TextView)findViewById(R.id.txtPlusAnoMes)).getText().toString();
                            ((TextView)findViewById(R.id.txtPlusAnoMes)).setText(value +" "+hourOfDay+":"+minute);
                            Log.d("", "dada");


                        }
                    }, hour, minute, true
                    );
                    timepick.setTitle("select time");
                    timepick.show();
                }
            }, year, month, day);
            datepick.setTitle("select date");
            datepick.show();
            Log.d("", "");

        }
        else if (!(s == R.id.btnSaveAddValue)) {

            (findViewById(R.id.pnlNewCoima)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.pnlNewCar)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.pnlNewInspection)).setVisibility(View.INVISIBLE);

            if (s == R.id.btnAddCar) {
                ((EditText) findViewById(R.id.txtPlusAnoMes)).setFocusable(false);
                (findViewById(R.id.pnlNewCar)).setVisibility(View.VISIBLE);
            } else if (s == R.id.btnAddInspction) {
                (findViewById(R.id.pnlNewInspection)).setVisibility(View.VISIBLE);
            }
            if (s == R.id.btnAddCoime) {
                (findViewById(R.id.pnlNewCoima)).setVisibility(View.VISIBLE);
            }
        }
        else {

            //Imaginar uma maneira de criar um calendar no meio do ecra, "apagar tudo" enquanto o calendario está on e quando clicar desaparecer e enviar para a textbox.
            Toast.makeText(getApplicationContext(), "Clicado o botão", Toast.LENGTH_LONG).show();
            String value = ((Spinner)findViewById(R.id.spinner1)).getSelectedItem().toString();

            if(checkValues()){

            }
        }
    }

    private boolean checkValues(){
        if((findViewById(R.id.pnlNewInspection)).isShown()){

        }
        else if((findViewById(R.id.pnlNewCar)).isShown()){

        }
        else if((findViewById(R.id.pnlNewCoima)).isShown()){

        }

        return true;
    }
}
