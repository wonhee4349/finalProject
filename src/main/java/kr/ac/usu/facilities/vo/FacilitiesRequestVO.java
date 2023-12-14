package kr.ac.usu.facilities.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.ac.usu.user.vo.StudentVO;
import kr.ac.usu.validate.grouphint.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "fcltsReqstNo")
@ToString
public class FacilitiesRequestVO {

	
	private int rnum;

	
    @NotBlank
    private String fcltsReqstNo;

    @NotBlank(groups = InsertGroup.class)
    private String fcltsReqstApplcnt;

    private String fcltsReqstPurps;

    @NotBlank(groups = InsertGroup.class)
    @Size(max = 4, groups = InsertGroup.class)
    private String confmSe;

    private String fcltsReqstReturn;

    @NotBlank(groups = InsertGroup.class)
    @Size(max = 30, groups = InsertGroup.class)
    private String fcltsNo;

    @NotBlank(groups = InsertGroup.class)
    @Size(min = 2, max = 2, groups = InsertGroup.class)
    private String tmtbSe;

    @NotBlank(groups = InsertGroup.class)
    @Size(min = 10, max = 10, groups = InsertGroup.class)
    private String fcltsReqstDate;
    
    
	private String student;
}


