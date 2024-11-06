package edu.abcd.doancnpm.Fragment.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.abcd.doancnpm.Activity.GopYActivity2;
import edu.abcd.doancnpm.Activity.LoginActivity;
import edu.abcd.doancnpm.Activity.LuutinActivity;
import edu.abcd.doancnpm.Activity.TempActivity;
import edu.abcd.doancnpm.R;

public class CanhanAdminFragment extends Fragment {

    Button btn_luutin,btn_logout,btn_calender,btn_gopy;
    TextView tendangnhap;
    FirebaseUser user;
    public CanhanAdminFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_canhan_admin, container, false);
        btn_luutin = view.findViewById(R.id.btn_saveNews);
        tendangnhap = view.findViewById(R.id.txt_DangNhap);
        btn_calender = view.findViewById(R.id.btn_calender);
        user = FirebaseAuth.getInstance().getCurrentUser();
        tendangnhap.setText(user.getEmail());
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_gopy = view.findViewById(R.id.btn_gopy);

        btn_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TempActivity.class);
                intent.putExtra("calendar","calendar");
                startActivity(intent);
            }
        });

        btn_luutin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LuutinActivity.class));
                getActivity().finish();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                Toast.makeText(getActivity(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        btn_gopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GopYActivity2.class));
            }
        });
        return view;
    }
}