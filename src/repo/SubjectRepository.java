/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.util.HashMap;
import model.Subject;
import tools.Console;
import tools.InputSubjectValidation;

/**
 *
 * @author MSI
 */
public class SubjectRepository implements ICrud {
    private HashMap<String, Subject> subjectList = new HashMap<>();

    public SubjectRepository() {
    }
    
    public SubjectRepository(HashMap<String, Subject> listSubject) {
        this.subjectList = listSubject;
    }

    public HashMap<String, Subject> getListStudent() {
        return subjectList;
    }

    public void setListStudent(HashMap<String, Subject> listSubject) {
        this.subjectList = listSubject;
    }

    @Override
    public void create() {
        String subjectId = null;
        boolean OK;
        
        OK = true;
        do {
            subjectId = Console.inputStr("Input subject id\n");
            try {
                if(!InputSubjectValidation.checkSubjectIdFormat(subjectId)) {
                    throw new Exception("Wrong id format!");
                }
                
                if(this.contains(subjectId)) {
                    throw new Exception("Id duplicated!");
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid subject id");
                OK = false;
            }
        } while(!OK);
        
        create(subjectId);
    }
    
    public void create(String subjectId) {
        if(!InputSubjectValidation.checkSubjectIdFormat(subjectId)) {
            throw new RuntimeException("The code is invalid!");
        }

        if(this.contains(subjectId)) {
            throw new RuntimeException("The code is duplicated!");
        }
        
        String subjectName = null;
        int credit = 0;
        boolean OK;
        
        OK = true;
        do {
            subjectName = Console.inputStr("Input subject name\n");
            try {
                if(!InputSubjectValidation.checkSubjectNameLength(subjectName)) {
                    throw new Exception();
                }
                
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid subject name");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            
            try {
                credit = (int) Console.inputNumber("Input credit\n");
                if(!InputSubjectValidation.checkCreditRange(credit)) {
                    throw new Exception();
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid credit");
                OK = false;
            }
        } while(!OK);
        
        Subject subject = new Subject(subjectId, subjectName, credit);
        subjectList.put(subjectId, subject);
    }
    
    @Override
    public void read() {
        for(Subject subject : subjectList.values()) {
            System.out.println(subject);
        }
    }

    @Override
    public void details() {
        String subjectId = Console.inputStr("Enter the code: ");
        
        if(!this.contains(subjectId)) {
            System.out.println("Subject does not exist");
            return;
        }
        
        System.out.println(subjectList.get(subjectId));
    }

    @Override
    public void update() {
        String subjectId = null; 
        boolean OK;
        
        OK = true;
        do {
            subjectId = Console.inputStr("Input subject id\n");
            try {
                if(!InputSubjectValidation.checkSubjectIdFormat(subjectId)) {
                    throw new Exception("Wrong id fromat!");
                }
                
                if(!this.contains(subjectId)) {
                    throw new Exception("Id does not exist!");
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid subject id");
                OK = false;
            }
        } while(!OK);
        
        try {
            update(subjectId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(String subjectId) {
        if(!this.contains(subjectId))
            throw new RuntimeException("Subject does not exist!");
        
        Subject subject = this.get(subjectId);
        
        String subjectName = subject.getSubjectName();
        int credit = subject.getCredit();
        
        String input;
        boolean OK;
        
        OK = true;
        do {
            input = Console.inputStr("Input subject name\n");
            if(input.isBlank()) break;
            try {
                if(!InputSubjectValidation.checkSubjectNameLength(input)) {
                    throw new Exception();
                }
                subjectName = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid subject name");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input credit\n");
            if(input.isBlank()) break;
            
            try {
                int inputConverted = Integer.parseInt(input);
                if(!InputSubjectValidation.checkCreditRange(inputConverted)) {
                    throw new Exception();
                }
                credit = inputConverted;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid credit");
                OK = false;
            }
        } while(!OK);
        
//        update:
        subject.setSubjectName(subjectName);
        subject.setCredit(credit);
    }

    @Override
    public void delete() {
        String subjectId = null; 
        boolean OK;
        
        OK = true;
        do {
            subjectId = Console.inputStr("Input subject id\n");
            try {
                if(!InputSubjectValidation.checkSubjectIdFormat(subjectId)) {
                    throw new Exception("Wrong id format!");
                }
                
                if(!this.contains(subjectId)) {
                    throw new Exception("Id does not exist!");
                }
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid subject id");
                OK = false;
            }
        } while(!OK);
        
        try {
            delete(subjectId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String subjectId) {
        if(!subjectList.containsKey(subjectId))
            throw new RuntimeException("Subject does not exist");
        
        subjectList.remove(subjectId);
    }
    
    /// LOGIC METHODS:
    public boolean contains(String subjectId) {
        return subjectList.containsKey(subjectId);
    }
    
    public Subject get(String subjectId) {
        return subjectList.get(subjectId);
    } 
    
    /// CRUD with parameters
    public void create(String subjectId, String subjectName, int credit) {
        if(this.contains(subjectId))
            throw new RuntimeException("Subject id duplicated!");
        
        subjectList.put(subjectId, new Subject(subjectId, subjectName, credit));
   }
}
