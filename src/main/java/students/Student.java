package students;

import marks.MarksList;
import util.DateTimeFormatterUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Objects;

public class Student {
    private String name;
    private LocalDate dateOfBirth = LocalDate.now();
    private String gender = "Male";
    private String contact = "";
    private String matricNumber;
    private String remark;
    private MarksList marksList = new MarksList();

    public Student(
            String name, LocalDate dateOfBirth, String gender,
            String contact, String matricNumber
    ) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contact = contact;
        this.matricNumber = matricNumber;
        this.marksList = new MarksList();
    }
    public Student(String name, String matricNumber) {
        this.name = name;
        this.matricNumber = matricNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public MarksList getMarksList() {
        return marksList;
    }

    public void setMarksList(MarksList marksList) {
        this.marksList = marksList;
    }

    public String toFileFormat() {
        return getName() +","+ getAge() +"," + getGender() +"," + getContact() +","+ getMatricNumber() +"/n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(matricNumber, student.matricNumber); // Compare based on matricNumber
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricNumber);
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", dateOfBirth=" + DateTimeFormatterUtil.formatDate(dateOfBirth)
                + ", gender=" + gender + ", contact=" + contact + ", matricNumber=" + matricNumber + "]";
    }


    public static int getMaxNameLength(ArrayList<Student> students) {
        int maxLength = "Name".length(); // So header isn't shorter
        for (Student student : students) {
            if (student.getName().length() > maxLength) {
                maxLength = student.getName().length();
            }
        }
        return maxLength;
    }

    public static void printStudentTable(ArrayList<Student> students) {
        int nameWidth = getMaxNameLength(students) + 2; // Add buffer

        String format = String.format("%%-%ds %%-%ds %%-%ds %%-%ds %%-%ds\n",
                nameWidth, 12, 6, 15, 12); // Adjust widths

        // Print header
        System.out.printf(format, "Name", "DOB", "Gender", "Contact", "Matric No.");
        System.out.println("-".repeat(nameWidth + 12 + 6 + 15 + 12 + 4)); // +4 for spacing

        // Print each student
        for (Student student : students) {
            System.out.printf(format,
                    student.getName(),
                    DateTimeFormatterUtil.formatDate(student.getDateOfBirth()),
                    student.getGender(),
                    student.getContact(),
                    student.getMatricNumber());
        }
    }




}
