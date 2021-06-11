# Activities



## 1. Activity Lifecycle
![Image of activity lifecycle](https://github.com/DaiJiChen/Android_Learning/blob/main/Readme/Activity_Lifecycle.jpg?raw=true)

There are 6 activity lifecycle methods. You just override them when needed.

***onCreate()***: Create views and bind data to lists.

***onStart()***: Becomes visible to user after call onStart().

***onResume()***: Activity is at the top of the activity stack and captures all user input.

Which methods will be called when you press `Back Button`: onPause(), onStop(), onDestroy().

Which methods will be called when you press `Home Button`: onPause(), onStop().

What happen when you `rotate your screen`: current activity will be destroyed and then been resumed again. Called method is: onPause(), onStop(), onDestroy(), onCreate(), onStart(), onResume().

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

