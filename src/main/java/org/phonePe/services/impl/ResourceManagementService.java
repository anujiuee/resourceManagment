package org.phonePe.services.impl;

import org.phonePe.dao.DataCenterDao;
import org.phonePe.dao.ResourceDao;
import org.phonePe.dao.TaskDao;
import org.phonePe.exception.ResourceManagementException;
import org.phonePe.model.DataCenter;
import org.phonePe.model.Resource;
import org.phonePe.model.Task;

import java.util.Date;
import java.util.List;

public class ResourceManagementService {
    private ResourceDao resourceDAO;
    private TaskDao taskDAO;
    private DataCenterDao dataCenterDAO;

    public ResourceManagementService(ResourceDao resourceDAO, TaskDao taskDAO, DataCenterDao dataCenterDAO) {
        this.resourceDAO = resourceDAO;
        this.taskDAO = taskDAO;
        this.dataCenterDAO = dataCenterDAO;
    }

    public void addResource(String location, Resource resource) throws ResourceManagementException {
        DataCenter dataCenter = dataCenterDAO.getDataCenter(location);
        if (dataCenter != null) {
            dataCenter.addResource(resource);
        } else {
            throw new ResourceManagementException("Data center not found for location: " + location);
        }
    }

    public List<Resource> getAvailableResources(String location, String type, int cpuConfiguration) throws ResourceManagementException {
        DataCenter dataCenter = dataCenterDAO.getDataCenter(location);
        if (dataCenter != null) {
            return dataCenter.getResourcesByTypeAndConfig(type, cpuConfiguration);
        } else {
            throw new ResourceManagementException("Data center not found for location: " + location);
        }
    }

    public void submitTask(String location, Task task) throws ResourceManagementException {
        DataCenter dataCenter = dataCenterDAO.getDataCenter(location);
        taskDAO.addTask(task);
        if (dataCenter != null) {
            dataCenter.submitTask(task);
        } else {
            throw new ResourceManagementException("Data center not found for location: " + location);
        }
    }

    public void allocateResources(String location) throws ResourceManagementException {
        DataCenter dataCenter = dataCenterDAO.getDataCenter(location);
        if (dataCenter != null) {
            while (dataCenter.pollTaskExist()) {
                Task task = dataCenter.pollTask();
                List<Resource> availableResources = getAvailableResources(location, task.getResourceType(), task.getMinCpuConfiguration());

                if (!availableResources.isEmpty()) {
                    Resource allocatedResource = allocateResource(availableResources);
                    executeTask(location, task, allocatedResource);
                } else {
                    // If resources are not available, put the task in the waiting queue
                    dataCenter.submitTask(task);
                }
            }
        } else {
            throw new ResourceManagementException("Data center not found for location: " + location);
        }
    }

    private Resource allocateResource(List<Resource> resources) {
        // Allocation logic based on least price, and if prices are equal, select the resource with more CPU
        resources.sort((r1, r2) -> {
            // First, compare by price
            int priceComparison = Double.compare(r1.getPricing(), r2.getPricing());
            if (priceComparison != 0) {
                return priceComparison;
            }

            // If prices are equal, compare by CPU configuration in descending order
            return Integer.compare(r2.getCpuConfiguration(), r1.getCpuConfiguration());
        });

        return resources.get(0);
    }

    private void executeTask(String location, Task task, Resource allocatedResource) {
        task.setStartTime(new Date());
        // Simulating task execution time (assuming inverse proportion to CPU)
        try {
            Thread.sleep(1000 / allocatedResource.getCpuConfiguration());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        task.setEndTime(new Date());
        //task.removeResource();
    }

    public void printTaskStatus(String location) throws ResourceManagementException {
        DataCenter dataCenter = dataCenterDAO.getDataCenter(location);
        if (dataCenter != null) {
            System.out.println("Tasks in waiting queue for Data Center at " + dataCenter.getLocation());
            int queueSize = dataCenter.getQueueSize();
            while (queueSize-->0) {
                Task task = dataCenter.pollTask();
                dataCenter.submitTask(task);
                System.out.println("Task " + task.getTaskId() + " is waiting for resources.");
            }
        } else {
            throw new ResourceManagementException("Data center not found for location: " + location);
        }
    }

    public List<Resource> getAllocatedResourcesByType(String location, String type) throws ResourceManagementException {
        DataCenter dataCenter = dataCenterDAO.getDataCenter(location);
        if (dataCenter != null) {
            return dataCenter.getAllocatedResourcesByType(type);
        } else {
            throw new ResourceManagementException("Data center not found for location: " + location);
        }
    }

    public void printTaskStatus() {
        System.out.println(taskDAO.getAllTask());
    }
}

