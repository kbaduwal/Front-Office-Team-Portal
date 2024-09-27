package in.kb.service;

import in.kb.binding.DashBoardResponse;
import in.kb.binding.EnquiryForm;
import in.kb.binding.EnquirySearchCriteria;
import in.kb.constants.AppConstants;
import in.kb.entity.CoursesEntity;
import in.kb.entity.EnquiryStatusEntity;
import in.kb.entity.StudentEnquiriesEntity;
import in.kb.entity.UserDtlsEntity;
import in.kb.repo.CourseRepository;
import in.kb.repo.EnquiryStatusRepository;
import in.kb.repo.StudentEnquiriesRepository;
import in.kb.repo.UserDtlsRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private StudentEnquiriesRepository enqRepo;

    @Autowired
    private HttpSession session;

    @Override
    public DashBoardResponse getDashBoardData(Integer userId) {
        DashBoardResponse response = new DashBoardResponse();

        Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);

        if(findById.isPresent()){
            UserDtlsEntity userEntity = findById.get();

            List<StudentEnquiriesEntity> enquiries =  userEntity.getEnquiries();

            Integer totalCnt = enquiries.size();

            Integer enrolledCnt = enquiries.stream()
                    .filter(e -> e.getEnquiryStatus().equals("Enrolled"))
                    .collect(Collectors.toList()).size();

            Integer lostCnt = enquiries.stream()
                    .filter(e -> e.getEnquiryStatus().equals("Lost"))
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
        //TODO: Copy data from binding object to entity object
        StudentEnquiriesEntity enqEntity = new StudentEnquiriesEntity();
        BeanUtils.copyProperties(form,enqEntity);

        //To get to know which user is inserting the data
        Integer userId = (Integer) session.getAttribute(AppConstants.STR_USERID);

        UserDtlsEntity userEntity = userDtlsRepo.findById(userId).get();
        enqEntity.setUser(userEntity);

        enqRepo.save(enqEntity);

        return true;
    }

    @Override
    public List<StudentEnquiriesEntity> getEnquiries() {
        Integer userId = (Integer) session.getAttribute(AppConstants.STR_USERID);
        Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
        if(findById.isPresent()){
            UserDtlsEntity userDtlsEntity = findById.get();
            List<StudentEnquiriesEntity> enquiries = userDtlsEntity.getEnquiries();
            return enquiries;
        }
        return null;
    }

    @Override
    public List<StudentEnquiriesEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {
        Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
        if(findById.isPresent()){
            UserDtlsEntity userDtlsEntity = findById.get();
            List<StudentEnquiriesEntity> enquiries = userDtlsEntity.getEnquiries();

            //Filter logic using Java 8 stream()
            if(null != criteria.getCourseName()
                    && !criteria.getCourseName().isEmpty() )
            {
                enquiries = enquiries.stream()
                        .filter(e->e.getCourseName().equals(criteria.getCourseName()))
                        .collect(Collectors.toList());

            }

            if(null != criteria.getEnquiryStatus() && !AppConstants.STR_EMPTY.equals(criteria.getEnquiryStatus()) )
            {
                enquiries = enquiries.stream()
                        .filter(e -> e.getEnquiryStatus().equals(criteria.getEnquiryStatus()))
                        .collect(Collectors.toList());
            }

            if(null != criteria.getClassMode() && !AppConstants.STR_EMPTY.equals(criteria.getClassMode()) )
            {
                enquiries = enquiries.stream()
                        .filter(e -> e.getClassMode().equals(criteria.getClassMode()))
                        .collect(Collectors.toList());
            }

            return enquiries;
        }
        return null;
    }
}
