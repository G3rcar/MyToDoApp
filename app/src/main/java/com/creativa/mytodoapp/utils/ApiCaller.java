package com.creativa.mytodoapp.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCaller extends AsyncTask<Void,Integer,String> {

    private static final String TAG = "ApiCaller";


    private boolean isPost = false;
    private boolean isDelete = false;

    private String url = "";
    private int statusCode = 0;
    private OnApiCallFinish listener;
    private int requestId = 0;

    private HashMap<String,String> postData;

    public ApiCaller(){
        this.postData = new HashMap<>();
    }




    public void setUrl(String url) {
        this.url = url;
    }

    public void setOnApiCallFinish(OnApiCallFinish listener) {
        this.listener = listener;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setPost(boolean post) {
        this.isPost = post;
    }

    public void setDelete(boolean delete) {
        this.isDelete = delete;
    }

    public void setPostData(HashMap<String, String> postData) {
        this.postData = postData;
    }

    @Override
    protected String doInBackground(Void... voids) {

        OkHttpClient client = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder();

        // Evaluar condiciones
        if(!this.url.equals("")){
            requestBuilder.url(this.url);
        }

        // peticion en POST
        if(this.isPost && !this.postData.isEmpty()){
            FormBody.Builder formBodyBuilder = new FormBody.Builder();

            for (Object o : this.postData.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                formBodyBuilder.add(pair.getKey().toString(), pair.getValue().toString());
            }
            FormBody formBody = formBodyBuilder.build();
            requestBuilder.post(formBody);
        }

        // peticion en DELETE
        if(this.isDelete){

            if(this.postData.isEmpty()){
                requestBuilder.delete();
            }else{
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                for (Object o : this.postData.entrySet()) {
                    Map.Entry pair = (Map.Entry) o;
                    formBodyBuilder.add(pair.getKey().toString(), pair.getValue().toString());
                }
                FormBody formBody = formBodyBuilder.build();
                requestBuilder.delete(formBody);
            }

        }




        // Ejecución la llamada al server
        Request request = requestBuilder.build();

        // Procesamos la respuesta
        try {
            Response response = client.newCall(request).execute();

            this.statusCode = response.code();
            if(this.statusCode >= 200 && this.statusCode < 300){
                if (response.body() != null) {
                    return response.body().string();
                }else{
                    return "";
                }
            }else{
                return "";
            }
        } catch (IOException e) {
            Log.e(TAG,e.getMessage());
        }

        return "";
    }

    @Override
    protected void onPostExecute(String content) {
        super.onPostExecute(content);
        if (this.listener == null) {
            return;
        }


        if (this.statusCode >= 200 && this.statusCode < 300) {
            this.listener.onSuccess(this.requestId, content);
        }else{
            this.listener.onError(this.requestId, this.statusCode);
        }
    }
}
