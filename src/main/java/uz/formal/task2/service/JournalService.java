package uz.formal.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.formal.task2.entity.Journal;
import uz.formal.task2.entity.Subject;
import uz.formal.task2.payload.req.JournalDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.repository.GroupRepository;
import uz.formal.task2.repository.JournalRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JournalService {

    @Autowired
    JournalRepository journalRepository;

    @Autowired
    GroupRepository groupRepository;

    public ApiResponse save(JournalDTO journalDTO) {
        Optional<Journal> byGroupId = journalRepository.findByGroupId(journalDTO.getGroupId());
        if (byGroupId.isPresent()){
            return new ApiResponse("Journal is already exist for this group!",false);
        }
        Journal journal = new Journal();
        journal.setName(journalDTO.getName());
        journal.setGroup(groupRepository.findById(journalDTO.getGroupId()).get());
        journalRepository.save(journal);
        return new ApiResponse("Saved!",true);
    }

    public ApiResponse getAll() {
        List<Journal> all = journalRepository.findAll();
        if (all.isEmpty()){
            return new ApiResponse(" Journals are not exist yet!",false);
        }
        return new ApiResponse("Mana",true,all);
    }

    public ApiResponse getOneById(Integer id) {
        Optional<Journal> byId = journalRepository.findById(id);
        if (byId.isPresent()){
            return new ApiResponse("Mana",true,byId.get());
        }else return new ApiResponse("Journal not found with Id: "+id,false);
    }


    public ApiResponse update(Integer id, JournalDTO journalDTO) {
        Optional<Journal> byId = journalRepository.findById(id);
        if (byId.isPresent()) {
            Journal journal = byId.get();
            journal.setName(journalDTO.getName());
            journal.setGroup(groupRepository.findById(journalDTO.getGroupId()).get());
            Set<Subject> subjects = new HashSet<>();
            Set<Subject> dtoSubjects = journalDTO.getSubjects();
            if (dtoSubjects==null){
                journal.setSubjects(null);
            }else{
                for (Subject dtoSubject : dtoSubjects) {
                    Subject subject = new Subject();
                    subject.setId(dtoSubject.getId());
                    subject.setName(dtoSubject.getName());
                    subjects.add(subject);
                }
                journal.setSubjects(subjects);
                journalRepository.save(journal);
            }
            return new ApiResponse("Updated!", true);
        }else return new ApiResponse("Journal not found with Id: "+id,false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Journal> byId = journalRepository.findById(id);
        if (byId.isPresent()){
            journalRepository.deleteById(id);
            return new ApiResponse("Deleted!",true);
        }else return new ApiResponse("Journal not found with Id: "+id,false);
    }
}
