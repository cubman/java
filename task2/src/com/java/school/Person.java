package com.java.school;

/*
 класс персона
 */
public class Person {
    private final boolean man; // пол персоны
    private final String name; // имя персоны

    private  Person spouse; // тот, с кем в браке

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
        spouse = null;
    }

    public boolean isMan() {
        return man;
    }

    public String getName() {
        return name;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */

    public boolean marry(Person person) {
        if((isMan() ^ person.isMan()) && person.getSpouse() != this)
        {
            // если состоим в браке, то разводимся
            if(getSpouse() != null && getSpouse().divorce())
                divorce();

            // если пара в барке, то разводим
            if(person.getSpouse() != null && person.getSpouse().divorce())
                person.divorce();

            // сводим
            person.setSpouse(this);
            setSpouse(person);

            return  true;
        }

        return  false;
    }

    /**
     * Sets spouse = null if spouse is not null
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        boolean result = spouse != null;

        spouse = null;

        return result;
    }

    @Override
    public String toString() {
        return (man ? "мужчина " : "женщина ") + name + (spouse != null ? " в браке с " + spouse.getName() : "");
    }
}
