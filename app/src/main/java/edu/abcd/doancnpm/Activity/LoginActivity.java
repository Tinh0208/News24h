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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.abcd.doancnpm.Model.User;
import edu.abcd.doancnpm.R;
import edu.abcd.doancnpm.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    public static User userLogin = new User();
    ActivityLoginBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth auth;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,StartActivity.class));
            finish();
        });

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        binding.button.setOnClickListener(v -> {
            String email = binding.editTextTextPersonName.getText().toString();
            String password = binding.editTextTextPassword.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    binding.editTextTextPersonName.setError("Email is empty");
                }
                if (password.isEmpty()) {
                    binding.editTextTextPassword.setError("Password is empty");
                }
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    boolean userFound = false; // Biến cờ để xác định xem có tìm thấy user không
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                        User user = dataSnapshot.getValue(User.class);

                                        // Kiểm tra xem đối tượng user và các giá trị email, password có null hay không
                                        if (user != null && user.getEmail() != null && user.getPassword() != null) {
                                            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                                                userFound = true; // Đánh dấu là đã tìm thấy user hợp lệ
                                                userLogin = user;

                                                // Đăng nhập vào ứng dụng
                                                if (user.getRole() == 0) {
                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công vào TK", Toast.LENGTH_SHORT).show();
                                                }

                                                finish();  // Dừng activity hiện tại sau khi đăng nhập thành công
                                                return;
                                            }
                                        }
                                    }

                                    // Nếu không tìm thấy user hợp lệ
                                    if (!userFound) {
                                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Không tìm thấy dữ liệu người dùng!", Toast.LENGTH_SHORT).show();
                                }

                            }

                                @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });

            }
        });


        binding.textView6.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });


    }
}