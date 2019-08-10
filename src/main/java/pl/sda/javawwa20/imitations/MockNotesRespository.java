package pl.sda.javawwa20.imitations;

import java.util.List;

public class MockNotesRespository implements NotesRepository {

    @Override
    public void save(Note note) {
        
    }

    @Override
    public List<Note> getAllNotesOf(String fullName) {
        return null;
    }

    @Override
    public void removeAll() {

    }
}
