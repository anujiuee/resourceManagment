package org.phonePe;

import org.phonePe.dao.DataCenterDao;
import org.phonePe.dao.ResourceDao;
import org.phonePe.dao.TaskDao;
import org.phonePe.exception.ResourceManagementException;
import org.phonePe.model.Resource;
import org.phonePe.model.Task;
import org.phonePe.services.impl.ResourceManagementService;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws ResourceManagementException {


        ResourceDao resourceDAO = new ResourceDao();
        TaskDao taskDAO = new TaskDao();
        DataCenterDao dataCenterDAO = new DataCenterDao();

        ResourceManagementService resourceManagementService = new ResourceManagementService(resourceDAO, taskDAO, dataCenterDAO);

        // P0 Requirements:
        dataCenterDAO.addDataCenter("LocationA");
        dataCenterDAO.addDataCenter("LocationB");

        resourceManagementService.addResource("LocationA",new Resource("R1", "SERVER_INSTANCE", 8, 10.0));
        resourceManagementService.addResource("LocationA",new Resource("R2", "SERVER_INSTANCE", 16, 15.0));
        resourceManagementService.addResource("LocationA",new Resource("R3", "SERVER_INSTANCE", 4, 8.0));

        resourceManagementService.addResource("LocationA",new Resource("R4", "SERVER_INSTANCE", 12, 12.0));
        resourceManagementService.addResource("LocationA",new Resource("R5", "SERVER_INSTANCE", 6, 9.0));

        List<Resource> availableResourcesLocationA = resourceManagementService.getAvailableResources("LocationA", "SERVER_INSTANCE", 8);
        System.out.println("Available Resources at LocationA: " + availableResourcesLocationA);

        List<Resource> availableResourcesLocation = resourceManagementService.getAllocatedResourcesByType("LocationA", "SERVER_INSTANCE");
        System.out.println("Available Resources at LocationA: " + availableResourcesLocation);



        Task task1 = new Task("Task1", "LocationA", "SERVER_INSTANCE", 8);
        resourceManagementService.submitTask("LocationA", task1);

        Task task2 = new Task("Task2", "LocationA", "SERVER_INSTANCE", 16);
        resourceManagementService.submitTask("LocationA", task2);

        Task task3 = new Task("Task3", "LocationA", "SERVER_INSTANCE", 4);
        resourceManagementService.submitTask("LocationA", task3);

         //Allocate resources and execute tasks
        resourceManagementService.allocateResources("LocationA");

        // Print task status
        resourceManagementService.printTaskStatus("LocationA");

        //print All Task
        resourceManagementService.printTaskStatus();




    }
}
