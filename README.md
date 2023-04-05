# TodoList
3rd-Party Libraries
Used material for designing layouts and UI.

Used viewmodel-ktx for keeping the state of fragments during configuration changes.

Used StateFlow as a data holder for observing the screen's state changes.

Used retrofit2 for networking and API calls.

Used okhttp3 as a client agent and also defined interceptors.

Used moshi-kotlin as a JSON parser and also defined custom adapters.

Used glide for loading images.

Used navigation for navigating through fragments.

Used hilt for using dependency injection all over the app.

Used kotlinx-coroutines for using the concurrency design pattern and doing asynchronous jobs.

Used timber for logging within the app.

Used junit for writing unit tests.

Used truth for easy assertion in tests.

Used mockk for mocking.

Used turbine for testing flows.

Used kotlinx-coroutines-test for testing coroutines.

Used kotlinx-datetime for dealing with date and time (please check out AppRepositoryImpl for more information)

Used Kotlin-DSL for implementing Gradle configurations.

Optional features:
Night/Day mode: On HomeFragment, there is a button in the toolbar to change the theme to night and day mode. The composition of SharedPreferences and Flow has been used to implement this feature. Learn more about the implementation on AppPreferences.
Architecture:
I decided to develop this app by using MVVM clean architecture. And here are my top 3 reasons for using it:

Low coupling: Build things in a distributed manner. It's all about the separation of concerns. You can also use multiple view models inside a single view.

Test Friendly: ViewModel has no reference to the View (Activity/Fragment) and can be tested easier than MVP for instance.

Understand the app easier: Domain layer tells you everything! what this app is all about and also what it can do for the user!
