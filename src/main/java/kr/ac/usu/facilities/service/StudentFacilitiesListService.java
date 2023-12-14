package kr.ac.usu.facilities.service;

import kr.ac.usu.facilities.vo.FacilitiesVO;
import kr.ac.usu.paging.vo.PaginationInfo;
import kr.ac.usu.user.vo.ProfessorVO;

/**
 * 
 * @author PC-25
 *
 * @author 김원희
 * @since 2023. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2023. 11. 25.      작성자명       최초작성
 * Copyright (c) 2023 by DDIT All right reserved
 * </pre>
 */
public interface StudentFacilitiesListService {
	
	public void retrieveFacilitiesList(PaginationInfo<FacilitiesVO> paging);

	public FacilitiesVO retrieveFacilities(String fcltsNo);
	
	public int createFacilities(FacilitiesVO facilities);

	public int modifyFacilities(FacilitiesVO facilities);
}
