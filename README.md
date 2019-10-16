# Auto Restaurant + [Auto Restaurant Kitchen](https://github.com/syaheer/AutoRestaurantKitchen)

This is Syahir Sazali's final project application for CPSC-60000-001 Object Oriented Development @ Lewis University.

Auto Restaurant is an automated restaurant system where customers may order, pickup, and pay without having to talk to a waiter.
It is a full self-service system!

## Patterns Used

There are 5 patterns used throughout the two applications. All of the patterns used are clearly commented in the app so you
can just use "find all" (Ctrl/Command +Shift + F) and look for "Pattern".

### The Factory Pattern

**The Factory Method Pattern** defines an interface for creating an object, but lets subclasses decide which class to instantiate. 
Factory Method lets a class defer instantiation to subclasses.

Here we have the `MenuItem` abstract class which are extended by the 4 menu items that we have in the app: `BeefSandwich`,
`CreamyOatmeal`, `SpinachLasagna` and `TacoSalad`.

### The Proxy Pattern

**The Proxy Pattern** provides a surrogate or placeholder for another object to control access to it.

In this app we have our Firebase database as the proxy server. The Proxy keeps a reference to the
`OrderedItem`, so it can forward requests to the `OrderedItem` when necessary.

### The Iterator Pattern

**The Iterator Pattern** provides a way to access the elements of an aggregate object sequentially without exposing its underlying
representation.

In the Auto Restaurant app we implement our own `Iterator` class to help us iterate through the `MenuItem` list. There are also
usages of the iterator pattern using the Java Iterator class. Note that in the Kitchen app we are not using the Iterator pattern
because the kitchen does not need to know the menu.

### The Singleton Pattern

**The Singleton Pattern** ensures a class has only one instance, and provides a global point of access to it.

In our `RestaurantMenu` class, we have the `getInstance()` method to ensure that we are only using one instance of the menu item.
The menu item is the same so it makes sense to just use one instance and not wasting memory creating a lot of instances.

### The Adapter Pattern

**The Adapter Pattern** converts the interface of a class into another interface the clients expect. Adapter lets
classes work together that couldn’t otherwise because of incompatible interfaces.

There are two adapter classes we are using in the two apps: `OrderedItemAdapter` and `OrderListAdapter` to ensure that they can be
used in our `ListView`.

## Running The Application

There are a few ways you can run the application and I will provide you the methods for each. Note that running the application
via Android Studio might require some registration to Firebase since you will have to have access to the database (or you can use your own)

### Using Android Studio

[Download the latest Android Studio](https://developer.android.com/studio) and run the application using an emulator. You might
encounter crashes IF Firebase is not set up correctly. You may fix it by swapping the google-services.json with your own.
The easiest way is to use the Firebase helper on Android Studio (Tools > Firebase > Authentication > Connect your app to Firebase)

### Using Appetize.io

You can run the app on Appetize.io without having to worry about the Firebase stuff (since the .apk is already compiled)
[User app on Appetize.io](https://appetize.io/app/b7b8q08cdkcmjxtpbgj15g6ac8?device=nexus5&scale=75&orientation=portrait&osVersion=8.1)
[Kitchen app on Appetize.io](https://appetize.io/app/96pag5x3eeg1guebcm4rgn5gb4?device=nexus5&scale=75&orientation=portrait&osVersion=8.1)

### Using the .apk itself

You can download the two .apk files and run them on an Android Device (by downloading + installing the apk into the phone) or an emulator (by just dragging and dropping the apk).
[14 best Android emulators for PC and Mac of 2019!](https://www.androidauthority.com/best-android-emulators-for-pc-655308/)
Personally, I recommend BlueStacks.

### Using the Application

on the user app, you can click on the "+" button to order. once you ordered, an id will be created (this is based on the device so the Appetize.io link I sent you might have the same ids).
Kitchen app will then be notified, and a list will all ids that are ordered will appear. Clicking on an ID will show you a list of ordered items. On the kitchen app, 2 buttons will appear. One to show the ingredients and the other for the next available actions.
Until the the food is prepared, the user will then have a button to pickup and pay.

## Unit Tests

To run the unit test, you'll have to use Android Studio. Go to the `com.syahiramir.autorestaurant (test)` directory and run `AutoRestaurantTest`
Note that this is only included in the user (AutoRestaurant) app.

## Challenges and Difficulties

Firebase is a super simple database application that can be used for prototyping. Unfortunately it is not that simple if you work
on multiple machines (because of security reasons I guess?). You'll have to setup Firebase manually for each you machine that you use.

Other than that, I guess its the change of architecture that I am already used to when writing an Android Application.
I am used to MVVM and changing back to MVC requires some reading, which is good since I get to learn about it better.
