## Student Management Console Application
### 1. Functions:
    - CRUD with student list.
    - CRUD with subject list.
    - CRUD with grade list.
    - GUI:
        - Add a new student.
        - Update/delete already student.
        - Add a new subject
        - Update/delete already subject.
        - Enter new grade.
        - Student Grade Report
        - Subject Grade Report.
### 2. Data Format
- **Student**(
    - **StudentId**: String with pattern “STUxxx”, x is a digit in 0..9
    - **FirstName**: String
    - **LastName**: String
    - **Gender**: True/False ~ Male/Female
    - **Date of birth**: date matches with “dd/MM/yyyy”
    - **Email**: String
    - **PhoneNumber**: String
    
    )
    
- **Subject**(
    - **SubjectId**: String String with pattern “SUBxx”, x is a digit in 0..9
    - SubjectName: String
    - Credit: integer
    
    )
    
- **Grade**(
    - **StudentId**
    - **SubjectId**
    - **Lab:** int
    - **ProgressTest:** int
    - **FinalExam:** int
    
    )
    
### 3. Project Structure: using **repository pattern**

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/10b0103d-619e-4178-a02f-7604101b4b80/Untitled.png)
