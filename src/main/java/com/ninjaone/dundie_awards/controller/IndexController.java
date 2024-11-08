package com.ninjaone.dundie_awards.controller;

import com.ninjaone.dundie_awards.MessageBroker;
import com.ninjaone.dundie_awards.repository.ActivityRepository;
import com.ninjaone.dundie_awards.service.EmployeeService;
import com.ninjaone.dundie_awards.service.TotalAwardsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {
    private final EmployeeService employeeService;
    private final TotalAwardsService totalAwardsService;
    private final ActivityRepository activityRepository;
    private final MessageBroker messageBroker;

    public IndexController(EmployeeService employeeService, TotalAwardsService totalAwardsService, ActivityRepository activityRepository, MessageBroker messageBroker) {
        this.employeeService = employeeService;
        this.totalAwardsService = totalAwardsService;
        this.activityRepository = activityRepository;
        this.messageBroker = messageBroker;
    }

    @GetMapping()
    public String getIndex(Model model, Pageable pageable) {
        model.addAttribute("employees", employeeService.getAllEmployees(pageable));
        model.addAttribute("activities", activityRepository.findAll());
        model.addAttribute("queueMessages", messageBroker.getMessages());
        model.addAttribute("totalDundieAwards", totalAwardsService.getTotalAwards());
        return "index";
    }
}