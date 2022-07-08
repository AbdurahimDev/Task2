package uz.formal.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.formal.task2.payload.req.JournalDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.service.JournalService;

@RestController
@RequestMapping("/api/journal")
public class JournalController {

    @Autowired
    JournalService journalService;

    @PostMapping()
    public HttpEntity<?> addJournal(@RequestBody JournalDTO journalDTO){
        ApiResponse response = journalService.save(journalDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping()
    public HttpEntity<?> getAll(){
        ApiResponse response = journalService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneById(@PathVariable Integer id){
        ApiResponse response = journalService.getOneById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody JournalDTO journalDTO){
        ApiResponse response = journalService.update(id,journalDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = journalService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
