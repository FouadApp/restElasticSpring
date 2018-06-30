package com.fouad.api.restElasticSpring.resources;


public class EventWithStatus {

    private String key;
    private String timeStamp;
    private String status;
    private String appType;
    private String topic;
    private String uuld;

    public EventWithStatus(String key, String timeStamp, String status, String appType, String topic, String uuld) {
        this.key = key;
        this.timeStamp = timeStamp;
        this.status = status;
        this.appType = appType;
        this.topic = topic;
        this.uuld = uuld;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUuld() {
        return uuld;
    }

    public void setUuld(String uuld) {
        this.uuld = uuld;
    }

    @Override
    public String toString() {
        return "EventWithStatus{" +
                "key='" + key + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", status='" + status + '\'' +
                ", appType='" + appType + '\'' +
                ", topic='" + topic + '\'' +
                ", uuld='" + uuld + '\'' +
                '}';
    }
}
