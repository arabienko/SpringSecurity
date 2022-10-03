package by.arabienko.spring.security.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String getInfoForAllEmps(){

        return "view_for_all_emps";
    }

    @GetMapping("/hr_info")
    public String getHRInfo(){

        return "view_for_HR";
    }

    @GetMapping("/customer_info")
    public String getCustomers(){

        return "view_for_customers";
    }
}
