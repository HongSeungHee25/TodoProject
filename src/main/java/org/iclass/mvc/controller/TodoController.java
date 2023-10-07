package org.iclass.mvc.controller;

import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.PageResponseDTO;
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
    public void pagelist(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO responseDTO =  service.listWithSearch(pageRequestDTO);
        //list.html 에 전달한 model 관련 코드 작성. list.html 도 완성하기. 레이아웃도 적용 하기
//        model.addAttribute("list",service.pagelist(pageRequestDTO));
        model.addAttribute("paging",responseDTO);
        model.addAttribute("page",pageRequestDTO.getPage());
        model.addAttribute("today", LocalDate.now());
    }

    @GetMapping("/read")
    public void read(PageRequestDTO pageRequestDTO, long tno, @ModelAttribute("page") int page, Model model){
        model.addAttribute("vo",service.selectOne(tno));
    }

    @GetMapping("/modify")
    public void update(PageRequestDTO pageRequestDTO,long tno,@ModelAttribute("page") int page, Model model){
        model.addAttribute("vo", service.selectOne(tno));
    }
    @PostMapping("/save")
    public String save(PageRequestDTO pageRequestDTO,int page,long tno,TodoDto dto, RedirectAttributes redirectAttributes){
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

