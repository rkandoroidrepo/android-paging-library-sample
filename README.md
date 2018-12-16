# android-paging-library-sample
### A sample application to describe new way of paging in recyclerView using paging library. 
Here is Android Paging Library sample application for you. Android Paging Library is an important component of Android Jetpack.

Many app displays a large set of data to the users, for example consider the Amazon App, it shows you the list of products, and the app has too many products, but it do not loads all the product at once, it shows you some products, as soon as you reaches the last item of the product list it loads more products. This is called paging. 

<img src="https://github.com/rkandoroidrepo/android-paging-library-sample/blob/master/app/src/main/res/raw/gif.gif?raw=true" width="300" height="500" />

#### Why use Paging?

Assume you have more than 1000 items for your list that you are fetching from a backend server. Here are the cons if you are fetching everything at once.

#### Disadvantages of not using Paging

User do not see all the items at once, but you are fetching everything at once, so it will consume more bandwidth uselessly.
Creating a large List at once uses more system resources resulting in a lagging app and bad user experience.
Advantages of using Paging

You will only load a small chunk from your large data set, it will consume less bandwidth.
The app will use less resources resulting in a smooth app and nice user experience.
Android Paging Library

Android paging library is a component of android jetpack. Remember it is not available by default so we need to add it. It helps us to load data gradually and gracefully in our application’s RecyclerView.

In this Android Paging Library Tutorial I will not tell you about the theoretical things about the library. Because to know this thing you can visit the official documentation. Instead of discussing the theoretical bla bla bla we will learn implementing the library in our application, as the official documentation might confuse you.

#### Pre-Requisites

Before moving ahead, you should go through these tutorials first, as we are going to use these things.

Using Retrofit in Android: Complete Retrofit Playlist from Scratch.
We will use the Retrofit Library to fetch the data from backend API. The above tutorial discusses about Retrofit and Building API from scratch. So you can check the sereis.
RecyclerView: RecyclerView Tutorial.
We will load the items in a RecyclerView after fetching it from server.
Android ViewModel: Android ViewModel Tutorial.
This is another component from Android Jetpack. It helps us to store UI related data in a more efficient way.
