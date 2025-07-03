# 💊 **MediApp – Medical Shop Android App**

<p align="center">
  <img src="https://img.icons8.com/color/96/000000/android-os.png" width="60" alt="Android" />
  <img src="https://img.icons8.com/color/96/000000/kotlin.png" width="60" alt="Kotlin" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.9%2B-purple?logo=kotlin" alt="Kotlin" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-UI-blue?logo=android" alt="Jetpack Compose" />
  <img src="https://img.shields.io/badge/Hilt-DI-green?logo=dagger" alt="Hilt" />
  <img src="https://img.shields.io/badge/Retrofit-Networking-orange?logo=retrofit" alt="Retrofit" />
  <img src="https://img.shields.io/badge/Room-Database-blueviolet?logo=sqlite" alt="Room" />
  <img src="https://img.shields.io/badge/Coroutines-Async-lightblue?logo=kotlin" alt="Coroutines" />
  <img src="https://img.shields.io/badge/DataStore-Persistence-yellowgreen?logo=android" alt="DataStore" />
  <img src="https://img.shields.io/badge/MVVM-Architecture-ff69b4?logo=android" alt="MVVM" />
  <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="MIT License" />
</p>

---

> **MediApp** is a beautiful, modern, and modular Android app for medical shop management.  
> Built with the latest Android tech stack, it lets users register, log in, browse products, and place orders, all powered by a robust RESTful API.

---

## ✨ **Features**

- 📝 **User Registration & Login:** Secure sign-up and authentication
- 🛒 **Product Browsing:** View all available products with details and stock
- 📦 **Order Placement:** Add products to cart and place orders
- ✅ **Approval Workflow:** Users must be approved before ordering
- 🔒 **Persistent Login:** User ID stored securely with DataStore
- 🏗️ **MVVM Architecture:** Clean separation of concerns
- 💉 **Dependency Injection:** Powered by Hilt for testability and scalability
- ⚡ **Coroutines:** Fast, async networking and data operations
- 🎨 **Modern UI:** Built with Jetpack Compose and Material 3

---

## 🗂️ **Project Structure**

```plaintext
MediApp/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/mediapp/
│           │   ├── api/           # Retrofit API interfaces & builder
│           │   ├── common/        # Result wrappers
│           │   ├── di/            # Hilt modules
│           │   ├── models/        # Data models (responses, etc.)
│           │   ├── prefData/      # DataStore preferences
│           │   ├── repo/          # Repository (network/data logic)
│           │   ├── screens/       # UI screens (Compose)
│           │   │   └── nav/       # Navigation & routes
│           │   └── viewModel/     # ViewModels (business logic)
│           └── res/               # Resources (themes, etc.)
```

---

## 🚀 **Quick Start**

### 1. **Clone the Repository**

```sh
git clone https://github.com/yourusername/MediApp.git
cd MediApp
```

### 2. **Open in Android Studio**

- Open the project folder in **Android Studio** (Giraffe or newer recommended).

### 3. **Build & Run**

- Connect an Android device or start an emulator.
- Click **Run** ▶️ or use `Shift+F10`.

---

## 🧭 **App Flow & Screens**

| Screen         | Route                | Description                      |
|----------------|---------------------|----------------------------------|
| 📝 Sign Up     | `/signup_screen`    | Register a new user              |
| 🔑 Login       | `/login_screen`     | Authenticate and save session    |
| ⏳ Waiting     | `/waiting_screen`   | Await admin approval             |
| 🏠 Main        | `/main_screen`      | Dashboard after approval         |
| 🗂️ Tab         | `/tab_screen`       | Switch between login/sign-up     |
| ➕ Add Order   | `/add_order_screen` | Browse products & place orders   |
| 🏡 Home        | `/home_screen`      | Home (future extension)          |

---

## 🌐 **API Endpoints Used**

| Endpoint                | Method | Description                |
|-------------------------|--------|----------------------------|
| `/createUser`           | POST   | Register user              |
| `/login`                | POST   | User login                 |
| `/getSpecificUser`      | POST   | Get user details/approval  |
| `/getAllProduct`        | GET    | List products              |
| `/addOrderDetails`      | POST   | Place order                |

> **Backend:** [https://harsh01815.pythonanywhere.com/](https://harsh01815.pythonanywhere.com/)

---

## 🧩 **Key Modules & Technologies**

| Module/Tech           | Purpose/Role                                      |
|-----------------------|---------------------------------------------------|
| **Kotlin**            | Modern, concise programming language              |
| **Jetpack Compose**   | Declarative UI toolkit for Android                |
| **Hilt**              | Dependency injection for Android                  |
| **Retrofit**          | Type-safe HTTP client for API calls               |
| **Coroutines**        | Asynchronous programming                          |
| **DataStore**         | Modern data persistence for user session          |
| **MVVM**              | Clean architecture for separation of concerns     |
| **Material 3**        | Latest Material Design for beautiful UI           |

---

## 🛡️ **Best Practices**

- **MVVM:** ViewModels manage UI state, repositories handle data.
- **Hilt:** All dependencies injected for testability.
- **DataStore:** User session stored securely.
- **Error Handling:** All network calls wrapped in `Results` sealed class.
- **Navigation:** Compose Navigation for screen transitions.
- **Material 3:** Consistent, modern UI/UX.

---

## 🤝 **Contributing**

Contributions are welcome!  
1. **Fork** the repository  
2. **Create a branch:** `git checkout -b feature/your-feature`  
3. **Commit your changes:** `git commit -am 'Add new feature'`  
4. **Push to branch:** `git push origin feature/your-feature`  
5. **Open a Pull Request**

---

## 📄 **License**

This project is licensed under the [MIT License](LICENSE).

---

## ℹ️ **About**

MediApp is a showcase of modern Android development with Compose, MVVM, and Hilt, designed for medical shop management.  
It demonstrates best practices in UI, architecture, and API integration.

---

## 📞 **Contact**

For questions, suggestions, or support, please open an issue or contact [harsh.sshr@gmail.com](mailto:harsh.sshr@gmail.com).

---

<p align="center">
  <img src="https://img.icons8.com/color/96/000000/android-os.png" width="60" alt="Android" />
  <img src="https://img.icons8.com/color/96/000000/kotlin.png" width="60" alt="Kotlin" />
  <img src="https://img.icons8.com/color/96/000000/google-logo.png" width="60" alt="Google" />
  <br/>
  <b>Made with ❤️ using Kotlin & Jetpack Compose</b>
</p>
