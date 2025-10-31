# 🏧 ATM Simulation

This is a simple **ATM Simulation** application built using **Java Swing** for the graphical user interface and **MongoDB** as the backend database.  
It simulates core ATM functionalities such as user authentication, deposits, withdrawals, and transaction tracking.

---

## 🚀 Features

- 🔐 **User Authentication** — Secure login using Card Number and PIN  
- 💰 **Check Balance** — View the current account balance  
- 💵 **Cash Deposit** — Deposit money into the account  
- 🏧 **Cash Withdrawal** — Withdraw cash (validated against available balance)  
- 🔄 **PIN Change** — Change the account PIN securely  
- 📜 **Transaction History** — View recent transactions (mini-statement)  
- 🚪 **Exit** — Logout safely from the application  

---

## 🛠️ Technologies Used

| Component | Technology |
|------------|-------------|
| **Frontend** | Java Swing |
| **Backend** | Java |
| **Database** | MongoDB (via MongoDB Java Driver) |

---

## 🗂️ Database Structure

**Database Name:** `atmSimulation`

**Collections:**
- `accounts`
- `balance`
- `transactions`

---

## ⚙️ Prerequisites

Before running the project, ensure you have the following installed:

- ☕ **Java Development Kit (JDK)** 8 or higher  
- 🧩 **An IDE** (e.g., IntelliJ IDEA, Eclipse, or NetBeans)  
- 🗄️ **MongoDB Server** (local or cloud e.g., MongoDB Atlas)  
- 📦 **MongoDB Java Driver** library added to your project  

---

## 🧾 How to Run

1. **Clone the repository:**
   ```bash
   git clone [your-repository-url]
