package org.iclass.mvc.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PageRequestDTO {

	/* page,size 는 start,end 계산에 필요한 값 */
	private int page =1;	  //현재 페이지 번호
	private int size=10;      //size 는 한 개 페이지 글 갯수

	/* sql 쿼리에 필요한 값 */
	private int start;		//페이지 시작 글번호 rownum
	private int end;		//페이지 마지막 글번호 rownum

	//검색 파라미터
	private String[] types;		//"twc" 를 동적 쿼리로 전달하기 위해 배열로 변환하여 저장 {"t","w","c"}
	private String type;		//view 에서 "twc" 로 전달되는 값 저장
	private String keyword;

	@DateTimeFormat(pattern = "yyyy-MM-dd")	private LocalDate from;
	@DateTimeFormat(pattern = "yyyy-MM-dd")	private LocalDate to;
	private boolean finished;

	public void setDatas(){			//오라클에서만 필요합니다. mySql 은 쉽게 할 수 있는 limit 이라는 연산자가 있습니다.
		start=(page-1)* size+1;    //글목록 시작행번호(rownum)
		end = start + (size-1);

		//String "tc" 와 같이 view 에서 받은 파라미터 값은 배열로 변경
		if (type != null && !type.isEmpty() && !type.equals("a"))		//type="a" 를 전체로 할 예정.
			types = type.split("");			//"tc" 를 new String[]{"t", "c"} 로 변환합니다.
	}
	//글 읽기, 글 수정, 글 삭제 등에 페이지 번호와 검색 파라미터를 url 에 계속 데리고 다녀야 합니다.
	//이를 위해서 문자열 생성 메소드 정의합니다.
	private String link;		//url 에 들어갈 파라미터 문자열

	//Getter 는 처리 코드가 필요하여 직접 작성합니다.
	public String getLink(){

		if(link == null){
			StringBuilder builder = new StringBuilder();
			builder.append("page="+this.page);
			if(this.type!=null && this.type.length() > 0 && this.keyword != null){
				builder.append("&type="+this.type);
				try {
					builder.append("&keyword="+ URLEncoder.encode(this.keyword, "UTF-8"));
					//키워드는 한글 등 다국어 문자일 경우 인코딩이 필요합니다.
				}catch (UnsupportedEncodingException e){

				}
			}
			if(this.from != null && this.to != null){
				builder.append("&from="+this.from);
				builder.append("&to="+this.to);
			}
			this.link= builder.toString();
		}
		return this.link;
		//최종 link 는 EX : page=3&type=tc&keyword=hi&from=2023-03-01&to=2023-03-31
	}
}
