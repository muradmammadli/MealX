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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeActivity extends AppCompatActivity {
    private Button loginBtn;
    private Button registerBtn;
    private EditText editTextMail;
    private EditText editTextPassword;
    AuthenticationDao authenticationDao;
    UserDatabase userDatabase;
    Logger logger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        logger = LoggerFactory.getLogger(HomeActivity.class);
        userDatabase = Room.databaseBuilder(this,UserDatabase.class,"User")
                .allowMainThreadQueries()
                .build();
        authenticationDao = userDatabase.getUserDao();

        loginBtn.setOnClickListener(view -> {
            String mail = editTextMail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            User user = authenticationDao.getUser(mail,password);
            logger.info("User logined");
            if (user != null){
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("User",user);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Yanlış istifadəçi məlumatları", Toast.LENGTH_SHORT).show();
            }
        });

        registerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
            logger.info("User logined");
        });

    }

    private void initViews(){
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        editTextMail = findViewById(R.id.editTextMail);
        editTextPassword = findViewById(R.id.editPassword);
    }
}