package com.sam_chordas.android.stockhawk.data;

/**
 * Created by heleneshaikh on 27/07/16.
 */
public class Javascript {
    private String executionStopTime;
    private String instructionsUsed;
    private String executionTime;
    private String tableName;
    private String executionStartTime;

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

    public String getInstructionsUsed() {
        return instructionsUsed;
    }

    public void setInstructionsUsed(String instructionsUsed) {
        this.instructionsUsed = instructionsUsed;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "Javascript{" +
                "executionStartTime='" + executionStartTime + '\'' +
                ", executionStopTime='" + executionStopTime + '\'' +
                ", instructionsUsed='" + instructionsUsed + '\'' +
                ", executionTime='" + executionTime + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
