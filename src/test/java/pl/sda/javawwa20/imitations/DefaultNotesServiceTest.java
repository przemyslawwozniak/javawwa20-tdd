package pl.sda.javawwa20.imitations;

import org.junit.Test;

public class DefaultNotesServiceTest {

    private NotesService notesService = DefaultNotesService.createWith(new MockNotesRespository());

    @Test
    public void add() {
        //dodac poprawna ocene i sprawdzic czy zostala dodana
    }

    @Test
    public void add_null_note() {
        //sprobuj jako parametr przekazac null
    }

    @Test
    public void average_of_null() {
        //jako parametr null
    }

    @Test
    public void average_of_non_existent() {
        //probujecie policzyc srednia ocen dla nieistniejacego ucznia
    }

    @Test
    public void average_of_existing() {
        //sprawdzacie czy prawidlowo liczy srednia dla istniejacego ucznia
    }

    @Test
    public void clear() {
        //dodaj 2 uczniow i ich oceny
        //odczytaj ze sa
        //zrob clear
        //sprobuj odczytac ponownie
    }

}
