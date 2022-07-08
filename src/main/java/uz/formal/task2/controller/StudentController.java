package uz.formal.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.formal.task2.payload.req.FacultyDTO;
import uz.formal.task2.payload.req.StudentDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping()
    public HttpEntity<?> addStudent(@RequestBody StudentDTO studentDTO){
        ApiResponse response = studentService.save(studentDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping()
    public HttpEntity<?> getAll(){
        ApiResponse response = studentService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/my-subjects")
    public HttpEntity<?> getSubjects(@PathVariable Integer id){
        ApiResponse response = studentService.getSubjects(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneById(@PathVariable Integer id){
        ApiResponse response = studentService.getOneById(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }


    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody StudentDTO studentDTO){
        ApiResponse response = studentService.update(id,studentDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = studentService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
    @GetMapping("/search")
    public HttpEntity<?> findByName(@RequestParam("name") String name){
        ApiResponse response = studentService.findByName(name);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

}
