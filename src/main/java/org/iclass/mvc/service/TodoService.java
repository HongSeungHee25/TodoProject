package org.iclass.mvc.service;

import lombok.RequiredArgsConstructor;
import org.iclass.mvc.dao.TodoMapper;
import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<TodoDto> selectPageList(PageRequestDTO dto){
        return dao.selectPageList(dto);
    }

}
