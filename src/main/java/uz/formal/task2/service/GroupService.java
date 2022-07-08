package uz.formal.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.formal.task2.entity.Group;
import uz.formal.task2.entity.Student;
import uz.formal.task2.payload.req.GroupDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.payload.res.GroupResDTO;
import uz.formal.task2.repository.FacultyRepository;
import uz.formal.task2.repository.GroupRepository;
import uz.formal.task2.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    StudentRepository studentRepository;


    public ApiResponse save(GroupDTO groupDTO) {
        List<Group> allByFacultyId = groupRepository.findAllByFacultyId(groupDTO.getFacultyId());
        for (Group group : allByFacultyId) {
            if (group.getName().equals(groupDTO.getName())){
                return new ApiResponse("Gro already exist!",false);
            }
        }
        Group group = new Group();
        group.setName(groupDTO.getName());
        group.setFaculty(facultyRepository.findById(groupDTO.getFacultyId()).get());
        group.setYear(groupDTO.getYear());
        groupRepository.save(group);
        return new ApiResponse("Saved!",true);
    }


    public ApiResponse getAll() {
        List<Group> all = groupRepository.findAll();
        if (all.isEmpty()){
            return new ApiResponse(" Groups are not exist yet!",false);
        }
        return new ApiResponse("Mana",true,all);
    }

    public ApiResponse getOneById(Integer id) {
        Optional<Group> byId = groupRepository.findById(id);
        if(byId.isPresent()){
            List<Student> allByGroup_id = studentRepository.findAllByGroup_Id(id);
            GroupResDTO groupResDTOS = new GroupResDTO();
            groupResDTOS.setStudents(allByGroup_id);
            return new ApiResponse("Mana",true,byId.get());
        }else return new ApiResponse("Group not found with Id: "+id,false);
    }

    public ApiResponse update(Integer id, GroupDTO groupDTO) {
        Optional<Group> byId = groupRepository.findById(id);
        if(byId.isPresent()){
            Group group = byId.get();
            group.setName(groupDTO.getName());
            group.setFaculty(facultyRepository.findById(groupDTO.getFacultyId()).get());
            group.setYear(groupDTO.getYear());
            groupRepository.save(group);
            return new ApiResponse("Updated!",true);
        }else return new ApiResponse("Group not found with Id: "+id,false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Group> byId = groupRepository.findById(id);
        if(byId.isPresent()){
            groupRepository.deleteById(id);
            return new ApiResponse("Deleted!",true);
        }else return new ApiResponse("Group not found with Id: "+id,false);
    }

//    public ApiResponse getOneByIdWithMarks(Integer id) {
//
//    }
}
