package pl.sda.javawwa20.imitations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            nameToNotesMap.put(note.getFullName(), Arrays.asList(note));
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
