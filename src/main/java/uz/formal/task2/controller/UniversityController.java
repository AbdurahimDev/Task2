package uz.formal.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.formal.task2.payload.req.UniversityDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.service.FacultyService;
import uz.formal.task2.service.UniversityService;

@RestController
@RequestMapping("/api/university")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @Autowired
    FacultyService facultyService;

    @PostMapping()
    public HttpEntity<?> addUniversity(@RequestBody UniversityDTO universityDTO){
        ApiResponse response = universityService.save(universityDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping()
    public HttpEntity<?> getAll(){
        ApiResponse response = universityService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneById(@PathVariable Integer id){
        ApiResponse response = universityService.getOneById(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody UniversityDTO universityDTO){
        ApiResponse response = universityService.update(id,universityDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = universityService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/{id}/faculties")
    public HttpEntity<?> getAllFacultiesByUniversityId(@PathVariable Integer id){
        ApiResponse response = facultyService.getAllByUniversityId(id);
        return ResponseEntity.ok(response);
    }

}
