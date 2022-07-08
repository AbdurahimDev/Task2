package uz.formal.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.formal.task2.payload.req.GroupDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.service.GroupService;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping()
    public HttpEntity<?> addGroup(@RequestBody GroupDTO groupDTO){
        ApiResponse response = groupService.save(groupDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping()
    public HttpEntity<?> getAll(){
        ApiResponse response = groupService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneById(@PathVariable Integer id){
        ApiResponse response = groupService.getOneById(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

//    @GetMapping("/{id}/marks")
//    public HttpEntity<?> getOneByIdWithMarks(@PathVariable Integer id){
//        ApiResponse response = groupService.getOneByIdWithMarks(id);
//        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
//    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody GroupDTO groupDTO){
        ApiResponse response = groupService.update(id,groupDTO);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = groupService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }


}
