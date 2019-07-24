package com.salilvnair.day1;

import java.util.HashSet;
import java.util.Set;

public final class Employee {

    private String name;
    private Set<String> languages;

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public Employee setLanguages(Set<String> languages) {
        this.languages = languages;
        return this;
    }

    public Employee addLanguage(String language) {
        if(this.languages == null) {
            this.languages = new HashSet<>(1);
        }
        this.languages.add(language);
        return this;
    }

    public Set<String> getLanguages() {
        return this.languages;
    }


}
