🛠️ Technologies

- Backend: Spring Boot, Spring Data JPA, Lombok
- Frontend: Angular
- Database: MySQL with database name is AccountBank.

✅ Rule Description:
This project consists of two systems:

1. Student-Course Management
2. Bank Account Transaction Management

**📚 Student-Course Management Module**

➕ Requirements:

- Create Student and Course entities with the following schema:

Student Table
| Field | Type |
|---------|------------|
| id | Integer (Primary Key, Auto Increment) |
| name | String |
| address | String |
| phone | String |
| dob | Date |

Course Table
| Field | Type |
|------------|------------|
| id | Integer (Primary Key, Auto Increment) |
| name | String |
| score | Float |
| student_id | Integer (Foreign Key → Student.id) |

📋 Features:

1. Add, update, delete and search students by name.
2. For each student, list all enrolled courses in descending score order.
3. Additional analytics:

   - Count number of courses per student.
   - Calculate GPA and classify rank.
   - The formula for calculating the average score is the total score of all subjects for that student divided by the number of subjects

4. Edit or remove a course for a student.
5. Allow searching by subject or score name.

**🏦 Bank Account & Transaction Management Module**

➕ Requirements:

- Create Account and TransactionDetails entities:

Account Table
| Field | Type |
|---------------|----------|
| AccID | Integer (Primary Key, Auto Increment) |
| CustomerName | String |
| Email | String |
| Phone | String |
| Balance | Double |

TransactionDetails Table
| Field | Type |
|--------------|----------|
| TransID | Integer (Primary Key, Auto Increment) |
| AccID | Integer (Foreign Key → Account.AccID) |
| TransMoney | Double |
| TransType | Integer (1 = Deposit, 2 = Withdraw) |
| DateOfTrans | Date |

📋 Features:

1. Allow users to deposit/withdraw money.
   - Validate sufficient balance before withdrawal.
   - Update balance accordingly when Deposit or Withdraw.
2. Allow user to update account information (Account Id, Email, Phone)

💡 Notes:

**Backend**:

- Code Backend in backend folder
- First step configuration spring data jpa with mysql, cors
- Using mvc model
- Use Lombok, Use Spring Data JPA for CRUD operations
- Implement DTO mapping where needed.

**Frontend**:

- Code Frontend in frontend folder
- Use Angular to build UI with modules for both systems (Student/Course and Bank) in frontend folder
- On the home screen, there are 2 buttons to choose from, Lesson 1 and Lesson 2. Divide the 2 into 2 routes for 2 modules. With route lesson-1 for Student-Course Management Module and route lesson-2 for Bank Account & Transaction Management Module
- config .env and using axios call api
- create basic ui with pure html

**Final Step**:

- Please document both frontend and backend. Include the flow when interacting with APIs, and the business flow in both text form and using the Mermaid tool (written in Vietnamese). Save it in the learn.md file.
- present what has been done in Vietnamese
