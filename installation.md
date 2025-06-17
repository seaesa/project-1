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
2. **Node.js và npm**
3. **MySQL 8.0+**
4. **IDE/Editor (Khuyến nghị)**
   - IntelliJ IDEA (cho backend)
   - Visual Studio Code (cho frontend)

## 🗄️ Thiết Lập Cơ Sở Dữ Liệu

## 🏗️ Cài Đặt Backend (Spring Boot)

### Bước 1: Điều Hướng Đến Thư Mục Backend

1. vào mysql workbench tạo 1 database tên là AccountBank

### Bước 2: Cấu Hình Database

Mở file `src/main/resources/application.properties` và cập nhật:

```properties
# Cấu hình Database
spring.datasource.url=jdbc:mysql://localhost:3306/AccountBank
spring.datasource.username=root
spring.datasource.password=your_mysql_password
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
```

### Bước 4: Chạy Development Server

```bash
# Khởi động server development
npm start
```

### Bước 5: Kiểm Tra Frontend

- Ứng dụng sẽ mở tự động tại: http://localhost:4200
- Hoặc mở trình duyệt và truy cập địa chỉ trên

## 🔍 Kiểm Tra Hoạt Động

### Test Backend APIs

Sử dụng file tải rest client và truy cập `api.http` để test các endpoint:

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
