package pt.iade.andre.diogo.cartrackapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.errorprone.annotations.Var;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pt.iade.andre.diogo.cartrackapp.Models.ClassInspecoes;

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

        try {
            String path = "http://10.0.2.2:8080/inspecoes/centros";

            try {
                Request rq = new Request.Builder().url(path).build();
                OkHttpClient client = new OkHttpClient();

                client.newCall(rq).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.d("Error", "" + e);
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        ArrayList<ClassInspecoes> myClassList = new Gson().fromJson(response.body().string(), new TypeToken<List<ClassInspecoes>>() {
                        }.getType());

                        String[] Centro = new String[myClassList.size()];
                        for (int i = 0; i < myClassList.size(); i++)
                            Centro[i] = (myClassList.get(i).getNomeCentro());



                        Spinner s = (Spinner) findViewById(R.id.spnCentroDeInspecao);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlusActivity.this, android.R.layout.simple_spinner_item, Centro);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        s.setAdapter(adapter);
                    }
                });
            } catch (Exception ex) {
                Log.d("s", "" + ex);
            }
        } catch (Exception ex) {

        }

    }

    private void onclick(View v) {

        int s = v.getId();

        if (s == R.id.txtPlusAnoMes) {
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
                    ((TextView) findViewById(R.id.txtPlusAnoMes)).setText(dayOfMonth + "/" + month + "/" + year);
                    dateWhatever = new GregorianCalendar(year, month, dayOfMonth);

                    TimePickerDialog timepick = new TimePickerDialog(PlusActivity.this, new TimePickerDialog.OnTimeSetListener() {


                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            //rt1.setText(hourOfDay + ":" + minute);
                            String value = ((TextView) findViewById(R.id.txtPlusAnoMes)).getText().toString();
                            ((TextView) findViewById(R.id.txtPlusAnoMes)).setText(value + " " + hourOfDay + ":" + minute);
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

        } else if (!(s == R.id.btnSaveAddValue)) {

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
        } else {

            //Imaginar uma maneira de criar um calendar no meio do ecra, "apagar tudo" enquanto o calendario está on e quando clicar desaparecer e enviar para a textbox.
            Toast.makeText(getApplicationContext(), "Clicado o botão", Toast.LENGTH_LONG).show();
            String value = ((Spinner) findViewById(R.id.spinner1)).getSelectedItem().toString();

            if (checkValues()) {

            }
        }
    }

    private boolean checkValues() {
        if ((findViewById(R.id.pnlNewInspection)).isShown()) {

            //region Checkar se está tudo "ok"


            //endregion
        } else if ((findViewById(R.id.pnlNewCar)).isShown()) {

            //region Checkar se está tudo "ok"


            //endregion

        } else if ((findViewById(R.id.pnlNewCoima)).isShown()) {

            //region Checkar se está tudo "ok"
            String valorcoima = ((EditText) findViewById(R.id.txtValorMulta)).getText().toString();
            String DataHora = ((EditText) findViewById(R.id.txtDataHoraMulta)).getText().toString();
            String DetalhesMulta = ((EditText) findViewById(R.id.txtDetalhesMulta)).getText().toString();

            //region Checkar se o valor da coima está certo
            if (valorcoima.equals("")) {
                ((EditText) findViewById(R.id.txtValorMulta)).setError("O valor não pode estar a vazio!");
                return false;
            }
            if (!valorcoima.contains("."))
                valorcoima = valorcoima + ".00";
            if (valorcoima.matches("[0-9]+\\.?")) {
                ((EditText) findViewById(R.id.txtValorMulta)).setError("O valor só pode ter numeros!");
                return false;
            }
            if (valorcoima.chars().filter(ch -> ch == '.').count() > 1) {
                ((EditText) findViewById(R.id.txtValorMulta)).setError("O valor não pode ter mais que dois pontos!");
                return false;
            }
            //endregion
            //region Checkar se existe data

            if (DataHora.equals(""))
                ((EditText) findViewById(R.id.txtDataHoraMulta)).setError("A data não pode estar a vazio ... clica em mim!!");
            //endregion
            //endregion


        }

        return true;
    }
}
