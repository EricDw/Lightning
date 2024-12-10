# Lighting

Lighting is, primarily, a Personal Knowledge Manager or PKM application.

## Intentions

- Aid in the process of writing.
- Aid in the process of finance.

## Governing Principles

1. Powered by principles.
2. Put information next to where it is used.
3. Prioritize clarity.
4. Favor pragmatism.
5. Make long term choices.

# Architecture

In accordance with the overall project principles, the software architecture makes heavy use of principles and patterns.

## Governing Principles

### Powered By Principles

Principles are far more powerful then rules becuase they aid in ones thinking and are more broadly applicable.

A set of principles used in the making of this software include, but are not limited to, the following:

- SOLID
  - Single Responsibility
  - Open Closed
  - Liskov's Substitution
  - Interface Segregation
  - Dependency Inversion
- GRASP
  - General Responsibility Assignment Software Patterns
- YAGNI
  - You aint gonna need it
- DRY
  - Do not repeat yourself
- Test first
- Log often


### Put information next to where it is used.

Features have README documents at thier root.
Variables, functions, classes, interfaces, and so on must have clear documentation, in the form of KDoc for example.

### Prioritize clarity

Variables, functions, classes, interfaces, and so on must have concise and descriptive names relevant to the role they play.

### Favor Pragmatism

Pragmatic decisions are based on practical rather than theoretical considerations.

What does that mean in practice?
One exmaple is to implement a Design Pattern when the need for it naturally emerges and it would added practical values to the code.

### Make Long Term Choices


## Design System

Lighting utilizes the Material 3 Design system that ships with Compose Multiplatform.

For more information reference the [Material 3 documentation](https://m3.material.io/)

## Structure

This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here
  too.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack
channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.


## Setup

The project uses Fleet as the [IDE](Glossary.md).