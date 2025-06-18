class Student {
  final int? id;
  final String name;
  final String address;
  final String phone;
  final String dob;
  final List<Course>? courses;
  final int courseCount;
  final double? gpa;
  final String? rank;

  Student({
    this.id,
    required this.name,
    required this.address,
    required this.phone,
    required this.dob,
    this.courses,
    this.courseCount = 0,
    this.gpa,
    this.rank,
  });

  factory Student.fromJson(Map<String, dynamic> json) {
    return Student(
      id: json['id'],
      name: json['name'],
      address: json['address'],
      phone: json['phone'],
      dob: json['dob'],
      courses: json['courses'] != null
          ? (json['courses'] as List).map((c) => Course.fromJson(c)).toList()
          : null,
      courseCount: json['courseCount'] ?? 0,
      gpa: json['gpa']?.toDouble(),
      rank: json['rank'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'address': address,
      'phone': phone,
      'dob': dob,
      'courses': courses?.map((c) => c.toJson()).toList(),
      'courseCount': courseCount,
      'gpa': gpa,
      'rank': rank,
    };
  }

  Map<String, dynamic> toCreateJson() {
    return {'name': name, 'address': address, 'phone': phone, 'dob': dob};
  }
}

class Course {
  final int? id;
  final String name;
  final double score;
  final int? studentId;

  Course({this.id, required this.name, required this.score, this.studentId});

  factory Course.fromJson(Map<String, dynamic> json) {
    return Course(
      id: json['id'],
      name: json['name'],
      score: json['score'].toDouble(),
      studentId: json['studentId'],
    );
  }

  Map<String, dynamic> toJson() {
    return {'id': id, 'name': name, 'score': score, 'studentId': studentId};
  }

  Map<String, dynamic> toCreateJson() {
    return {'name': name, 'score': score, 'studentId': studentId};
  }

  Map<String, dynamic> toUpdateJson() {
    return {'name': name, 'score': score};
  }
}
