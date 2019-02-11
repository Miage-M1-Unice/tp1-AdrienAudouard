package fr.miage.adrienaudouard.exercice1;

import java.io.File;
import java.util.Arrays;

public class QuestionA {
    public static void main(String[] args) {

        System.out.println("Question 1.a:");
        listerRepertoireCourant();

        System.out.println("Question 1.b:");
        parcoursEnProfondeur();
    }

    private static void listerRepertoireCourant() {
        File f = new File(".");

        String[] contenu = f.list();

        for (String s : contenu) {
            System.out.println(s);
        }
    }

    private static void parcoursEnProfondeur() {
        File f = new File("");

        f.listFiles();

        System.out.println();
    }
}
