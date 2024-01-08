package pt.iade.andre.diogo.cartrackapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.internal.FallbackServiceBroker;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.StringEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pt.iade.andre.diogo.cartrackapp.Models.ClassRegist;

public class RegistarActivity extends AppCompatActivity {

    GregorianCalendar dateWhatever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    private void background_doWork() {

        findViewById(R.id.btnRegist).setOnClickListener(this::onClick);
        ((EditText) findViewById(R.id.txtNasc)).setFocusable(false);
        ((TextView) findViewById(R.id.txtNasc)).setOnClickListener(this::onClick);

        (findViewById(R.id.BackActivity)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistarActivity.this, AjudaActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    private void onClick(View v) {

        int temp = v.getId();
        if (temp == R.id.btnRegist) {

            try {
                if (CheckValues())
                    CreateAccount();
            } catch (Exception ex) {
                Log.d("", "");
            }
        } else if (temp == R.id.txtNasc) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = this.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(this);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            final Calendar c = Calendar.getInstance();
            final int year = c.get(Calendar.YEAR);
            final int month = c.get(Calendar.MONTH);
            final int day = c.get(Calendar.DAY_OF_WEEK);
            DatePickerDialog datepick = new DatePickerDialog(RegistarActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    dateWhatever = new GregorianCalendar(year, month, dayOfMonth);
                    ((TextView) findViewById(R.id.txtNasc)).setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth));
                }

            }, year, month, day);
            datepick.setTitle("select date");
            datepick.show();
        } else if (temp == R.id.textView2) {

        }
    }

    public static final MediaType aaa = MediaType.parse("application/json; charset=utf-8");

    private void CreateAccount() throws IOException {

        String url = "http://10.0.2.2:8080/Account/Create";

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", "your-email@email.com")
                .build();
        ClassRegist reg = new ClassRegist(0, ((TextView) findViewById(R.id.txtUsername)).getText().toString(), ((TextView) findViewById(R.id.txtPass)).getText().toString(), ((TextView) findViewById(R.id.txtName)).getText().toString().split(" ")[0], ((TextView) findViewById(R.id.txtName)).getText().toString().split(" ")[1], ((TextView) findViewById(R.id.txtEmail)).getText().toString(), ((TextView) findViewById(R.id.txtNasc)).getText().toString());

        String json = new Gson().toJson(reg);

        //String json = "[{\"Id\":0, \"Username\": \""+((TextView)findViewById(R.id.txtUsername)).getText()+"\", \"Nome\": \""+((TextView)findViewById(R.id.txtName)).getText().toString().split(" ")[0]+"\", \"Apelido\":\""+((TextView)findViewById(R.id.txtName)).getText().toString().split(" ")[1]+"\", \"Email\":\""+((TextView)findViewById(R.id.txtEmail)).getText()+"\", \"Password\":\""+((TextView)findViewById(R.id.txtPass)).getText()+"\", \"Nascimento\":\""+((TextView)findViewById(R.id.txtNasc)).getText()+"\"}]";
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
                    if(s.equals("Utilizador registado com sucesso")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Utilizador criado com sucesso", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });

                    }
                    else if(s.equals("Utilizador já existente!!"))
                        Toast.makeText(getApplicationContext(), "Utilizador já existente", Toast.LENGTH_LONG).show();

                    Log.d("", "");
                }
            }
        });

//Todo: Ligar a api via Post com o seguinte body:
//

    }

    private boolean CheckValues() {
        String pass = ((TextView) findViewById(R.id.txtPass)).getText().toString();
        String username = ((TextView) findViewById(R.id.txtUsername)).getText().toString();
        String email = ((TextView) findViewById(R.id.txtEmail)).getText().toString();
        String confemail = ((TextView) findViewById(R.id.txtConfEmail)).getText().toString();
        String confpass = ((TextView) findViewById(R.id.txtConfPass)).getText().toString();
        String dataNascimento = ((TextView) findViewById(R.id.txtNasc)).getText().toString();
        String NomeApe = ((TextView) findViewById(R.id.txtName)).getText().toString();

        boolean check = true;
        if (!email.equals(confemail)) {
            ((TextView) findViewById(R.id.txtConfEmail)).setError("O Email não é o mesmo");
            return false;
        }

        if (!confpass.equals(pass)) {
            ((TextView) findViewById(R.id.txtConfPass)).setError("A Palavra passe não é a mesma!!");
            return false;
        }


        if (!(Patterns.EMAIL_ADDRESS).matcher(email).matches()) {
            ((TextView) findViewById(R.id.txtEmail)).setError("O Email não é valido!!");
            return false;
        }

        if (!(Patterns.EMAIL_ADDRESS).matcher(confemail).matches()) {
            ((TextView) findViewById(R.id.txtConfEmail)).setError("O Email não é valido!!");
            return false;
        }

        if (!NomeApe.contains(" ")) {
            ((TextView) findViewById(R.id.txtName)).setError("O Nome e Apelido devem ter um espaço!!");
            return false;
        }

        if (username.equals("")) {
            ((TextView) findViewById(R.id.txtUsername)).setError("O username não pode estar vazio!!");
            return false;
        }

        if (NomeApe.equals("")) {
            ((TextView) findViewById(R.id.txtName)).setError("O Nome e Apelido não pode estar vazio!!");
            return false;
        }

        if (email.equals("")) {
            ((TextView) findViewById(R.id.txtEmail)).setError("O email não pode estar vazio!!");
            return false;
        }

        if (confemail.equals("")) {
            ((TextView) findViewById(R.id.txtConfEmail)).setError("A confirmação do email não pode estar vazia!!");
            return false;
        }

        if (pass.equals("")) {
            ((TextView) findViewById(R.id.txtPass)).setError("Tens de escrever uma palavra passe, não pode estar vazio!!");
            return false;
        }

        if (confpass.equals("")) {
            ((TextView) findViewById(R.id.txtConfPass)).setError("Tens de escrever a confirmação da palavra passe, não pode estar vazio!!");
            return false;
        }

        if (dataNascimento.equals("")) {
            ((TextView) findViewById(R.id.txtNasc)).setError("A data de Nascimento não pode estar vazia. Clica em mim!!");
            return false;
        }

        if (pass.length() < 8) {
            ((TextView) findViewById(R.id.txtPass)).setError("Parece que a sua palavra passe tem poucos caracteres!!");
            return false;
        }

        return true;
    }
}
