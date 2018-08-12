# RetroKotlin
This is an Android demo application to show how to better **unit testing** when using Architecture Components (ViewModel, LiveData & RxJava)

You can find the related article on my Medium : https://medium.com/@Phil_Boisney/how-to-better-unit-testing-42a956e005d6

## PRESENTATION
This simple app is composed of a single screen. When this screen appears, we will fetch (Retrofit) the Github information of Jake Wharton.

## PREVIEW
<p align="center">
 <img src ="https://cdn-images-1.medium.com/max/2000/1*xx_ZtJ2sUjqkgc0kNRbwtQ.png"/>
</p>

### Architecture Components
This application implements the following concepts :
- ViewModel
- LiveData
- RxJava 2
- Dagger 2
- MockWebServer

### Libraries
* [Android Support Library][support-lib]
* [Android Architecture Components][arch]
* [Dagger 2][dagger2] for dependency injection
* [RxJava 2][rxjava2] for composing asynchronous and event-based programs using observable sequences
* [Retrofit][retrofit] for REST api communication
* [Glide][glide] for image loading
* [MockWebServer][mockwebserver] for testing HTTP clients

[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[dagger2]: https://google.github.io/dagger
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[rxjava2]: https://github.com/ReactiveX/RxJava
