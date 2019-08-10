package pl.sda.javawwa20.imitations;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DefaultNotesServiceTest {

    private NotesRepository notesRepository = new MockNotesRespository();
    private NotesService notesService = DefaultNotesService.createWith(notesRepository);

    //dodac poprawna ocene i sprawdzic czy zostala dodana
    @Test
    public void add() {
        //given
        Note exampleNote = Note.of("PW", 5.0);
        //when
        notesService.add(exampleNote);
        //then
        List<Note> pwNotes = notesRepository.getAllNotesOf("PW");
        assertNotNull(pwNotes);
        assertEquals(1, pwNotes.size());
        assertEquals(5.0, pwNotes.get(0).getScore(), 0.0001);
    }

    //sprobuj jako parametr przekazac null
    @Test(expected = IllegalArgumentException.class)
    public void add_null_note() {
        notesService.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void average_of_null() {
        notesService.averageOf(null);
    }

    //probujecie policzyc srednia ocen dla nieistniejacego ucznia
    @Test(expected = NoSuchUserException.class)
    public void average_of_non_existent() {
        notesService.averageOf("Michal P");
    }

    //sprawdzacie czy prawidlowo liczy srednia dla istniejacego ucznia
    @Test
    public void average_of_existing() {
        notesService.add(Note.of("PW", 3.5));
        notesService.add(Note.of("PW", 2.5));
        notesService.add(Note.of("PW", 3));

        assertEquals(3.0, notesService.averageOf("PW"), 0.0001);
    }

    //dodaj 2 uczniow i ich oceny
    //odczytaj ze sa
    //zrob clear
    //sprobuj odczytac ponownie
    @Test
    public void clear() {
        //given
        notesService.add(Note.of("PW", 3.5));
        notesService.add(Note.of("AB", 5.0));

        assertEquals(3.5, notesRepository.getAllNotesOf("PW").get(0).getScore(), 0.0001);
        assertEquals(5.0, notesRepository.getAllNotesOf("AB").get(0).getScore(), 0.0001);
        //when
        notesService.clear();
        //then
        assertNull(notesRepository.getAllNotesOf("PW"));
    }

}
