# README #

### How do I get set up? ###

* If you have trouble compiling, try setting `versionCode = 1` in `app.gradle`

### Summary ###

* Libraries used
    - Volley (https://github.com/google/volley)  
    I used volley for network operations as it is a fast, easy to set up and recommended library in the official documentation. It removes a lot of boilerplate code that would need to be implemented otherwise.

    - Glide (https://github.com/bumptech/glide)  
    I used Glide to load and display images because it is efficient. It loads images lazily which is good for app performance.
    
    - Gson (https://github.com/google/gson)
    In my opinion, Gson is THE go-to library to consume or create JSON.

* 2 layouts for the property list items: basic and premium. 

* Tablet landscape mode is supported with master-detail workflow. A fragment will show property details.  
  When in portrait mode, an activity will show the property details.

* Tests
    - A simple test for all the parcelable data classes. With more time, I would write tests covering most of the app.

### Who do I talk to? ###

* Repo owner or admin  
Ali Kazi   
[LinkedIn](linkedin.com/in/mdalikazi)  
