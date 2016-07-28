package com.sam_chordas.android.stockhawk.data;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public class SubQuery {
    private String content;
    private String executioStopTime;
    private String params;
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

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getExecutioStopTime() {
        return executioStopTime;
    }

    public void setExecutioStopTime(String executioStopTime) {
        this.executioStopTime = executioStopTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "SubQuery{" +
                "content='" + content + '\'' +
                ", executioStopTime='" + executioStopTime + '\'' +
                ", params='" + params + '\'' +
                ", executionTime='" + executionTime + '\'' +
                ", executionStartTime='" + executionStartTime + '\'' +
                '}';
    }
}
