package com.creativa.mytodoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.creativa.mytodoapp.R;

public class FormToDoActivity extends AppCompatActivity {

    private static final String PARAM_TASK_ID = "TASK_ID";
    private static final String PARAM_ACTION_TYPE = "ACTION_TYPE";
    private static final String PARAM_ACTION_TYPE_ADD = "ADD";
    private static final String PARAM_ACTION_TYPE_EDIT = "EDIT";


    /**
     * Abrir activity limpiando o no el stack
     * @param activity el activity desde donde se quiere abrir
     * @param clearStack si se limpiara el stack o no
     */
    public static void open(AppCompatActivity activity, boolean clearStack){
        Intent intent = new Intent(activity,FormToDoActivity.class);
        intent.putExtra(PARAM_ACTION_TYPE,PARAM_ACTION_TYPE_ADD);
        activity.startActivity(intent);
    }


    /**
     * Abrir el activity sin limpiar el stack
     * @param activity activity desde donde se quiere abrir
     */
    public static void open(AppCompatActivity activity){
        open(activity,false);
    }


    /**
     * Abrir activity para editar un registro
     * @param activity activity desde donde se quiere abrir
     * @param id Id del registro a editar
     */
    public static void edit(AppCompatActivity activity, Long id){
        Intent intent = new Intent(activity,FormToDoActivity.class);
        intent.putExtra(PARAM_ACTION_TYPE,PARAM_ACTION_TYPE_EDIT);
        intent.putExtra(PARAM_TASK_ID,id);
        activity.startActivity(intent);
    }





    String action = PARAM_ACTION_TYPE_ADD;
    Long taskId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_to_do);

        action = getIntent().getStringExtra(PARAM_ACTION_TYPE);

        if(action.equals(PARAM_ACTION_TYPE_EDIT)){
            taskId = getIntent().getLongExtra(PARAM_TASK_ID,0L);
            // Cargar la información de la base de datos
        }



    }
}
