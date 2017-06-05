package com.adobe.ids.cjass.service.dataload;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by arvind on 3/6/17.
 */
public interface IUserInfo {
    String getId();

    void setId(String userId);

    String getName();

    void setName(String userName);

    Date getUserJoiningDate();

    void setUserJoiningDate(Date userJoiningDate);

    Iterator<Map.Entry<String, String>> getProperties();

    void setProperty(String key, String value);
}
