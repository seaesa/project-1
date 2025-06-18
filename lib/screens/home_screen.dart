import 'package:flutter/material.dart';
import 'students_screen.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(body: StudentsScreen());
  }
}
