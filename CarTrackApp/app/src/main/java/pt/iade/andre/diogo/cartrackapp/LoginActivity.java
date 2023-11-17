package pt.iade.andre.diogo.cartrackapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView txtUserEm;
    TextView txtPass;
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        Background_DoWork();
    }

    private void Background_DoWork(){

        txtPass = findViewById(R.id.txtPass);
        txtUserEm = findViewById(R.id.txtUserEmail);
        Button btn = findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!txtPass.getText().toString().isEmpty() && !txtUserEm.getText().toString().isEmpty()){
                // TODO : Checkar valores e se estão na database!
                    if(txtPass.getText().toString().equals("admin") && txtUserEm.getText().toString().equals("admin")){
                        Toast.makeText(getApplicationContext(), "Clicado o botão", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        new AlertDialog.Builder(LoginActivity.this).setTitle("Campos em Falta!").setMessage("Os campos acima, não podem estar vazios!!").setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show();

                }
                else
                    new AlertDialog.Builder(LoginActivity.this).setTitle("Campos em Falta!").setMessage("Os campos acima, não podem estar vazios!!").setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show();
            }
        });
    }
}
