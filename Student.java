
public class Student {

    private int studentId;
    private String name;
    private int age;
    private String course;
    private double marks;

    public Student(int studentId, String name, int age, String course, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %-5d | Name: %-20s | Age: %-3d | Course: %-20s | Marks: %.2f | Grade: %s",
                studentId, name, age, course, marks, getGrade()
        );
    }

    public String getGrade() {

        if (marks >= 90) {
            return "A";
        } else if (marks >= 80) {
            return "B";
        } else if (marks >= 70) {
            return "C";
        } else if (marks >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
