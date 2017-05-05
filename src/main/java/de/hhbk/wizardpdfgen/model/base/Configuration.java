package de.hhbk.wizardpdfgen.model.base;

/**
 * Created by monikaschepan on 05.05.17.
 */
public class Configuration {

    String name;
    Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Configuration(Integer id, String name) {
        this.name = name;
        this.id = id;


    }

    @Override
    public String toString() {
        return "ID " + id + ":" + "                 " + "Name: " + name ;
    }
}
