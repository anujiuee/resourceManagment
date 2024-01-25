package org.phonePe.model;

public class Resource {

    private String resourceId;
    private String type;
    private int cpuConfiguration;
    private double pricing;

    public Resource(String resourceId, String type, int cpuConfiguration, double pricing) {
        this.resourceId = resourceId;
        this.type = type;
        this.cpuConfiguration = cpuConfiguration;
        this.pricing = pricing;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCpuConfiguration() {
        return cpuConfiguration;
    }

    public void setCpuConfiguration(int cpuConfiguration) {
        this.cpuConfiguration = cpuConfiguration;
    }

    public double getPricing() {
        return pricing;
    }

    public void setPricing(double pricing) {
        this.pricing = pricing;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId='" + resourceId + '\'' +
                ", type='" + type + '\'' +
                ", cpuConfiguration=" + cpuConfiguration +
                ", pricing=" + pricing +
                '}';
    }
}
