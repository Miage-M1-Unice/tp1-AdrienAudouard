package fr.miage.adrienaudouard.tp1.exercice1;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class QuestionB {
    public static void main(String[] args) {
        QuestionB questionB = new QuestionB();

        try {
            questionB.parcourir();
            questionB.parcourirAvecFilter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parcourir() throws IOException {
        Files.walkFileTree(new File(".").toPath(),  new ListFiles());
    }

    public void parcourirAvecFilter() throws IOException {
        Files.walkFileTree(new File(".").toPath(), new FilterListFiles(".java"));
    }

    class FilterListFiles extends SimpleFileVisitor<Path> {
        private String filter;

        public FilterListFiles(String filter) {
            this.filter = filter;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toString().endsWith(filter)) {
                System.out.println(file);
            }
            return super.visitFile(file, attrs);
        }
    }

    class ListFiles extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println(file);
            return super.visitFile(file, attrs);
        }
    }
}
