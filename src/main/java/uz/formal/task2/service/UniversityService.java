package uz.formal.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.formal.task2.entity.University;
import uz.formal.task2.payload.req.UniversityDTO;
import uz.formal.task2.payload.res.ApiResponse;
import uz.formal.task2.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    @Autowired
    UniversityRepository universityRepository;



    public ApiResponse save(UniversityDTO universityDTO) {

        if (universityRepository.existsByName(universityDTO.getName())) {
            return new ApiResponse("Uni  already exist!",false);
        }
        University university = new University();
        university.setName(universityDTO.getName());
        university.setAddress(universityDTO.getAddress());
        university.setOpenYear(universityDTO.getOpenYear());
        universityRepository.save(university);
        return new ApiResponse("Saved",true);

    }

    public ApiResponse getAll() {
        List<University> all = universityRepository.findAll();
        if (all.isEmpty()){
            return new ApiResponse(" Unis are not exist yet!",false);
        }
        return new ApiResponse("Mana",true,all);
    }

    public ApiResponse getOneById(Integer id) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()){
            return new ApiResponse("Mana",true,byId.get());
        }else return new ApiResponse("Uni not found with Id: "+id,false);
    }

    public ApiResponse update(Integer id,UniversityDTO universityDTO) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()){
            University university = byId.get();
            university.setName(universityDTO.getName());
            university.setAddress(universityDTO.getAddress());
            university.setOpenYear(universityDTO.getOpenYear());
            universityRepository.save(university);
            return new ApiResponse("Updated",true);
        }else return new ApiResponse("Uni not found with Id: "+id,false);
    }

    public ApiResponse delete(Integer id) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()) {
            universityRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        }else return new ApiResponse("Uni not found with Id: "+id,false);
    }


}
