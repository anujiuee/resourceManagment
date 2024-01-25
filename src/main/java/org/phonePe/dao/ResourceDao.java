package org.phonePe.dao;

import org.phonePe.model.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceDao {

    private Map<String, Resource> resourcesById = new HashMap<>();

    public void addResource(Resource resource) {
        resourcesById.put(resource.getResourceId(), resource);
    }

    public Resource getResourceById(String resourceId) {
        return resourcesById.get(resourceId);
    }

    public List<Resource> getResourcesByTypeAndConfig(String type, int cpuConfiguration) {
        List<Resource> matchingResources = new ArrayList<>();
        for (Resource resource : resourcesById.values()) {
            if (resource.getType().equals(type) && resource.getCpuConfiguration() >= cpuConfiguration) {
                matchingResources.add(resource);
            }
        }
        return matchingResources;
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
