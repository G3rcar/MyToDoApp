package com.creativa.mytodoapp.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.creativa.mytodoapp.R;
import com.creativa.mytodoapp.fragments.DetailFragment;
import com.creativa.mytodoapp.fragments.ToDoListFragment;

public class MyFragmentActivity extends AppCompatActivity implements
        ToDoListFragment.OnFragmentInteractionListener, DetailFragment.OnFragmentDetailInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentDetailInteraction(Uri uri) {

    }
}
