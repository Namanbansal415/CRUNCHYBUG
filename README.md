
# 🚗 Car Rental Management System

A complete GUI-based Car Rental Management System built using **Java Swing**, **MySQL**, and **NetBeans**. This system allows users to register, login, manage car rentals, and view real-time statistics in a modern and interactive interface.

---

## 🧰 Features

- ✅ User Registration & Login
- ✅ MySQL Database Connectivity
- ✅ Car Management (Add, Update, Delete Cars)
- ✅ Customer Management
- ✅ Rent/Return Car Functionality
- ✅ Dashboard with Live Stats:
  - Total Cars
  - Rented Cars
  - Available Cars
  - Total Customers
- ✅ Sidebar Navigation Panel
- ✅ Light/Dark Theme Toggle *(optional)*
- ✅ Background Images and Icons for Better UI
- ✅ Search & Filter Cars and Customers

---

## 🛠️ Tech Stack

| Tech             | Purpose                      |
|------------------|------------------------------|
| Java (Swing)     | Frontend GUI                 |
| NetBeans IDE     | Development Environment      |
| MySQL            | Database                     |
| JDBC             | Java-MySQL Connection        |

---

## 🗂️ Project Structure

```
CarRentalSystem/
├── src/
│   ├── db/
│   │   └── DBConnection.java
│   ├── gui/
│   │   ├── LoginForm.java
│   │   ├── RegisterForm.java
│   │   ├── MainMenu.java
│   │   
│   ├── models/
│   │   ├── Car.java
│   │   ├── Customer.java
│   │   └── Rental.java
│   └── images/
│       ├── car_bg.jpg
│       ├── car_icon.png
│       └── customer_icon.png
```

---

## 🧑‍💻 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/car-rental-system.git
cd car-rental-system
```

### 2. Open in NetBeans

- Open NetBeans
- Go to `File` → `Open Project` → Select this folder

### 3. Setup MySQL Database

1. Create a database named:

```sql
CREATE DATABASE carrental;
```

2. Create required tables:

```sql
USE carrental;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(100),
    make VARCHAR(100),
    year INT,
    status VARCHAR(20),
    image_path VARCHAR(255)
);

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    contact VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE rentals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    car_id INT,
    customer_id INT,
    rent_date DATE,
    return_date DATE,
    FOREIGN KEY(car_id) REFERENCES cars(id),
    FOREIGN KEY(customer_id) REFERENCES customers(id)
);
```

3. Add a test user:

```sql
INSERT INTO users (username, password) VALUES ('admin', 'admin123');
```

### 4. Run the Project

- Right-click on `LoginForm.java`
- Click `Run File`

---

## 🖼️ Screenshots

> (Add your UI screenshots here)

---

## 🙌 Contribution

Pull requests are welcome. Feel free to open issues for suggestions or bugs.

---

## 📜 License

This project is for educational use. Modify it freely for learning or academic submission.

---

## 📧 Contact

**Developer:** Saad Khan  
**Email:** your-email@example.com

---
