package com.midterm.letruongsanh;

import java.io.Serializable;

public class Data implements Serializable {
    private String input;
    private String action;
    private String result;

    public Data(String input, String action, String result) {
        this.input = input;
        this.action = action;
        this.result = result;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
