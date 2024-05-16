package org.example.hibernatehaunters.models.exceptions.cities;


public class CityErrorResponse {
    private String message;
    private String url;
    private int statusCode;

    public CityErrorResponse(String message, int statusCode, String url){
        this.message = message;
        this.statusCode = statusCode;
        this.url = url;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
