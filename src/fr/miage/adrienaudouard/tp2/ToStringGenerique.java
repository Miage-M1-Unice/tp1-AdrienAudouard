package fr.miage.adrienaudouard.tp2;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class ToStringGenerique {
    public String toStringGenerique(Object object, int profondeur) throws IllegalAccessException {
        if (profondeur == 0) {
            return object.getClass().getName() + "@" + System.identityHashCode(object);
        }

        String toString = "";

        Class cl = object.getClass();

        toString += cl.getName();

        toString += "[";

        ArrayList<Field> fieldsList = getAllFields(cl);

        for (int i = 0; i < fieldsList.size(); i++) {
            Field field = fieldsList.get(i);
            field.setAccessible(true);

            Object fieldObject = field.get(object);

            toString += field.getName();
            toString += "=";

            if (fieldObject != null) {
                if (field.getType().isPrimitive()) {
                    toString += fieldObject;
                } else if (field.getType().isArray()) {
                    toString = arrayToString(toString, fieldObject);

                } else {
                    toString += toStringGenerique(fieldObject, profondeur - 1);
                }

            } else {
                toString += "null";
            }


            if ((i + 1) != fieldsList.size()) {
                toString += ";";
            }

        }
        
        toString += "]";

        return toString;
    }

    private String arrayToString(String toString, Object fieldObject) {
        int length = Array.getLength(fieldObject);
        toString += "{";
        for (int j = 0; j < length; j++) {
            toString += Array.get(fieldObject, j);
            if ((j+1) != length) {
                toString += ",";
            }
        }
        toString += "}";
        return toString;
    }

    private ArrayList<Field> getAllFields(Class cl) {
        ArrayList<Field> fieldsList = new ArrayList<>();

        fieldsList.addAll(Arrays.asList(cl.getDeclaredFields()));

        for (Field field : cl.getFields()) {
            if (!contains(fieldsList, field)) {
                fieldsList.add(field);
            }
        }
        return fieldsList;
    }

    private Boolean contains(ArrayList<Field> list, Field field) {
        for (Field field1 : list) {
            if (field.getName().equals(field1.getName())) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20,30, 40}, 3);
        pol.getBounds();

        try {
            System.out.println(new ToStringGenerique().toStringGenerique(pol, 2));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
