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

![Project Structure]([https://s3-us-west-2.amazonaws.com/secure.notion-static.com/10b0103d-619e-4178-a02f-7604101b4b80/Untitled.png](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F10b0103d-619e-4178-a02f-7604101b4b80%2FUntitled.png?table=block&id=f7e95cfd-d3b6-4785-a7a0-97305129e17d&spaceId=1551a6a5-44bd-46da-8d5d-f7a8f68253e4&width=1540&userId=0d2bf151-ad58-4f63-a5d0-7f3a6dfc5763&cache=v2))
