package org.iclass.mvc.controller;

import org.iclass.mvc.dto.TodoDto;
import org.iclass.mvc.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;

    private TodoController(TodoService service){
        this.service = service;
    }
    @GetMapping("/register")
    public void register(){
    }

   @PostMapping("/register")
    public String save(TodoDto dto, RedirectAttributes redirectAttributes){

        service.insert(dto);

        redirectAttributes.addFlashAttribute("message","등록 완료!");
        return "redirect:/todo/list";
    }

    /*@GetMapping("/list")
    public void list(Model model){
        List<TodoDto> list = service.list();
        model.addAttribute("list",list);
    }*/
    @GetMapping("/list")
    public void list(@RequestParam(defaultValue = "1") int page, Model model){
        model.addAttribute("list",service.pagelist(page).get("list"));
        model.addAttribute("paging",service.pagelist(page).get("paging"));
    }

    @GetMapping("/read")
    public void read(long tno, @ModelAttribute("page") int page, Model model){
        model.addAttribute("vo",service.selectOne(tno));
    }

    @GetMapping("/modify")
    public void update(long tno,@ModelAttribute("page") int page, Model model){
        model.addAttribute("vo", service.selectOne(tno));
    }
    @PostMapping("/save")
    public String save(int page,long tno,TodoDto dto, RedirectAttributes redirectAttributes){
        service.update(dto);
        
        redirectAttributes.addAttribute("tno",tno);
        redirectAttributes.addAttribute("page",page);
        redirectAttributes.addFlashAttribute("message","수정 완료!");

        return "redirect:/todo/read";
    }

    @PostMapping("/delete")
    public String delete(int page,long tno,RedirectAttributes redirectAttributes){
        service.delete(tno);

        redirectAttributes.addAttribute("page",page);
        redirectAttributes.addFlashAttribute("message","삭제 완료!");

        return "redirect:/todo/list";
    }


}

