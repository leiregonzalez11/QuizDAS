package com.example.quizdas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void acceder(View view){
        Intent intent = new Intent(this, bienvenida.class);
        startActivity(intent);
        finish();
    }

    //TODO: Modificar de registro a inicio de sesion
    public boolean validarInicioSesion() {
        boolean valido = true;
        //Validamos el email
        EditText textEmail = findViewById(R.id.textEmailRegistro);
        String email = textEmail.getText().toString();
        Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (email.equals("")) { //Si el email está vacío
            Toast.makeText(getApplicationContext(), getString(R.string.emailVacio), Toast.LENGTH_SHORT).show();
            textEmail.setText("");
            valido = false;
        } else if (!patternEmail.matcher(email).matches()) { //Si el email no es correcto
            Toast.makeText(getApplicationContext(), getString(R.string.emailNoValido), Toast.LENGTH_SHORT).show();
            textEmail.setText("");
            valido = false;
        }

        //Validamos la contraseña
        EditText textPasswd1 = findViewById(R.id.textPasswdRegistro);
        String passwd = textPasswd1.getText().toString();
        EditText textPasswd2 = findViewById(R.id.textPasswdRegistro2);
        String passwdConf = textPasswd2.getText().toString();
        if (!passwdConf.equals(passwd)) { //Si son distintas
            Toast.makeText(getApplicationContext(), getString(R.string.passwdNoCoincide), Toast.LENGTH_SHORT).show();
            textPasswd1.setText("");
            textPasswd2.setText("");
            valido = false;
        } else {
            if (passwd.equals("")) { //Si el EditText de Password está vacío
                Toast.makeText(getApplicationContext(), getString(R.string.passwdVacia), Toast.LENGTH_SHORT).show();
                textPasswd1.setText("");
                valido = false;
            } else if (passwd.length() > 16) { //Si el EditText de Password supera la longitud máxima permitida
                Toast.makeText(getApplicationContext(), getString(R.string.passwdLarga), Toast.LENGTH_SHORT).show();
                textPasswd1.setText("");
                valido = false;
            }
        }
        return valido;
    }
}