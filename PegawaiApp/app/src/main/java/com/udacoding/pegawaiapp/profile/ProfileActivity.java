package com.udacoding.pegawaiapp.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.udacoding.pegawaiapp.R;
import com.udacoding.pegawaiapp.login.LoginActivity;
import com.udacoding.pegawaiapp.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.profileNama)
    TextView profileNama;
    @BindView(R.id.profileEmail)
    TextView profileEmail;
    @BindView(R.id.btnlogut)
    Button btnlogut;


    private SessionManager sessionManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);
        profileEmail.setText(sessionManager.getEmail());
        profileNama.setText(sessionManager.getNama());
    }

    @OnClick(R.id.btnlogut)
    public void onViewClicked() {

        sessionManager.logout();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
