package org.adex;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.adex.services.JsonGenerator;
import org.adex.services.ObjectMetaData;
import org.adex.services.ObjectType;

import java.lang.reflect.Field;
import java.util.*;

public class App {
    public static void main(String[] args) {

        final ObjectMetaData nom = ObjectMetaData.builder()
                .name("nom")
                .type(ObjectType.STRING)
                .min(2)
                .max(10)
                .upperCase(true)
                .build();

        final ObjectMetaData prenom = ObjectMetaData.builder()
                .name("prenom")
                .type(ObjectType.STRING)
                .min(2)
                .max(10)
                .build();

        final ObjectMetaData childrenNumber = ObjectMetaData.builder()
                .name("childrenNumber")
                .type(ObjectType.NUMBER)
                .min(0)
                .max(5)
                .build();

        final ObjectMetaData prenoms = ObjectMetaData.builder()
                .name("prenoms")
                .type(ObjectType.ARRAY)
                .min(2)
                .max(10)
                .children(Set.of(prenom))
                .build();

        final ObjectMetaData city = ObjectMetaData.builder()
                .name("city")
                .type(ObjectType.STRING)
                .min(3)
                .max(20)
                .upperCase(true)
                .build();


        final ObjectMetaData zipCode = ObjectMetaData.builder()
                .name("zipCode")
                .type(ObjectType.NUMBER)
                .min(10000)
                .max(99999)
                .build();

        final ObjectMetaData address = ObjectMetaData.builder()
                .type(ObjectType.OBJECT)
                .name("address")
                .children(Set.of(city, zipCode))
                .build();

        final ObjectMetaData statusPerso = ObjectMetaData.builder()
                .type(ObjectType.BOOLEAN)
                .name("single")
                .build();

        final ObjectMetaData person = ObjectMetaData.builder()
                .name("person")
                .type(ObjectType.OBJECT)
                .children(Set.of(nom, prenom, childrenNumber, prenoms, address, statusPerso))
                .build();

        System.out.println(JsonGenerator.generate(person));

    }
}


class Main {
    public static void main(String[] args) {


        final Student student = new Student();
        student.setId(1000L);
        student.setName("John Doe");
        student.setCodeEtudiant("Code Test");

        final Address address = new Address("Oujda", 60000);
        student.setAddress(address);

        final Map<String, String> fields = mapObjectFields(student);

        System.out.println(fields);
    }

    @Getter
    @Setter
    static abstract class Person {
        private Long id;
        private String name;

        private List<String> prenoms = List.of("med", "med");
        private Address address;
    }

    @Getter
    @Setter
    static class Student extends Person {
        private String codeEtudiant;
    }

    @Getter
    @Setter
    static class Address {
        private String city;
        private int zipCode;

        public Address(String city, int zipCode) {
            this.city = city;
            this.zipCode = zipCode;
        }
    }

    public static Map<String, String> mapObjectFields(Object obj) {
        Map<String, String> fieldMap = new HashMap<>();
        mapObjectFieldsRecursive(obj, "", fieldMap);
        return fieldMap;
    }

    private static boolean isBoxedPrimitive(Class<?> type) {
        return (type == Integer.class || type == Long.class || type == Float.class ||
                type == Double.class || type == Boolean.class || type == Character.class ||
                type == Short.class || type == Byte.class);
    }

    private static void mapObjectFieldsRecursive(Object obj, String prefix, Map<String, String> fieldMap) {
        Class<?> clazz = obj.getClass();

        while (Objects.nonNull(clazz)) {

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {

                if (!field.getDeclaringClass().getPackageName().contains("org.adex"))
                    continue;

                field.setAccessible(true);
                try {
                    Object value = field.get(obj);

                    if (value != null) {
                        String fieldName = field.getName();
                        String fullPath = prefix.isEmpty() ? fieldName : prefix + "." + fieldName;
                        if (!field.getType().isPrimitive() && !field.getType().equals(String.class) && !isBoxedPrimitive(field.getType())) {
                            mapObjectFieldsRecursive(value, fullPath, fieldMap);
                        } else {
                            fieldMap.put(fullPath, value.toString());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            final Class<?> superclass = clazz.getSuperclass();
            clazz = superclass.getPackageName().startsWith("org.adex")
                    ? superclass
                    : null;
        }
    }
}


