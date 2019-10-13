package com.udacoding.pegawaiapp.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.udacoding.pegawaiapp.R;
import com.udacoding.pegawaiapp.login.LoginActivity;
import com.udacoding.pegawaiapp.network.NetworkConfig;
import com.udacoding.pegawaiapp.register.data.ResultRegister;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtPasswordConfir)
    EditText edtPasswordConfir;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRegister)
    public void onViewClicked() {


        //get inputan user
        String name =edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String confirm = edtPasswordConfir.getText().toString();


        if(name.isEmpty()){
            edtName.setError("name tidak kosong");
        }
        else if (email.isEmpty()){

            edtEmail.setError("email tidak boleh kosong");
        }
        else if (password.isEmpty()){
            edtPassword.setError("password tidk boleh kosong");
        }
        else if (confirm.isEmpty()){
            edtPasswordConfir.setError("confirm tidak boleh kosong");
        }
        else if ( password.length() < 6){

            edtPassword.setError("harus lebih dari 6 karakter");
        }
        else if ( !password.equals(confirm)){

            edtPasswordConfir.setError("not match");
        }
        else{

          new  NetworkConfig().getService().register(name,email,password)
                  .enqueue(new Callback<ResultRegister>() {
                      @Override
                      public void onResponse(Call<ResultRegister> call, Response<ResultRegister> response) {


                          if(response.isSuccessful() ){

                              Boolean status = response.body().isStatus();

                              if(status){

                                  startActivity(new Intent(RegisterActivity.this, LoginActivity.class));


                                  Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                              }

                          }
                      }

                      @Override
                      public void onFailure(Call<ResultRegister> call, Throwable t) {

                      }
                  });


        }


    }
}
