# OompaLoompas
A custom Android app made as a technical preview for Napptilus.

## Index
* [Pre-requisites](https://github.com/Wikijito7/OompaLoompas#pre-requisites)
* [Dependencies](https://github.com/Wikijito7/OompaLoompas#dependencies)
* [How to run it](https://github.com/Wikijito7/OompaLoompas#how-to-run-it)
* [How it works](https://github.com/Wikijito7/OompaLoompas#how-it-works)

## Pre-requisites
* Android 5.0+.
* A little bit of time.
* Optional: Coffee to drink while executing the app.


## Dependencies
This app uses some dependencies in order to work. Apart from the basic suite of Google dependencies,such as Material, fragments, etc., this app uses:

* **Kotlin**: Kotlin is a modern language that is used as an alternative to Java. Kotlin is null safe and it has a lot of benefits over Java. It is recomended to use it instead of Java in order to create new Android apps. This app uses Kotlin as the main language.
* **LiveData** and **ViewModel**: as it name says, those dependencies are for  LiveData and ViewModel purpose. They're here to make a more robust architecture and to follow the [Clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) pattern.
* **Navigation**: this dependency, as last case implied, does what it name says. In order to use the last navigation component on the Jetpack suite, we need this dependency. Also, we've got _safe-args_, so we can use the human-friendly _NavDirections_ interface and use NavGraphs' arguments safely.
* **Retrofit2**: this dependency is used to make the HTTP requests to the API. It's a very popular dependency to handle this type of request and it's very easy to use. Also, in order to parse the JSON response, we use **Gson**.
* **Glide**: this dependency is used to load the images from the API. It's very lightweight and easy to use, taking out the _boilerplate_ part from other alternatives such as Fresco.
* **Dagger Hilt**: this dependency is used to inject the dependencies into the classes. It's a very popular dependency to handle this type of task and it's very easy to use. Also, it's the official dependency for the new Android Hilt library and it's the recommended one to use as is part of Android Jetpack.

## How to run it
To run OompaLoompa's app, firstly you have to download the APK from the [releases](https://github.com/Wikijito7/OompaLoompas/releases) section. Then, you have to install it on your Android device, giving permissions to install from unknown sources. After that, you can run the app and enjoy it.

## How it works
Oompa Loompas is an HR like app made to make the Chocolate Factory HQ life easier. The app has three screens: the main screen, where we can see all the employees and some of their important information divided on 25 employees per page. The detail screen, where we can see all the information about the employee. And the filter screen, where we can filter the employees by profession, by gender or both.

### Functions
* **Main screen**: as we mentioned before, this screen shows all the employees divided on 25 employees per page. We can see the employee's name, age, profession, gender and email. Also, we can see the employee's image. We can navigate between pages using the pagination buttons at the bottom of the screen. We can also navigate to the detail screen by clicking on the employee's row.
* **Detail**: This screen shows all the employee information. It is a _BottomSheetDialog_, which is a dialog that comes from the bottom to the top of the screen.
* **Filter**: In this screen, we can select filters to filter the employees of the main screen. This filter is applied to all pages. In order to apply it, you have to select the filter and click on the _Accept_ button and then on the _Apply filters_. To remove it, do the same but selecting the _Nothing selected_ option.


### Error handling
This app has error handling built in, it works by detecting and handling the errors that can occur in the app. The errors are handled in the following way:

* If the app detects that there is no internet connection, or some error has ocurred while receiving the data, it will show a dialog with the error message and an _Accept_ button, which closes the dialog, _Retry_ button, which tries to make the connection again. If the user clicks on the _Retry_ button, the app will try to connect again. If the connection is successful, the app will continue working as normal. If not, it will show the same dialog again. This error handling can be scaled up, as it is defined in a single class called _ErrorType_, and captured on the _ErrorManager_ wrapped, so it can be used in any screen just by listening to LiveData's observer with an _AsyncResult_ object.

* When a filter is applied, if there are no results, it will show a message on the screen saying that there are no results and a _Remove filters_ button which clears filters when clicked.

### Other features
This app has other features that we've haven't mentioned before, such as:
* **Pagination**: the app has pagination implemented, so it can show 25 employees per page. This is done because the API only shows 25 results per page, so we have to make a request for each page. This is done by using the _page_ parameter in the request.
* **Filter warning**: on the Main screen, when a filter is applied, a warning message is shown on the screen, saying that the results are filtered. This is done by using a _TextView_ on the top left of the screen.
* **Language support**: by default, this app supports Spanish and English. The main language is English, and other languages can be added easily by adding a new _strings.xml_ file on the _values_ folder. The rest will be handled by the framework automatically.
* **Dark mode**: this app supports dark mode as well as light mode. The app will automatically select the theme based on the user's global theme. If the user has dark mode enabled, the app will use the dark theme. If not, it will use the light theme.

## Known bugs
* Have you found one? Create a ticket [here](https://github.com/Wikijito7/OompaLoompas/issues).

## License
The API this App uses is owned by [Napptilus](https://napptilus.com/), so it can be used only for this technical previews purpose. The rest of the code is free to use, a mention to the author would be appreciated, but it isn't necesary.