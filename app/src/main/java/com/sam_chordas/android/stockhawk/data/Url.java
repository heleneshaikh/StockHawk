package com.sam_chordas.android.stockhawk.data;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public class Url {
    private String content;
    private String executionStopTime;
    private String executionTime;
    private String executionStarTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExecutionStarTime() {
        return executionStarTime;
    }

    public void setExecutionStarTime(String executionStarTime) {
        this.executionStarTime = executionStarTime;
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

    @Override
    public String toString() {
        return "Url{" +
                "content='" + content + '\'' +
                ", executionStopTime='" + executionStopTime + '\'' +
                ", executionTime='" + executionTime + '\'' +
                ", executionStarTime='" + executionStarTime + '\'' +
                '}';
    }
}
