## Intents
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
