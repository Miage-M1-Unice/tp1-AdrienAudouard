package fr.miage.adrienaudouard.tp3;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;

public class MyClassLoader extends SecureClassLoader {
    private ArrayList<File> path;
    public MyClassLoader(ArrayList<File> p) {
        this.path = p;
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = new byte[0];
        try {
            b = loadClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) throws ClassNotFoundException, IOException {
        for (int i = 0; i < path.size(); i++) {
            String url = path.get(i).getAbsolutePath() + "/" + name.replace(".", "/") + ".class";
            File f = new File(url);

            if (f.exists()) {
                return Files.readAllBytes(f.toPath());
            }
        }


        throw new ClassNotFoundException(name);
    }

    public static void main(String[] args) {
        ArrayList<File> al = new ArrayList<File>();

        for (String arg : args) {
            File f = new File(arg);

            if (f.isDirectory()) {
                al.add(new File(arg));
            }
        }

        MyClassLoader myCL = new MyClassLoader(al);
        try {
            Class cl = myCL.loadClass("Exo4.MoyennePOO");

            for(Field f: cl.getDeclaredFields()) {
                System.out.println("Field name " + f.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}