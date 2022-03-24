package com.example.frontend.Model;

public class Credentials {

    private String type;
    private String value;
    private boolean temporary;

    public Credentials() {};
    public Credentials(String value) {
        this.temporary = false;
        this.type = "password";
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }
}
