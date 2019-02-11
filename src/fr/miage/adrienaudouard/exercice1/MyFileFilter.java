package fr.miage.adrienaudouard.exercice1;

import java.io.File;
import java.io.FilenameFilter;

public class MyFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        File f = new File(dir.getAbsolutePath() + "/" + name);

        if (f.isDirectory()) {
            return true;
        }

        return name.toLowerCase().endsWith(".java");
    }
}
