# Activities



## 1. Activity Lifecycle
![Image of activity lifecycle](https://github.com/DaiJiChen/Android_Learning/blob/main/Readme/Activity_Lifecycle.jpg?raw=true)

![google_activity_lifecycle](https://developer.android.com/guide/components/images/activity_lifecycle.png)

There are 6 activity lifecycle methods. You just override them when needed.

***onCreate()***: 
> Do things that only happen once in lifecycle.(Such as create views and bind data to lists)
> Receives the parameter `savedInstanceState`, which is a `bundle` object. If the activity never existed before, the `bunlde` activity will be null.

***onStart()***: Becomes visible to user after call onStart().

***onResume()***: Activity is at the top of the activity stack and captures all user input.

***onPause()***: This occures when, for example, the user taps the Back or Recents button. The activity after onPause() is still visible or partial visible. You should **not** use  onPause() to save application or user data, make network calls, or execute databsae transactions.
 
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

