# Amz E-Commerce

A simple e-commerce Android application that displays a list of products from Amazon and allows users to view product details.

## Features

* Displays a list of Amazon products with title, price, and image.
* Allows users to click on a product to view its details.
* Uses Jetpack Compose for the user interface.
* Uses Koin for dependency injection.
* Uses Coil for image loading.
* Uses Coroutines for asynchronous operations.
* Uses Flow for state management.
* Uses ViewModel to separate UI logic.
* Uses Room for data persistence (optional).
* Uses Retrofit for network communication (optional).

## Architecture

The application follows the MVVM (Model-View-ViewModel) pattern.

* **Model:** Represents the application data, such as products and categories.
* **View:** Represents the user interface, built with Jetpack Compose.
* **ViewModel:** Acts as an intermediary between the View and the Model, providing data to the View and handling events from the View.

## Dependencies

The application uses the following libraries:

* Jetpack Compose
* Koin
* Coil
* Coroutines
* Flow
* ViewModel
* Retrofit

## How to run

1. Clone the repository.
2. Open the project in Android Studio.
3. Run the application on a device or emulator.

## Screenshots
Product List Screen           |  Product Detail Screen
:-------------------------:|:-------------------------:
![Screenshot_20241105_113327](https://github.com/user-attachments/assets/cb5e0398-e36f-4a4d-a51e-819934bc86ea) | ![Screenshot_20241105_113345](https://github.com/user-attachments/assets/f2db390e-f0cb-4602-a08f-ddd62c329326)

