/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import model.Grade;
import model.KeyGrade;
import model.Student;
import model.Subject;
import tools.Console;

/**
 *
 * @author MSI
 */
public class GradeRepository implements ICrud, IGradeRepository {
    private HashMap<KeyGrade, Grade> gradeList = new HashMap<>();
    private StudentRepository studentRepo = new StudentRepository();
    private SubjectRepository subjectRepo = new SubjectRepository();

    public GradeRepository() {
    }
    
    public GradeRepository(StudentRepository studentRepo, SubjectRepository subjectRepo) {
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
    }
    
    public GradeRepository(HashMap<KeyGrade, Grade> gradeList, StudentRepository studentRepo, SubjectRepository subjectRepo) {
        this.gradeList = gradeList;
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
    }

    public HashMap<KeyGrade, Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(HashMap<KeyGrade, Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public StudentRepository getStudentRepo() {
        return studentRepo;
    }

    public void setStudentRepo(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public SubjectRepository getSubjectRepo() {
        return subjectRepo;
    }

    public void setSubjectRepo(SubjectRepository subjectRepo) {
        this.subjectRepo = subjectRepo;
    }
    
    @Override
    public void enterGrade() {
        KeyGrade key = inputKeyGrade();
        
        if(this.contains(key)) update(key);
        else create(key);
    }

    @Override
    public void studentGradeReport() {
//        String studentId = Console.inputStr("Input student id\n");
        String studentId = inputExistStudentId();
        Student student = studentRepo.get(studentId);
        
        System.out.println("===== STUDENT REPORT =====");
        System.out.println("Student id: " + studentId);
        System.out.println("Student name: " + student.getFullName());
        System.out.println("List of subjects sorted by Subject Name (ascending)");
        System.out.println("===== Subject name ===== Average mark ===== Status =====");
        
        SortedMap<String, String> records = new TreeMap<>();
        
        for(KeyGrade key: gradeList.keySet()) {
            if(!key.getStudentId().equals(studentId)) continue;
            
            Subject subject = subjectRepo.get(key.getSubjectId());
            Grade grade = this.get(key);
            
            records.put(subject.getSubjectName(),"***** " + subject.getSubjectName() + " ***** " + grade.getAvarageMark() + " ***** " + grade.getStatus() + " *****");
        }
        
        for(String record : records.values()) {
            System.out.println(record);
        }
    }

    @Override
    public void subjectGradeReport() {
//        String subjectId = Console.inputStr("Input subject id\n");
        String subjectId = inputExistSubjectId();
        Subject subject = subjectRepo.get(subjectId);
        
        System.out.println("===== SUBJECT REPORT =====");
        System.out.println("Subject id: " + subjectId);
        System.out.println("Subject name: " + subject.getSubjectName());
        System.out.println("List of students sorted by Student Name (ascending)");
        System.out.println("===== Student id ===== Student name ===== Average mark ===== Status =====");
        
        SortedMap<String, String> records = new TreeMap<>();
        
        for(KeyGrade key: gradeList.keySet()) {
            if(!key.getSubjectId().equals(subjectId)) continue;
            
            Student student = studentRepo.get(key.getStudentId());
            Grade grade = this.get(key);
            
            records.put(student.getFullName(), "***** " + student.getStudentId() + " ***** " + student.getFullName() + " ***** " + grade.getAvarageMark() + " ***** " + grade.getStatus() + " *****");
        }
        
        for(String record : records.values()) {
            System.out.println(record);
        }
    }

    @Override
    public void create() {
        KeyGrade key = inputKeyGrade();
        
        try {
            create(key);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void create(KeyGrade key) {
        if(this.contains(key)) {
            throw new RuntimeException("Key duplicated!");
        }
        
        gradeList.put(key, inputGrade());
    }
    
    @Override
    public void read() {
        for(KeyGrade key : gradeList.keySet()) {
            System.out.println(gradeReportFormated(key));
        }
    }

    @Override
    public void details() {
        KeyGrade key = inputKeyGrade();
        
        if(this.contains(key))
            System.out.println(gradeReportFormated(key));
        else System.out.println("Grade does not exist!");
    }

    @Override
    public void update() {
        KeyGrade key = inputKeyGrade();
        
        try {
            update(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(KeyGrade key) {
        if(!this.contains(key)) {
            throw new RuntimeException("Grade does not exist!");
        }
        
        Grade grade = this.get(key);
        modifyGrade(grade);
    }

    @Override
    public void delete() {
        KeyGrade key = inputKeyGrade();
        try {
            delete(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(KeyGrade key) {
        if(!this.contains(key))
            throw new RuntimeException("Grade does not exist!");
        
        gradeList.remove(key);
    }
    
    private String gradeReportFormated(KeyGrade key) {
        if(!this.contains(key)) return "Grade does not exist!";
        
        Grade grade = get(key);
        return key.toString() + " " + grade.toString();
    }
    
    private boolean contains(KeyGrade key) {
        return gradeList.containsKey(key);
    }

    private Grade get(KeyGrade key) {
        return gradeList.get(key);
    }
    
    private Grade inputGrade() {
        float lab = 0;
        float progressTest = 0;
        float finalExam = 0;
        String input;
        boolean OK;
        
        OK = true;
        do {
            input = Console.inputStr("Input lab score\n");
//            if(input.isBlank()) break;
            
            try {
                float tmp = Float.parseFloat(input); /// catch exception already below
                if(!InputGradeValidation.checkValidGrade(tmp)) {
                    throw new Exception("Invalid grade!");
                }
                lab = tmp;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid lab score");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input progress test score\n");
//            if(input.isBlank()) break;
            
            try {
                float tmp = Float.parseFloat(input); /// catch exception already below
                if(!InputGradeValidation.checkValidGrade(tmp)) {
                    throw new Exception("Invalid grade!");
                }
                progressTest = tmp;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid progress test score");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input final exam score\n");
//            if(input.isBlank()) break;
            
            try {
                float tmp = Float.parseFloat(input); /// catch exception already below
                if(!InputGradeValidation.checkValidGrade(tmp)) {
                    throw new Exception("Invalid grade!");
                }
                finalExam = tmp;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid final exam score");
                OK = false;
            }
        } while(!OK);
        
        return new Grade(lab, progressTest, finalExam);
    }
    
    private Grade modifyGrade(Grade grade) {
        float lab = grade.getLab();
        float progressTest = grade.getProgressTest();
        float finalExam = grade.getFinalExam();
        String input;
        boolean OK;
        
        OK = true;
        do {
            input = Console.inputStr("Input lab score\n");
            if(input.isBlank()) break;
            
            try {
                float tmp = Float.parseFloat(input); /// catch exception already below
                if(!InputGradeValidation.checkValidGrade(tmp)) {
                    throw new Exception("Invalid grade!");
                }
                lab = tmp;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid lab score");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input progress test score\n");
            if(input.isBlank()) break;
            
            try {
                float tmp = Float.parseFloat(input); /// catch exception already below
                if(!InputGradeValidation.checkValidGrade(tmp)) {
                    throw new Exception("Invalid grade!");
                }
                progressTest = tmp;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid progress test score");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input final exam score\n");
            if(input.isBlank()) break;
            
            try {
                float tmp = Float.parseFloat(input); /// catch exception already below
                if(!InputGradeValidation.checkValidGrade(tmp)) {
                    throw new Exception("Invalid grade!");
                }
                finalExam = tmp;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid final exam score");
                OK = false;
            }
        } while(!OK);
        
        grade.setLab(lab);
        grade.setProgressTest(progressTest);
        grade.setFinalExam(finalExam);
        
        return grade;
    }

    private String inputExistStudentId() {
        boolean OK;
        String studentId = null;
        
        OK = true;
        do {
            try {
                studentId = Console.inputStr("Input student id\n");

                if(!studentRepo.contains(studentId))
                    throw new Exception("Student does not exist!");

                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid student id");
                OK = false;
            }
        } while(!OK);
        
        return studentId;
    }
    
    private String inputExistSubjectId() {
        boolean OK;
        String subjectId = null;
        
        OK = true;
        do {
            try {
                subjectId = Console.inputStr("Input subject id\n");
                
                if(!subjectRepo.contains(subjectId))
                    throw new Exception("Subject does not exist!");
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid subject id");
                OK = false;
            }
        } while(!OK);
        
        return subjectId;
    }
    
    private KeyGrade inputKeyGrade() {
        boolean OK;
        String studentId = null;
        String subjectId = null;
        
        OK = true;
        do {
            try {
                studentId = Console.inputStr("Input student id\n");

                if(!studentRepo.contains(studentId))
                    throw new Exception("Student does not exist!");

                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid student id");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            try {
                subjectId = Console.inputStr("Input subject id\n");
                
                if(!subjectRepo.contains(subjectId))
                    throw new Exception("Subject does not exist!");
//                if(this.contains(new KeyGrade(studentId, subjectId)))
//                    throw new Exception("No grade match between student " + studentId + " and subject " + subjectId + "!");
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid subject id");
                OK = false;
            }
        } while(!OK);
        
        return new KeyGrade(studentId, subjectId);
    }
    
}
