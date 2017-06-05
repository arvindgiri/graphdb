package com.adobe.ids.cjass.service.dataload;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by arvind on 3/6/17.
 */
public interface IEvent {

    String getEventGuid();

    void setEventGuid(String eventGuid);

    String getUserId();

    void setUserId(String userId);

    String getCategory();

    void setCategory(String eventCategory);

    String getEventSubCategory();

    void setEventSubCategory(String eventSubCategory);

    Date getEventDts();

    void setEventDts(Date eventDts);

    Iterator<Map.Entry<String, String>> getProperties();

    void setProperty(String key, String value);

    boolean isIgnoreDuplicate();

    void setIgnoreDuplicate(boolean ignoreDuplicate);

}
