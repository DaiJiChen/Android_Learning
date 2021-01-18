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


## References:
- `Android Programming: The Big Nerd Ranch Guide` by Bill Phillips, Chris Stewart and Kristin Marsicano
- [https://developer.android.com/](https://developer.android.com/)
- [https://guides.codepath.com/android/](https://guides.codepath.com/android/)
