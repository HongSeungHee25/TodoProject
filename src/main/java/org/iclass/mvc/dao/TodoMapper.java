package org.iclass.mvc.dao;

import java.util.List;
//import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
//import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.TodoDto;

							// 인터페이스는 객체를 생성할 수 없다. 구현(체)클래스가 필요하다.
@Mapper						// 이 애노테이션은 mybatis mapper  인터페이스 ->  프록시가 구현체 만들어 줍니다.
public interface TodoMapper {
	
	int insert(TodoDto vo);
	List<TodoDto> selectAll();
	TodoDto selectOne(long tno);
	int update(TodoDto vo);
	int delete(long tno);
	long getCount(PageRequestDTO dto);
	List<TodoDto> selectPageList(PageRequestDTO dto);
}
