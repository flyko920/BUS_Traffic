package com.xcc.bustraffic.bustraffic.bean;

/**
 * Created by flykozhang on 2017/1/4.
 */

public class Version {
    private static final long serialVersionUID = 2313606695767330258L;

    private Integer id ;

    private String version ;

    private Integer updateStatus;

    private String downloadUrl;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", updateStatus=" + updateStatus +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
