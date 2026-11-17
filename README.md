# Hospital Management Web Application

A full-stack Java EE web application designed to manage core hospital operations, including patient records, doctor allocations, appointments, admissions, and billing.

---

## 🛠️ Tech Stack & Technologies

- **Backend:** Java EE / Jakarta EE (Servlets, JSP, JDBC, DAO Pattern)
- **Frontend:** HTML5, CSS3, Bootstrap 5, JavaScript
- **Database:** MySQL
- **Application Server:** Apache Tomcat 8.5+ / 10+
- **Build Tool:** Apache Ant / NetBeans

---

## 📋 Features

- **Authentication System:** Secure login/logout for administrative staff.
- **Patient Management:** Add, update, view, and manage patient details and records.
- **Doctor Directory:** Maintain doctor details, specialties, and schedules.
- **Appointment Booking:** Schedule, monitor, and update appointments.
- **In-Patient Admissions:** Track patient admissions, room assignments, and discharge dates.
- **Billing Management:** Generate and maintain invoices and payment details.
- **Reports:** Summarized patient reports and hospital operational records.

---

## 🗄️ Database Setup

1. Start your **MySQL** server (via XAMPP, WAMP, or standalone MySQL).
2. Open **phpMyAdmin** (`http://localhost/phpmyadmin`) or your MySQL CLI.
3. Import the database script:
   - File: `hospital_management.sql`
   - This automatically creates the `hospital_management` database and required tables.
4. If your MySQL root user has a password, update database credentials in:
   `src/java/connection/DBConnection.java`

---

## 🚀 How to Run Locally

### Option 1: Using Apache NetBeans (Recommended)
1. Open **NetBeans IDE**.
2. Click **File** > **Open Project...** and select the project folder.
3. Ensure Apache Tomcat is configured under **Tools > Servers**.
4. Press **F6** (or right-click the project and select **Run**).

### Option 2: Using XAMPP (Apache Tomcat)
1. Start **MySQL** and **Tomcat** from the XAMPP Control Panel.
2. Deploy the project folder into `xampp/tomcat/webapps/HospitalManagementWeb`.
3. Open your browser and navigate to:
   ```
   http://localhost:8080/HospitalManagementWeb/
   ```

---

## 🔐 Default Credentials

- **Username:** `admin`
- **Password:** `admin123`

---

## 📁 Project Structure

```
HospitalManagementWeb/
├── nbproject/                 # NetBeans project configuration
├── src/java/
│   ├── connection/            # Database connection handler (DBConnection.java)
│   ├── controller/            # Servlets (Login, Patient, Doctor, Appointment)
│   ├── dao/                   # Data Access Objects (CRUD operations)
│   └── model/                 # Entity models (Patient, Doctor, Appointment, etc.)
├── web/
│   ├── META-INF/              # Deployment context
│   ├── WEB-INF/lib/           # JDBC Driver, JSTL dependencies
│   ├── *.jsp                  # JSP view pages
│   └── index.html             # Entry landing page
├── hospital_management.sql    # Database schema and initial data
├── build.xml                  # Ant build configuration
└── .gitignore                 # Git ignore rules
```
