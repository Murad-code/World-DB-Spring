package org.example.hibernatehaunters.models.exceptions.countrylanguage;

public class CountryLanguageErrorResponse {
    private String url;
     private String message;
     private int statusCode;

    public CountryLanguageErrorResponse(String url, String message, int statusCode) {
        this.url = url;
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getUrl() {
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
