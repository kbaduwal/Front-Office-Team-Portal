package in.kb.service;

import in.kb.binding.DashBoardResponse;
import in.kb.binding.EnquiryForm;
import in.kb.binding.EnquirySearchCriteria;

import java.util.List;

public interface EnquiryService {
    public List<String> getCourseName();
    public List<String> getEnqStatus();
    public DashBoardResponse getDashboardData(Integer userId);
    public String addEnquiry(EnquiryForm form);
    public List<EnquiryForm> getEnquiries(Integer userID, EnquirySearchCriteria criteria);
}
