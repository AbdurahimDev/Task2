package uz.formal.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.formal.task2.payload.req.FacultyDTO;
import uz.formal.task2.payload.req.MarkDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.service.MarkService;

import java.util.List;

@RestController
@RequestMapping("/api/mark")
public class MarkController {
    
    @Autowired
    MarkService markServiceservice;

    @PostMapping("")
    public HttpEntity<?> addMark(@RequestBody MarkDTO markDTO){
        ApiResponse response = markServiceservice.save(markDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody MarkDTO markDTO){
        ApiResponse response = markServiceservice.update(id,markDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

}
