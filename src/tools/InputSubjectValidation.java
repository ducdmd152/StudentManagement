/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.util.regex.Pattern;

/**
 *
 * @author MSI
 */
public class InputSubjectValidation {
    public static boolean checkSubjectNameLength(String subjectName) {
        if(subjectName.isBlank()) return false;
        return subjectName.length() <= 10;
    }

    public static boolean checkCreditRange(int credit) {
        return credit > 0 && credit <=6;
    }

    public static boolean checkSubjectIdFormat(String subjectId) {
        return subjectId.matches("^SUB\\d{2}$");
    }
}
