
package com.tregix.serviceprovider.Model.Provider;;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileBrochure implements Serializable {

    @SerializedName("file_type")
    @Expose
    private String fileType;
    @SerializedName("default_file")
    @Expose
    private Integer defaultFile;
    @SerializedName("file_data")
    @Expose
    private Object fileData;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getDefaultFile() {
        return defaultFile;
    }

    public void setDefaultFile(Integer defaultFile) {
        this.defaultFile = defaultFile;
    }

    public Object getFileData() {
        return fileData;
    }

    public void setFileData(Object fileData) {
        this.fileData = fileData;
    }

}
