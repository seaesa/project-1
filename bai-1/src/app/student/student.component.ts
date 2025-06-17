import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface Student {
  id?: number;
  name: string;
  address?: string;
  phone?: string;
  dob?: string;
  courses?: Course[];
  courseCount?: number;
  gpa?: number;
  rank?: string;
}

interface Course {
  id?: number;
  name: string;
  score?: number;
  studentId?: number;
}

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './student.component.html',
})
export class StudentComponent implements OnInit {
  private apiUrl = 'http://localhost:8080/api';

  students: Student[] = [];
  currentStudent: Student = { name: '', address: '', phone: '', dob: '' };
  selectedStudent: Student | null = null;
  selectedStudentCourses: Course[] = [];
  currentCourse: Course = { name: '', score: 0 };
  showStudentForm = false;
  editingStudent = false;
  editingCourse = false;
  showCourseForm = false;
  searchTerm = '';
  showCourseDetail = false;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadStudents();
  }

  loadStudents() {
    this.http.get<Student[]>(`${this.apiUrl}/students`).subscribe({
      next: (data) => (this.students = data),
      error: (error) => console.error('Lỗi tải danh sách sinh viên:', error),
    });
  }

  saveStudent() {
    if (this.editingStudent && this.currentStudent.id) {
      this.http
        .put<Student>(
          `${this.apiUrl}/students/${this.currentStudent.id}`,
          this.currentStudent
        )
        .subscribe({
          next: () => {
            this.loadStudents();
            this.cancelStudentForm();
          },
          error: (error) => console.error('Lỗi cập nhật sinh viên:', error),
        });
    } else {
      this.http
        .post<Student>(`${this.apiUrl}/students`, this.currentStudent)
        .subscribe({
          next: () => {
            this.loadStudents();
            this.cancelStudentForm();
          },
          error: (error) => console.error('Lỗi tạo sinh viên:', error),
        });
    }
  }

  editStudent(student: Student) {
    this.currentStudent = { ...student };
    this.editingStudent = true;
    this.showStudentForm = true;
    this.showCourseDetail = false;
  }

  deleteStudent(id: number) {
    if (confirm('Bạn có chắc chắn muốn xóa sinh viên này?')) {
      this.http.delete(`${this.apiUrl}/students/${id}`).subscribe({
        next: () => this.loadStudents(),
        error: (error) => console.error('Lỗi xóa sinh viên:', error),
      });
    }
  }

  searchStudents() {
    if (this.searchTerm.trim()) {
      this.http
        .get<Student[]>(
          `${this.apiUrl}/students/search?name=${this.searchTerm}`
        )
        .subscribe({
          next: (data) => (this.students = data),
          error: (error) => console.error('Lỗi tìm kiếm sinh viên:', error),
        });
    } else {
      this.loadStudents();
    }
  }

  viewStudentDetail(student: Student) {
    this.selectedStudent = student;
    this.showCourseDetail = true;
    this.showStudentForm = false;
    this.cancelCourseForm();
    this.loadStudentCourses(student.id!);
  }

  loadStudentCourses(studentId: number) {
    this.http
      .get<Course[]>(`${this.apiUrl}/students/${studentId}/courses`)
      .subscribe({
        next: (data) => (this.selectedStudentCourses = data),
        error: (error) => console.error('Lỗi tải khóa học:', error),
      });
  }

  saveCourse() {
    if (this.selectedStudent) {
      this.currentCourse.studentId = this.selectedStudent.id;

      if (this.editingCourse && this.currentCourse.id) {
        // Cập nhật khóa học qua API PUT /api/courses/{id}
        this.http
          .put<Course>(
            `${this.apiUrl}/courses/${this.currentCourse.id}`,
            this.currentCourse
          )
          .subscribe({
            next: () => {
              this.loadStudentCourses(this.selectedStudent!.id!);
              this.loadStudents();
              this.cancelCourseForm();
              alert('Cập nhật khóa học thành công!');
            },
            error: (error) => {
              console.error('Lỗi cập nhật khóa học:', error);
              alert(
                'Lỗi cập nhật khóa học: ' +
                  (error.error?.message || error.message)
              );
            },
          });
      } else {
        // Tạo khóa học mới
        this.http
          .post<Course>(`${this.apiUrl}/courses`, this.currentCourse)
          .subscribe({
            next: () => {
              this.loadStudentCourses(this.selectedStudent!.id!);
              this.loadStudents();
              this.cancelCourseForm();
              alert('Thêm khóa học thành công!');
            },
            error: (error) => {
              console.error('Lỗi tạo khóa học:', error);
              alert(
                'Lỗi tạo khóa học: ' + (error.error?.message || error.message)
              );
            },
          });
      }
    }
  }

  editCourse(course: Course) {
    this.currentCourse = { ...course };
    this.editingCourse = true;
    this.showCourseForm = true;
  }

  showAddCourseForm() {
    this.currentCourse = {
      name: '',
      score: 0,
      studentId: this.selectedStudent?.id,
    };
    this.editingCourse = false;
    this.showCourseForm = true;
  }

  cancelCourseForm() {
    this.showCourseForm = false;
    this.editingCourse = false;
    this.currentCourse = { name: '', score: 0 };
  }

  deleteCourse(id: number) {
    if (confirm('Bạn có chắc chắn muốn xóa khóa học này?')) {
      this.http.delete(`${this.apiUrl}/courses/${id}`).subscribe({
        next: () => {
          this.loadStudentCourses(this.selectedStudent!.id!);
          this.loadStudents();
        },
        error: (error) => console.error('Lỗi xóa khóa học:', error),
      });
    }
  }

  cancelStudentForm() {
    this.showStudentForm = false;
    this.editingStudent = false;
    this.currentStudent = { name: '', address: '', phone: '', dob: '' };
  }

  closeCourseDetail() {
    this.showCourseDetail = false;
    this.selectedStudent = null;
    this.selectedStudentCourses = [];
    this.cancelCourseForm();
  }
}
