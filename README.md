# University & Bank Management System

A full-stack web application built with Spring Boot (backend) and Angular (frontend) that provides comprehensive management systems for:

1. **Student-Course Management Module**
2. **Bank Account Transaction Management Module**

## 🏗️ Architecture

### Backend (Spring Boot)

- **Framework**: Spring Boot 3.5.0 with Java 21
- **Database**: MySQL with Spring Data JPA
- **Dependencies**: Lombok, Spring Web, Spring Data JPA, MySQL Connector
- **API**: RESTful APIs with CORS support

### Frontend (Angular)

- **Framework**: Angular 20 with TypeScript
- **HTTP Client**: Angular HttpClient for API communication
- **UI**: Responsive design with modern CSS styling
- **Forms**: Template-driven forms with validation

## 📋 Features

### Student-Course Management

- **Student Management**: Create, Read, Update, Delete students
- **Course Management**: Add courses to students with scores
- **Analytics**: Automatic GPA calculation and ranking system
- **Search**: Search students by name
- **Ranking System**: Vietnamese academic ranking (Xuất sắc, Giỏi, Khá, Trung bình, Yếu)

### Bank Account Management

- **Account Management**: Create, Read, Update, Delete bank accounts
- **Transaction Processing**: Deposit and withdrawal operations
- **Transaction History**: View complete transaction history
- **Balance Management**: Real-time balance updates
- **Data Integrity**: Transaction validation and error handling

## 🚀 Getting Started

### Prerequisites

- **Java 21+** (for Spring Boot backend)
- **Node.js 18+** and **npm** (for Angular frontend)
- **MySQL 8.0+** (for database)
- **Git** (for version control)

### Database Setup

1. **Install MySQL** and create a database:

   ```sql
   CREATE DATABASE AccountBank;
   ```

2. **Update database credentials** in `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### Backend Setup (Spring Boot)

1. **Navigate to backend directory**:

   ```bash
   cd backend
   ```

2. **Run the Spring Boot application**:

   ```bash
   # Using Gradle Wrapper (recommended)
   ./gradlew bootRun

   # Or if you have Gradle installed
   gradle bootRun
   ```

3. **Verify backend is running**:
   - Backend will start on `http://localhost:8080`
   - Check health: `http://localhost:8080/api/students`

### Frontend Setup (Angular)

1. **Navigate to frontend directory**:

   ```bash
   cd frontend
   ```

2. **Install dependencies**:

   ```bash
   npm install
   ```

3. **Start the development server**:

   ```bash
   npm start
   ```

4. **Access the application**:
   - Frontend will start on `http://localhost:4200`
   - Application will automatically open in your browser

## 📊 Database Schema

### Student Module

```sql
-- Students table
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    dob DATE
);

-- Courses table
CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    score FLOAT,
    student_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);
```

### Bank Module

```sql
-- Accounts table
CREATE TABLE accounts (
    AccID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerName VARCHAR(255) NOT NULL,
    Email VARCHAR(255),
    Phone VARCHAR(20),
    Balance DECIMAL(15,2) DEFAULT 0
);

-- Transaction Details table
CREATE TABLE transaction_details (
    TransID INT AUTO_INCREMENT PRIMARY KEY,
    AccID INT NOT NULL,
    TransMoney DECIMAL(15,2) NOT NULL,
    TransType INT NOT NULL, -- 1=Deposit, 2=Withdraw
    DateOfTrans DATE NOT NULL,
    FOREIGN KEY (AccID) REFERENCES accounts(AccID) ON DELETE CASCADE
);
```

## 🔌 API Endpoints

### Student Management APIs

```
GET    /api/students              - Get all students
GET    /api/students/{id}         - Get student by ID
POST   /api/students              - Create new student
PUT    /api/students/{id}         - Update student
DELETE /api/students/{id}         - Delete student
GET    /api/students/search?name= - Search students by name
GET    /api/students/{id}/courses - Get student's courses
```

### Course Management APIs

```
GET    /api/courses               - Get all courses
GET    /api/courses/{id}          - Get course by ID
POST   /api/courses               - Create new course
PUT    /api/courses/{id}          - Update course
DELETE /api/courses/{id}          - Delete course
GET    /api/courses/search?name=  - Search courses by name
GET    /api/courses/score/{score} - Get courses by score
```

### Bank Account APIs

```
GET    /api/accounts              - Get all accounts
GET    /api/accounts/{id}         - Get account by ID
POST   /api/accounts              - Create new account
PUT    /api/accounts/{id}         - Update account
DELETE /api/accounts/{id}         - Delete account
```

### Transaction APIs

```
GET    /api/transactions                    - Get all transactions
GET    /api/transactions/{id}               - Get transaction by ID
GET    /api/transactions/account/{accID}    - Get transactions by account
POST   /api/transactions                    - Perform transaction
POST   /api/transactions/deposit            - Deposit money
POST   /api/transactions/withdraw           - Withdraw money
```

## 🎯 Usage Guide

### Student-Course Management

1. **Adding Students**:

   - Click "Add Student" button
   - Fill in required information (Name is mandatory)
   - Submit the form

2. **Managing Courses**:

   - Click "Courses" button for a student
   - Add new courses with scores (0-10 scale)
   - View automatic GPA calculation and ranking

3. **Search and Filter**:
   - Use the search bar to find students by name
   - Results update in real-time

### Bank Account Management

1. **Creating Accounts**:

   - Click "Add Account" button
   - Fill in customer information
   - Set initial balance (optional)

2. **Processing Transactions**:

   - Click "Transaction" button for an account
   - Select Deposit or Withdraw
   - Enter amount and submit

3. **Viewing History**:
   - Click "History" button to view all transactions
   - See transaction type, amount, and date

## 🛠️ Development

### Project Structure

```
project-1/
├── backend/                    # Spring Boot application
│   ├── src/main/java/com/main/app/
│   │   ├── controller/         # REST controllers
│   │   ├── service/           # Business logic
│   │   ├── repository/        # Data access layer
│   │   ├── entity/           # JPA entities
│   │   ├── dto/              # Data transfer objects
│   │   └── config/           # Configuration classes
│   └── src/main/resources/
│       └── application.properties
├── frontend/                   # Angular application
│   ├── src/app/
│   │   ├── app.ts            # Main component
│   │   ├── app.html          # Main template
│   │   └── app.css           # Styles
│   └── package.json
└── README.md
```

### Key Technologies

**Backend**:

- Spring Boot 3.5.0
- Spring Data JPA
- MySQL Connector/J
- Lombok
- Spring Web

**Frontend**:

- Angular 20
- TypeScript 5.8
- RxJS 7.8
- Angular Forms

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**:

   - Ensure MySQL is running
   - Check database credentials in `application.properties`
   - Verify database `AccountBank` exists

2. **CORS Issues**:

   - CORS is configured for `http://localhost:4200`
   - Check `CorsConfig.java` if running on different port

3. **Frontend Build Errors**:

   - Run `npm install` to ensure dependencies are installed
   - Check Node.js version (requires 18+)

4. **Backend Startup Issues**:
   - Ensure Java 21+ is installed
   - Check if port 8080 is available

### Development Tips

- Use browser developer tools to debug API calls
- Check backend logs for detailed error messages
- Use MySQL Workbench or similar tools to inspect database
- Enable Spring Boot DevTools for hot reloading

## 📝 License

This project is created for educational purposes as part of a university assignment.

## 👥 Contributors

- Full-stack implementation with Spring Boot and Angular
- RESTful API design and implementation
- Modern responsive UI/UX design
- Comprehensive data validation and error handling

---

**Happy Coding! 🚀**
