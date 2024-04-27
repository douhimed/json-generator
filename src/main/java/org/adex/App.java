package org.adex;

import org.adex.services.JsonGenerator;
import org.adex.inputs.ObjectMetaData;
import org.adex.inputs.ObjectType;

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

        final ObjectMetaData otherAddress = ObjectMetaData.builder()
                .type(ObjectType.ARRAY)
                .name("otherAddress")
                .min(1)
                .max(1)
                .children(Set.of(address))
                .build();

        final ObjectMetaData person = ObjectMetaData.builder()
                .name("person")
                .type(ObjectType.OBJECT)
                .children(Set.of(nom, prenom, childrenNumber, prenoms, address, statusPerso, otherAddress))
                .build();

        System.out.println(JsonGenerator.generate(person));

    }
}