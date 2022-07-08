package uz.formal.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.formal.task2.entity.Subject;
import uz.formal.task2.payload.req.FacultyDTO;
import uz.formal.task2.payload.req.SubjectDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.service.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping()
    public HttpEntity<?> addSubjects(@RequestBody SubjectDTO subjectDTO){
        ApiResponse response = subjectService.save(subjectDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping()
    public HttpEntity<?> getAll(){
        ApiResponse response = subjectService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneById(@PathVariable Integer id){
        ApiResponse response = subjectService.getOneById(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody SubjectDTO subjectDTO){
        ApiResponse response = subjectService.update(id,subjectDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = subjectService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

}
