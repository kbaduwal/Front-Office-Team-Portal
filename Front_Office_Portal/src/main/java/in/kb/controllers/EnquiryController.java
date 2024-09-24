package in.kb.controllers;

import in.kb.binding.DashBoardResponse;
import in.kb.binding.EnquiryForm;
import in.kb.service.EnquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
         Integer userId = (Integer) session.getAttribute("userId");

        //Todo: Logic to fetch data for dashboard
        DashBoardResponse dashBoardData = enquiryService.getDashBoardData(userId);
        model.addAttribute("dashBoardData",dashBoardData);

        return "dashboard";
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

    @GetMapping("/view")
    public String viewEnquiriesPage(){
        return "view-enquiries";
    }
}
