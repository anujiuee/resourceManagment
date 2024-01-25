package org.phonePe.dao;

import org.phonePe.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TaskDao {

    //id,Task

    private Map<String, Task> taskById = new HashMap<>();

    public void addTask(Task task) {
        taskById.put(task.getTaskId(), task);
    }

    public Task Task(String taskId) {
        return taskById.get(taskById);
    }

    public List<Task> getAllTask() {
        return new ArrayList<Task>(taskById.values());
    }


}
