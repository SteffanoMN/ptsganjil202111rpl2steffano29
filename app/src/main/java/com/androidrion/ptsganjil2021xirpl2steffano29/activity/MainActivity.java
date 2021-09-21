package com.androidrion.ptsganjil2021xirpl2steffano29.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidrion.ptsganjil2021xirpl2steffano29.fragment.Dashboard;
import com.androidrion.ptsganjil2021xirpl2steffano29.R;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.LoginButton);

        //untuk mengecek data yang dimasukkan saat button login ditekan
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                //apabila pengguna memasukkan string yang benar, pengguna dapat masuk
                if (str_email.equalsIgnoreCase("admin@gmail.com")
                        && str_password.equalsIgnoreCase("admin123")) {
                    Toast.makeText(getApplicationContext(), "selamat datang kembali", Toast.LENGTH_SHORT).show();
                    Intent Home = new Intent(getApplicationContext(), Dashboard.class);
                    Home.putExtra("email", str_email);
                    startActivity(Home);
                } //jika semua kotak yang harus diisi kosong, pengguna tidak dapat masuk
                else if (str_email.isEmpty() && str_password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Masukkan Email dan Password!", Toast.LENGTH_SHORT).show();
                } //jika string yang dimasukkan tidak sesuai, pengguna tidak dapat masuk
                else {
                    Toast.makeText(getApplicationContext(), "Email atau Password Anda salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}