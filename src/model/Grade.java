/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI
 */
public class Grade {
    private float lab;
    private float progressTest;
    private float finalExam;

    public Grade() {
    }

    public Grade(float lab, float progressTest, float finalExam) {
        this.lab = lab;
        this.progressTest = progressTest;
        this.finalExam = finalExam;
    }

    public float getLab() {
        return lab;
    }

    public void setLab(float lab) {
        this.lab = lab;
    }

    public float getProgressTest() {
        return progressTest;
    }

    public void setProgressTest(float progressTest) {
        this.progressTest = progressTest;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam = finalExam;
    }

    @Override
    public String toString() {
        return lab + ", " + progressTest + ", " + finalExam + ", " + getAvarageMark() + ", " + getStatus();
    }

//    LOGIC METHODS:
    public float getAvarageMark() {
        return (30*lab + 30*progressTest + 40*finalExam)/100;
    }
    
    public boolean isPassed() {
        return getAvarageMark()>4;
    }
    
    public String getStatus() {
        return isPassed() ? "Pass" : "Not Pass";
    }
}
