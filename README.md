## Doug code Challenge - Average Library + Simple App to consume library

### Average Library

The Average library is an Android Library with three main functionality:

-  Load array of integers from the API and store it in a shared preferences
-  Add an integer to the stored array
-  Get the average of all integers stored in the shared preferences

#### Architecture and libraries

The library's architecture is very simple and consist of two layers.  
The first layer is the Repository, it is responsible for fetching data from the API and persist it to the storage (SharedPrefs).
The second layer is the Functionality. This layer holds business logic and and access the repository data to execute the appropriate logic.

#### Network

Retrofit was used to build the network layer because it is a mature library and simple to implement, also is the most used network library across the Android community.
To de/serialize JSON objects was used Moshi. Moshi has adapters and converters that work well with Refrofit and Courotines and also has good performance.

#### Courotines

Coroutines were introduced to handle asynchronous calls. They are mainly used to handle the Network calls.

#### Unit Tests

Unit tests are covering the Repository and GetAverage functionality (the only that has some business logic)

### Average Test App (to consume library)

Having in mind the time constraints the Average Test App is a very simple App with just one activity to the test the Average Library, it has the following features:
- Text input and a Submit button to include new numbers to the array.
- Get average button that displays the average on a text view below the button.
- Text input validations to check the number format
