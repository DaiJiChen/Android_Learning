## Resources

Resources are the additional files and static content that your code uses.

You can access them using resource IDs that are generated in your project's R class.

### specify alternative resources

1. create a new directory in res/ named in the form `<resource_name>-<qualifier>`

### qualifier name rules

1. You can specify multiple qualifiers for a single set of resources, seperated by dashes, For example, `drawable-en-rUS-land` applies to US-English devices in landscape orientation.
2. The qualifiers must be in the order listed in https://developer.android.com/guide/topics/resources/providing-resources
3. Alternative resoures directories cannot be nested. For example: we cannot have `res/drawable/drawable-en/`
4. Directory names are case-insensitive.
5. Only one value for each qualifier type is supported

### Creating alias resources

```xml
<resources>
    <drawable name="icon">@drawable/icon_ca</drawable>
</resources>
```

```xml
<merge>
    <include layout="@layout/main_ltr"/>
</merge>
```

```xml
<resources>
    <string name="hello">Hello</string>
    <string name="hi">@string/hello</string>
</resources>
```

```xml
<resources>
    <color name="red">#f00</color>
    <color name="highlight">@color/red</color>
</resources>
```

### Accessing your app resources

When your app is compiled, `aapt` generates the `R` class, which contains resource IDs for all the resources in your res/ directory. 

For each type of resource, there is an R subclass( for example: R.drawable). And for each resource of that type, there is a static integer (for example, R.drawable.icon). This integer is the resource ID that you can use to retrieve your resource.

Two way to access your resource:
1. In code: Using static integer from sub-class of your `R` class, such as
```
R.string.hello`
```
2. In XML: Using a special XML syntax, such as:
```
@string/hello
```

### Referencing style attributes
A style attribute resource allows you to reference the value of an attribute in the currently-applied theme. Referencing a style attribute allows you to customize the look of UI elements by styling them to match standard variations supplied by the current theme, instead of supplying a hard-coded value. Referencing a style attribute essentially says, "use the style that is defined by this attribute, in the current theme."

To reference a style attribute, the name syntax is almost identical to the normal resource format, but instead of the at-symbol (@), use a question-mark (?), and the resource type portion is optional. For instance:

```xml
<EditText id="text"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:textColor="?android:textColorSecondary"
    android:text="@string/hello_world" />
```

### Accessing original files
While uncommon, you might need access your original files and directories. If you do, then saving your files in res/ won't work for you, because the only way to read a resource from res/ is with the resource ID. Instead, you can save your resources in the assets/ directory.

Files saved in the assets/ directory are not given a resource ID, so you can't reference them through the R class or from XML resources. Instead, you can query files in the assets/ directory like a normal file system and read raw data using AssetManager.

However, if all you require is the ability to read raw data (such as a video or audio file), then save the file in the res/raw/ directory and read a stream of bytes using openRawResource().

### providing the best device compatibility with resources
You should always provide default resources.

If you provide different layout resources based on the screen orientation, you should pick one orientation as your default. For example, instead of providing layout resources in layout-land/ for landscape and layout-port/ for portrait, leave one as the default, such as layout/ for landscape and layout-port/ for portrait.

### How Android finds the best-matching resource
When you request a resource for which you provide alternatives, Android selects which alternative resource to use at runtime, depending on the current device configuration. 


Assuming we have following drawable directories.
```
drawable/
drawable-en/
drawable-fr-rCA/
drawable-en-port/
drawable-en-notouch-12key/
drawable-port-ldpi/
drawable-port-notouch-12key/
```

And assuming the following is the device configuration:
```
Locale = en-GB
Screen orientation = port
Screen pixel density = hdpi
Touchscreen type = notouch
Primary text input method = 12key
```

Decision process are shown in image:
https://developer.android.com/images/resources/res-selection-flowchart.png

Note: When selecting resources based on the screen size qualifiers, the system can accept a screen smaller than the current screen if there are no better match(for example, a large-size screen uses normal-size screen resources if necessary).












