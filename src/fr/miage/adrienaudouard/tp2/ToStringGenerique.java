package fr.miage.adrienaudouard.tp2;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ToStringGenerique {
    public String toStringGenerique(Object object, int profondeur) throws IllegalAccessException {
        if (profondeur == 0) {
            return object + "";
        }

        String toString = "";

        Class cl = object.getClass();

        toString += cl.getName();

        toString += "[";

        Field[] fields = cl.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            Object fieldObject = field.get(object);

            toString += field.getName();
            toString += "=";

            if (fieldObject != null) {
                if (field.getType().isPrimitive()) {
                    toString += fieldObject;
                } else if (field.getType().isArray()) {
                    int length = Array.getLength(fieldObject);
                    toString += "{";
                    for (int j = 0; j < length; j++) {
                        toString += Array.get(fieldObject, j);
                        if ((j+1) != length) {
                            toString += ",";
                        }
                    }
                    toString += "}";

                } else {
                    toString += toStringGenerique(fieldObject, profondeur - 1);
                }

            } else {
                toString += "null";
            }



            if ((i + 1) != fields.length) {
                toString += ";";
            }

        }
        
        toString += "]";

        return toString;
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
