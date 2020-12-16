package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity.*;

import java.util.List;

@RestController
//@RequestMapping("/time-entries")
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntryCreated = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntryCreated);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntryCreated = timeEntryRepository.find(timeEntryId);

        if(timeEntryCreated == null){
            return ResponseEntity.notFound().build();
        }else
            return ResponseEntity.ok(timeEntryCreated);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
           List<TimeEntry> timeEntryCreated = timeEntryRepository.list();
        return ResponseEntity.ok(timeEntryCreated);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntryCreated = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if(timeEntryCreated == null){
            return ResponseEntity.notFound().build();
        }else
            return ResponseEntity.ok(timeEntryCreated);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
