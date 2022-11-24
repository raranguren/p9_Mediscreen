package com.mediscreen.abernathy.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/patHistory")
public class PatientHistoryController {

    private final NoteService service;
    @Autowired
    public PatientHistoryController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/patient/{id}")
    public List<NoteDTO> notesByPatientId(@PathVariable Long id) {
        return service.findAllByPatientId(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String add(@RequestBody NoteDTO note) {
        if (note.id != null) throw new ValidationException("id != null");
        if (note.patId == null) throw new ValidationException("patId == null");
        if (note.e == null) throw new ValidationException("e == null");
        service.create(note);
        return "Created";
    }

    @PostMapping("/update")
    public String update(@RequestBody NoteDTO note) throws IdNotFoundException {
        if (note.id == null) throw new ValidationException("id == null");
        if (note.e == null) throw new ValidationException("e == null");
        service.update(note); //throws IdNotFoundException
        return "OK";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "OK";
    }

    // Handlers for MediaType x-www-form-urlencoded

    @PostMapping(path="/add", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String addUrlEncoded(Long patId, String e) {
        return this.add(new NoteDTO(null, patId, e));
    }

    @PostMapping(path="/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateUrlEncoded(String id, String e) throws IdNotFoundException {
        return this.update(new NoteDTO(id, null, e));
    }

    // Return 400 when not valid

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException() {
        return "Bad request";
    }

    // Return 404 when not found

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleIdNotFoundException() {
        return "Not found";
    }

}
