class AppConfig {
  // Cấu hình cho các môi trường khác nhau

  // 1. Android Emulator
  static const String emulatorBaseUrl = 'http://10.0.2.2:8080/api';

  // 2. Android Device thật (thay IP này bằng IP thực của máy tính)
  static const String deviceBaseUrl = 'http://192.168.1.100:8080/api';

  // 3. iOS Simulator
  static const String iosSimulatorBaseUrl = 'http://localhost:8080/api';

  // 4. Web/Desktop
  static const String webBaseUrl = 'http://localhost:8080/api';

  // URL hiện tại được sử dụng
  static const String currentBaseUrl = emulatorBaseUrl;

  // Phương thức để lấy base URL phù hợp
  static String getBaseUrl() {
    // Có thể thêm logic để tự động detect platform
    return currentBaseUrl;
  }

  // Hướng dẫn sử dụng:
  // 1. Nếu dùng Android Emulator: sử dụng emulatorBaseUrl
  // 2. Nếu dùng Android Device thật:
  //    - Kiểm tra IP máy tính: ipconfig (Windows) hoặc ifconfig (Mac/Linux)
  //    - Cập nhật deviceBaseUrl với IP thực
  // 3. Nếu dùng iOS Simulator: sử dụng iosSimulatorBaseUrl
  // 4. Nếu dùng Web/Desktop: sử dụng webBaseUrl
}
