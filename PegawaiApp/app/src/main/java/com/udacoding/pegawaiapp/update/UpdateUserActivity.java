package com.udacoding.pegawaiapp.update;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.udacoding.pegawaiapp.R;
import com.udacoding.pegawaiapp.home.MainActivity;
import com.udacoding.pegawaiapp.home.data.UserItem;
import com.udacoding.pegawaiapp.network.NetworkConfig;
import com.udacoding.pegawaiapp.update.data.ResultUpdate;
import com.udacoding.pegawaiapp.utils.Constans;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends AppCompatActivity {

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    private UserItem userItem ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        ButterKnife.bind(this);


        userItem = (UserItem) getIntent().getSerializableExtra(Constans.USER);

        edtEmail.setText(userItem.getStaffEmail());
        edtName.setText(userItem.getStaffNama());


    }

    @OnClick(R.id.btnRegister)
    public void onViewClicked() {


        new NetworkConfig().getService().updateUser(userItem.getStaffId(),
              edtName.getText().toString(),edtEmail.getText().toString()  )
                .enqueue(new Callback<ResultUpdate>() {
                    @Override
                    public void onResponse(Call<ResultUpdate> call, Response<ResultUpdate> response) {


                        Boolean status = response.body().isStatus();
                        if(status){

                            startActivity(new Intent(UpdateUserActivity.this, MainActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultUpdate> call, Throwable t) {

                    }
                });

    }


}
