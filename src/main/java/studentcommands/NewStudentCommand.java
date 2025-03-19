package studentcommands;

import Util.DateTimeFormatterUtil;
import exception.TASyncException;
import students.Student;
import students.StudentList;
import taskCommands.Command;

import java.time.LocalDate;

public class NewStudentCommand implements Command<StudentList> {
    @Override
    public void execute(String parts, StudentList studentList) {
        try {
            String[] studentParts = parts.split(",");

            if (studentParts.length != 6) {
                throw TASyncException.invalidNewStudentCommand();
            }

            String studentName = studentParts[0].trim();
            LocalDate dob = DateTimeFormatterUtil.parseDate(studentParts[1]);
            String gender = studentParts[2].trim();
            String contact = studentParts[3].trim();
            String matricNumber = studentParts[4].trim();
            String tutorialClass = studentParts[5].trim();
            Student student = new Student(studentName, dob, gender, contact, matricNumber, tutorialClass);
            studentList.addStudent(student);
            System.out.println("New student added: " + studentName);
        } catch (TASyncException e) {
            System.out.println(e.getMessage());
        }
    }
}
