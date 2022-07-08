package uz.formal.task2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.formal.task2.entity.Faculty;
import uz.formal.task2.entity.Group;
import uz.formal.task2.entity.University;
import uz.formal.task2.payload.req.FacultyDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.payload.res.FacultyGroupsResDTO;
import uz.formal.task2.repository.FacultyRepository;
import uz.formal.task2.repository.GroupRepository;
import uz.formal.task2.repository.StudentRepository;
import uz.formal.task2.repository.UniversityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    StudentRepository studentRepository;

    public ApiResponse save(FacultyDTO facultyDTO) {
        Optional<University> universityById = universityRepository.findById(facultyDTO.getUniversityId());
        if(universityById.isPresent()){
            List<Faculty> facultiesByUniversityId = facultyRepository.facultiesByUniversityId(facultyDTO.getUniversityId());
            for (Faculty faculty : facultiesByUniversityId) {
                if (faculty.getName().equals(facultyDTO.getName())){
                    return new ApiResponse("Fac already exist!",false);
                }
            }
            Faculty faculty = new Faculty();
            faculty.setName(facultyDTO.getName());
            faculty.setUniversity(universityRepository.findById(facultyDTO.getUniversityId()).get());
            facultyRepository.save(faculty);
            return new ApiResponse("Saved!",true);
        }else return new ApiResponse("Uni not found with Id: "+facultyDTO.getUniversityId(),false);
    }

    public ApiResponse getAll() {
        List<Faculty> all = facultyRepository.findAll();
        if(all.isEmpty()){
            return new ApiResponse(" Facs are not exist yet!",false);
        }
        return new ApiResponse("Mana",true,all);
    }

    public ApiResponse getOneById(Integer id) {
        Optional<Faculty> byId = facultyRepository.findById(id);
        if (byId.isPresent()){
            return new ApiResponse("Mana",true,byId.get());
        }else return new ApiResponse("Fac not found with Id: "+id,false);
    }

    public ApiResponse update(Integer id, FacultyDTO facultyDTO) {
        Optional<Faculty> byId = facultyRepository.findById(id);
        if (byId.isPresent()){
            Faculty faculty = byId.get();
            faculty.setName(facultyDTO.getName());
            facultyRepository.save(faculty);
            return new ApiResponse("Updated!",true);
        }else return new ApiResponse("Fac not found with Id: "+id,false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Faculty> byId = facultyRepository.findById(id);
        if (byId.isPresent()){
            facultyRepository.deleteById(id);
            return new ApiResponse("Deleted!",true);
        }else return new ApiResponse("Fac not found with Id: "+id,false);
    }

    public ApiResponse groupsOfFaculty(Integer id) {
        List<Group> allByFacultyId = groupRepository.findAllByFacultyId(id);
        if(allByFacultyId.isEmpty()){
            return new ApiResponse("Groups are not found with FacultyId: "+id,false);
        }else {
            List<FacultyGroupsResDTO> facultyGroupsResDTOS = new ArrayList<>();
            for (Group group : allByFacultyId) {
                FacultyGroupsResDTO facultyGroupsResDTO = new FacultyGroupsResDTO();
                facultyGroupsResDTO.setName(group.getName());
                int numberOfStudents = studentRepository.countNumberOfStudentsByGroupId(group.getId());
                facultyGroupsResDTO.setNumberOfStudents(numberOfStudents);
                facultyGroupsResDTO.setYear(group.getYear());
                facultyGroupsResDTO.setFacultyName(group.getFaculty().getName());
                facultyGroupsResDTOS.add(facultyGroupsResDTO);
            }
            return new ApiResponse("Mana",true,facultyGroupsResDTOS);
        }
    }

    public ApiResponse getAllByUniversityId(Integer id) {
        Optional<University> university = universityRepository.findById(id);
        if(university.isPresent()){
            List<Faculty> faculties = facultyRepository.facultiesByUniversityId(id);
            if(faculties.isEmpty()){
                return new ApiResponse("Faculties are not exist!",false);
            }else return new
                    ApiResponse("Mana",true,faculties);
        }else return new ApiResponse("University not found woth Id:"+id,false);
    }
}
