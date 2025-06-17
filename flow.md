# 📊 Business Flow - Luồng Nghiệp Vụ Hệ Thống

## 🎯 Tổng Quan Hệ Thống

Hệ thống **Quản lý Sinh viên - Môn học và Ngân hàng** được thiết kế để phục vụ 2 nghiệp vụ chính:

1. **Module Quản lý Sinh viên - Môn học**: Quản lý thông tin sinh viên, môn học, điểm số và xếp hạng học tập
2. **Module Quản lý Ngân hàng**: Quản lý tài khoản khách hàng và các giao dịch ngân hàng

## 🏫 Module 1: Quản Lý Sinh Viên - Môn Học

### 📋 Quy Trình Nghiệp Vụ

#### 1. Quản Lý Sinh Viên

- **Thêm sinh viên mới**: Nhập thông tin cá nhân (tên, địa chỉ, số điện thoại, ngày sinh)
- **Cập nhật thông tin**: Chỉnh sửa thông tin sinh viên đã có
- **Xóa sinh viên**: Xóa sinh viên và tất cả môn học liên quan
- **Tìm kiếm**: Tìm kiếm sinh viên theo tên

#### 2. Quản Lý Môn Học

- **Thêm môn học**: Gán môn học cho sinh viên với điểm số (thang điểm 0-10)
- **Cập nhật điểm**: Chỉnh sửa điểm số môn học
- **Xóa môn học**: Xóa môn học khỏi danh sách của sinh viên

#### 3. Tính Toán Học Tập

- **Tự động tính GPA**: Tổng điểm / Số môn học
- **Xếp hạng học tập**:
  - Xuất sắc: GPA ≥ 9.0
  - Giỏi: 8.0 ≤ GPA < 9.0
  - Khá: 7.0 ≤ GPA < 8.0
  - Trung bình: 5.0 ≤ GPA < 7.0
  - Yếu: GPA < 5.0

### 🔄 Sơ Đồ Luồng Sinh Viên - Môn Học

```mermaid
graph TD
    A[Bắt đầu] --> B{Chọn chức năng}

    %% Quản lý sinh viên
    B -->|Quản lý sinh viên| C[Xem danh sách sinh viên]
    C --> D{Chọn thao tác}
    D -->|Thêm mới| E[Nhập thông tin sinh viên]
    D -->|Sửa| F[Chỉnh sửa thông tin]
    D -->|Xóa| G[Xác nhận xóa sinh viên]
    D -->|Tìm kiếm| H[Nhập từ khóa tìm kiếm]

    E --> I[Lưu sinh viên vào DB]
    F --> I
    G --> J[Xóa sinh viên và môn học]
    H --> K[Hiển thị kết quả tìm kiếm]

    %% Quản lý môn học
    D -->|Xem môn học| L[Chọn sinh viên]
    L --> M[Hiển thị danh sách môn học]
    M --> N{Chọn thao tác môn học}
    N -->|Thêm môn| O[Nhập tên môn và điểm]
    N -->|Sửa điểm| P[Cập nhật điểm số]
    N -->|Xóa môn| Q[Xóa môn học]

    O --> R[Lưu môn học]
    P --> R
    Q --> S[Xóa môn khỏi DB]

    R --> T[Tính toán GPA]
    S --> T
    T --> U[Cập nhật xếp hạng]
    U --> V[Cập nhật giao diện]

    I --> V
    J --> V
    K --> V
    V --> W[Kết thúc]

    style A fill:#e1f5fe
    style W fill:#e8f5e8
    style T fill:#fff3e0
    style U fill:#fff3e0
```

## 🏦 Module 2: Quản Lý Ngân Hàng

### 📋 Quy Trình Nghiệp Vụ

#### 1. Quản Lý Tài Khoản

- **Mở tài khoản mới**: Tạo tài khoản với thông tin khách hàng và số dư ban đầu
- **Cập nhật thông tin**: Chỉnh sửa thông tin cá nhân (không thay đổi số dư)
- **Xóa tài khoản**: Xóa tài khoản và lịch sử giao dịch

#### 2. Xử Lý Giao Dịch

- **Gửi tiền (Deposit)**: Tăng số dư tài khoản
- **Rút tiền (Withdraw)**: Giảm số dư (kiểm tra số dư đủ)
- **Ghi nhận giao dịch**: Lưu lịch sử với timestamp

#### 3. Kiểm Soát Nghiệp Vụ

- **Kiểm tra số dư**: Đảm bảo đủ tiền khi rút
- **Validation**: Kiểm tra số tiền > 0
- **Cập nhật real-time**: Số dư được cập nhật ngay lập tức

### 🔄 Sơ Đồ Luồng Ngân Hàng

```mermaid
graph TD
    A[Bắt đầu] --> B{Chọn chức năng}

    %% Quản lý tài khoản
    B -->|Quản lý tài khoản| C[Xem danh sách tài khoản]
    C --> D{Chọn thao tác}
    D -->|Mở tài khoản| E[Nhập thông tin khách hàng]
    D -->|Sửa thông tin| F[Cập nhật thông tin]
    D -->|Đóng tài khoản| G[Xác nhận đóng tài khoản]

    E --> H[Tạo tài khoản với số dư ban đầu]
    F --> I[Lưu thông tin mới]
    G --> J[Xóa tài khoản và lịch sử]

    %% Xử lý giao dịch
    D -->|Giao dịch| K[Chọn tài khoản]
    K --> L{Loại giao dịch}

    L -->|Gửi tiền| M[Nhập số tiền gửi]
    L -->|Rút tiền| N[Nhập số tiền rút]

    M --> O[Kiểm tra số tiền > 0]
    N --> P[Kiểm tra số tiền > 0]

    O -->|Hợp lệ| Q[Tăng số dư tài khoản]
    P -->|Hợp lệ| R{Kiểm tra số dư đủ?}

    R -->|Đủ| S[Giảm số dư tài khoản]
    R -->|Không đủ| T[Hiển thị lỗi: Số dư không đủ]

    Q --> U[Tạo bản ghi giao dịch]
    S --> U

    U --> V[Cập nhật số dư real-time]
    V --> W[Hiển thị thông báo thành công]

    %% Xem lịch sử
    D -->|Xem lịch sử| X[Chọn tài khoản]
    X --> Y[Hiển thị lịch sử giao dịch]
    Y --> Z[Sắp xếp theo thời gian]

    %% Error handling
    O -->|Không hợp lệ| AA[Hiển thị lỗi: Số tiền không hợp lệ]
    P -->|Không hợp lệ| AA

    H --> BB[Cập nhật giao diện]
    I --> BB
    J --> BB
    W --> BB
    T --> BB
    AA --> BB
    Z --> BB

    BB --> CC[Kết thúc]

    style A fill:#e1f5fe
    style CC fill:#e8f5e8
    style R fill:#ffebee
    style T fill:#ffcdd2
    style AA fill:#ffcdd2
```

## 🔄 Luồng Tương Tác Hệ Thống Tổng Thể

### 🎛️ Giao Diện Người Dùng

```mermaid
graph LR
    A[Người dùng truy cập hệ thống] --> B[Giao diện chính]
    B --> C{Chọn module}

    C -->|Module Sinh viên| D[Giao diện Sinh viên - Môn học]
    C -->|Module Ngân hàng| E[Giao diện Ngân hàng]

    D --> F[Quản lý sinh viên]
    D --> G[Quản lý môn học]
    D --> H[Xem thống kê GPA]

    E --> I[Quản lý tài khoản]
    E --> J[Thực hiện giao dịch]
    E --> K[Xem lịch sử giao dịch]

    F --> L[API Backend]
    G --> L
    H --> L
    I --> L
    J --> L
    K --> L

    L --> M[(Database MySQL)]

    style A fill:#e3f2fd
    style B fill:#f3e5f5
    style L fill:#fff8e1
    style M fill:#e8f5e8
```

## 🏗️ Kiến Trúc Hệ Thống

### 📡 Luồng Dữ Liệu API

```mermaid
sequenceDiagram
    participant U as User (Angular)
    participant C as Controller
    participant S as Service
    participant R as Repository
    participant D as Database

    Note over U,D: Ví dụ: Tạo sinh viên mới

    U->>+C: POST /api/students
    Note right of U: Gửi dữ liệu sinh viên

    C->>+S: studentService.saveStudent()
    Note right of C: Xử lý request

    S->>S: Validation & Business Logic
    Note right of S: Kiểm tra dữ liệu

    S->>+R: studentRepository.save()
    Note right of S: Lưu vào database

    R->>+D: INSERT INTO students
    Note right of R: Thực thi SQL

    D-->>-R: Student entity saved
    R-->>-S: Saved student DTO
    S-->>-C: StudentDTO response
    C-->>-U: HTTP 200 + Student data

    Note over U,D: Cập nhật giao diện real-time
```

### 🔄 Luồng Giao Dịch Ngân Hàng

```mermaid
sequenceDiagram
    participant U as User
    participant F as Frontend
    participant B as Backend
    participant D as Database

    Note over U,D: Luồng rút tiền

    U->>F: Nhập số tiền rút
    F->>F: Validate form

    F->>+B: POST /api/transactions/withdraw
    Note right of F: {accID: 1, transMoney: 500000}

    B->>+D: SELECT balance FROM accounts WHERE id = 1
    D-->>-B: Current balance: 1000000

    B->>B: Check: 1000000 >= 500000 ✓

    B->>+D: BEGIN TRANSACTION

    B->>D: UPDATE accounts SET balance = 500000
    B->>D: INSERT INTO transaction_details

    B->>+D: COMMIT TRANSACTION
    D-->>-B: Transaction successful

    B-->>-F: Success response
    F->>F: Update account balance
    F-->>U: Hiển thị thông báo thành công

    Note over U,D: Nếu số dư không đủ
    B->>B: Check: balance < amount ✗
    B-->>F: Error: Insufficient balance
    F-->>U: Hiển thị lỗi
```

## 📊 Quy Trình Tính Toán GPA

```mermaid
flowchart TD
    A[Sinh viên có môn học mới/cập nhật] --> B[Lấy tất cả môn học của sinh viên]
    B --> C{Có môn học nào?}

    C -->|Không| D[GPA = 0, Rank = 'Chưa có điểm']
    C -->|Có| E[Tính tổng điểm tất cả môn]

    E --> F[Đếm số lượng môn học]
    F --> G[GPA = Tổng điểm / Số môn]

    G --> H{Phân loại xếp hạng}
    H -->|GPA >= 9.0| I[Xuất sắc]
    H -->|8.0 <= GPA < 9.0| J[Giỏi]
    H -->|7.0 <= GPA < 8.0| K[Khá]
    H -->|5.0 <= GPA < 7.0| L[Trung bình]
    H -->|GPA < 5.0| M[Yếu]

    D --> N[Cập nhật database]
    I --> N
    J --> N
    K --> N
    L --> N
    M --> N

    N --> O[Trả về frontend]
    O --> P[Hiển thị trên giao diện]

    style A fill:#e1f5fe
    style G fill:#fff3e0
    style N fill:#e8f5e8
    style P fill:#f3e5f5
```

## 🔍 Luồng Tìm Kiếm và Lọc Dữ Liệu

```mermaid
graph TD
    A[User nhập từ khóa tìm kiếm] --> B{Loại tìm kiếm}

    B -->|Tìm sinh viên| C[Gọi API search students]
    B -->|Tìm môn học| D[Gọi API search courses]

    C --> E[Backend: LIKE %keyword%]
    D --> F[Backend: LIKE %keyword% hoặc score = value]

    E --> G[Truy vấn database]
    F --> G

    G --> H[Trả về kết quả]
    H --> I[Frontend cập nhật danh sách]
    I --> J[Hiển thị kết quả tìm kiếm]

    J --> K{User muốn xóa filter?}
    K -->|Có| L[Load lại toàn bộ danh sách]
    K -->|Không| M[Giữ nguyên kết quả]

    L --> N[Hiển thị danh sách đầy đủ]
    M --> O[Kết thúc]
    N --> O

    style A fill:#e3f2fd
    style G fill:#fff8e1
    style O fill:#e8f5e8
```
