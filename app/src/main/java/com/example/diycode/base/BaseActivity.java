package com.example.diycode.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.diycode.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    protected void startActivity(Class<?> cls){
        startActivity(this,cls);
    }
    public static void startActivity(Context context,Class<?> cls){
        context.startActivity(new Intent(context,cls));
    }


    protected abstract void initView();

    public abstract int layoutId();

    private void initToolbar(){
        Toolbar toolbar=findViewById(R.id.tbl_title);
        if (toolbar!=null){
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
