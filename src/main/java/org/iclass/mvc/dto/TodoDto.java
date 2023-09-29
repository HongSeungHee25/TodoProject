package org.iclass.mvc.dto;

import java.time.LocalDate;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TodoDto {
	private long tno;
	private String title;						//제목

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;

	private String writer;						//작성
	private boolean finished;					//완료여부

}
