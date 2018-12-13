package com.creativa.mytodoapp.utils;

public interface OnApiCallFinish {
    void onSuccess(Integer id, String content);
    void onError(Integer id, Integer code);
}
