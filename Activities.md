# Activities



## 1. Activity Lifecycle
![Image of activity lifecycle](https://github.com/DaiJiChen/Android_Learning/blob/main/Readme/Activity_Lifecycle.jpg?raw=true)

![google_activity_lifecycle](https://developer.android.com/guide/components/images/activity_lifecycle.png)

There are 6 activity lifecycle methods. You just override them when needed.

***onCreate()***: 
> Do things that only happen once in lifecycle.(Such as create views and bind data to lists)
> 
> Receives the parameter `savedInstanceState`, which is a `bundle` object. If the activity never existed before, the `bunlde` activity will be null.

***onStart()***: 
> Makes the activity visible to user.
> 
> After call onStart(), activity won't stay in *Created state**, it immediately enters **Resumed state** and system invokes `onResume()` method.

***onResume()***: 
> When the activity enters the Resumed state, it comes to the foreground. Then the system invokes the onResume() callback. 
> 
> Activity will stay in resumed state until something happens to take focus away from the app.

***onPause()***: 
> Means activity is no longer i the foreground (though still visible in multi window mode)
> 
> onPause() execution i svery brief, so you should **not** use onPause() to do heavy-load shutdown operations, such as save application or user data, make network calls. Instead, you should perform heavy-load operations during onStop().
>  
>  Completion of onPause() does not mean the activity leaves the Paused state, it will remain in this state until either the activity resumes or becomes completely invisible.
>  
>  Instances will still stay in memory when activity is paused. So you don't need to re-initialize any components.
 
***onStop()***: The activity is no longer visible. The next calback is either onRestart() or onDestroy().

***onRestart()***: Always follower by `onStart()` Restores the state of the activity from the time that it was stopped.

***onDestroy***: Implemented to makesure that all of an activity's resources are released

When you press `Back Button`: onPause(), onStop(), onDestroy().

When you press `Home Button`: onPause(), onStop().

When you `rotate your screen`: current activity will be destroyed and then been resumed again. Called method is: onPause(), onStop(), onDestroy(), onCreate(), onStart(), onResume().

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

After onStop() is called, activity will be marked as killable and memory might be token away. But don't worry, because we already execured onSaveInstanceState(). The `bunlde` object is stored into this activity's `activity record` by the OS. This record only been deleted after onDestroy() is called.

