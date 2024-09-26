# Tinder-like Swipeable Cards App

This project is a Tinder-like swipeable cards application built using Compose Multiplatform, and various other libraries.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)

## Features

- Swipeable cards with animations
- Bottom navigation bar
- Multiple screens: Feed, Liked, Profile
- Asynchronous image loading

## Installation

### Prerequisites

- Android Studio Koala Feature Drop | 2024.1.2 Patch 1
- Kotlin 2.0.20
- Gradle

### Steps

1. Clone the repository:
    ```sh
    git clone https://github.com/huynn109/tinder-swipeable-cards.git
    cd tinder-swipeable-cards
    ```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

## Usage

### Running the App

1. Connect an Android device or start an emulator.
2. Click on the "Run" button in Android Studio.

### Project Structure

- `composeApp/src/commonMain/kotlin/com/huynn109/kmp/tinder/feature/main/MainScreen.kt`: Contains the main screen composable function.
- `gradle/libs.versions.toml`: Manages the dependencies and their versions.

### Main Components

#### MainScreen
This function sets up the main screen with a bottom navigation bar and handles navigation between different screens.

```kotlin
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    component: MainComponent,
) {
    // Implementation details...
}

```
#### SwipeCardState
This class manages the state of a swipeable card, including its position and swipe direction.
```kotlin
class SwipeCardState(
    internal val maxWidth: Float,
    internal val maxHeight: Float,
) {
    // Implementation details...
}

```

