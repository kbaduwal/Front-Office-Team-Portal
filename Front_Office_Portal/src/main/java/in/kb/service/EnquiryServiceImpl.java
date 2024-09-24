package in.kb.service;

import in.kb.binding.DashBoardResponse;
import in.kb.entity.StudentEnquiriesEntity;
import in.kb.entity.UserDtlsEntity;
import in.kb.repo.UserDtlsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements EnquiryService{
    
    @Autowired
    private UserDtlsRepository userDtlsRepo;

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
}
