package com.udacoding.pegawaiapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.udacoding.pegawaiapp.R;
import com.udacoding.pegawaiapp.home.MainActivity;
import com.udacoding.pegawaiapp.login.data.ResultLogin;
import com.udacoding.pegawaiapp.login.data.User;
import com.udacoding.pegawaiapp.network.NetworkConfig;
import com.udacoding.pegawaiapp.register.RegisterActivity;
import com.udacoding.pegawaiapp.utils.SessionManager;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.linkRegister)
    TextView linkRegister;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnLogin, R.id.linkRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:

                actionLogin();
                break;
            case R.id.linkRegister:

                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void actionLogin() {

        avi.setVisibility(View.VISIBLE);

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (email.isEmpty()) {

            edtEmail.setError("tidak boleh kosong");
        } else if (password.isEmpty()) {

            edtPassword.setError("tidak boleh kosong");
        } else {

            new NetworkConfig().getService().login(email, password)
                    .enqueue(new Callback<ResultLogin>() {
                        @Override
                        public void onResponse(Call<ResultLogin> call, Response<ResultLogin> response) {

                            Boolean status = response.body().isStatus();

                            if (status) {
                                avi.setVisibility(View.GONE);

                                User user = response.body().getUser();

                                SessionManager sessionManager = new SessionManager(LoginActivity.this);

                                sessionManager.createLoginSession("1");
                                sessionManager.setEmail(user.getStaffEmail());
                                sessionManager.setNama(user.getStaffNama());


                                startActivity(new Intent(LoginActivity.this, MainActivity.class));


                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultLogin> call, Throwable t) {

                        }
                    });
        }
    }
}
