package uz.formal.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.formal.task2.entity.Subject;
import uz.formal.task2.payload.req.SubjectDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;


    public ApiResponse save(SubjectDTO subjectDTO) {
        Optional<Subject> byName = subjectRepository.findByName(subjectDTO.getName());
        if(byName.isPresent()){
            return new ApiResponse("Subject with this name is already exist!",false);
        }
        Subject subject = new Subject();
        subject.setName(subjectDTO.getName());
        subjectRepository.save(subject);
        return new ApiResponse("Saved!",true);
    }

    public ApiResponse getAll() {
        List<Subject> all = subjectRepository.findAll();
        if (all.isEmpty()){
            return new ApiResponse("Subjects are not exist yet!",false);
        }else return new ApiResponse("Mana",true,all);
    }


    public ApiResponse getOneById(Integer id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        if(byId.isPresent()){
            return new ApiResponse("Mana",true,byId.get());
        }else return new ApiResponse("Subject not found with Id: "+id,false);
    }

    public ApiResponse update(Integer id, SubjectDTO subjectDTO) {
        Optional<Subject> byId = subjectRepository.findById(id);
        if(byId.isPresent()){
            Subject subject = byId.get();
            subject.setName(subjectDTO.getName());
            subjectRepository.save(subject);
            return new ApiResponse("Updated!",true);
        }else return new ApiResponse("Subject not found with Id: "+id,false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        if(byId.isPresent()){
            subjectRepository.deleteById(id);
            return new ApiResponse("Deleted!",true);
        }else return new ApiResponse("Subject not found with Id: "+id,false);
    }
}
