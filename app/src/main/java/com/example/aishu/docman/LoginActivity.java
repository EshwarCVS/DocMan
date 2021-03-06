package com.example.aishu.docman;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmailLogin;
    private EditText txtPwd;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmailLogin= (EditText) findViewById(R.id.txtEmailLogin);
        txtPwd= (EditText) findViewById(R.id.txtPasswordLogin);
        firebaseAuth=FirebaseAuth.getInstance();


    }
    public void btnUserLogin_Click(View v)
    {

        (firebaseAuth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(),txtPwd.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                final ProgressBar progressBar = new ProgressBar(LoginActivity.this, null, android.R.attr.progressBarStyleSmall);


                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){

                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(LoginActivity.this,DashboardActivity.class);
                    i.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());
                    startActivity(i);

                }
                else
                {

                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}
