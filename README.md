# Revolt API Wrapper

An intuitive and lightweight Kotlin wrapper for the Revolt API.

## Table of Contents

- [Installation](#installation)
- [Features](#features)
- [Usage](#usage)
  - [Authentication](#authentication)
  - [Fetching Data](#fetching-data)
- [Error Handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)

## Installation

```kotlin
// Add installation instructions. 
// For example, if it's a Gradle dependency:
implementation 'ge.ted3x:revolt-kotlin:1.0.0'
```

## Features

- Seamless integration with the Revolt platform.
- Supports all major authentication methods including MFA.
- Efficient error handling with comprehensive messages.
- Lazy initialization for better performance.
- Modular design for easy maintenance and updates.

## Usage

### Authentication

```kotlin
// Sample code to illustrate authentication
val api = RevoltApi(API_KEY)
api.auth.login("username", "password")
```

### Fetching Data

```kotlin
// Sample code to illustrate how to fetch data using the wrapper
val user = api.auth.account.fetchSelf()
```

## Error Handling

Our wrapper provides intuitive error messages to ease the debugging process. In case of an error, the library will throw a `RevoltApiException` with a detailed message.

```kotlin
try {
    val user = api.auth.account.fetchSelf()
} catch (e: RevoltApiException) {
    println(e.message)
}
```

## Contributing

We welcome contributions! Please see our [CONTRIBUTING.md](./CONTRIBUTING.md) file for details.

## License

This project is licensed under the MIT License. See the [LICENSE.md](./LICENSE.md) file for details.
