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
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pt.iade.andre.diogo.cartrackapp.Models.ClassCar;
import pt.iade.andre.diogo.cartrackapp.Models.ClassInspecoes;
import pt.iade.andre.diogo.cartrackapp.Models.ClassRegist;

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
        (findViewById(R.id.txtDataHoraMulta)).setOnClickListener(this::onclick);
        (findViewById(R.id.txtInspDataHora)).setOnClickListener(this::onclick);

        findViewById(R.id.BackActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Todas as inspecções
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

        try {
            String path = "http://10.0.2.2:8080/car/" + LoginActivity.IdUser;

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

                        String value = response.body().string().toString();

                        if (!value.equals("vazio!!")) {
                            ArrayList<ClassCar> myClassList = new Gson().fromJson(value, new TypeToken<List<ClassCar>>() {
                            }.getType());

                            String[] Centro = new String[myClassList.size()];
                            for (int i = 0; i < myClassList.size(); i++)
                                Centro[i] = (myClassList.get(i).getModelo()) + " (" + (myClassList.get(i).getMatricula()) + ")";


                            Spinner s = (Spinner) findViewById(R.id.spnCarro);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlusActivity.this, android.R.layout.simple_spinner_item, Centro);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(adapter);

                            Spinner ss = (Spinner) findViewById(R.id.spnCarroMulta);
                            ArrayAdapter<String> adapters = new ArrayAdapter<String>(PlusActivity.this, android.R.layout.simple_spinner_item, Centro);
                            adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            ss.setAdapter(adapters);
                        }
                    }
                });
            } catch (Exception ex) {
                Log.d("s", "" + ex);
            }
        } catch (Exception ex) {

        }

        // Fazer um request post com header do id do user

    }

    private void onclick(View v) {

        int s = v.getId();

        if (s == R.id.txtPlusAnoMes || s == R.id.txtDataHoraMulta || s == R.id.txtInspDataHora) {
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


            //mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
            DatePickerDialog datepick = new DatePickerDialog(PlusActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                    //view.setMaxDate(System.currentTimeMillis());

                    String years = ""+year;
                    if (s == R.id.txtPlusAnoMes)
                        ((TextView) findViewById(R.id.txtPlusAnoMes)).setText(month + 1 + "/" + years.substring(2,4));
                    if (s == R.id.txtDataHoraMulta)
                        ((TextView) findViewById(R.id.txtDataHoraMulta)).setText(dayOfMonth + "/" + month + 1 + "/" + year);
                    if (s == R.id.txtInspDataHora)
                        ((TextView) findViewById(R.id.txtInspDataHora)).setText(dayOfMonth + "/" + month + 1 + "/" + year);
                    dateWhatever = new GregorianCalendar(year, month + 1, dayOfMonth);

                    if (s != R.id.txtPlusAnoMes) {
                        TimePickerDialog timepick = new TimePickerDialog(PlusActivity.this, new TimePickerDialog.OnTimeSetListener() {


                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //rt1.setText(hourOfDay + ":" + minute);

                                if (s == R.id.txtDataHoraMulta) {
                                    String value = ((TextView) findViewById(R.id.txtDataHoraMulta)).getText().toString();
                                    ((TextView) findViewById(R.id.txtDataHoraMulta)).setText(value + " " + hourOfDay + ":" + minute);
                                }

                                if (s == R.id.txtInspDataHora) {
                                    String value = ((TextView) findViewById(R.id.txtInspDataHora)).getText().toString();
                                    ((TextView) findViewById(R.id.txtInspDataHora)).setText(value + " " + hourOfDay + ":" + minute);
                                }
                                Log.d("", "dada");


                            }
                        }, hour, minute, true
                        );

                        timepick.setTitle("select time");
                        timepick.show();
                    }
                }
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

            if (s != R.id.txtInspDataHora)
                datepick.getDatePicker().setMaxDate(System.currentTimeMillis());
            else
              datepick.getDatePicker().setMinDate(System.currentTimeMillis() - 100);


            Date ss = Calendar.getInstance().getTime();
            datepick.updateDate(ss.getYear(), ss.getMonth(), ss.getDay());

            datepick.setTitle("select date");
            datepick.show();
            Log.d("", "");

        } else if (!(s == R.id.btnSaveAddValue)) {

            (findViewById(R.id.pnlNewCoima)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.pnlNewCar)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.pnlNewInspection)).setVisibility(View.INVISIBLE);

            if (s == R.id.btnAddCar) {
                ((EditText) findViewById(R.id.txtPlusAnoMes)).setFocusable(false);//TODO: CHECKAR
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
            String value = ((Spinner) findViewById(R.id.spnConsumo)).getSelectedItem().toString();

            if (checkValues())
                SendApi();
        }
    }

    private boolean checkValues() {
        if ((findViewById(R.id.pnlNewInspection)).isShown()) {

            //region Checkar se está tudo "ok", Inspecções

            if (((Spinner) findViewById(R.id.spnCentroDeInspecao)).getSelectedItem().toString() == "Sem Dados") {
                Toast.makeText(getApplicationContext(), "Tem de escolher uma das opções em Centro de Inspecções", Toast.LENGTH_LONG).show();
                return false;
            }

            if (((Spinner) findViewById(R.id.spnCarro)).getSelectedItem().toString() == "Sem Dados") {
                Toast.makeText(getApplicationContext(), "Tem de escolher uma das opções em Carros", Toast.LENGTH_LONG).show();
                return false;
            }

            if (((EditText) findViewById(R.id.txtInspDataHora)).getText().equals("")) {
                ((EditText) findViewById(R.id.txtValorMulta)).setError("A data e Hora da inspecção não pode estar vazia!");
                return false;
            }


            //endregion
        } else if ((findViewById(R.id.pnlNewCar)).isShown()) {

            //region Checkar se está tudo "ok"

            String Cilindrada = ((EditText) findViewById(R.id.txtPlusCilindrada)).getText().toString();
            String Matricula = ((EditText) findViewById(R.id.txtPlusMatricula)).getText().toString();
            String km = ((EditText) findViewById(R.id.txtPlusKm)).getText().toString();

            if (((EditText) findViewById(R.id.txtPlusModelo)).getText().toString().equals("")) {
                ((EditText) findViewById(R.id.txtPlusModelo)).setError("Não posso estar a vazio!!");
                return false;
            }

            if (Matricula.equals("")) {
                ((EditText) findViewById(R.id.txtPlusMatricula)).setError("Não posso estar a vazio!!");
                return false;
            }

            if (!Matricula.matches("^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$") || !Matricula.contains("-") || Matricula.split("-").length != 3) {
                ((EditText) findViewById(R.id.txtPlusMatricula)).setError("Parece que a matricula está mal escrita ... escreva assim como por exemplo\"RT-54-IU\"!!");
                return false;
            }

            if (Cilindrada.equals("")) {
                ((EditText) findViewById(R.id.txtPlusCilindrada)).setError("Não posso estar a vazio!!");
                return false;
            }

            if (!Cilindrada.matches("^[0-9]*$")) {
                ((EditText) findViewById(R.id.txtPlusCilindrada)).setError("Só aceito numeros!!");
                return false;
            }

            if (((EditText) findViewById(R.id.txtPlusAnoMes)).getText().toString().equals("")) {
                ((EditText) findViewById(R.id.txtPlusAnoMes)).setError("Não pode estar a vazio!!");
                return false;
            }

            if (((EditText) findViewById(R.id.txtPlusKm)).getText().toString().equals("")) {
                ((EditText) findViewById(R.id.txtPlusKm)).setError("Não pode estar a vazio!!");
                return false;
            }

            if (km.equals("")) {
                ((EditText) findViewById(R.id.txtPlusKm)).setError("Não pode estar a vazio!!");
                return false;
            }

            if (!km.matches("^[0-9]+(\\.[0-9]+)?$") || !km.matches("^[0-9]*$")) {
                ((EditText) findViewById(R.id.txtPlusKm)).setError("Só pode ter numeros!!");
                return false;
            }


            // Todo: Regras new Car
            //endregion

        } else if ((findViewById(R.id.pnlNewCoima)).isShown()) {

            //region Checkar se está tudo "ok", Multas
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
            /*if (!valorcoima.matches("[0-9]+\\.?") || !valorcoima.matches("^[0-9]*$")) {
                ((EditText) findViewById(R.id.txtValorMulta)).setError("O valor só pode ter numeros!");
                return false;//TODO: DONT WORK
            }*/
            if (valorcoima.chars().filter(ch -> ch == '.').count() > 1) {
                ((EditText) findViewById(R.id.txtValorMulta)).setError("O valor não pode ter mais que dois pontos!");
                return false;
            }
            //endregion
            //region Checkar se existe data

            if (DataHora.equals("")) {
                ((EditText) findViewById(R.id.txtDataHoraMulta)).setError("A data não pode estar a vazio ... clica em mim!!");
                return false;
            }

            if (((Spinner) findViewById(R.id.spnCarroMulta)).getSelectedItem().toString() == "Sem Dados") {
                Toast.makeText(getApplicationContext(), "Tem de escolher uma das opções em Carros", Toast.LENGTH_LONG).show();
                return false;
            }

            //endregion
            //endregion


        }

        return true;
    }

    private void SendApi() {
        String url = "";
        String json = "";
        if ((findViewById(R.id.pnlNewInspection)).isShown()) {
            url = "http://10.0.2.2:8080/inspecoes/nova";


            json = new Gson().toJson("{\"CentroDeInspecoes\":\"" + ((Spinner) findViewById(R.id.spnCentroDeInspecao)).getSelectedItem().toString() + "\", \"DataHoraCentro\":\"" + ((EditText) findViewById(R.id.txtInspDataHora)).getText().toString() + "\", \"Carro\":\"" + ((Spinner) findViewById(R.id.spnCarro)).getSelectedItem().toString() + "\"}"); //TODO: Checkar se funfa, sem api
        } else if ((findViewById(R.id.pnlNewCar)).isShown()) {
            url = "http://10.0.2.2:8080/car/" + LoginActivity.IdUser + "/newCar";
            ClassCar reg = (new ClassCar(0, ((EditText) findViewById(R.id.txtPlusMatricula)).getText().toString(), ((EditText) findViewById(R.id.txtPlusModelo)).getText().toString(), ((EditText) findViewById(R.id.txtPlusAnoMes)).getText().toString(), ((Spinner) findViewById(R.id.spnConsumo)).getSelectedItem().toString(), Integer.parseInt(((EditText) findViewById(R.id.txtPlusCilindrada)).getText().toString()), Float.parseFloat(((EditText) findViewById(R.id.txtPlusKm)).getText().toString()), ""));

            json = new Gson().toJson(reg);//TODO: Checkar se funfa, sem api
        } else if ((findViewById(R.id.pnlNewCoima)).isShown()) {
            url = "http://10.0.2.2:8080/Account/Create";
            ClassRegist reg = new ClassRegist(0, ((TextView) findViewById(R.id.txtUsername)).getText().toString(), ((TextView) findViewById(R.id.txtPass)).getText().toString(), ((TextView) findViewById(R.id.txtName)).getText().toString().split(" ")[0], ((TextView) findViewById(R.id.txtName)).getText().toString().split(" ")[1], ((TextView) findViewById(R.id.txtEmail)).getText().toString(), ((TextView) findViewById(R.id.txtNasc)).getText().toString());

            json = new Gson().toJson(reg);
        }

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(aaa, json))
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Log.d("", "");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String s = response.body().string().toString();
                    if (s.equals("Utilizador registado com sucesso")) {
                        Toast.makeText(getApplicationContext(), "Utilizador criado com sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    } else if (s.equals("Utilizador já existente!!"))
                        Toast.makeText(getApplicationContext(), "Utilizador já existente", Toast.LENGTH_LONG).show();

                    Log.d("", "");
                }
            }
        });
    }

    public static final MediaType aaa = MediaType.parse("application/json; charset=utf-8");

}
