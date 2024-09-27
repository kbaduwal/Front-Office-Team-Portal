package in.kb.controllers;

import in.kb.binding.DashBoardResponse;
import in.kb.binding.EnquiryForm;
import in.kb.binding.EnquirySearchCriteria;
import in.kb.constants.AppConstants;
import in.kb.entity.StudentEnquiriesEntity;
import in.kb.service.EnquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EnquiryController {

    @Autowired
    private HttpSession session;

    @Autowired
    private EnquiryService enquiryService;

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model){
         Integer userId = (Integer) session.getAttribute(AppConstants.STR_USERID);

        //Todo: Logic to fetch data for dashboard
        DashBoardResponse dashBoardData = enquiryService.getDashBoardData(userId);
        model.addAttribute("dashBoardData",dashBoardData);

        return "dashboard";
    }

    @PostMapping("/addEnq")
    public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model){
        System.out.println(formObj);
        //Todo: Save the data
        boolean status= enquiryService.saveEnquiry(formObj);

        if(status){
            model.addAttribute("succMsg", "Enquiry Added");
        }else {
            model.addAttribute("errMsg", "Problem Occurred");
        }
        //Get courses for the dropdown
        List<String> courses = enquiryService.getCourses();

        //Get enq status for the dropdown
        List<String> enqStatuses = enquiryService.getEnqStatus();
        //Set data in Model object
        model.addAttribute("coursesNames", courses);
        model.addAttribute("enqStatusesNames", enqStatuses);

        return "add-enquiry";
    }

    @GetMapping("/enquiry")
    public String addEnquiryPage(Model model){

        //Get courses for the dropdown
        List<String> courses = enquiryService.getCourses();

        //Get enq status for the dropdown
        List<String> enqStatuses = enquiryService.getEnqStatus();

        //Create binding class object
        EnquiryForm formObj = new EnquiryForm();

        //Set data in Model object
        model.addAttribute("coursesNames", courses);
        model.addAttribute("enqStatusesNames", enqStatuses);
        model.addAttribute("formObj",formObj);

        return "add-enquiry";
    }

    private void initForm(Model model){
        //Get the course from the dropdown
        List<String> courses = enquiryService.getCourses();

        //Get enquiries status from the dropdown
        List<String> enqStatuses = enquiryService.getEnqStatus();

        //Create Binding class object
        EnquiryForm formObj = new EnquiryForm();

        //Set data to the model obj
        model.addAttribute("courseNames",courses);
        model.addAttribute("statusNames",enqStatuses);
        model.addAttribute("formObj",formObj);

    }

    @GetMapping("/enquiries")
    public String viewEnquiriesPage(EnquirySearchCriteria criteria, Model model){
        initForm(model);
        model.addAttribute("searchForm", new EnquirySearchCriteria());
        List<StudentEnquiriesEntity> enquiries = enquiryService.getEnquiries();
        model.addAttribute("enquiries",enquiries);
        return "view-enquiries";
    }

    @GetMapping("/filtered-enquiries")
    public String getFilteredEnqs(@RequestParam String cname, @RequestParam String status,
                                  @RequestParam String mode, Model model){

        EnquirySearchCriteria criteria = new EnquirySearchCriteria();
        criteria.setCourseName(cname);
        criteria.setClassMode(mode);
        criteria.setEnquiryStatus(status);

        Integer userId = (Integer) session.getAttribute(AppConstants.STR_USERID);


        List<StudentEnquiriesEntity> filteredEnqs = enquiryService.getFilteredEnqs(criteria, userId);

        model.addAttribute("enquiries", filteredEnqs);

        System.out.println(criteria);

        return "filter-enquiries-page";
    }


}
