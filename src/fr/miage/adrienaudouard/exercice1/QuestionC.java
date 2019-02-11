package fr.miage.adrienaudouard.exercice1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionC {
    public static void main(String[] main) {
        QuestionC questionC = new QuestionC();

        questionC.parcourirAvecFilter(new File("."), questionC.new MyFileFilter());
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
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    class MyFileFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            File f = new File(dir.getAbsolutePath() + "/" + name);

            if (f.isDirectory()) {
                return true;
            }

            Pattern p = Pattern.compile(".*/src.*");
            Matcher m = p.matcher(dir.getAbsolutePath()) ;

            return m.matches();
        }
    }
}
