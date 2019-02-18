package fr.miage.adrienaudouard.tp1.exercice2;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Cette ligne ne sera pas lu

public class SeLit {
    void lireFichiersJava() throws IOException {
        FilterListFiles filterListFiles = new FilterListFiles(".java");

        Files.walkFileTree(new File(".").toPath(), filterListFiles);

        ArrayList<String> fileToRead = filterListFiles.getListFile();

        for (String s : fileToRead) {
            System.out.println("Reading " + s + ":");

            File f = new File(s);
            Scanner sc = new Scanner(f);
            lecture(sc);

            System.out.println();
        }
    }

    void lireCeFichier() throws FileNotFoundException {
        File f = new File("./src/fr/miage/adrienaudouard/exercice2/SeLit.java");

        Scanner sc = new Scanner(f);
        lecture(sc);
    }

    void lecture(Scanner source) {
        Pattern p = Pattern.compile("^[\\s]*\\/\\/.*$");

        while(source.hasNextLine()) {
            // Cette ligne ne sera pas lu
            String s = source.nextLine();

            Matcher m = p.matcher(s);

            if (!m.matches()) {
                System.out.println("LU:"+s);
            }
        }
    }

    static public void main(String[] args) {
        SeLit seLit = new SeLit();

        try {
            seLit.lireCeFichier();
            seLit.lireFichiersJava();

            System.out.println("Ecriture dans un fichier");
            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("Output.txt"))));
            seLit.lireFichiersJava();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class FilterListFiles extends SimpleFileVisitor<Path> {
        private String filter;
        private ArrayList<String> listFile;
        public FilterListFiles(String filter) {
            this.filter = filter;
            this.listFile = new ArrayList<>();
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toString().endsWith(filter)) {
                listFile.add(file.toString());

            }

            return super.visitFile(file, attrs);
        }

        public ArrayList<String> getListFile() {
            return listFile;
        }
    }
}