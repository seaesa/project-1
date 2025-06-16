# 🚀 Hướng Dẫn Cài Đặt và Chạy Dự Án

## 📋 Tổng Quan

Đây là hệ thống quản lý tích hợp gồm 2 module:

- **Module Quản lý Sinh viên - Môn học**
- **Module Quản lý Tài khoản Ngân hàng và Giao dịch**

Hệ thống được xây dựng với:

- **Backend**: Spring Boot 3.5.0 + MySQL
- **Frontend**: Angular 20 + TypeScript

## 🔧 Yêu Cầu Hệ Thống

### Phần Mềm Cần Thiết

1. **Java Development Kit (JDK) 21+**

   - Tải từ: https://adoptium.net/
   - Kiểm tra: `java -version`

2. **Node.js 18+ và npm**

   - Tải từ: https://nodejs.org/
   - Kiểm tra: `node -v` và `npm -v`

3. **MySQL 8.0+**

   - Tải từ: https://dev.mysql.com/downloads/mysql/
   - Hoặc sử dụng XAMPP: https://www.apachefriends.org/

4. **IDE/Editor (Khuyến nghị)**
   - IntelliJ IDEA (cho backend)
   - Visual Studio Code (cho frontend)

## 🗄️ Thiết Lập Cơ Sở Dữ Liệu

### Bước 1: Cài Đặt MySQL

1. **Cài đặt MySQL Server**
2. **Khởi động MySQL Service**
3. **Đăng nhập MySQL:**
   ```bash
   mysql -u root -p
   ```

### Bước 2: Tạo Database

```sql
-- Tạo database
CREATE DATABASE AccountBank;

-- Sử dụng database
USE AccountBank;

-- Kiểm tra database đã tạo
SHOW DATABASES;
```

### Bước 3: Tạo User Database (Tùy chọn)

```sql
-- Tạo user cho ứng dụng
CREATE USER 'bankapp'@'localhost' IDENTIFIED BY 'password123';

-- Cấp quyền cho user
GRANT ALL PRIVILEGES ON AccountBank.* TO 'bankapp'@'localhost';

-- Áp dụng thay đổi
FLUSH PRIVILEGES;
```

## 🏗️ Cài Đặt Backend (Spring Boot)

### Bước 1: Điều Hướng Đến Thư Mục Backend

```bash
cd backend
```

### Bước 2: Cấu Hình Database

Mở file `src/main/resources/application.properties` và cập nhật:

```properties
# Cấu hình Database
spring.datasource.url=jdbc:mysql://localhost:3306/AccountBank
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Cấu hình JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Cấu hình Server
server.port=8080

# Cấu hình CORS
cors.allowed-origins=http://localhost:4200
cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
cors.allowed-headers=*
```

### Bước 3: Cài Đặt Dependencies

```bash
# Sử dụng Gradle Wrapper (Khuyến nghị)
./gradlew build

# Hoặc nếu đã cài Gradle
gradle build
```

### Bước 4: Chạy Ứng Dụng Backend

```bash
# Sử dụng Gradle Wrapper
./gradlew bootRun

# Hoặc sử dụng Gradle
gradle bootRun

# Hoặc từ IDE
# Click chuột phải vào AppApplication.java -> Run
```

### Bước 5: Kiểm Tra Backend

- Mở trình duyệt: http://localhost:8080/api/students
- Hoặc test với Postman/REST Client

## 🎨 Cài Đặt Frontend (Angular)

### Bước 1: Điều Hướng Đến Thư Mục Frontend

```bash
cd frontend
```

### Bước 2: Cài Đặt Dependencies

```bash
# Cài đặt Node modules
npm install

# Hoặc sử dụng yarn
yarn install
```

### Bước 3: Kiểm Tra Angular CLI

```bash
# Kiểm tra Angular CLI
ng version

# Nếu chưa có, cài đặt global
npm install -g @angular/cli
```

### Bước 4: Chạy Development Server

```bash
# Khởi động server development
npm start

# Hoặc
ng serve

# Hoặc chạy trên port tùy chỉnh
ng serve --port 4200
```

### Bước 5: Kiểm Tra Frontend

- Ứng dụng sẽ mở tự động tại: http://localhost:4200
- Hoặc mở trình duyệt và truy cập địa chỉ trên

## 🔍 Kiểm Tra Hoạt Động

### Test Backend APIs

Sử dụng file `api.http` để test các endpoint:

1. **Test sinh viên:**

   ```http
   GET http://localhost:8080/api/students
   ```

2. **Test tài khoản ngân hàng:**
   ```http
   GET http://localhost:8080/api/accounts
   ```

### Test Frontend

1. Truy cập http://localhost:4200
2. Chuyển đổi giữa 2 module bằng navigation
3. Thử tạo sinh viên mới và tài khoản ngân hàng

## 🛠️ Xử Lý Sự Cố Thường Gặp

### Lỗi Database Connection

**Triệu chứng:** `Communications link failure`

**Giải pháp:**

1. Kiểm tra MySQL đang chạy:

   ```bash
   # Windows
   net start mysql

   # Linux/Mac
   sudo systemctl start mysql
   ```

2. Kiểm tra port MySQL (mặc định 3306)
3. Xác minh username/password trong `application.properties`

### Lỗi Port Đã Được Sử Dụng

**Triệu chứng:** `Port 8080 already in use`

**Giải pháp:**

1. Tìm process đang sử dụng port:

   ```bash
   # Windows
   netstat -ano | findstr :8080

   # Linux/Mac
   lsof -i :8080
   ```

2. Kill process hoặc đổi port trong `application.properties`

### Lỗi CORS

**Triệu chứng:** `Access to XMLHttpRequest blocked by CORS policy`

**Giải pháp:**

1. Kiểm tra `CorsConfig.java`
2. Đảm bảo frontend chạy trên port 4200
3. Restart backend sau khi thay đổi cấu hình

### Lỗi npm install

**Triệu chứng:** `npm ERR! code ERESOLVE`

**Giải pháp:**

```bash
# Xóa node_modules và package-lock.json
rm -rf node_modules package-lock.json

# Cài đặt lại
npm install --legacy-peer-deps

# Hoặc sử dụng yarn
yarn install
```

### Lỗi Java Version

**Triệu chứng:** `Unsupported class file major version`

**Giải pháp:**

1. Kiểm tra Java version: `java -version`
2. Đảm bảo đang sử dụng Java 21+
3. Cập nhật JAVA_HOME environment variable

## 📁 Cấu Trúc Thư Mục Dự Án

```
project-1/
├── backend/                           # Ứng dụng Spring Boot
│   ├── src/main/java/com/main/app/   # Source code
│   │   ├── controller/               # REST Controllers
│   │   ├── service/                  # Business Logic
│   │   ├── repository/               # Data Access Layer
│   │   ├── entity/                   # JPA Entities
│   │   ├── dto/                      # Data Transfer Objects
│   │   └── config/                   # Configuration Classes
│   ├── src/main/resources/           # Resources
│   │   └── application.properties    # Cấu hình ứng dụng
│   ├── build.gradle                  # Gradle build file
│   └── gradlew                       # Gradle wrapper
├── frontend/                         # Ứng dụng Angular
│   ├── src/app/                      # Source code Angular
│   │   ├── app.ts                    # Component chính
│   │   ├── app.html                  # Template chính
│   │   └── app.css                   # Styles
│   ├── package.json                  # Dependencies Node.js
│   └── angular.json                  # Cấu hình Angular
├── api.http                          # Test API endpoints
├── installation.md                   # Hướng dẫn cài đặt
├── flow.md                          # Business flow
└── README.md                        # Tài liệu tổng quan
```

## 🚀 Chạy Dự Án Trong Môi Trường Production

### Build Frontend

```bash
cd frontend
npm run build

# File build sẽ ở thư mục dist/
```

### Build Backend

```bash
cd backend
./gradlew build

# File JAR sẽ ở thư mục build/libs/
```

### Deploy

```bash
# Chạy backend
java -jar backend/build/libs/app-0.0.1-SNAPSHOT.jar

# Serve frontend (cần web server như Nginx)
# Copy nội dung dist/ vào web server
```

## 🔐 Bảo Mật

### Cấu Hình Database Production

- Thay đổi password mặc định
- Sử dụng SSL connection
- Giới hạn quyền user database

### Cấu Hình CORS Production

- Chỉ định domain cụ thể thay vì "\*"
- Sử dụng HTTPS

## 📞 Hỗ Trợ

Nếu gặp vấn đề:

1. Kiểm tra logs trong terminal
2. Xem browser developer console (F12)
3. Kiểm tra các port đang sử dụng
4. Đảm bảo tất cả services đều đang chạy

---

**Chúc bạn cài đặt thành công! 🎉**
