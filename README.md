# README #

* App name: Sentia Test - Ali Kazi

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

* There are 2 layouts for the property list items: basic and premium. 

* Tablet landscape mode is supported with master-detail workflow. A fragment will show property details.  
  When in portrait mode, an activity will show the property details.

* Tests
    - I have written a simple test for all the parcelable data classes. With more time, I would write tests covering most of the app.
    
* Further considerations
    - With more time, I would add 
        - Google maps feature to get directions to property address
        - If pictures were different, I would have added a ViewPager to card items especially for premium listings
    
* Git flow
	- I have used the standard recommended git flow method (pull requests, rebasing etc.). You can see the history of the workflow.

### Who do I talk to? ###

* Repo owner or admin  
Ali Kazi  
mdalikazi@gmail.com  
0401553443  
[LinkedIn](linkedin.com/in/mdalikazi)  
[Github](github.com/mdalikazi)
