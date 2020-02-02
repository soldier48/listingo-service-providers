package com.tregix.serviceprovider.Interface;

/**
 * Created by Gohar Ali on 12/25/2017.
 */

public interface DataManager {

    void loadDataFromServer(OnDataLoadListener listener, boolean isforce);
    void loadDataFromDataBase(OnDataLoadListener listener);
    void saveDataToDataBase(Object data);
    void deleteDataToDataBase();
}
