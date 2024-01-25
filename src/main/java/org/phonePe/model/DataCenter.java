package org.phonePe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DataCenter {
    private String location;
    private Map<String, Resource> resourcesById = new HashMap<>();
    private BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();

    public DataCenter(String location) {
        this.location = location;
    }

    public void addResource(Resource resource) {
        resourcesById.put(resource.getResourceId(), resource);
    }

    public Resource getResourceById(String resourceId) {
        return resourcesById.get(resourceId);
    }

    //treemap
    //0(log(n))
    public List<Resource> getResourcesByTypeAndConfig(String type, int cpuConfiguration) {
        List<Resource> matchingResources = new ArrayList<>();
        for (Resource resource : resourcesById.values()) {
            if (resource.getType().equals(type) && resource.getCpuConfiguration() >= cpuConfiguration) {
                matchingResources.add(resource);
            }
        }
        return matchingResources;
    }

    public void submitTask(Task task) {
        taskQueue.offer(task);
    }

    public Task pollTask() {
        return taskQueue.poll();
    }

    public boolean pollTaskExist() {
        return !taskQueue.isEmpty();
    }

    public int getQueueSize() {
        return taskQueue.size();
    }

    public String getLocation() {
        return location;
    }


    public List<Resource> getAllocatedResourcesByType(String type) {
        List<Resource> allocatedResources = new ArrayList<>();
        for (Resource resource : resourcesById.values()) {
            if (resource.getType().equals(type)) {
                allocatedResources.add(resource);
            }
        }
        return allocatedResources;
    }
}
