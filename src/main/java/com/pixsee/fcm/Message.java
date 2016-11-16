package com.pixsee.fcm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tudor on 16-Nov-16.
 * Implementation of the following doc https://firebase.google.com/docs/cloud-messaging/http-server-ref#send-downstream
 */
public class Message {
    private String to;
    private String collapse_key;
    private String priority;
    private boolean content_available;
    private long time_to_live;
    private String restricted_package_name;
    private boolean dry_run;
    private Map<String, Object> data = new HashMap<>();
    private Notification notification;

    private Message(MessageBuilder builder) {
        this.to = builder.to;
        this.collapse_key = builder.collapse_key;
        this.priority = builder.priority;
        this.content_available = builder.content_available;
        this.time_to_live = builder.time_to_live;
        this.restricted_package_name = builder.restricted_package_name;
        this.dry_run = builder.dry_run;
        this.data = builder.data;
        this.notification = builder.notification;
    }


    public static final class Priority {
        public static final String NORMAL = "normal";
        public static final String HIGH = "high";

        public static boolean isValid(String priority) {
            return priority.equalsIgnoreCase(NORMAL) || priority.equalsIgnoreCase(HIGH);
        }
    }

    public static class MessageBuilder {
        private String to;
        private String collapse_key;
        private String priority;
        private boolean content_available;
        private long time_to_live;
        private String restricted_package_name;
        private boolean dry_run;
        private Map<String, Object> data;
        private Notification notification;

        public MessageBuilder setTo(String to) {
            this.to = to;
            return this;
        }

        public MessageBuilder setCollapse_key(String collapse_key) {
            this.collapse_key = collapse_key;
            return this;
        }

        public MessageBuilder setPriority(String priority) {
            if (Priority.isValid(priority))
                this.priority = priority;
            else
                this.priority = Priority.NORMAL;
            return this;
        }

        public MessageBuilder setContentAvailable(boolean content_available) {
            this.content_available = content_available;
            return this;
        }

        public MessageBuilder setTimeToLive(long time_to_live) {
            this.time_to_live = time_to_live;
            return this;
        }

        public MessageBuilder setRestrictedPackageName(String restricted_package_name) {
            this.restricted_package_name = restricted_package_name;
            return this;
        }

        public MessageBuilder setDryRun(boolean dry_run) {
            this.dry_run = dry_run;
            return this;
        }

        public MessageBuilder setData(Map<String, Object> data) {
            this.data = data;
            return this;
        }
        public MessageBuilder addData(String key,Object value){
            this.data.put(key, value);
            return this;
        }

        public MessageBuilder setNotification(Notification notification) {
            this.notification = notification;
            return this;
        }

        public Message createMessage() {
            return new Message(this);
        }
    }
}
