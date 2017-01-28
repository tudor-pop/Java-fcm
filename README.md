# Java-fcm
The purpose of this library is to provide a easy to use, ready-to-go [Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging/) implementation for your project. 
I hope people will get interested into this project and I appreciate all the valuable contributions that you guys/girls make!

If you are just getting started with FCM you should start reading the [official docs.](https://firebase.google.com/docs/cloud-messaging/)

This library was inspired by the nodejs version https://github.com/ToothlessGear/node-gcm

## Installation
### Gradle
Add the following to your build.gradle file
```gradle
repositories {
    maven {
        url  "http://dl.bintray.com/tudor/Pixsee" 
    }
}
...
```
then
```gradle
dependencies {
    compile 'org.pixsee.java-fcm:Java-fcm:1.0.0'
}
```
### Maven
```maven
<dependency>
  <groupId>org.pixsee.java-fcm</groupId>
  <artifactId>Java-fcm</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
## Requirements
This library provides the easy means for sending [messages](https://firebase.google.com/docs/cloud-messaging/send-message#send_messages_to_specific_devices) to [Android](https://firebase.google.com/docs/cloud-messaging/android/client) or [iOS](https://firebase.google.com/docs/cloud-messaging/ios/client) clients. Before starting to use it, you require(as per docs) a [SERVER_KEY](https://firebase.google.com/docs/cloud-messaging/server#auth) which is available in the [Cloud Messaging](https://console.firebase.google.com/project/_/settings/cloudmessaging) tab of the Firebase console Settings pane.

## Example & Usage
### Send a message
```java
import org.pixsee.fcm.Sender;
...
Sender fcm = new Sender("SERVER_KEY");
...
// build the message 
Message message = new Message.MessageBuilder()
    .toToken(toClientToken) // single android/ios device
    .addData("key_1","value_1")
    .build();
// send the message
fcm.send(message);
...
// build complex message
Message message = new Message.MessageBuilder()
    .toToken(to)
    .collapseKey("chat-app")
    .contentAvailable(true)
    .priority(Message.Priority.HIGH) // or just type "normal" or "high"
    .timeToLive(3)
    .restrictedPackageName("your.package.name")
    .addData("key1", "data1")
    .addData("key2", "data2")
    .notification(new Notification("optional_title", "optional_body"))
    .build();
sender.send(message)
...
// build multiple tokens ids
List<String> registrationIds = new ArrayList<>();
registrationIds.add("token1");
registrationIds.add("token2");

Message message = new Message.MessageBuilder()
    .addRegistrationToken(registrationIds) // add array
    .addRegistrationToken(to) // add individual
    .addData("key1", "data1")
    .addData("key2", "data2")
    .notification(someNotification)
    .build();
    
sender.send(message, new Callback() {
    @Override
    public void onResponse(Call call, Response response) {
        if(response.isSuccessful())
          System.out.print("Hooray!");
    }
    @Override
    public void onFailure(Call call, Throwable t) {
        t.printStackTrace();
    }
});
```
## Notification
```java
Notification notification = new Notification("title", "body");
notification.setIcon("icon");
notification.setSound("sound");
notification.setBadge("badge");
notification.setClickAction("click.action");
Message message = messageBuilder.notification(notification).build(); // add notification
```

## Logging request/response
provided by OkHttp interceptors
```java
import okhttp3.logging.HttpLoggingInterceptor;
...
Sender sender = new Sender("server_key");
sender.setLoggingLevel(HttpLoggingInterceptor.Level.BODY);
sender.setLoggingLevel(HttpLoggingInterceptor.Level.BASIC);
sender.setLoggingLevel(HttpLoggingInterceptor.Level.NONE);
sender.setLoggingLevel(HttpLoggingInterceptor.Level.HEADERS);
```
## Recipients
You can send a message to different types of targets. All the methods apply to a MessageBuilder object

| Key               | Type          | Description                                          | Method              |
| ----------------- |:-------------:|:----------------------------------------------------:|:--------------------|
| to                | String        | A single [registration token] or [topic].            | toToken             |
| topic             | String        | A single publish/subscribe [topic].                  | toTopic             |
| condition         | String        | Multiple topics using a [condition].                 | toCondition         |
| registration_ids  | String[]      | List of registration tokens/ids. Support up to 1000  | addRegistrationToken|

If you don't provide any of the above, you get a bad request inside onResponse callback

## Support
[Bitcoin](https://blockchain.info/address/1M6gwLMGvGJYVAMhqNZgvgN7ovGJ3EJ3GW)
[Paypal](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MUMUF9YPD7S8S)
[Patreon](https://www.patreon.com/user?u=4450469)
[registration token]:https://firebase.google.com/docs/cloud-messaging/android/client#sample-register
[topic]:https://firebase.google.com/docs/cloud-messaging/android/topic-messaging
[condition]:https://firebase.google.com/docs/cloud-messaging/android/topic-messaging#build_send_requests
