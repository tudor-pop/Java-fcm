package com.pixsee.fcm;

/**
 * Created by Tudor on 15-Nov-16.
 */
public class Main {
    public static void main(String[] args) {
        Sender sender = new Sender("AIzaSyCl1eei6fHUYXl1xetFGkNSOKYNsXv8PUU");

        Message message = new Message.MessageBuilder()
                .setTo("fYgYpYs1fDQ:APA91bEP4m18lwRkNbbmpGIthgpyAQ_6QU1Et7kygcbS8y2IrPKzj1jw7jE_Vkk5yfc2vtFzKDWRsek53kTasou3FliOFO2ywhkk9UrsJnIjfGtl-iqRUU6tNYINsKEik2UM-YiX5oNY").createMessage();
        sender.send(message);
        return;
    }
}
