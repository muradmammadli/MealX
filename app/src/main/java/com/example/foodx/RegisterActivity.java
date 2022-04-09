package com.example.foodx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodx.Database.AuthenticationDao;
import com.example.foodx.Database.UserDatabase;
import com.example.foodx.Models.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEdt;
    private EditText mailEdt;
    private EditText passEdt;
    private EditText passRptEdt;
    private Button registerBtn;
    private AuthenticationDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        dao = Room.databaseBuilder(this, UserDatabase.class,"User").allowMainThreadQueries().build().getUserDao();
        registerBtn.setOnClickListener(view -> {
            String userName = usernameEdt.getText().toString().trim();
            String email = mailEdt.getText().toString().trim();
            String password = passEdt.getText().toString().trim();
            String passwordRpt = passRptEdt.getText().toString().trim();

            if (password.equals(passwordRpt)){
                User user = new User(userName,password,email);
                dao.insert(user);
                Intent moveToLoginIntent = new Intent(this,HomeActivity.class);
                startActivity(moveToLoginIntent);
                finish();
            }else {
                Toast.makeText(this, "Şifrələr uyuşmur", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(){
        usernameEdt = findViewById(R.id.usernameRegisterEdit);
        mailEdt = findViewById(R.id.mailRegisterEdit);
        passEdt = findViewById(R.id.passwordRegisterEdit);
        passRptEdt = findViewById(R.id.rptPasswordEdt);
        registerBtn = findViewById(R.id.registerButton);
    }
}