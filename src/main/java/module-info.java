module Malak_Khaled {
    requires javafx.fxml;
    requires java.xml;
    requires javafx.base;
    requires javafx.controls;
    requires mongo.java.driver;
    requires core;
    requires junit;
    requires java.sql;
    opens Malak_Khaled;
    opens GUI;
}