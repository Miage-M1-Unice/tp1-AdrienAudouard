package fr.miage.adrienaudouard.tp3;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomURLClassLoader {
    public static void main(String args[]) throws MalformedURLException, ClassNotFoundException {
        URL classUrl = new URL("file:///Users/adrien/Documents/Cours/L3/S5/POO/TP1/out/production/TP1/");

        URL[] urls = { classUrl };

        URLClassLoader ucl = new URLClassLoader(urls);

        Class c = ucl.loadClass("Exo4.MoyennePOO"); // LINE 14
        for(Field f: c.getDeclaredFields()) {
            System.out.println("Field name " + f.getName());
        }
    }
}
