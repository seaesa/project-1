# Hướng Dẫn Cài Đặt và Chạy Dự Án Flutter - Student Management System

## 📋 Mục Lục

1. [Yêu Cầu Hệ Thống](#yêu-cầu-hệ-thống)
2. [Cài Đặt Flutter SDK](#cài-đặt-flutter-sdk)
3. [Cài Đặt Android Studio](#cài-đặt-android-studio)
4. [Thiết Lập Android Studio cho Flutter](#thiết-lập-android-studio-cho-flutter)
5. [Clone và Setup Dự Án](#clone-và-setup-dự-án)
6. [Chạy Dự Án](#chạy-dự-án)
7. [Troubleshooting](#troubleshooting)

---

## 🖥️ Yêu Cầu Hệ Thống

### Windows

- **OS**: Windows 10/11 (64-bit)
- **RAM**: Tối thiểu 8GB (khuyến nghị 16GB)
- **Dung lượng ổ cứng**: Tối thiểu 10GB trống
- **Git**: Git for Windows

### macOS

- **OS**: macOS 10.14 (Mojave) trở lên
- **RAM**: Tối thiểu 8GB (khuyến nghị 16GB)
- **Dung lượng ổ cứng**: Tối thiểu 10GB trống
- **Xcode**: Xcode Command Line Tools

### Linux

- **OS**: Ubuntu 18.04 LTS trở lên (hoặc các distro tương đương)
- **RAM**: Tối thiểu 8GB (khuyến nghị 16GB)
- **Dung lượng ổ cứng**: Tối thiểu 10GB trống

---

## 📦 Cài Đặt Flutter SDK

### Windows

1. **Tải Flutter SDK**

   - Truy cập: https://docs.flutter.dev/get-started/install/windows
   - Tải file `flutter_windows_x.x.x-stable.zip`
   - Giải nén vào thư mục `C:\flutter`

2. **Thiết lập biến môi trường**

   - Mở **System Properties** → **Environment Variables**
   - Thêm `C:\flutter\bin` vào biến **PATH**
   - Khởi động lại Command Prompt

3. **Kiểm tra cài đặt**
   ```cmd
   flutter --version
   flutter doctor
   ```

### macOS

1. **Cài đặt qua Homebrew (khuyến nghị)**

   ```bash
   # Cài đặt Homebrew nếu chưa có
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

   # Cài đặt Flutter
   brew install --cask flutter
   ```

2. **Hoặc tải thủ công**

   ```bash
   # Tạo thư mục development
   mkdir ~/development
   cd ~/development

   # Tải và giải nén Flutter
   curl -O https://storage.googleapis.com/flutter_infra_release/releases/stable/macos/flutter_macos_x.x.x-stable.zip
   unzip flutter_macos_x.x.x-stable.zip

   # Thêm vào PATH
   echo 'export PATH="$PATH:~/development/flutter/bin"' >> ~/.zshrc
   source ~/.zshrc
   ```

3. **Kiểm tra cài đặt**
   ```bash
   flutter --version
   flutter doctor
   ```

### Linux (Ubuntu/Debian)

1. **Cài đặt dependencies**

   ```bash
   sudo apt update
   sudo apt install curl git unzip xz-utils zip libglu1-mesa
   ```

2. **Tải và cài đặt Flutter**

   ```bash
   # Tạo thư mục development
   mkdir ~/development
   cd ~/development

   # Tải Flutter
   wget https://storage.googleapis.com/flutter_infra_release/releases/stable/linux/flutter_linux_x.x.x-stable.tar.xz

   # Giải nén
   tar xf flutter_linux_x.x.x-stable.tar.xz

   # Thêm vào PATH
   echo 'export PATH="$PATH:~/development/flutter/bin"' >> ~/.bashrc
   source ~/.bashrc
   ```

3. **Kiểm tra cài đặt**
   ```bash
   flutter --version
   flutter doctor
   ```

---

## 🛠️ Cài Đặt Android Studio

### Tất cả các platform

1. **Tải Android Studio**

   - Truy cập: https://developer.android.com/studio
   - Tải bản **Android Studio** phù hợp với OS
   - Dung lượng: ~1GB

2. **Cài đặt Android Studio**

   - **Windows**: Chạy file `.exe` và làm theo hướng dẫn
   - **macOS**: Kéo thả vào thư mục Applications
   - **Linux**: Giải nén và chạy `studio.sh`

3. **Setup lần đầu**
   - Khởi động Android Studio
   - Chọn **"Do not import settings"** (nếu lần đầu)
   - Chọn **"Standard"** installation
   - Chấp nhận license agreements
   - Đợi download SDK components (~3-5GB)

---

## ⚙️ Thiết Lập Android Studio cho Flutter

### 1. Cài đặt Flutter Plugin

1. Mở Android Studio
2. Vào **File** → **Settings** (Windows/Linux) hoặc **Android Studio** → **Preferences** (macOS)
3. Chọn **Plugins** → **Marketplace**
4. Tìm kiếm **"Flutter"**
5. Click **Install** → **Accept** → **Restart IDE**

### 2. Thiết lập Android SDK

1. Vào **Tools** → **SDK Manager**
2. Trong tab **SDK Platforms**, đảm bảo đã check:

   - ✅ Android 13 (API level 33)
   - ✅ Android 12 (API level 31)
   - ✅ Android 11 (API level 30)

3. Trong tab **SDK Tools**, đảm bảo đã check:

   - ✅ Android SDK Build-Tools
   - ✅ Android SDK Command-line Tools
   - ✅ Android SDK Platform-Tools
   - ✅ Android Emulator
   - ✅ Intel x86 Emulator Accelerator (HAXM installer)

4. Click **Apply** → **OK**

### 3. Tạo Android Virtual Device (AVD)

1. Vào **Tools** → **AVD Manager**
2. Click **"Create Virtual Device"**
3. Chọn device (khuyến nghị: **Pixel 4** hoặc **Pixel 6**)
4. Chọn system image (khuyến nghị: **API 33, Android 13**)
5. Click **Download** nếu chưa có
6. Đặt tên AVD và click **Finish**

### 4. Kiểm tra Flutter Doctor

Mở Terminal/Command Prompt và chạy:

```bash
flutter doctor
```

Kết quả mong muốn:

```
Doctor summary (to see all details, run flutter doctor -v):
[✓] Flutter (Channel stable, 3.x.x)
[✓] Android toolchain - develop for Android devices
[✓] Chrome - develop for the web
[✓] Android Studio (version 2023.x)
[✓] VS Code (version 1.x.x)
[✓] Connected device (1 available)
[✓] Network resources
```

---

## 📁 Clone và Setup Dự Án

### 1. Clone Repository

```bash
# Di chuyển đến thư mục làm việc
cd ~/Documents/Projects  # macOS/Linux
cd C:\Users\YourName\Documents\Projects  # Windows

# Clone project
git clone <repository-url>
cd bai_1
```

### 2. Cài đặt Dependencies

```bash
# Cài đặt các package dependencies
flutter pub get
```

### 3. Kiểm tra Project

```bash
# Kiểm tra có lỗi không
flutter analyze

# Chạy tests (nếu có)
flutter test
```

---

## 🚀 Chạy Dự Án

### Phương pháp 1: Chạy từ Android Studio

1. **Mở project trong Android Studio**

   - Mở Android Studio
   - Click **"Open"**
   - Chọn thư mục `bai_1`
   - Đợi indexing hoàn tất

2. **Chọn device**

   - Trong thanh toolbar, chọn device (AVD hoặc thiết bị thật)
   - Nếu chưa có AVD, click **"No devices"** → **"Create device"**

3. **Chạy app**
   - Click nút **Run** (▶️) hoặc nhấn `Shift + F10`
   - Hoặc click **"main.dart"** → **"Run 'main.dart'"**

### Phương pháp 2: Chạy từ Terminal

1. **Kiểm tra devices**

   ```bash
   flutter devices
   ```

2. **Khởi động emulator**

   ```bash
   # Liệt kê emulators
   flutter emulators

   # Khởi động emulator cụ thể
   flutter emulators --launch <emulator_id>
   ```

3. **Chạy app**

   ```bash
   # Chạy app (debug mode)
   flutter run

   # Chạy app trên device cụ thể
   flutter run -d <device_id>

   # Chạy app (release mode)
   flutter run --release
   ```

### Phương pháp 3: Chạy trên thiết bị thật

1. **Bật Developer Options trên Android**

   - Vào **Settings** → **About phone**
   - Tap **Build number** 7 lần
   - Quay lại **Settings** → **Developer options**
   - Bật **USB debugging**

2. **Kết nối thiết bị**

   - Cắm USB cable
   - Chấp nhận **"Allow USB debugging"**
   - Kiểm tra: `flutter devices`

3. **Chạy app**
   ```bash
   flutter run
   ```

---

## 🔧 Troubleshooting

### Lỗi thường gặp và cách khắc phục

#### 1. "Flutter SDK not found"

```bash
# Kiểm tra PATH
echo $PATH  # macOS/Linux
echo %PATH%  # Windows

# Thêm Flutter vào PATH
export PATH="$PATH:/path/to/flutter/bin"
```

#### 2. "Android SDK not found"

1. Mở Android Studio → **SDK Manager**
2. Note đường dẫn **Android SDK Location**
3. Set biến môi trường:
   ```bash
   export ANDROID_HOME=/path/to/android/sdk
   export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
   ```

#### 3. "No devices found"

```bash
# Khởi động lại adb
adb kill-server
adb start-server

# Kiểm tra devices
adb devices
flutter devices
```

#### 4. "Gradle build failed"

```bash
# Làm sạch project
flutter clean
flutter pub get

# Xóa cache Gradle (Windows)
rmdir /s %USERPROFILE%\.gradle\caches

# Xóa cache Gradle (macOS/Linux)
rm -rf ~/.gradle/caches
```

#### 5. "Emulator is slow"

1. Đảm bảo đã bật **Hardware Acceleration**
2. Tăng RAM cho emulator (4GB+)
3. Sử dụng **x86_64** image thay vì ARM
4. Bật **Hardware - GLES 2.0**

#### 6. "Hot reload not working"

1. Kiểm tra kết nối device
2. Restart app: `r` trong terminal
3. Hot restart: `R` trong terminal

### Kiểm tra hệ thống

```bash
# Kiểm tra tổng quan
flutter doctor -v

# Kiểm tra Flutter version
flutter --version

# Kiểm tra connected devices
flutter devices

# Kiểm tra emulators
flutter emulators

# Analyze project
flutter analyze

# Test project
flutter test
```

---

## 📚 Tài Liệu Tham Khảo

- **Flutter Documentation**: https://docs.flutter.dev/
- **Android Studio Guide**: https://developer.android.com/studio/intro
- **Flutter Installation**: https://docs.flutter.dev/get-started/install
- **Flutter Cookbook**: https://docs.flutter.dev/cookbook

---

## 🆘 Hỗ Trợ

Nếu gặp vấn đề trong quá trình cài đặt:

1. **Kiểm tra Flutter Doctor**:

   ```bash
   flutter doctor -v
   ```

2. **Tìm kiếm lỗi trên**:

   - Stack Overflow
   - Flutter GitHub Issues
   - Flutter Discord/Community

3. **Liên hệ team development** với thông tin:
   - OS version
   - Flutter version
   - Error messages
   - Screenshots

---

**Chúc bạn setup thành công! 🎉**
