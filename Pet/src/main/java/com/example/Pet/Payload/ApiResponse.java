package com.example.Pet.Payload;

public class ApiResponse {
    private String mess;
    private String success;

    public ApiResponse(String userDeleteSuccessfully, boolean b) {

    }
    public ApiResponse(String mess, String success) {
        this.mess = mess;
        this.success = success;
    }
    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
