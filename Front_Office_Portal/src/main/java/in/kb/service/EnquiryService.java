package in.kb.service;

import in.kb.binding.DashBoardResponse;
import in.kb.binding.EnquiryForm;

import java.util.List;

public interface EnquiryService {

   public DashBoardResponse getDashBoardData(Integer userId);

   public List<String> getCourses();

   public List<String> getEnqStatus();

   public boolean saveEnquiry(EnquiryForm form);
}
