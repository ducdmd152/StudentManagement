// @author SE160104
package gui;
 
import java.util.List;
import java.util.Vector;
import repo.*;
import tools.Console;

public class SE160104 {
    static StudentRepository studentRepo = new StudentRepository();
    static SubjectRepository subjectRepo = new SubjectRepository();
    static GradeRepository gradeRepo = new GradeRepository(studentRepo, subjectRepo);
    
    static IMenu menu = new Menu();
    static List<IMenu> subMenus = new Vector();
    static IMenu subMenu1 = new Menu();
    static IMenu subMenu2 = new Menu();
    
    public static void main(String[] args) {
        menu.add("1. Add new student");
        menu.add("2. Update Student"
                + "\n  2.1 Update Student"
                + "\n  2.2 Delete Student");
        menu.add("3. Add new Subject");
        menu.add("4. Update Subject"
                + "\n  4.1 Update Subject"
                + "\n  4.2 Delete Subject");
        menu.add("5. Enter Grade");
        menu.add("6. Student Grade Report");
        menu.add("7. Subject Grade Report");
        menu.add("Others- Quit");
        
        subMenu1.add("2.1 Update Student");
        subMenu1.add("2.2 Delete Student");
        
        subMenu2.add("4.1 Update Subject");
        subMenu2.add("4.2 Delete Subject");
        
        int choice;
        do {
            menu.show();
            choice = menu.getChoice();
            
            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    addNewSubject();
                    break;
                case 4:
                    updateSubject();
                    break;
                case 5:
                    enterGrade();
                    break;
                case 6:
                    studentGradeReport();
                    break;
                case 7:
                    subjectGradeReport();
                    break;
                default:
            }
        } while(choice > 0 && choice < menu.size());
    }
    
    private static void addNewStudent() {
        studentRepo.create();
    }
    
    private static void updateStudent() {
        int choice;
        do {
            subMenu1.show();
            choice = subMenu1.getChoice();
            
            switch (choice) {
                case 1:
                studentRepo.update();
                break;
                case 2:
                    studentRepo.delete();
                    break;
            }
        } while(choice > 0 && choice <= subMenu1.size());
    }
    
    private static void addNewSubject() {
        subjectRepo.create();
    }
    
    private static void updateSubject() {
        int choice;
        do {
            subMenu2.show();
            choice = subMenu2.getChoice();
            
            switch (choice) {
                case 1:
                subjectRepo.update();
                break;
            case 2:
                subjectRepo.delete();
                break;
            }
        } while(choice > 0 && choice <= subMenu2.size());
    }
    
    private static void enterGrade() {
        gradeRepo.enterGrade();
    }
    
    private static void studentGradeReport() {
        gradeRepo.studentGradeReport();
    }
    
    private static void subjectGradeReport() {
        gradeRepo.subjectGradeReport();
    }
}
