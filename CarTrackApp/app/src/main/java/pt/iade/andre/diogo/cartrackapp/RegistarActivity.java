package pt.iade.andre.diogo.cartrackapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.internal.FallbackServiceBroker;

public class RegistarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        background_doWork(); // função para incrementar todos os valores aqui!!
    }

    private void background_doWork(){

        findViewById(R.id.btnRegist).setOnClickListener(this::onClick);
        ((EditText)findViewById(R.id.txtNasc)).setFocusable(false);
        ((EditText)findViewById(R.id.txtNasc)).setEnabled(false);
    }

    private void onClick(View v){

        String pass = ((TextView)findViewById(R.id.txtPass)).getText().toString();
        String username = ((TextView)findViewById(R.id.txtUsername)).getText().toString();
        String email = ((TextView)findViewById(R.id.txtEmail)).getText().toString();
        String confemail = ((TextView)findViewById(R.id.txtConfEmail)).getText().toString();
        String confpass = ((TextView)findViewById(R.id.txtConfPass)).getText().toString();
        String dataNascimento = ((TextView)findViewById(R.id.txtNasc)).getText().toString();
        String NomeApe = ((TextView)findViewById(R.id.txtName)).getText().toString();

        if(email!=confemail)
            ((TextView)findViewById(R.id.txtConfEmail)).setError("Email are not the same");
        if(confpass!=pass)
            ((TextView)findViewById(R.id.txtConfPass)).setError("Email are not the same");

        if(email ==confemail && confpass==pass)
            CreateAccount();
    }

    private void CreateAccount() {
        //Ligar a api
    }
}
