package com.system.residence.response;

public class Response<T> implements IResponse<T> {

    private T data;
    private boolean success;

    public Response() {
        this.success = true;
    }

    public Response(T data) {
        this();
        this.data = data;
    }

    public Response(T data, boolean success) {
        this(data);
        this.success = success;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean getSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

}
