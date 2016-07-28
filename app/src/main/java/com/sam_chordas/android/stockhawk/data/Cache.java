package com.sam_chordas.android.stockhawk.data;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public class Cache {
    private String content;
    private String executionStopTime;
    private String method;
    private String type;
    private String executionTime;
    private String executionStartTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExecutionStartTime() {
        return executionStartTime;
    }

    public void setExecutionStartTime(String executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    public String getExecutionStopTime() {
        return executionStopTime;
    }

    public void setExecutionStopTime(String executionStopTime) {
        this.executionStopTime = executionStopTime;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cache{" +
                "content='" + content + '\'' +
                ", executionStopTime='" + executionStopTime + '\'' +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                ", executionTime='" + executionTime + '\'' +
                ", executionStartTime='" + executionStartTime + '\'' +
                '}';
    }
}
