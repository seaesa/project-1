# Student Management System - Flutter App

A comprehensive Flutter application for managing students and their courses with full CRUD operations and integrated course management.

## Features

### 📚 Student Management

- **Add, Update, Delete** students with personal information
- **Search students** by name with real-time results
- **View detailed student information** including:
  - Personal details (name, address, phone, date of birth)
  - Course count and GPA calculation
  - Academic rank classification
  - List of enrolled courses sorted by score (descending)

### 📖 Course Management (Integrated)

- **Add, Edit, Delete** courses directly from student details
- **Course-to-student association** management
- **Score validation** (0.0 - 10.0 range)
- **Real-time GPA updates** when courses are modified

### 📊 Analytics & Ranking

- **Automatic GPA calculation** based on enrolled courses
- **Academic rank classification**:
  - Xuất sắc (Excellent): GPA ≥ 9.0
  - Giỏi (Good): GPA ≥ 8.0
  - Khá (Fair): GPA ≥ 7.0
  - Trung bình (Average): GPA ≥ 5.0
  - Yếu (Poor): GPA < 5.0

## Architecture

### Project Structure

```
lib/
├── main.dart                       # App entry point
├── models/
│   └── student.dart               # Student and Course data models
├── services/
│   └── api_service.dart           # HTTP API integration
├── screens/
│   ├── home_screen.dart           # Main navigation screen
│   └── students_screen.dart       # Student management UI
└── widgets/
    ├── student_form_dialog.dart   # Student add/edit dialog
    └── student_detail_dialog.dart # Student details with course management
```

### API Integration

The app integrates with a REST API running on `http://localhost:8080/api` with the following endpoints:

#### Student Endpoints

- `GET /students` - Get all students
- `POST /students` - Create new student
- `GET /students/{id}` - Get student by ID
- `PUT /students/{id}` - Update student
- `DELETE /students/{id}` - Delete student
- `GET /students/search?name={name}` - Search students by name
- `GET /students/{id}/courses` - Get student's courses

#### Course Endpoints

- `GET /courses` - Get all courses
- `POST /courses` - Create new course
- `GET /courses/{id}` - Get course by ID
- `PUT /courses/{id}` - Update course
- `DELETE /courses/{id}` - Delete course
- `GET /courses/search?name={name}` - Search courses by name
- `GET /courses/score/{score}` - Search courses by score

## Getting Started

### Prerequisites

- Flutter SDK (3.8.1 or higher)
- Dart SDK
- Android Studio / VS Code with Flutter extension
- A running backend API server on `http://localhost:8080`

### Installation

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd bai_1
   ```

2. **Install dependencies**

   ```bash
   flutter pub get
   ```

3. **Configure API endpoint**
   If your API server runs on a different host/port, update the `baseUrl` in `lib/services/api_service.dart`:

   ```dart
   static const String baseUrl = 'http://your-api-host:port/api';
   ```

4. **Run the application**
   ```bash
   flutter run
   ```

### Development

To run the app in debug mode:

```bash
flutter run --debug
```

To build for production:

```bash
flutter build apk  # For Android
flutter build ios  # For iOS
```

## Usage Guide

### Navigation

The app focuses on student management with integrated course management functionality.

### Managing Students

#### Adding a Student

1. Tap the **+ floating action button**
2. Fill in the required fields:
   - Name (required)
   - Address (required)
   - Phone (required)
   - Date of Birth (required, select from date picker)
3. Tap **Add** to save

#### Editing a Student

1. Find the student in the list
2. Tap the **three-dot menu** → **Edit**
3. Modify the fields as needed
4. Tap **Update** to save changes

#### Viewing Student Details & Managing Courses

1. Find the student in the list
2. Tap the **three-dot menu** → **View Details**
3. See comprehensive information including:
   - Personal details
   - GPA and academic rank
   - Complete list of courses (sorted by score)

**Course Management within Student Details:**

- **Add Course**: Click the "Add Course" button in the student details
- **Edit Course**: Use the three-dot menu on any course item
- **Delete Course**: Use the three-dot menu on any course item
- **Automatic Updates**: GPA and rank are automatically recalculated

#### Deleting a Student

1. Find the student in the list
2. Tap the **three-dot menu** → **Delete**
3. Confirm the deletion in the dialog

### Search Functionality

- Use the search box at the top of the students list
- Type to find students by name in real-time
- Clear search results using the X button

## UI Features

### Visual Indicators

- **Color-coded scores**: Different colors for different score ranges
- **Rank badges**: Visual indicators for student academic ranks
- **Real-time search**: Instant results as you type
- **Loading states**: Progress indicators during API calls
- **Error handling**: User-friendly error messages

### Integrated Course Management

- **Context-aware**: Courses are managed within the student context
- **Automatic association**: New courses are automatically linked to the student
- **Real-time updates**: GPA and course count update immediately
- **Intuitive workflow**: All course operations within the student detail view

### Responsive Design

- **Adaptive layouts** for different screen sizes
- **Material Design** components throughout
- **Smooth animations** and transitions
- **Intuitive navigation** patterns

## Error Handling

The app includes comprehensive error handling for:

- **Network connectivity issues**
- **API server errors**
- **Invalid data input**
- **User-friendly error messages**
- **Graceful fallbacks**

## Dependencies

```yaml
dependencies:
  flutter: sdk
  cupertino_icons: ^1.0.8
  http: ^1.1.0 # HTTP client for API calls
  intl: ^0.19.0 # Internationalization (date formatting)
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## API Server Setup

Make sure you have a backend API server running that implements the endpoints documented in the `api.http` file. The server should be running on `http://localhost:8080` or update the `baseUrl` in the `ApiService` class accordingly.

## Support

For issues and questions:

1. Check the existing issues in the repository
2. Create a new issue with detailed description
3. Include logs and screenshots if applicable
