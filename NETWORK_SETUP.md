# Hướng dẫn cài đặt kết nối Backend cho Flutter App

## 🔧 Các bước khắc phục lỗi kết nối

### 1. **Kiểm tra IP máy tính của bạn**

#### Windows:

```bash
ipconfig
```

Tìm dòng `IPv4 Address` trong phần `Wireless LAN adapter Wi-Fi` hoặc `Ethernet adapter`

#### macOS/Linux:

```bash
ifconfig
```

hoặc

```bash
ip addr show
```

### 2. **Cấu hình URL đúng trong app**

Mở file `lib/config/app_config.dart` và cập nhật:

#### Nếu dùng **Android Emulator**:

```dart
static const String currentBaseUrl = emulatorBaseUrl; // đã OK
```

#### Nếu dùng **Android Device thật**:

```dart
// Thay 192.168.1.XXX bằng IP thực của máy tính
static const String deviceBaseUrl = 'http://192.168.1.XXX:8080/api';
static const String currentBaseUrl = deviceBaseUrl;
```

### 3. **Đảm bảo backend đang chạy**

```bash
# Kiểm tra backend có đang chạy trên port 8080 không
curl http://localhost:8080/api/students
```

### 4. **Kiểm tra firewall**

#### Windows:

- Mở Windows Defender Firewall
- Cho phép ứng dụng backend qua firewall
- Hoặc tạm thời tắt firewall để test

#### macOS:

```bash
# Kiểm tra firewall status
sudo /usr/libexec/ApplicationFirewall/socketfilterfw --getglobalstate
```

### 5. **Test kết nối từ device**

Từ Android device, mở trình duyệt và truy cập:

```
http://192.168.1.XXX:8080/api/students
```

Nếu thấy dữ liệu JSON thì kết nối OK!

## 🚨 Các lỗi thường gặp

### Lỗi: "Network is unreachable"

- **Nguyên nhân**: Sai IP hoặc firewall chặn
- **Giải pháp**: Kiểm tra lại IP và cấu hình firewall

### Lỗi: "Connection refused"

- **Nguyên nhân**: Backend không chạy hoặc sai port
- **Giải pháp**: Khởi động backend và kiểm tra port 8080

### Lỗi: "Cleartext HTTP traffic not permitted"

- **Nguyên nhân**: Android không cho phép HTTP
- **Giải pháp**: Đã thêm `android:usesCleartextTraffic="true"` trong manifest

## 📱 Cấu hình cho từng platform

| Platform         | URL Configuration               |
| ---------------- | ------------------------------- |
| Android Emulator | `http://10.0.2.2:8080/api`      |
| Android Device   | `http://[IP_MÁY_TÍNH]:8080/api` |
| iOS Simulator    | `http://localhost:8080/api`     |
| Web/Desktop      | `http://localhost:8080/api`     |

## ✅ Checklist debug

- [ ] Backend đang chạy trên port 8080
- [ ] IP máy tính được cấu hình đúng trong app
- [ ] Firewall không chặn port 8080
- [ ] Android permissions đã được thêm
- [ ] Device và máy tính cùng mạng WiFi
