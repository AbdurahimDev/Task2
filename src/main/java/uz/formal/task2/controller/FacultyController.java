package uz.formal.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.formal.task2.payload.req.FacultyDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.service.FacultyService;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @PostMapping()
    public HttpEntity<?> addFaculty(@RequestBody FacultyDTO facultyDTO){
        ApiResponse response = facultyService.save(facultyDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping()
    public HttpEntity<?> getAll(){
        ApiResponse response = facultyService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneById(@PathVariable Integer id){
        ApiResponse response = facultyService.getOneById(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody FacultyDTO facultyDTO){
        ApiResponse response = facultyService.update(id,facultyDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = facultyService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/{id}/groups")
    public HttpEntity<?> groupsOfFaculty(@PathVariable Integer id){
        ApiResponse response = facultyService.groupsOfFaculty(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

}
