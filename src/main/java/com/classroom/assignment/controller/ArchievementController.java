package com.classroom.assignment.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.classroom.assignment.entity.Archievement;
import com.classroom.assignment.model.request.ArchievementForm;
import com.classroom.assignment.service.ArchievementService;

@Controller
@RequestMapping("/archievement")
public class ArchievementController {

  private final ArchievementService archievementService;

  @Autowired
  public ArchievementController(ArchievementService archievementService) {
    this.archievementService = archievementService;
  }

  @GetMapping
  public String index(Model model) {
    List<Archievement> list = archievementService.selectAll();

    model.addAttribute("archievementList", list);
    return "archievement/index";
  }
}
