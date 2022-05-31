/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author MSI
 */
public class KeyGrade {
    private String studentId;
    private String subjectId;    

    public KeyGrade() {
    }
    
    
    public KeyGrade(String studentId, String subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return studentId + ", " + subjectId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.studentId);
        hash = 41 * hash + Objects.hashCode(this.subjectId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KeyGrade other = (KeyGrade) obj;
        
        if(!this.studentId.equals(other.studentId)) return false;
        if(!this.subjectId.equals(other.subjectId)) return false;
        
        return true;
    }
    
    
}
