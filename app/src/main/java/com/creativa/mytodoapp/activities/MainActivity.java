package com.creativa.mytodoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.creativa.mytodoapp.R;
import com.creativa.mytodoapp.items.ToDoItem;
import com.creativa.mytodoapp.utils.ToDoListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ToDoListAdapter.OnItemClickListener {

    @BindView(R.id.myTodoList) RecyclerView myTodoList;

    private ToDoListAdapter mAdapter;
    private List<ToDoItem> toDoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        myTodoList.setHasFixedSize(true);

        myTodoList.setLayoutManager(new LinearLayoutManager(this));

        toDoItems = new ArrayList<>();
        toDoItems.add(new ToDoItem(1,"Tarea 1","Descripcion 1"));
        toDoItems.add(new ToDoItem(2,"Tarea 2","Descripcion 2"));
        toDoItems.add(new ToDoItem(3,"Tarea 3","Descripcion 3"));

        mAdapter = new ToDoListAdapter(toDoItems);
        myTodoList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(ToDoItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
