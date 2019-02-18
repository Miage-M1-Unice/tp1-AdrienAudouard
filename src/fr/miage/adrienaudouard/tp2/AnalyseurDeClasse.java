package fr.miage.adrienaudouard.tp2;

/**
 * @version 1.00 23 Mars 2001
 * @author Michel Buffa
 * Inspiré par la classe Reflectiontest.java de
 * Cay S. Horstmann & Gary Cornell, publiée dans le livre Core Java, Sun Press
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AnalyseurDeClasse {

    public static void analyseClasse(String nomClasse) throws ClassNotFoundException {
        // Récupération d'un objet de type Class correspondant au nom passé en paramètres
        Class cl = getClasse(nomClasse);

        afficheEnTeteClasse(cl);

        System.out.println();
        afficheAttributs(cl);

        System.out.println();
        afficheConstructeurs(cl);

        System.out.println();
        afficheMethodes(cl);

        // L'accolade fermante de fin de classe !
        System.out.println("}");
    }


    /**
     * Retourne la classe dont le nom est passé en paramètre
     */
    public static Class getClasse(String nomClasse) throws ClassNotFoundException {
        return Class.forName(nomClasse);
    }

    /**
     * Cette méthode affiche par ex "public class Toto extends Tata implements Titi, Tutu {"
     */
    public static void afficheEnTeteClasse(Class cl) {

        //  Affichage du modifier et du nom de la classe
        // CODE A ECRIRE

        int modifiersValue = cl.getModifiers();
        String modifiers = Modifier.toString(modifiersValue);

        System.out.print(modifiers + " ");
        System.out.print(cl.getName());

        // Récupération de la superclasse si elle existe (null si cl est le type Object)
        Class supercl = cl.getSuperclass(); // CODE A ECRIRE

        if (supercl != null && supercl.getSuperclass() != null) {
            System.out.print(" extends ");
            System.out.print(supercl.getName());
        }

        Class[] interfaces = cl.getInterfaces();

        if (interfaces.length != 0) {
            System.out.print(" implements");
            for (int i = 0; i < interfaces.length; i++) {
                Class interf = interfaces[i];

                System.out.print(" " + interf.getName());

                if ((i + 1) != interfaces.length) {
                    System.out.print(",");
                }
            }
        }

        System.out.print(" {\n");
    }

    public static void afficheAttributs(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            System.out.print("\t");
            System.out.print(field.toString());
            System.out.println(";");
        }
    }

    public static void afficheConstructeurs(Class cl) {
        Constructor[] constructors = cl.getConstructors();

        for (Constructor constructor : constructors) {
            System.out.print("\t");
            System.out.print(constructor.toString());
            System.out.println(";");
        }
    }

    public static void afficheMethodes(Class cl) {
        Method[] methods = cl.getMethods();

        for (Method method : methods) {
            System.out.print("\t");
            System.out.print(method.toString());
            System.out.println(";");
        }

    }


    public static String litChaineAuClavier() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static void main(String[] args) {
        boolean ok = false;

        while (!ok) {
            try {
                System.out.print("Entrez le nom d'une classe (ex : java.util.Date): ");
                String nomClasse = litChaineAuClavier();

                analyseClasse(nomClasse);

                ok = true;
            } catch (ClassNotFoundException e) {
                System.out.println("Classe non trouvée.");
            } catch (IOException e) {
                System.out.println("Erreur d'E/S!");
            }
        }
    }
}
