package org.phonePe.model;

import java.util.Date;

public class Task {
    private String taskId;
    private String dataCenterLocation;
    private String resourceType;
    private int minCpuConfiguration;
    private Date startTime;
    private Date endTime;

    public Task(String taskId, String dataCenterLocation, String resourceType, int minCpuConfiguration) {
        this.taskId = taskId;
        this.dataCenterLocation = dataCenterLocation;
        this.resourceType = resourceType;
        this.minCpuConfiguration = minCpuConfiguration;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDataCenterLocation() {
        return dataCenterLocation;
    }

    public void setDataCenterLocation(String dataCenterLocation) {
        this.dataCenterLocation = dataCenterLocation;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getMinCpuConfiguration() {
        return minCpuConfiguration;
    }

    public void setMinCpuConfiguration(int minCpuConfiguration) {
        this.minCpuConfiguration = minCpuConfiguration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", dataCenterLocation='" + dataCenterLocation + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", minCpuConfiguration=" + minCpuConfiguration +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
