package org.iclass.mvc.service;

import lombok.RequiredArgsConstructor;
import org.iclass.mvc.dao.TodoMapper;
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

    public Map<String,Object> pagelist(int page){

        int pageSize=10;		//pageSize 를 15 또는 10으로 변경해서 실행해 봅시다.
        int totalCount = dao.count();

        //위의 값들을 이용해서 Paging 객체를 생성하면서 다른 필드값을 계산합니다.
        Paging paging = new Paging(page, totalCount, pageSize);

        //pagelist() 메소드를 실행하기 위한 Map을 생성합니다.
        Map<String,Integer> map = new HashMap<>();
        map.put("start",paging.getStartNo());
        map.put("end",paging.getEndNo());

        List<TodoDto> list = dao.pagelist(map);

        Map<String,Object> result = new HashMap<>();
        result.put("paging", paging);
        result.put("list", list);

        return result;
    }
}
