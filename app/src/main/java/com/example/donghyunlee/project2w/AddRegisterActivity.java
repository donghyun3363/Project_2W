package com.example.donghyunlee.project2w;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DONGHYUNLEE on 2017-07-14.
 */

public class AddRegisterActivity extends AppCompatActivity {

    AddMap appmap = new AddMap();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addregister);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.topback)
    void topBackFun(){
        finish();
    }
    @OnClick(R.id.topexit)
    void topNextFun() {
        finish();
    }
    @OnClick(R.id.bottomback)
    void bottomBackfun(){
        finish();
    }
    @OnClick(R.id.bottomnext)
    void bottomNextfun()
    {
        openFragment(appmap);
    }

    private void openFragment(final AddMap fragment) {
        Log.e("openFragment 함 내", "들어옴");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.e("openFragment 함 내2", "들어옴");
        transaction.replace(R.id.register_container, fragment);
        Log.e("openFragment 함 내3", "들어옴");
        transaction.addToBackStack(null);
        transaction.commit();
        Log.e("openFragment 함 내4", "들어옴");
    }

}
