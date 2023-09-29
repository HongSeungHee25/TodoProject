package org.iclass.mvc.controller;

import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.TodoDto;
import org.iclass.mvc.service.TodoService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        redirectAttributes.addFlashAttribute("message","등록 완료");
        return "redirect:/todo/list";
    }

    @GetMapping("/list")
    public void list(Model model){
        List<TodoDto> list = service.list();
        model.addAttribute("list",list);
    }

    @GetMapping("/read")
    public void read(long tno,TodoDto dto ,Model model){
        model.addAttribute("vo",service.selectOne(dto.getTno()));
    }

    @GetMapping("/modify")
    public void update(long tno,TodoDto dto, Model model){
        model.addAttribute("vo", service.selectOne(dto.getTno()));
    }
    @PostMapping("/save")
    public String save(long tno,TodoDto dto, RedirectAttributes redirectAttributes){
        service.update(dto);
        
        redirectAttributes.addAttribute("tno",dto.getTno());
        redirectAttributes.addFlashAttribute("message","수정 완료");

        return "redirect:/todo/read";
    }

    @PostMapping("/delete")
    public String delete(long tno,RedirectAttributes redirectAttributes){
        service.delete(tno);

        redirectAttributes.addFlashAttribute("message","삭제 완료");

        return "redirect:/todo/list";
    }

}

