package uz.formal.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.formal.task2.entity.Journal;
import uz.formal.task2.entity.Mark;
import uz.formal.task2.entity.Student;
import uz.formal.task2.entity.Subject;
import uz.formal.task2.payload.req.MarkDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.repository.JournalRepository;
import uz.formal.task2.repository.MarkRepository;
import uz.formal.task2.repository.StudentRepository;
import uz.formal.task2.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MarkService {

    @Autowired
    MarkRepository markRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    JournalRepository journalRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public ApiResponse save(MarkDTO markDTO) {
        Optional<Student> studentById = studentRepository.findById(markDTO.getSubjectId());
        if(studentById.isPresent()){
            Optional<Journal> journalById = journalRepository.findById(markDTO.getJournalId());
            if (journalById.isPresent()){
                Optional<Subject> subjectById = subjectRepository.findById(markDTO.getSubjectId());
                if (subjectById.isPresent()){
                    Set<Subject> journalSubjects = journalById.get().getSubjects();
                    if(journalSubjects.contains(subjectById.get())){
                        Mark mark = new Mark();
                        mark.setJournal(journalRepository.findById(markDTO.getJournalId()).get());
                        mark.setSubject(subjectRepository.findById(markDTO.getSubjectId()).get());
                        mark.setStudent(studentRepository.findById(markDTO.getStudentId()).get());
                        mark.setValue(markDTO.getValue());
                        markRepository.save(mark);
                        return new ApiResponse("Saved!",true);
                    }else return new ApiResponse("Student does not study this subject",false);
                }else return new ApiResponse("Subject not found with Id: "+markDTO.getSubjectId(),false);
            }else return new ApiResponse("Journal not found with Id: "+markDTO.getJournalId(),false);
        }else return new ApiResponse("Student not found with Id: "+markDTO.getStudentId(),false);
    }

    public ApiResponse update(Integer id, MarkDTO markDTO) {
        Optional<Mark> markById = markRepository.findById(id);
        if (markById.isPresent()){
            Mark mark = markById.get();
            mark.setValue(markDTO.getValue());
            markRepository.save(mark);
            return new ApiResponse("Updated!",true);
        }else return new ApiResponse("Mark not found with Id: "+id,false);
    }
}
