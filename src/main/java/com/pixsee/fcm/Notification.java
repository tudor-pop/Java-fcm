package com.pixsee.fcm;

import java.util.List;

/**
 * Created by Tudor on 16-Nov-16.
 */
public class Notification {
    private String title;
    private String body;
    private String sound;
    /* android */
    private String icon;
    private String tag;
    private String color;
    /*iOS*/
    private String badge;

    private String click_action;
    private String title_loc_key;
    private List<String> title_loc_args;
    private String body_loc_key;
    private List<String> body_loc_args;
}
