## Endpoints for User

| Request Type  |URL   | Description |
| ------------ | ------------ | ------------ |
| POST| /register  | User registration |
| GET| /users/{id}  | Retrieves user information |
| PUT| /users/{id}  | Updates user information |
| DELETE| /users/{id}  | Deletes a user |


## Endpoints for Notes

| Request Type  |URL   | Description |
| ------------ | ------------ | ------------ |
| POST| /authenticate  | Returns a JWT upon sucessfully authentication |
| GET| /users/{id}/notes  | Retrieves notes of an authenticate user |
| POST| /users/{id}/notes  | Creates a new note by an authenticated user |
| GET| /users/{id}/notes/{noteId} | Retrieves a particular note by an authenticated user |
| PUT| /users/{id}/notes/{noteId} | Updates a particular by an authenticated user |
| DELETE| /users/{id}/notes/{noteId}| Deletes a particular note by an authenticated user

## Endpoints for Profile
| Request Type  |URL   | Description |
| ------------ | ------------ | ------------ |
| GET| /users/{id}/profile  | Retrieves user profile information |
| PUT| /users/{id}/notes  | Updates user profile information |

## Sample codes
<details>
  <summary>Click to show Note Controller Code</summary>
  
  
```java
@RestController
public class NoteController {

    private final NoteService noteService;
    private final NoteRepository noteRespo;
    private final UserRepository userRespo;

    NoteController(NoteRepository noteRespo, NoteService noteService, UserRepository userRespo) {
        this.noteRespo = noteRespo;
        this.noteService= noteService;
        this.userRespo=userRespo;
    }

    /***
     * Get notes by user id
     * Only authorized user can read their own notes
     * @param id
     * @return
     */
    @PreAuthorize("#id == principal.id")
    @GetMapping("/users/{id}/notes")
    Collection<Note> getUserNotes(@PathVariable Long id) {
        return noteRespo.findNotesByUserId(id);
    }

    /**
     * Search One Single Note only
     * @param id
     * @param noteId
     * @return
     */
    @PreAuthorize("#id == principal.id")
    @GetMapping("/users/{id}/notes/{noteId}")
    Note findOne(@PathVariable("id")Long id,@PathVariable("noteId") Long noteId) {
        return noteRespo.findById(noteId)
                .orElseThrow(() -> new NoteNotFoundException(String.valueOf(id)));
    }

    /**
     * Only authorized user can create thiern own note
     * @param notePayload
     * @return
     */
    @PreAuthorize("#id == principal.id")
    @PostMapping("/users/{id}/notes")
    Note newNote(@PathVariable Long id, @RequestBody NotePayload notePayload) {
        //Optional<User> authenticatedUser = userRespo.findById(id);
        User user = userRespo.findById(id).orElse(null);
        Note newNote = new Note();
        BeanUtils.copyProperties(notePayload,newNote);
        user.addNote(newNote);
        newNote.setUser(user);
        return noteRespo.save(newNote);
    }

    /**
     * Only authenticated user can update their own note
     * @param notePayload
     * @param id
     * @param noteId
     * @return
     */
    @PreAuthorize("#id == principal.id")
    @PutMapping("/users/{id}/notes/{noteId}")
    Optional<Note> updateNote(@RequestBody NotePayload notePayload, @PathVariable("id") Long id, @PathVariable("noteId") Long noteId) {
        return noteRespo.findById(noteId)
                .map(targetNote -> {
                    targetNote.setContent(notePayload.getContent());
                    targetNote.setDatePosted(Calendar.getInstance());
                    return noteRespo.save(targetNote);
                });
    }

    /**
     * Only user can delete their own note
     * @param id
     * @param noteId
     */
    @PreAuthorize("#id == principal.id")
    @DeleteMapping("/users/{id}/notes/{noteId}")
    void deleteNote(@PathVariable("id") Long id,@PathVariable("noteId") Long noteId) {
        noteRespo.deleteById(noteId);
    }

    /**
     * Only admin has the permission to check all the notes using the pagination feature
     * @param offset
     * @param pageSize
     * @return
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/notes/pagination/{offset}/{pageSize}")
    private Page<Note> pagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Note> notesWithPagination = noteService.findNotesWithPagination(offset,pageSize);
        return notesWithPagination;
    }
}
```
</details>
