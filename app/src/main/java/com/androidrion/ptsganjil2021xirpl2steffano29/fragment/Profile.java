package com.androidrion.ptsganjil2021xirpl2steffano29.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidrion.ptsganjil2021xirpl2steffano29.R;
import com.androidrion.ptsganjil2021xirpl2steffano29.activity.MainActivity;

public class Profile extends Fragment {

    private Toolbar toolbar;
    private Bundle bundle;
    private TextView email_account;
    private Button btn_logout;

    private String txt_email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        email_account = view.findViewById(R.id.set_email);
        btn_logout = view.findViewById(R.id.btn_logout);

        bundle = getActivity().getIntent().getExtras();
        if (bundle != null){
            txt_email = bundle.getString("email");
        }
        email_account.setText(txt_email);


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}