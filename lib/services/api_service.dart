import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/student.dart';

class ApiService {
  static const String baseUrl = 'http://localhost:8080/api';

  // Student APIs

  // Get all students
  static Future<List<Student>> getAllStudents() async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/students'),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        final List<dynamic> jsonList = json.decode(response.body);
        return jsonList.map((json) => Student.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load students');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Create new student
  static Future<Student> createStudent(Student student) async {
    try {
      final response = await http.post(
        Uri.parse('$baseUrl/students'),
        headers: {'Content-Type': 'application/json'},
        body: json.encode(student.toCreateJson()),
      );

      if (response.statusCode == 200) {
        return Student.fromJson(json.decode(response.body));
      } else {
        throw Exception('Failed to create student');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Get student by ID
  static Future<Student> getStudentById(int id) async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/students/$id'),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        return Student.fromJson(json.decode(response.body));
      } else if (response.statusCode == 404) {
        throw Exception('Student not found');
      } else {
        throw Exception('Failed to load student');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Update student
  static Future<Student> updateStudent(int id, Student student) async {
    try {
      final response = await http.put(
        Uri.parse('$baseUrl/students/$id'),
        headers: {'Content-Type': 'application/json'},
        body: json.encode(student.toCreateJson()),
      );

      if (response.statusCode == 200) {
        return Student.fromJson(json.decode(response.body));
      } else if (response.statusCode == 404) {
        throw Exception('Student not found');
      } else {
        throw Exception('Failed to update student');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Search students by name
  static Future<List<Student>> searchStudentsByName(String name) async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/students/search?name=$name'),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        final List<dynamic> jsonList = json.decode(response.body);
        return jsonList.map((json) => Student.fromJson(json)).toList();
      } else {
        throw Exception('Failed to search students');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Get student courses
  static Future<List<Course>> getStudentCourses(int studentId) async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/students/$studentId/courses'),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        final List<dynamic> jsonList = json.decode(response.body);
        return jsonList.map((json) => Course.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load student courses');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Delete student
  static Future<void> deleteStudent(int id) async {
    try {
      final response = await http.delete(Uri.parse('$baseUrl/students/$id'));

      if (response.statusCode == 404) {
        throw Exception('Student not found');
      } else if (response.statusCode != 200) {
        throw Exception('Failed to delete student');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Course APIs

  // Get all courses
  static Future<List<Course>> getAllCourses() async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/courses'),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        final List<dynamic> jsonList = json.decode(response.body);
        return jsonList.map((json) => Course.fromJson(json)).toList();
      } else {
        throw Exception('Failed to load courses');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Create new course
  static Future<Course> createCourse(Course course) async {
    try {
      final response = await http.post(
        Uri.parse('$baseUrl/courses'),
        headers: {'Content-Type': 'application/json'},
        body: json.encode(course.toCreateJson()),
      );

      if (response.statusCode == 200) {
        return Course.fromJson(json.decode(response.body));
      } else {
        throw Exception('Failed to create course');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Get course by ID
  static Future<Course> getCourseById(int id) async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/courses/$id'),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        return Course.fromJson(json.decode(response.body));
      } else if (response.statusCode == 404) {
        throw Exception('Course not found');
      } else {
        throw Exception('Failed to load course');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Update course
  static Future<Course> updateCourse(int id, Course course) async {
    try {
      final response = await http.put(
        Uri.parse('$baseUrl/courses/$id'),
        headers: {'Content-Type': 'application/json'},
        body: json.encode(course.toUpdateJson()),
      );

      if (response.statusCode == 200) {
        return Course.fromJson(json.decode(response.body));
      } else if (response.statusCode == 404) {
        throw Exception('Course not found');
      } else {
        throw Exception('Failed to update course');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Search courses by name
  static Future<List<Course>> searchCoursesByName(String name) async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/courses/search?name=$name'),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        final List<dynamic> jsonList = json.decode(response.body);
        return jsonList.map((json) => Course.fromJson(json)).toList();
      } else {
        throw Exception('Failed to search courses');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Search courses by score
  static Future<List<Course>> searchCoursesByScore(double score) async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/courses/score/$score'),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        final List<dynamic> jsonList = json.decode(response.body);
        return jsonList.map((json) => Course.fromJson(json)).toList();
      } else {
        throw Exception('Failed to search courses by score');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }

  // Delete course
  static Future<void> deleteCourse(int id) async {
    try {
      final response = await http.delete(Uri.parse('$baseUrl/courses/$id'));

      if (response.statusCode == 404) {
        throw Exception('Course not found');
      } else if (response.statusCode != 200) {
        throw Exception('Failed to delete course');
      }
    } catch (e) {
      throw Exception('Network error: $e');
    }
  }
}
