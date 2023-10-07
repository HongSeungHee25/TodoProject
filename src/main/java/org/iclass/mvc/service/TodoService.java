package org.iclass.mvc.service;

import lombok.RequiredArgsConstructor;
import org.iclass.mvc.dao.TodoMapper;
import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.PageResponseDTO;
import org.iclass.mvc.dto.Paging;
import org.iclass.mvc.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper dao;

    public int insert(TodoDto vo){
        return dao.insert(vo);
    }

    public List<TodoDto> list(){
        return dao.selectAll();
    }

    public TodoDto selectOne(long tno){
        return dao.selectOne(tno);
    }
    public int update(TodoDto dto){
        return dao.update(dto);
    }
    public int delete(long tno){
        return dao.delete(tno);
    }
    public List<TodoDto> pagelist(PageRequestDTO dto){

        dto.setSize(10);			//한 페이지에 보이는 글의 갯수 설정
        dto.setDatas();				//start 와 end 계산
        List<TodoDto> list = dao.pagelist(dto);		//글 목록

        //페이지 목록 구현은 예정.
        return list;
    }
    public PageResponseDTO listWithSearch(PageRequestDTO dto){
        dto.setSize(10);
        dto.setDatas();
        List<TodoDto> list = dao.pagelist(dto);
        PageResponseDTO pageResponseDTO = PageResponseDTO.of(dto,dao.count(dto),5);
        pageResponseDTO.setList(list);

        return pageResponseDTO;
    }
}
