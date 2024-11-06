package edu.abcd.doancnpm.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.abcd.doancnpm.Activity.LuutinActivity;
import edu.abcd.doancnpm.Model.News;
import edu.abcd.doancnpm.Model.User;
import edu.abcd.doancnpm.R;

public class AddNewsFragment extends Fragment {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static User userLogin = new User();
    private DatabaseReference reference = database.getReference("Approved");
    EditText title,content,category;
    Button addnews,huy;
    EditText anh;
    private String decodeImage = "";

    public AddNewsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_news, container, false);
        title = view.findViewById(R.id.add_title);
        content = view.findViewById(R.id.add_content);
        category = view.findViewById(R.id.add_category);
        anh = view.findViewById(R.id.add_anh);
        addnews = view.findViewById(R.id.addNews);
        huy = view.findViewById(R.id.huy);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(), TrangchinhFragment.class));
                getActivity().finish();

            }
        });
        addnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newsTitle = title.getText().toString();
                String newsContent = content.getText().toString();
                String newsCategory = category.getText().toString();
                String newsAnh = anh.getText().toString();
                boolean isApproved = false;
                String id = reference.push().getKey();

                if (newsTitle.isEmpty() || newsContent.isEmpty() || newsCategory.isEmpty() || newsAnh.isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }else {
                    News news = new News(newsTitle,newsContent,newsAnh, newsCategory,id,isApproved);
                    reference.child(id).setValue(news).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
                            title.setText("");
                            content.setText("");
                            category.setText("");
                            decodeImage = "";
                            anh.setText("");
                        }
                    });
                }
            }
        });

        return view;
    }
}