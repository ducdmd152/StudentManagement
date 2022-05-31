/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import model.Student;
import tools.Console;
import tools.DateValidation;
import tools.InputStudentValidation;

/**
 *
 * @author MSI
 */
public class StudentRepository implements ICrud {
    private HashMap<String, Student> studentList = new HashMap<>();

    public StudentRepository() {
    }
    
    public StudentRepository(HashMap<String, Student> listStudent) {
        this.studentList = listStudent;
    }
    
    public HashMap<String, Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(HashMap<String, Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public void create() {
        boolean OK;
        
        String studentId = null;
        OK = true;
        do {
            studentId = Console.inputStr("Input student id\n");
            try {
                if(!InputStudentValidation.checkStudentIdFormat(studentId)) {
                    throw new Exception("Wrong format id!");
                }
                
                if(this.contains(studentId)) {
                    throw new Exception("Id duplicated!");
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid student id");
                OK = false;
            }
        } while(!OK);
        
        create(studentId);
    }
    
    public void create(String studentId) {
        if(!InputStudentValidation.checkStudentIdFormat(studentId))
            throw new RuntimeException("Invalid student id");
        
        if(this.contains(studentId))
            throw new RuntimeException("The code is duplicated!");
        
        boolean OK;
        String firstName = null;
        OK = true;
        do {
            firstName = Console.inputStr("Input first name\n");
            try {
                if(!InputStudentValidation.checkNameLength(firstName)) {
                    throw new Exception();
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid first name");
                OK = false;
            }
        } while(!OK);
        
        String lastName = null;
        OK = true;
        do {
            lastName = Console.inputStr("Input last name\n");
            try {
                if(!InputStudentValidation.checkNameLength(lastName)) {
                    throw new Exception();
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid last name");
                OK = false;
            }
        } while(!OK);
        
        boolean gender = true;
        OK = true;
        do {
            String input = Console.inputStr("Input gender\n");
            try {
                if(!InputStudentValidation.checkGenderFormat(input)) {
                    throw new Exception();
                }
                gender = input.trim().toUpperCase().equals("TRUE");
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid gender");
                OK = false;
            }
        } while(!OK);
        
        Date doB = null;
        OK = true;
        do {
            String input = Console.inputStr("Input date of birth\n");
            try {
                if(!InputStudentValidation.checkDateOfBirth(input))
                    throw new Exception();
                
                doB = new SimpleDateFormat("dd/MM/yyyy").parse(input);
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid date of birth");
                OK = false;
            }
        } while(!OK);
        
        String email = null;
        OK = true;
        do {
            email = Console.inputStr("Input email\n");
            try {
                if(!InputStudentValidation.checkValidEmail(email)) {
                    throw new Exception();
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid email");
                OK = false;
            }
        } while(!OK);
        
        String phoneNumber = null;
        OK = true;
        do {
            phoneNumber = Console.inputStr("Input phone number\n");
            try {
                if(!InputStudentValidation.checkValidPhoneNumber(phoneNumber)) {
                    throw new Exception();
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid phone number");
                OK = false;
            }
        } while(!OK);
        
        Student student = new Student(studentId, firstName, lastName, gender, doB, email, phoneNumber);
        studentList.put(studentId, student);
    }

    @Override
    public void read() {
        for(Student student : studentList.values()) {
            System.out.println(student);
        }
    }

    @Override
    public void details() {
        String studentId = Console.inputStr("Enter the code: ");
        
        if(!studentList.containsKey(studentId)) {
            System.out.println("Not found!");
            return;
        }
        System.out.println(studentList.get(studentId));
    }

    @Override
    public void update() {
        boolean OK;
        
        String studentId = null;
        OK = true;
        do {
            studentId = Console.inputStr("Input student id\n");
            try {
                if(!InputStudentValidation.checkStudentIdFormat(studentId)) {
                    throw new Exception("Wrong format id!");
                }
                
                if(!this.contains(studentId)) {
                    throw new Exception("Id does not exist!");
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid student id");
                OK = false;
            }
        } while(!OK);
        
        try {
            update(studentId);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String studentId) {
        if(!studentList.containsKey(studentId))
            throw new RuntimeException("Student does not exist!");
        
        Student student = studentList.get(studentId);
        
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        boolean gender = student.getGender();
        Date doB = student.getDoB();
        String email = student.getEmail();
        String phoneNumber = student.getPhoneNumber();
        
        String input;
        boolean OK;
        
        OK = true;
        do {
            input = Console.inputStr("Input first name\n");
            if(input.isBlank()) break;
            
            try {
                if(!InputStudentValidation.checkNameLength(input)) {
                    throw new Exception();
                }
                firstName = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid first name");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input last name\n");
            if(input.isBlank()) break;
            
            try {
                if(!InputStudentValidation.checkNameLength(input)) {
                    throw new Exception();
                }
                lastName = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid last name");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input gender\n");
            if(input.isBlank()) break;
            
            try {
                if(!InputStudentValidation.checkGenderFormat(input)) {
                    throw new Exception();
                }
                gender = input.trim().toUpperCase().equals("TRUE");
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid gender");
                OK = false;
            }
        } while(!OK);
        
        
        OK = true;
        do {
            input = Console.inputStr("Input date of birth\n");
            if(input.isBlank()) break;
            try {
                if(!InputStudentValidation.checkDateOfBirth(input)) {
                    throw new Exception();
                }
                doB = new SimpleDateFormat("dd/MM/yyyy").parse(input);  
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid date of birth");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input email\n");
            if(input.isBlank()) break;
            
            try {
                if(!InputStudentValidation.checkValidEmail(input)) {
                    throw new Exception();
                }
                email = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid email");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input phone number\n");
            if(input.isBlank()) break;
            
            try {
                if(!InputStudentValidation.checkValidPhoneNumber(input)) {
                    throw new Exception();
                }
                phoneNumber = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid phone number");
                OK = false;
            }
        } while(!OK);
        
//        Update:
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGender(gender);
        student.setDoB(doB);
        student.setEmail(email);
        student.setPhoneNumber(phoneNumber);
    }

    @Override
    public void delete() {
        boolean OK;
        
        String studentId = null;
        OK = true;
        do {
            studentId = Console.inputStr("Input student id\n");
            try {
                if(!InputStudentValidation.checkStudentIdFormat(studentId)) {
                    throw new Exception("Wrong format id!");
                }
                
                if(!this.contains(studentId)) {
                    throw new Exception("Id does not exist!");
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid student id");
                OK = false;
            }
        } while(!OK);
        
        try {
            delete(studentId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String studentId) {
        if (!studentList.containsKey(studentId))
            throw new RuntimeException("Student does not exist!");
        
        studentList.remove(studentId);
    }

    public boolean contains(String studentId) {
        return studentList.containsKey(studentId);
    }    

    public Student get(String studentId) {
        return studentList.get(studentId);
    }
    
    /// CRUD with parameters
    public void create(String studentId, String firstName, String lastName, boolean gender, Date doB, String email, String phoneNumber) {
        if(this.contains(studentId))
            throw new RuntimeException("Student id duplicated!");
        if(!InputStudentValidation.checkStudentIdFormat(studentId))
            throw new RuntimeException("Invalid student id format!");
        
        if(!InputStudentValidation.checkNameLength(firstName))
            throw new RuntimeException("Invalid first name!");
        
        if(!InputStudentValidation.checkNameLength(lastName))
            throw new RuntimeException("Invalid last name!");
        
        if(!DateValidation.checkDateValid(doB))
            throw new RuntimeException("Invalid date!");
        
        if(!InputStudentValidation.checkValidEmail(email))
            throw new RuntimeException("Invalid email!");
        
        if(!InputStudentValidation.checkValidPhoneNumber(phoneNumber))
            throw new RuntimeException("Invalid phone number!");
        
        studentList.put(studentId, new Student(studentId, firstName, lastName, gender, doB, email, phoneNumber));
    }
}
