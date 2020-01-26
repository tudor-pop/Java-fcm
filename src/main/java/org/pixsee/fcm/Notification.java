package org.pixsee.fcm;

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
    private String android_channel_id;
    /*iOS*/
    private String badge;

    private String click_action;
    private String title_loc_key;
    private List<String> title_loc_args;
    private String body_loc_key;
    private List<String> body_loc_args;

    public Notification() {
    }

    public Notification(String title, String body) {

        this.title = title;
        this.body = body;
    }

    public Notification(String title) {

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAndroidChannelId() { return android_channel_id; }

    public void setAndroidChannelId(String android_channel_id) { this.android_channel_id = android_channel_id; }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getClickAction() {
        return click_action;
    }

    public void setClickAction(String click_action) {
        this.click_action = click_action;
    }

    public String getTitleLocKey() {
        return title_loc_key;
    }

    public void setTitleLocKey(String title_loc_key) {
        this.title_loc_key = title_loc_key;
    }

    public List<String> getTitleLocArgs() {
        return title_loc_args;
    }

    public void setTitleLocArgs(List<String> title_loc_args) {
        this.title_loc_args = title_loc_args;
    }

    public String getBodyLocKey() {
        return body_loc_key;
    }

    public void setBodyLocKey(String body_loc_key) {
        this.body_loc_key = body_loc_key;
    }

    public List<String> getBodyLockArgs() {
        return body_loc_args;
    }

    public void setBodyLockArgs(List<String> body_loc_args) {
        this.body_loc_args = body_loc_args;
    }
}
