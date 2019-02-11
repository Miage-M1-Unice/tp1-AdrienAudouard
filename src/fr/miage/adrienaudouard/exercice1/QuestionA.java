package fr.miage.adrienaudouard.exercice1;

import java.io.File;
import java.io.FilenameFilter;

public class QuestionA {
    public static void main(String[] args) {

        QuestionA questionA = new QuestionA();

        System.out.println("Question 1.a:");
        questionA.listerRepertoireCourant();

        System.out.println("");

        System.out.println("Question 1.b:");
        questionA.parcoursEnProfondeur();

        System.out.println("");

        System.out.println("Question 1.c:");
        System.out.println("Classe interne:");
        questionA.parcourirAvecFilter(new File("."), questionA.new InnerClassFileNameFilter(".java"));
        System.out.println("\nClasse anonyme:");
        questionA.parcourirAvecFilter(new File("."), new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                File f = new File(dir.getAbsolutePath() + "/" + name);

                if (f.isDirectory()) {
                    return true;
                }

                return name.toLowerCase().endsWith(".java");
            }
        });

        System.out.println("\nClasse normale:");

        questionA.parcourirAvecFilter(new File("."), new MyFileFilter());
    }

    public void listerRepertoireCourant() {
        File f = new File(".");

        String[] contenu = f.list();

        for (String s : contenu) {
            System.out.println(s);
        }
    }

    public void parcoursEnProfondeur() {
        File f = new File(".");

        parcourir(f, "");
    }

    public void parcourirAvecFilter(File f, FilenameFilter filenameFilter) {
        File[] files = f.listFiles(filenameFilter);

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                parcourirAvecFilter(file, filenameFilter);
            } else {
                System.out.print(file.getName() + "\n");
            }
        }
    }

    public void parcourir(File f, String prefix) {
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

    public class InnerClassFileNameFilter implements FilenameFilter {
        private String filter;

        InnerClassFileNameFilter(String filter) {
            this.filter = filter;
        }

        @Override
        public boolean accept(File dir, String name) {
            File f = new File(dir.getAbsolutePath() + "/" + name);

            if (f.isDirectory()) {
                return true;
            }

            return name.toLowerCase().endsWith(filter);
        }
    }
}
