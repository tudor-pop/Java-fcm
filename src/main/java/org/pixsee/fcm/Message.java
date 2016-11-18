package org.pixsee.fcm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tudor on 16-Nov-16.
 * Implementation of the following doc https://firebase.google.com/docs/cloud-messaging/http-server-ref#send-downstream
 */
public class Message {
    private String to;
    private String condition; // field to must be null if condition is set

    private String collapse_key;
    private String priority;
    private Boolean content_available;
    private Long time_to_live;
    private String restricted_package_name;
    private Boolean dry_run;
    private Map<String, Object> data;
    private List<String> registration_ids;
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
        this.condition = builder.condition;
        this.registration_ids = builder.registration_ids;
    }


    public static final class Priority {
        public static final String NORMAL = "normal";
        public static final String HIGH = "high";

        private static boolean isValid(String priority) {
            return priority.equalsIgnoreCase(NORMAL) || priority.equalsIgnoreCase(HIGH);
        }
    }

    public static class MessageBuilder {
        private String to;
        private String condition; // field to must be null if condition is set

        private String collapse_key;
        private String priority;
        private Long time_to_live;
        private String restricted_package_name;
        private Boolean content_available;
        private Boolean dry_run;
        private Map<String, Object> data = new HashMap<>(2);
        private List<String> registration_ids = new ArrayList<>(2);
        private Notification notification;

        /**
         * Send message to single topic
         * NOTE: A message can have a topic or a registration toToken
         *
         * @param topic the devices that are subscribed to a topic and will receive the message
         * @return the message
         */
        public MessageBuilder toTopic(String topic) {
            this.to = topic;
            return this;
        }

        /**
         * Send message to single device
         * NOTE: A message can have a topic or a registration toToken
         *
         * @param toToken the device's toToken that will receive the message
         * @return the message
         */
        public MessageBuilder toToken(String toToken) {
            this.to = toToken;
            return this;
        }

        public MessageBuilder toCondition(String condition) {
            this.condition = condition;
            this.to = null;
            return this;
        }

        public MessageBuilder addRegistrationToken(List<String> registrationTokens) {
            this.registration_ids.addAll(registrationTokens);
            this.to = null;
            return this;
        }

        public MessageBuilder addRegistrationToken(String registrationToken) {
            this.registration_ids.add(registrationToken);
            this.to = null;
            return this;
        }

        public MessageBuilder collapseKey(String collapse_key) {
            this.collapse_key = collapse_key;
            return this;
        }

        public MessageBuilder priority(String priority) {
            if (Priority.isValid(priority))
                this.priority = priority;
            else
                this.priority = Priority.NORMAL;
            return this;
        }

        public MessageBuilder contentAvailable(boolean content_available) {
            this.content_available = content_available;
            return this;
        }

        public MessageBuilder timeToLive(long time_to_live) {
            this.time_to_live = time_to_live;
            return this;
        }

        public MessageBuilder restrictedPackageName(String restrictedPackageName) {
            this.restricted_package_name = restrictedPackageName;
            return this;
        }

        public MessageBuilder dryRun(boolean dryRun) {
            this.dry_run = dryRun;
            return this;
        }

        public MessageBuilder addData(Map<String, Object> data) {
            this.data.putAll(data);
            return this;
        }

        public MessageBuilder addData(String key, Object value) {
            this.data.put(key, value);
            return this;
        }

        public MessageBuilder notification(Notification notification) {
            this.notification = notification;
            return this;
        }

        public Message build() {
            removeEmptyCollections();
            return new Message(this);
        }

        /**
         * This is needed because else the json will look like this
         * {
         * to:"sometoken",
         * data:{},
         * registration_ids:[]
         * }
         */
        private void removeEmptyCollections() {
            if (this.data != null && this.data.isEmpty())
                this.data = null;
            if (this.registration_ids != null && this.registration_ids.isEmpty())
                this.registration_ids = null;
        }
    }
}
