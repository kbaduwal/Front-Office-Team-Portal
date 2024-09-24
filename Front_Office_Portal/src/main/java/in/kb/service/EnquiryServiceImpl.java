package in.kb.service;

import in.kb.binding.DashBoardResponse;
import in.kb.binding.EnquiryForm;
import in.kb.entity.CoursesEntity;
import in.kb.entity.EnquiryStatusEntity;
import in.kb.entity.StudentEnquiriesEntity;
import in.kb.entity.UserDtlsEntity;
import in.kb.repo.CourseRepository;
import in.kb.repo.EnquiryStatusRepository;
import in.kb.repo.UserDtlsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements EnquiryService{
    
    @Autowired
    private UserDtlsRepository userDtlsRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private EnquiryStatusRepository statusRepo;

    @Override
    public DashBoardResponse getDashBoardData(Integer userId) {
        DashBoardResponse response = new DashBoardResponse();

        Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);

        if(findById.isPresent()){
            UserDtlsEntity userEntity = findById.get();

            List<StudentEnquiriesEntity> enquiries =  userEntity.getEnquiries();

            Integer totalCnt = enquiries.size();

            Integer enrolledCnt = enquiries.stream()
                    .filter(e -> e.getEnquiryStatus().equals("ENROLLED"))
                    .collect(Collectors.toList()).size();

            Integer lostCnt = enquiries.stream()
                    .filter(e -> e.getEnquiryStatus().equals("LOST"))
                    .collect(Collectors.toList()).size();

            response.setTotalEnquiriesSent(totalCnt);
            response.setEnrolledCnt(enrolledCnt);
            response.setLostCnt(lostCnt);

        }

        return response;
    }

    @Override
    public List<String> getCourses() {
        List<CoursesEntity> findAll = courseRepo.findAll();

        List<String> names = new ArrayList<>();

        for (CoursesEntity entity : findAll){
            names.add(entity.getCourseName());
        }

        return names;
    }

    @Override
    public List<String> getEnqStatus() {
        List<EnquiryStatusEntity> findAll = statusRepo.findAll();

        List<String> status = new ArrayList<>();

        for (EnquiryStatusEntity entity : findAll){
            status.add(entity.getStatusName());
        }

        return status;
    }

    @Override
    public boolean saveEnquiry(EnquiryForm form) {
        return false;
    }
}
