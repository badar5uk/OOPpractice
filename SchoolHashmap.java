import java.util.*;

public class SchoolHashmap {

    /*

    VERSION : 1.1

    Advanced School System without using OOP ( object Oriented Programming)

    Notes: - The current code in version 1 would use 1 java class and multiple functions to take user input
           for various schools and their students.
           - The code displays a menu with the options to:
                1. Enter Data
                2. Search Student marks
                3. Get marks average for specific student
                4. Get Teacher's data
                5. Exit
           - The code would take inputs and use various hashmaps and lists to store data to be displayed

     */

    static Scanner scanner = new Scanner(System.in);
    static List<HashMap<String, Object>> schoolList = new ArrayList<>();


    public static void main(String[] args) {
        menuSelection();
        scanner.close();
    }

    public static List<HashMap<String, Object>> schoolEntry() {


        List<HashMap<String, Object>> listOfSchools = new ArrayList<>();

        HashMap<String, Object> schoolMap = new HashMap<>();
        Boolean schoolFlag = true;
        while (schoolFlag) {
            System.out.println("Would you like to enter new data? Y/N ?");
            String newDataScanner = scanner.next();

            if (newDataScanner.contains("Y") || newDataScanner.contains("y")) {
                System.out.println("Enter school name: ");
                String nameOfSchool = scanner.next();
                System.out.println("Enter address: ");
                String address = scanner.next();
                schoolMap.put("school name", nameOfSchool);
                schoolMap.put("address", address);
                schoolMap.put("Students", studentEntry());
                listOfSchools.add(schoolMap);
            } else {
                break;
            }
        }

        return listOfSchools;
    }

    public static List<HashMap<String, Object>> studentEntry() {
        List<HashMap<String, Object>> studentsForThisSchool = new ArrayList<>();
        HashMap<String, Object> studentMap = new HashMap<>();

        Boolean studentFlag = true;
        while (studentFlag) {
            System.out.println("Enter student name: ");
            String studentName = scanner.next();
            System.out.println("Enter student id: ");
            String id = scanner.next();
            System.out.println("Enter student grade: ");
            String grade = scanner.next();
            System.out.println("Enter student age");
            Short age = scanner.nextShort();
            studentMap.put("student name", studentName);
            studentMap.put("student id", id);
            studentMap.put("grade", grade);
            studentMap.put("student age", age);
            studentsForThisSchool.add(studentMap);
            studentMap.put("Subjects", subjectEntry());
            studentsForThisSchool.add(studentMap);

            System.out.println("Would you like to enter another student? Y/N");
            String studentExit = scanner.next();

            if (studentExit.contains("N") || studentExit.contains("n")) {
                studentFlag = false;
            }
        }

        return studentsForThisSchool;

    }

    public static List<HashMap<String, Object>> subjectEntry() {
        List<HashMap<String, Object>> subjectsForThisStudent = new ArrayList<>();
        HashMap<String, Object> subjectMap = new HashMap<>();
        Boolean subjectFlag = Boolean.TRUE;
        while (subjectFlag) {
            System.out.println("Enter subject name: ");
            String subjectName = scanner.next();
            System.out.println("Enter teacher name: ");
            String teacherName = scanner.next();
            System.out.println("Enter credit: ");
            Short creditHours = scanner.nextShort();

            subjectMap.put("Subject Name", subjectName);
            subjectMap.put("Teacher's Name", teacherName);
            subjectMap.put("Credit Hours", creditHours);

            subjectMap.put("Marks", marksEntry());
            subjectsForThisStudent.add(subjectMap);

            System.out.println("Would you like to enter another subject? Y/N");
            String subjectExit = scanner.next();

            if (subjectExit.contains("N") || subjectExit.contains("n")) {
                subjectFlag = false;
            }

        }
        return subjectsForThisStudent;
    }

    public static List<HashMap<String, Object>> marksEntry() {
        List<HashMap<String, Object>> marksForThisSubject = new ArrayList<>();
        Boolean marksFlag = Boolean.TRUE;
        while (marksFlag) {
            System.out.println("Enter the test name :");
            String testName = scanner.next();
            System.out.println("Enter the marks:");
            Integer marks = scanner.nextInt();

            HashMap<String, Object> marksHashmaps = new HashMap<>();
            marksHashmaps.put("Test Name", testName);
            marksHashmaps.put("Marks", marks);

            marksForThisSubject.add(marksHashmaps);

            System.out.println("would you like to enter another test? Y/N");
            String testExit = scanner.next();

            if (testExit.contains("N") || testExit.contains("n")) {
                marksFlag = false;
            }
        }
        return marksForThisSubject;
    }

    public static void getMarksData(List<HashMap<String, Object>> listOfSchools) {
        System.out.println("Would you like to search existing data? Y/N ?");
        String existingDataScanner = scanner.next();
        if (existingDataScanner.contains("Y") || existingDataScanner.contains("y")) {
            System.out.println("Enter existing School name: ");
            String schoolName = scanner.next();
            for (HashMap<String, Object> schoolMap : listOfSchools) {
                if (schoolMap.containsValue(schoolName)) {
                    List<HashMap<String, Object>> studentsForThisSchoolList = (List<HashMap<String, Object>>) schoolMap.get("Students");
                    System.out.println("Enter a student ID: ");
                    String studentId = scanner.next();
                    for (HashMap<String, Object> student : studentsForThisSchoolList) {
                        if (student.containsValue(studentId)) {
                            List<HashMap<String, Object>> subjects = (List<HashMap<String, Object>>) student.get("Subjects");
                            System.out.println("Enter subject name:");
                            String subjectName = scanner.next();
                            for (HashMap<String, Object> subject : subjects) {
                                if (subject.containsValue(subjectName)) {
                                    List<HashMap<String, Object>> marks = (List<HashMap<String, Object>>) subject.get("Marks");
                                    System.out.println("Marks of " + student + "for " + subjectName + ": " + marks);
                                    System.out.println("Grade: " + student.get("grade"));
                                } else {
                                    System.out.println("Subject not found");
                                }
                            }
                        } else {
                            System.out.println("Student not found");
                        }
                    }
                } else {
                    System.out.println("School not found");
                }
            }
        }
    }

    public static void marksCalculations(List<HashMap<String, Object>> listOfSchools) {
        System.out.println("Would you like to calculate mark's average? Y/N ?");
        String existingDataScanner = scanner.next();
        if (existingDataScanner.contains("Y") || existingDataScanner.contains("y")) {
            System.out.println("Enter existing School name: ");
            String schoolName = scanner.next();
            for (HashMap<String, Object> schoolMap : listOfSchools) {
                if (schoolMap.containsValue(schoolName)) {
                    List<HashMap<String, Object>> studentsForThisSchoolList = (List<HashMap<String, Object>>) schoolMap.get("Students");
                    System.out.println("Enter a student ID: ");
                    String studentId = scanner.next();
                    int marksTobeAdded = 0;
                    int count = 0;
                    for (HashMap<String, Object> student : studentsForThisSchoolList) {
                        if (student.containsValue(studentId)) {
                            List<HashMap<String, Object>> subjects = (List<HashMap<String, Object>>) student.get("Subjects");
                            for (HashMap<String, Object> subject : subjects) {
                                List<HashMap<String, Object>> marks = (List<HashMap<String, Object>>) subject.get("Marks");
                                for (HashMap<String, Object> mark : marks) {
                                    marksTobeAdded += (int) mark.get("Marks");
                                    count++;
                                }
                            }
                            double average = (double) marksTobeAdded / count;
                            System.out.println(average);
                        } else {
                            System.out.println("Student not found");
                        }
                    }
                } else {
                    System.out.println("School not found");
                }
            }
        }
    }

    public static void getTeacherDetails(List<HashMap<String, Object>> listOfSchools) {
        System.out.println("Would you like to search existing data? Y/N ?");
        String existingDataScanner = scanner.next();
        if (existingDataScanner.contains("Y") || existingDataScanner.contains("y")) {
            System.out.println("Enter existing School name: ");
            String schoolName = scanner.next();
            for (HashMap<String, Object> schoolMap : listOfSchools) {
                if (schoolMap.containsValue(schoolName)) {
                    List<HashMap<String, Object>> students = (List<HashMap<String, Object>>) schoolMap.get("Students");
                    for (HashMap<String, Object> student : students) {
                        List<HashMap<String, Object>> subjects = (List<HashMap<String, Object>>) student.get("Subjects");
                        for (HashMap<String, Object> subject : subjects) {
                            Object teacher = subject.get("Teacher's Name");
                            System.out.println("Enter Teacher's Name: ");
                            String teacherName = scanner.next();
                            if (teacher.equals(teacherName)) {
                                System.out.println("Here are the details for " + teacherName + "\n" +
                                        teacherName + " is the tutor of " + subject.get("Subject Name") +
                                        "\n" + "The credit hours for " + subject.get("Subject Name") + " are" + subject.get(" Credit Hours"));
                            } else {
                                System.out.println("Teacher does no exist");
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("School does not exist");
        }
    }


    public static void menuSelection() {
        Boolean exitFlag = true;
        while (exitFlag) {
            System.out.println("Pick an Option: " +
                    "\n" + "1. Enter Data" +
                    "\n" + "2. Get student marks" +
                    "\n" + "3. Get average for student" +
                    "\n" + "4. Get Teacher details" +
                    "\n" + "5. Exit");
            String menuInput = scanner.next();
            if (menuInput.equals("1")) {
                schoolList = schoolEntry();
            } else if (menuInput.equals("2")) {
                getMarksData(schoolList);
            } else if (menuInput.equals("3")) {
                marksCalculations(schoolList);
            } else if (menuInput.equals("4")) {
                getTeacherDetails(schoolList);
            } else if (menuInput.equals("6")) {
                exitFlag = false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}



