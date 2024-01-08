package pt.iade.andre.diogo.cartrackapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pt.iade.andre.diogo.cartrackapp.Models.ClassLogin;

public class LoginActivity extends AppCompatActivity {

    public static int IdUser=-1;
    TextView txtUserEm;
    TextView txtPass;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        Background_DoWork();
    }


    private void Background_DoWork() {

        txtPass = findViewById(R.id.txtPass);
        txtUserEm = findViewById(R.id.txtUserEmail);
        Button btn = findViewById(R.id.btnLogin);

        txtUserEm.setText("admin");
        txtPass.setText("admin");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtPass.getText().toString().isEmpty() && !txtUserEm.getText().toString().isEmpty()) {
                    // TODO : Checkar valores e se estão na database!
                    CheckDataBase(txtUserEm.getText().toString(), txtPass.getText().toString());

                } else
                    new AlertDialog.Builder(LoginActivity.this).setTitle("Campos em Falta!").setMessage("Os campos acima, não podem estar vazios!!").setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show();
            }
        });

        findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AjudaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    public static final MediaType aaa = MediaType.parse("application/json; charset=utf-8");

    private void CheckDataBase(String username, String password) {

        txtUserEm.requestFocus();
        String url = "http://10.0.2.2:8080/Account/Login";


        ClassLogin login = new ClassLogin(0, username, password);

        String json = new Gson().toJson(login);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(aaa, json))
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // TODO: Edit message.
                new AlertDialog.Builder(LoginActivity.this).setTitle("Campos em Falta!").setMessage("Os campos acima, não podem estar vazios!!").setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {

                    // TODO: response has id.
                    String value = response.body().string().toString();
                    if (value.contains("- fez login!!")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //intent.putExtra("Username", ((TextView)findViewById(R.id.txtUserEmail)).getText());
                        //intent.putExtra("userid", value.split("-")[1].split("-")[0]);
                        IdUser = Integer.parseInt(value.split("-")[1].split("-")[0]);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        txtPass.setText("");
                        txtUserEm.setText("");
                        startActivityForResult(intent, 1);
                    } else
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "O utilizador está com os dados invalidos!", Toast.LENGTH_LONG).show();
                            }
                        });


                }
            }
        });

    }

}
