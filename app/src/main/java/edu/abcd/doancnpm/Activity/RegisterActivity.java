package edu.abcd.doancnpm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.abcd.doancnpm.Model.User;
import edu.abcd.doancnpm.R;
import edu.abcd.doancnpm.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth auth;
    ImageButton btnBack1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btnBack1 = findViewById(R.id.btnBack1);
        btnBack1.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this,StartActivity.class));
            finish();
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");
        auth = FirebaseAuth.getInstance();

        binding.btnRegister.setOnClickListener(v->{
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            String passwordConfirm = binding.etConfirmpassword.getText().toString();
            String name = binding.etFullname.getText().toString();

            if(email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || name.isEmpty()){
                if(email.isEmpty()){
                    binding.etEmail.setError("Email is empty");
                }
                if(password.isEmpty()){
                    binding.etPassword.setError("Password is empty");
                }
                if(passwordConfirm.isEmpty()){
                    binding.etConfirmpassword.setError("Confirm password is empty");
                }
                if(name.isEmpty()){
                    binding.etFullname.setError("Full name is empty");
                }
            }else{
                if(!password.equals(passwordConfirm)){
                    binding.etConfirmpassword.setError("Confirm password is not match");
                }else{
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String id = task.getResult().getUser().getUid();
                                //set role = 0 mặc định là người dùng
                                User user = new User(email,name,password,0);
                                database.getReference().child("Users").child(id).setValue(user);
                                Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

        });

        binding.tvLogin.setOnClickListener(v->{
            finish();
        });
    }
}