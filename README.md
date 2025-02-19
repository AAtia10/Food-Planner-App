# FoodPlanner App

## Overview
FoodPlanner is an Android application developed using Java in Android Studio. The app helps users explore meals based on categories, countries, and ingredients. Users can save meals to their favorites or plan meals for specific days. It supports Firebase authentication, allowing users to sign up, log in, and log in with Google. A guest mode is also available, but guests cannot add items to favorites or plans.

## Features

### Authentication
- **Sign Up & Login**: Users can create an account and log in.
- **Google Sign-In**: Users can log in using their Google account.
- **Guest Mode**: Users can explore meals but cannot add items to favorites or meal plans.

### Home Screen
- **Random Meal**: Displays a randomly selected meal.
- **Categories**: Lists different meal categories.
- **Areas (Countries)**: Shows a list of meal origins.
- **Ingredients**: Displays available ingredients.
- **Meal Search**: Users can search for meals by category, country, or ingredients.

### Meal Details Screen
- **Meal Image & Description**: Displays the selected meal’s image and details.
- **Ingredients List**: Shows all ingredients required for the meal.
- **Preparation Steps**: Detailed steps to prepare the meal.
- **Video Tutorial**: A video guide for preparing the meal.
- **Add to Favorites**: Users can save the meal to their favorite list.
- **Add to Calendar**: Users can schedule meals for specific days.

### Profile Screen
- **User Info**: Displays user details.
- **Logout Option**: Users can log out from their account.

## Technologies Used
- **Android Studio**: Development environment
- **Java**: Programming language
- **Firebase Authentication**: User authentication & Google sign-in
- **Room Database**: Local storage for meals and favorites
- **Shared Preferences**: Storing user preferences
- **Firebase Realtime Database**: Data synchronization & backup

## Data Synchronization
- Users can synchronize and back up their data using Firebase to ensure their saved meals and plans are accessible across devices.

## Installation & Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/AAtia10/Food-Planner-App.git
   ```
2. Open the project in **Android Studio**.
3. Configure Firebase authentication by adding `google-services.json` to the `app` folder.
4. Build and run the application on an emulator or physical device.

## License
This project is open-source and available for modification and enhancement.

---
Developed by **Abdelrahman Atia Abdelrahman Atia**

