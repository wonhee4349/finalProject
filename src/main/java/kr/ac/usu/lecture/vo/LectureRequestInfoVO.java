package kr.ac.usu.lecture.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.facilities.vo.BuildingVO;
import kr.ac.usu.facilities.vo.FacilitiesVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @author PC-21
 *
 * @author 김재성
 * @since 2023. 11. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 *   수정일          수정자             수정내용
 * ----------     ---------    ------------------
 * 2023.11.23.     김재성             최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of={"tmtbSe","lctreReqstNo"})
@ToString
public class LectureRequestInfoVO {
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String tmtbSe;
	
	@NotBlank
	@Size(min = 10 , max = 10)
	private String lctreReqstNo;
	
	@NotBlank
	@Size(min = 5 , max = 5)
	private String fcltsNo;
	
	@NotBlank
	@Size(min = 2 , max = 2)
	private String buldNo;
	
	private List<FacilitiesVO> facilities;	// has 1:many
	private BuildingVO building;	// has 1:1
}
