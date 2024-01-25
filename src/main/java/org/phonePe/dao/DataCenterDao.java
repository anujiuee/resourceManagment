package org.phonePe.dao;

import org.phonePe.model.DataCenter;

import java.util.HashMap;
import java.util.Map;

public class DataCenterDao {

    //location,datacenter
    private Map<String, DataCenter> dataCenters = new HashMap<>();

    public DataCenter addDataCenter(String location) {
        DataCenter dataCenter = new DataCenter(location);
        dataCenters.put(location, dataCenter);
        return dataCenter;
    }

    public DataCenter getDataCenter(String location) {
        return dataCenters.get(location);
    }

}
