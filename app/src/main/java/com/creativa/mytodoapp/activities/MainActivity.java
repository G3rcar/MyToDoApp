package com.creativa.mytodoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.creativa.mytodoapp.R;
import com.creativa.mytodoapp.api.Response;
import com.creativa.mytodoapp.items.ToDoItem;
import com.creativa.mytodoapp.utils.ApiCaller;
import com.creativa.mytodoapp.utils.OnApiCallFinish;
import com.creativa.mytodoapp.utils.ToDoListAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ToDoListAdapter.OnItemClickListener, OnApiCallFinish {

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

        ApiCaller task = new ApiCaller();
        task.setUrl("https://visibleoutsource.com/admin/api/idiomas/lista");
        task.setRequestId(1);
        task.setOnApiCallFinish(this);
        task.execute();

        ApiCaller delete = new ApiCaller();
        delete.setUrl("http;//192.168.100.36:3000/api/task/4");
        delete.setRequestId(3);
        delete.setDelete(true);
        delete.setOnApiCallFinish(this);
        delete.execute();
    }


    @Override
    public void onItemClick(ToDoItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();


        // Agregar nuevo sin limpiar el stack
        FormToDoActivity.open(this);
        FormToDoActivity.open(this,false);

        // Agregar nuevo limpiando el stack
        FormToDoActivity.open(this,true);

        // Editar un registro pasandole el ID
        FormToDoActivity.edit(this,11L);

    }





    @Override
    public void onSuccess(Integer requestId, String content) {

        switch (requestId){
            case 1:
                Response response1 = new Gson().fromJson(content,Response.class);
                Toast.makeText(this, response1.getResults().size()+" 1", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Response response2 = new Gson().fromJson(content,Response.class);
                Toast.makeText(this, response2.getResults().size()+" 2", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    @Override
    public void onError(Integer requestId, Integer code) {
        Toast.makeText(this, code+"", Toast.LENGTH_SHORT).show();
    }
}
