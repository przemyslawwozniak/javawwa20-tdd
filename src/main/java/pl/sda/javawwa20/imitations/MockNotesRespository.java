package pl.sda.javawwa20.imitations;

import java.util.*;

public class MockNotesRespository implements NotesRepository {

    private Map<String, List<Note>> nameToNotesMap = new HashMap<>();

    @Override
    public void save(Note note) {
        //1. uczen juz istnieje
        if(nameToNotesMap.containsKey(note.getFullName())) {
            List<Note> memberNotes = nameToNotesMap.get(note.getFullName());
            memberNotes.add(note);
            nameToNotesMap.put(note.getFullName(), memberNotes);
        }
        //2. zupelnie nowy uczen
        else {
            //aby nie tworzyc niemutowalnej listy
            List<Note> notes = new ArrayList<>();
            notes.add(note);
            nameToNotesMap.put(note.getFullName(), notes);
        }
    }

    @Override
    public List<Note> getAllNotesOf(String fullName) {
        return nameToNotesMap.get(fullName);
    }

    @Override
    public void removeAll() {
        nameToNotesMap.clear();
    }
}
