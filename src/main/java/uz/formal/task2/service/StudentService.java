package uz.formal.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.formal.task2.entity.Group;
import uz.formal.task2.entity.Journal;
import uz.formal.task2.entity.Student;
import uz.formal.task2.entity.Subject;
import uz.formal.task2.payload.req.StudentDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.payload.res.StudentResDTO;
import uz.formal.task2.repository.GroupRepository;
import uz.formal.task2.repository.JournalRepository;
import uz.formal.task2.repository.StudentRepository;
import uz.formal.task2.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    JournalRepository journalRepository;

    public ApiResponse save(StudentDTO studentDTO) {

        Optional<Student> byNameAndGroupId = studentRepository.findByNameAndGroupId(studentDTO.getName(), studentDTO.getGroupId());
        if (byNameAndGroupId.isPresent()){
            return new ApiResponse("Student with this name and group is already exist!",false);
        }else{
            Student student = new Student();
            student.setName(studentDTO.getName());
            student.setGroup(groupRepository.findById(studentDTO.getGroupId()).get());
            studentRepository.save(student);
            return new ApiResponse("Saved!",true);
        }

    }

    public ApiResponse getAll() {
        List<Student> all = studentRepository.findAll();
        if(all.isEmpty()){
            return new ApiResponse("Students are not exist yet!",false);
        }
        return new ApiResponse("Mana",true,all);
    }

    public ApiResponse getOneById(Integer id) {
        Optional<Student> byId = studentRepository.findById(id);
        if(byId.isPresent()){
            return new ApiResponse("Mana",true,byId.get());
        }else return new ApiResponse("Student not found with Id: "+id,false);
    }

    public ApiResponse update(Integer id, StudentDTO studentDTO) {
        Optional<Student> byId = studentRepository.findById(id);
        if(byId.isPresent()){
            Student student = byId.get();
            student.setName(studentDTO.getName());
            student.setGroup(groupRepository.findById(studentDTO.getGroupId()).get());
            studentRepository.save(student);
            return new ApiResponse("Updated!",true);
        }else return new ApiResponse("Student not found with Id: "+id,false);
    }


    public ApiResponse delete(Integer id) {
        Optional<Student> byId = studentRepository.findById(id);
        if(byId.isPresent()){
            studentRepository.deleteById(id);
            return new ApiResponse("Deleted!",true);
        }else return new ApiResponse("Student not found with Id: "+id,false);
    }

    public ApiResponse getSubjects(Integer id) {
        Optional<Student> byId = studentRepository.findById(id);
        if(byId.isPresent()){
            Optional<Group> byId1 = groupRepository.findById(byId.get().getGroup().getId());
            Optional<Journal> byGroupId = journalRepository.findByGroupId(byId1.get().getId());
            List<Subject> allByJournalId = subjectRepository.findAllByJournalId(byGroupId.get().getId());
            return new ApiResponse("Mana",true,allByJournalId);
        }else return new ApiResponse("Not found",false);
    }

    public ApiResponse findByName(String name) {
        List<Student> studentByNameContains = studentRepository.findStudentByNameContains(name);
        if (studentByNameContains.isEmpty()){
            return new ApiResponse("Student not found with name: "+name,false);
        }else {
            List<StudentResDTO> studentResDTOS = new ArrayList<>();
            for (Student studentByNameContain : studentByNameContains) {
                StudentResDTO studentResDTO = new StudentResDTO();
                studentResDTO.setName(studentByNameContain.getName());
                studentResDTO.setGroupName(studentByNameContain.getGroup().getName());
                studentResDTO.setFacultyName(studentByNameContain.getGroup().getFaculty().getName());
                studentResDTOS.add(studentResDTO);
            }return new ApiResponse("Mana",true,studentResDTOS);
        }
    }
}
