# Android Learning: Projects and Notes

## 1. Activity Lifecycle
![Image of activity lifecycle](https://github.com/DaiJiChen/Android_Learning/blob/main/Readme/Activity_Lifecycle.jpg?raw=true)

As shown in graph above, there are 6 activity lifecycle methods. You just override them when needed.

Which methods will be called when you press `Back Button`: onPause(), onStop(), onDestroy().

Which methods will be called when you press `Home Button`: onPause(), onStop().

What happen when you `rotate you r screen`: current activity will be destroyed and then been resumed again. Called method is: onPause(), onStop(), onDestroy(), onCreate(), onStart(), onResume().

In order to hold the same data across rotation, we should save date and use them to build the new activity.

One way is using ` protected void onSaveInstanceState(Bundle outState)`.
   
   This method is called before onStop(), except when the user presses the Back button. The default implementation of onSaveInstanceState(Bundle) directs all of the activity’s views to save their state as data in the Bundle object. A Bundle is a structure that maps string keys to values of certain limited types.
```java
public class QuizActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ...
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(someKey, 0);
        }
        ...
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
         super.onSaveInstanceState(savedInstanceState);
         ...
         savedInstanceState.putInt(someKey, someValue);
         ...
    }
}
```

After onStop() is called, activity will be marked as killable and memory might be token away. But don't worry, because we already execured onSaveInstanceState(). The `bunlde` objet is stored into this activity's `activity record` by the OS. This record only been deleted after onDestroy() is called.




## 2. Intents
An intent is an object that a component can use to communicate with the OS.
```java
   public Intent(Context packageContext, Class<?> cls)
  ```
The Class argument specifies the activity class that the ActivityManager should start. 

The Context argument tells the ActivityManager which application package the activity class can be found in.

### Explicit and implicit intents

Explicit Intents: start activities within same application.
Implicit intents: start activities in another application.

### Using intent extra to passing data between activities.
Put data into intent
```
intent.putExtra("key", "value");
```


Use data in new activity:
```    
    Intent intent = getIntent();
    String value = intent.getStringExtra("key");
```


## Application Fundamentals

Kotlin， Java or C++ code ----SDK----> `.apk` (Android Package)

### Android Security features:
1. Android OS is a multi-user Linux system in which each app is a different user.
2. By default, the system assigns each app a unique UID (this ID is used only by the system and is unknown to the app). The system sets permissions for all files in an app so that only the UID assigned to this app can access them.
3. Each app has it's own virtual machine (VM), so an app's code runs in isolation from other apps.
4. By default, each app runs in its own Linux process. the Android system starts the process when any of the app's components need to be executed, and shuts down the process when it's no longer needed or when system must recover mrmory for other apps.
5. The Android system implements the principle of least privilege. Each app, by default, has access only to the components that it requires to do its work and no more.

### Ways for an app to share data with other apps or access system service
1. It's possible for two apps to share the same Linux UID, so that they can access each other's files, they can also share the same VM. The apps must be signed with the same certificate.
2. An app can request premission to access device data such as the device's location, camera, Bluetooth. The user has to explicitly grant these permissions.

### Four app components
* Activities
* Services
* Broadcast receivers
* Content providers

`An activity` is the entry point for interacting with the user. It represents a single screen.

`A service` is a general-purpose entry point for keeping an app running in the background. A service don't provide a user interface. There are two kinds of services:
1. Started services keep running until their work is complete.(might be killed and restart when needed)
2. Bound services privides an API to other process, thus is bound to other processes. There is a dependency between these processes.

`Broadcast receiver` enables the system to deliver events to the apps outside of a regular user flow, allowing the app to respond to system-wide broadcast announcements. Althouth broadcast receivers don't display a user interface, they may create a status bar notification. A broadcast receiver is implemented as a subclass of BroadcastReceiver and each broadcast is delivered as an Intent object.

`Content provider` manages data that stored in file system, in a SQLite database, or web. 

`Any app can start another app's cmponents.` When the system starts a component, it starts the process for taht app if it's not already running.

Your app cannot directly activate a component from another app. But you can deliver a message to system and the system then activates the component for you.

#### Activating components
Three of the four component types—`activities, services, and broadcast receivers`—are activated by an asynchronous message called an `intent`.

Content providers are not activated by intents. Rather, they are activated when targeted by a request from a `ContentResolver`. The content resolver handles all direct transactions with the content provider. This leaves a layer of abstraction between the content provider and the component requesting information (for security).

### The manifest file

### Resources 

seperated from code so that you can optimize app's behavior for a variety of device configurations




## References:
- `Android Programming: The Big Nerd Ranch Guide` by Bill Phillips, Chris Stewart and Kristin Marsicano
- [https://developer.android.com/](https://developer.android.com/)
- [https://guides.codepath.com/android/](https://guides.codepath.com/android/)
