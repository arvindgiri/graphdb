package com.adobe.ids.cjass.service.dataload;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by arvind on 3/6/17.
 */

public class UserInfo implements IUserInfo {
    private String userId;

    private String userName;

    private Date userJoiningDate;

    private Map<String, String> properties = new HashMap<>(20);


    public UserInfo(String userId) {
        this.userId = userId;
    }

    public UserInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String getId() {
        return userId;
    }

    @Override
    public void setId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public Date getUserJoiningDate() {
        return userJoiningDate;
    }

    public void setUserJoiningDate(Date userJoiningDate) {
        this.userJoiningDate = userJoiningDate;
    }

    public Iterator<Map.Entry<String, String>> getProperties() {
        return properties.entrySet().iterator();
    }

    public void setProperty(String key, String value) {
        this.properties.put(key, value);
    }
}
