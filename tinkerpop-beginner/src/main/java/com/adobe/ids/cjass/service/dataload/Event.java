package com.adobe.ids.cjass.service.dataload;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by arvind on 3/6/17.
 */
public class Event implements IEvent {

    private String eventGuid;

    private String userId;

    private String eventCategory;

    private String eventSubCategory;

    private Date eventDts;

    private HashMap<String, String> properties = new HashMap<String, String>();

    //ignoreDuplicate = false : Insert the event even if same event already exists
    //TODO Duplicate check to be implemented
    private boolean ignoreDuplicate = false;

    public Event(String eventGuid, String userId, String eventCategory, Date eventDts) {
        this.eventGuid = eventGuid;
        this.userId = userId;
        this.eventCategory = eventCategory;
        this.eventDts = eventDts;
    }

    @Override
    public String getEventGuid() {
        return this.eventGuid;
    }

    @Override
    public void setEventGuid(String eventGuid) {
        this.eventGuid = eventGuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventSubCategory() {
        return eventSubCategory;
    }

    public void setEventSubCategory(String eventSubCategory) {
        this.eventSubCategory = eventSubCategory;
    }

    public Date getEventDts() {
        return eventDts;
    }

    public void setEventDts(Date eventDts) {
        this.eventDts = eventDts;
    }

    public Iterator<Map.Entry<String, String>> getProperties() {
        return properties.entrySet().iterator();
    }

    @Override
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public boolean isIgnoreDuplicate() {
        return ignoreDuplicate;
    }

    public void setIgnoreDuplicate(boolean ignoreDuplicate) {
        this.ignoreDuplicate = ignoreDuplicate;
    }

}
