package in.kb.service;

import in.kb.binding.DashBoardResponse;
import in.kb.binding.EnquiryForm;
import in.kb.binding.EnquirySearchCriteria;
import in.kb.entity.StudentEnquiriesEntity;

import java.util.List;

public interface EnquiryService {

   public DashBoardResponse getDashBoardData(Integer userId);

   public List<String> getCourses();

   public List<String> getEnqStatus();

   public boolean saveEnquiry(EnquiryForm form);

   public List<StudentEnquiriesEntity> getEnquiries();

   public List<StudentEnquiriesEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId);
}
