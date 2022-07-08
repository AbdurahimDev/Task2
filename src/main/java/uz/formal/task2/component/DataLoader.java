package uz.formal.task2.component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.formal.task2.entity.*;
import uz.formal.task2.repository.*;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    JournalRepository journalRepository;

    @Autowired
    MarkRepository markRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {

            List<University> universities = new ArrayList<>(Arrays.asList(
                    new University(1,"ADU",true,"Andijon",2000),
                    new University(2,"NamDU",true,"Namangan",2004)
            ));
            universityRepository.saveAll(universities);



            Set<Faculty> faculties = new HashSet<>(Arrays.asList(
                    new Faculty(1,"Matematika fakulteti",true,universityRepository.findById(1).get()),
                    new Faculty(2,"Fizika fakulteti",true,universityRepository.findById(1).get())
            ));
            facultyRepository.saveAll(faculties);



            List<Group> groups = new ArrayList<>(Arrays.asList(
                    new Group(1,"228-12",true,facultyRepository.findById(1).get(),1),
                    new Group(2,"228-11",true,facultyRepository.findById(1).get(),2)
            ));
            groupRepository.saveAll(groups);



            List<Student> students = new ArrayList<>(Arrays.asList(
               new Student(1,"Akbarov Arslon",true,groupRepository.findById(1).get()),
               new Student(2,"Akbarov Akbar",true,groupRepository.findById(1).get()),
               new Student(3,"Hamidov Qurbon",true,groupRepository.findById(1).get()),
               new Student(4,"Malikov Qayum",true,groupRepository.findById(2).get()),
               new Student(5,"Karimov Eldor",true,groupRepository.findById(2).get())
            ));
            studentRepository.saveAll(students);




            Set<Subject> subjects = new HashSet<>(Arrays.asList(
                    new Subject(1,"Fizika",true),
                    new Subject(2,"Tarix",true)
            ));
            subjectRepository.saveAll(subjects);

            List<Subject> allSubject = subjectRepository.findAll();
            Set<Subject> subjects1 = new HashSet<>(allSubject);


            List<Journal> journals = new ArrayList<>(Arrays.asList(
                    new Journal(1,"228-12 gurux jurnali",true,subjects1,groupRepository.findById(1).get()),
                    new Journal(2,"228-11 gurux jurnali",true,subjects1,groupRepository.findById(2).get())
            ));
            journalRepository.saveAll(journals);



            List<Mark> marks = new ArrayList<>(Arrays.asList(
                    new Mark(1,4,students.get(0),journalRepository.findById(1).get(),subjectRepository.findById(1).get()),
                    new Mark(2,4,students.get(0),journalRepository.findById(1).get(),subjectRepository.findById(2).get()),
                    new Mark(3,4,students.get(1),journalRepository.findById(1).get(),subjectRepository.findById(1).get()),
                    new Mark(4,5,students.get(1),journalRepository.findById(1).get(),subjectRepository.findById(2).get()),
                    new Mark(5,3,students.get(2),journalRepository.findById(1).get(),subjectRepository.findById(1).get()),
                    new Mark(6,5,students.get(2),journalRepository.findById(1).get(),subjectRepository.findById(2).get()),
                    new Mark(7,3,students.get(3),journalRepository.findById(2).get(),subjectRepository.findById(1).get()),
                    new Mark(8,3,students.get(3),journalRepository.findById(2).get(),subjectRepository.findById(2).get()),
                    new Mark(9,5,students.get(4),journalRepository.findById(2).get(),subjectRepository.findById(1).get()),
                    new Mark(10,5,students.get(4),journalRepository.findById(2).get(),subjectRepository.findById(2).get())
                    ));
            for (Mark mark : marks) {
                markRepository.save(mark);
            }


        }
    }

}
