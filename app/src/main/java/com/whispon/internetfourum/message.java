package com.whispon.internetfourum;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by noriaki_oshita on 15/03/05.
 */
@ParseClassName("Message")
public class Message extends ParseObject {
    public String getUserId() {
        return getString("userId");
    }

    public String getBody() {
        return getString("body");
    }

    public void setUserId(String userId) {
        put("userId", userId);
    }

    public void setBody(String body) {
        put("body", body);
    }
}