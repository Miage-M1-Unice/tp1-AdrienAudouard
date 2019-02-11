package fr.miage.adrienaudouard.exercice1;

import java.io.File;

public class QuestionA {
    public static void main(String[] args) {

        System.out.println("Question 1.a:");
        listerRepertoireCourant();

        System.out.println("");

        System.out.println("Question 1.b:");
        parcoursEnProfondeur();

        System.out.println("");

        System.out.println("Question 1.c:");
    }

    private static void listerRepertoireCourant() {
        File f = new File(".");

        String[] contenu = f.list();

        for (String s : contenu) {
            System.out.println(s);
        }
    }

    private static void parcoursEnProfondeur() {
        File f = new File(".");

        parcourir(f, "");
    }



    private static void parcourir(File f, String prefix) {
        File[] files = f.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            System.out.print(prefix);
            System.out.print(file.isDirectory() ? " + " : " - ");
            System.out.print(file.getName() + "\n");

            if (file.isDirectory()) {
                parcourir(file, prefix+"\t");
            }
        }
    }
}
