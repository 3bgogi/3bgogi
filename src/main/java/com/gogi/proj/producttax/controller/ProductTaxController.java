package com.gogi.proj.producttax.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.commission.SmartstoreCommission;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.producttax.model.ProductTaxService;
import com.gogi.proj.producttax.vo.ProductInfoListVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;
import com.gogi.proj.producttax.vo.ResCompanyVO;
import com.gogi.proj.producttax.vo.TaxTableVO;
import com.gogi.proj.util.FileuploadUtil;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping(value="/tax")
public class ProductTaxController {

	@Autowired
	private ProductTaxService ptService;
	
	@Autowired
	private FileuploadUtil fileUploadUtil;
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	
	/**
	 * 
	 * @MethodName : resCompanyGet
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 거래처 입력 페이지 들어가기
	 */
	@RequestMapping(value="/res_company/insert.do", method=RequestMethod.GET)
	public String resCompanyGet() {
		
		return "tax/res_company/insert";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyUpdate
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param rcVOs
	 * @param model
	 * @return
	 * @메소드설명 : 입력된 거래처 상세확인
	 */
	@RequestMapping(value="/res_company/read.do", method=RequestMethod.GET)
	public String resCompanyUpdateGet(@ModelAttribute ResCompanyVO rcVOs, Model model) {
		
		ResCompanyVO rcVO = ptService.selectRecCompanyByRcPk(rcVOs);
		
		model.addAttribute("rcVO", rcVO);
		
		return "tax/res_company/read";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyUpdatePost
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @param model
	 * @return
	 * @메소드설명 : 거래처 수정하기
	 */
	@RequestMapping(value="/res_company/read.do", method=RequestMethod.POST)
	public String resCompanyUpdatePost(@ModelAttribute ResCompanyVO rcVO, Model model) {
		
		String msg = "";
		String url = "/tax/res_company/list.do";
		
		int result = ptService.updateResCompany(rcVO);
		
		if(result > 0) {
			msg = "거래처 수정 완료";
			
		}else {
			msg = "거래처 수정 실패";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyDelete
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @param model
	 * @return
	 * @메소드설명 : 거래처 삭제하기
	 */
	@RequestMapping(value="/res_company/delete.do", method=RequestMethod.GET)
	public String resCompanyDelete(@ModelAttribute ResCompanyVO rcVO, Model model) {
		String msg = "";
		String url = "/tax/res_company/list.do";
		
		int result = ptService.deleteResCompany(rcVO);
		
		if(result > 0) {
			msg = "거래처 삭제완료";
			
		}else {
			msg = "거래처 삭제 실패";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyPost
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param rcVO
	 * @param model
	 * @return
	 * @메소드설명 : 새로운 거래처 입력하기
	 */
	@RequestMapping(value="/res_company/insert.do", method=RequestMethod.POST)
	public String resCompanyPost(@ModelAttribute ResCompanyVO rcVO, Model model) {
		
		String msg = "";
		String url = "/tax/res_company/list.do";
		
		int result = ptService.insertResCompany(rcVO);
		
		if(result > 0) {
			msg = "새로운 거래처 등록 완료";
			
		}else {
			msg = "거래처 등록 실패, 로그를 확인해주세요";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : resCompanyList
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 입력된 모든 거래처 확인하기
	 */
	@RequestMapping(value="/res_company/list.do", method=RequestMethod.GET)
	public String resCompanyList(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		List<ResCompanyVO> rcList = ptService.selectRecCompany(osVO);
		
		model.addAttribute("osVO", osVO);
		model.addAttribute("rcList", rcList);
		
		return "tax/res_company/list";
	}
	
	
	/**
	 * 
	 * @MethodName : insertProductInfoGet
	 * @date : 2020. 11. 4.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 거래내역서 입력 페이지
	 */
	@RequestMapping(value="/product_info/insert.do", method=RequestMethod.GET)
	public String insertProductInfoGet(Model model) {
		OrderSearchVO osVO = new OrderSearchVO();
		
		List<ResCompanyVO> rcList = ptService.selectRecCompany(osVO);
		
		model.addAttribute("rcList", rcList);
		
		return "tax/product_info/insert";
	}
	
	
	/**
	 * 
	 * @MethodName : insertProductInfoPost
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param request
	 * @param piList
	 * @param model
	 * @return
	 * @메소드설명 : 거래내역서 입력
	 */
	@RequestMapping(value="/product_info/insert.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	public String insertProductInfoPost(HttpServletRequest request, @ModelAttribute ProductInfoListVO piList, Model model) {
		String fileExe = "";
		String fileUniqName = "";
		String filePath = "";
		String fileOriName = "";
		
		String msg = "";
		String url = "/tax/product_info/insert.do";
		
		try {
			List<Map<String, Object>> fileInfo = fileUploadUtil.fileupload2(request, FileuploadUtil.TAX_FILE);
			
			fileExe=fileInfo.get(0).get("fileExtType")+"";
			fileUniqName=fileInfo.get(0).get("uniFileName")+"";
			filePath=fileInfo.get(0).get("filePath")+"";
			fileOriName=fileInfo.get(0).get("oriFileName")+"";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = "파일 업로드 실패. 로그를 확인해주세요.";
		}
		
		piList.getPiList();
		
		int result = 0;
		
		for(int i = 0; i < piList.getPiList().size(); i++) {
			
			if(piList.getPiList().get(i).getPiName() != null) {				
				piList.getPiList().get(i).setPiFileExe(fileExe);
				piList.getPiList().get(i).setPiFileOriName(fileOriName);
				piList.getPiList().get(i).setPiFilePath(filePath);
				piList.getPiList().get(i).setPiFileUniqName(fileUniqName);
				piList.getPiList().get(i).setPiInputDate(piList.getInputDates());
				piList.getPiList().get(i).setRcFk(piList.getResCompany());
				result = ptService.insertProductInfo(piList.getPiList().get(i));
				
			}
		}
		
		if(result > 0) {
			msg = "거래내역서 입력 완료";
			
		}else {
			msg = "거래내역서 입력 실패";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : selectProductInfoGet
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 거래내역서 목록 가져오기
	 */
	@RequestMapping(value="/product_info/list.do", method=RequestMethod.GET)
	public String selectProductInfoGet(@ModelAttribute OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}
		
		int totalRecord = ptService.selectProductInfoListCounting(osVO);
		
		osVO.setTotalRecord(totalRecord);
		osVO.setBlockSize(10);
		
		if(totalRecord <=osVO.getRecordCountPerPage()) {
			osVO.setCurrentPage(1);
		}
		
		if(osVO.getRecordCountPerPage() == 0) {			
			osVO.setRecordCountPerPage(PageUtility.RECORD_COUNT_PER_PAGE);
			
		}
		
		List<ProductInfoVO> piList = ptService.selectProductInfoList(osVO);
		
		List<ResCompanyVO> rcList = ptService.selectRecCompany(osVO);
		
		model.addAttribute("rcList", rcList);
		model.addAttribute("osVO", osVO);
		model.addAttribute("piList", piList);
		
		return "tax/product_info/list";
	}
	
	
	/**
	 * 
	 * @MethodName : productInfoFileDownload
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param request
	 * @param piVO
	 * @return
	 * @메소드설명 : 거래내역서 파일 다운로드
	 */
	@RequestMapping(value="/product_info/file_down.do", method=RequestMethod.GET)
	public ModelAndView productInfoFileDownload(HttpServletRequest request, @ModelAttribute ProductInfoVO piVO) {
		
		File file = new File(piVO.getPiFilePath(), piVO.getPiFileUniqName());
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
	
	
	/**
	 * 
	 * @MethodName : taxUpdate
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @메소드설명 : 세금계산서 체크
	 */
	@RequestMapping(value="/product_info/tax_update.do", method=RequestMethod.GET)
	@ResponseBody
	public int taxUpdate(@ModelAttribute ProductInfoVO piVO) {
		
		return ptService.updateTaxbilFlag(piVO);
	}
	
	
	/**
	 * 
	 * @MethodName : accUpdate
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @return
	 * @메소드설명 : 이체 여부 체크
	 */
	@RequestMapping(value="/product_info/acc_update.do", method=RequestMethod.GET)
	@ResponseBody
	public int accUpdate(@ModelAttribute ProductInfoVO piVO) {
	
		return ptService.updateAccFlag(piVO);
	}
	
	
	/**
	 * 
	 * @MethodName : deleteProductInfoByPiPk
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @param model
	 * @return
	 * @메소드설명 : 거래내역서 삭제하기
	 */
	@RequestMapping(value="/product_info/delete.do", method=RequestMethod.GET, produces="application/text; charset=utf8")
	@ResponseBody
	public String deleteProductInfoByPiPk(@ModelAttribute ProductInfoVO piVO ){
		String msg = "";

		int result = ptService.deleteProductInfo(piVO);
		
		if(result > 0) {
			msg = "거래내역서 삭제 완료";
		}else {
			msg = "거래내역서 삭제 실패";
		}
		
		return msg;
	}
	
	
	/**
	 * 
	 * @MethodName : readProductInfoByPiPk
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @param model
	 * @return
	 * @메소드설명 : 거래내역서 상세보기
	 */
	@RequestMapping(value="/product_info/detail.do", method=RequestMethod.GET)
	public String readProductInfoByPiPk(@ModelAttribute ProductInfoVO piVO, Model model) {
		
		ProductInfoVO read = ptService.selectProductInfoByPiPk(piVO);
		
		model.addAttribute("piVO", read);
		
		return "tax/product_info/read";
	}
	
	
	/**
	 * 
	 * @MethodName : updateProductInfo
	 * @date : 2020. 10. 30.
	 * @author : Jeon KiChan
	 * @param piVO
	 * @param model
	 * @return
	 * @메소드설명 : 거래내역서 수정하기
	 */
	@RequestMapping(value="/product_info/update.do", method=RequestMethod.POST)
	public String updateProductInfo(HttpServletRequest request ,@ModelAttribute ProductInfoVO piVO, Model model) {
		
		String fileExe = "";
		String fileUniqName = "";
		String filePath = "";
		String fileOriName = "";

		
		try {
			List<Map<String, Object>> fileInfo = fileUploadUtil.fileupload2(request, FileuploadUtil.TAX_FILE);
			
			fileExe=fileInfo.get(0).get("fileExtType")+"";
			fileUniqName=fileInfo.get(0).get("uniFileName")+"";
			filePath=fileInfo.get(0).get("filePath")+"";
			fileOriName=fileInfo.get(0).get("oriFileName")+"";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
		if(fileExe != null && !fileExe.equals("")) {
			piVO.setPiFileExe(fileExe);
			piVO.setPiFileOriName(fileOriName);
			piVO.setPiFilePath(filePath);
			piVO.setPiFileUniqName(fileUniqName);
		}
		
		String msg = "";
		String url = "/tax/product_info/detail.do?piPk="+piVO.getPiPk();
		
		int result = ptService.updateProductInfo(piVO);
		
		if(result > 0) {
			msg = "수정 완료";
		}else {
			msg = "수정 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/delete_tax_table.do", method=RequestMethod.GET)
	public String taxTableDelete(Model model) {
		String msg = "";
		String url = "/tax/tax_table.do";
		
		int result = ptService.deleteTaxTable();
		
		if(result > 0) {
			msg = "삭제 완료";
		}else {
			msg = "삭제 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping(value="/tax_table.do", method=RequestMethod.GET)
	public String taxTablePost(Model model) {
		
		List<TaxTableVO> taxList = ptService.taxZero();
		List<TaxTableVO> dutyList = ptService.dutyZero();
		List<TaxTableVO> totalCount = ptService.taxTableCount();
		
		model.addAttribute("taxList", taxList);
		model.addAttribute("dutyList", dutyList);
		model.addAttribute("totalCount", totalCount);
		
		return "tax/tax_table";
	}
	
	@RequestMapping(value="/tax_table.do", method=RequestMethod.POST)
	public String taxTablePost(HttpServletRequest request, Model model) {
		
		
		String fileName = "";
		
		try {
			
			fileName = fileUploadUtil.fileupload(request, FileuploadUtil.ORDER_EXCEL);
			
		} catch (Exception e) {
			
		}
		
		List<TaxTableVO> taxList = new ArrayList<TaxTableVO>();
		
		FileuploadUtil fu = new FileuploadUtil();
		
		try {
	
			FileInputStream fis= new FileInputStream("C:\\Users\\3bgogi\\Desktop\\server_file\\order_excel\\"+fileName);
			
			// 엑셀파일 확장자가 xlsx 일 경우
			// 분기별로 처리해서 해야함
					XSSFWorkbook workbook=new XSSFWorkbook(fis);
					
					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
					
					int rowStartIndex = 1;
					
					int columnindex=0;
					
					XSSFSheet sheet=workbook.getSheetAt(0);
					
					int rows=sheet.getPhysicalNumberOfRows();
					
					for(int rowindex = rowStartIndex; rowindex<rows;rowindex++){
						
						XSSFRow row=sheet.getRow(rowindex);
						
						TaxTableVO ttVO = null;
						
						if(row !=null){
							ttVO = new TaxTableVO();
							
							for(columnindex=0;columnindex<14;columnindex++){
								
								XSSFCell cell=row.getCell(columnindex);
								
								if(columnindex==0) {
									if(cell == null) {
										break;
									}else {
										continue;
									}
										
								}if(columnindex==1) {
									ttVO.setTtDate(cell.getStringCellValue());
									
								}if(columnindex==2) {
									
									ttVO.setTtOrderNumber(cell.getStringCellValue());
									
								}if(columnindex==3) {
									
									if(cell == null) {
										ttVO.setTtProductOrderNumber("-");
									}else {										
										ttVO.setTtProductOrderNumber(cell.getStringCellValue());
									}
										
								}if(columnindex==4) {
									
									ttVO.setTtTaxType(cell.getStringCellValue());
										
								}if(columnindex==5) {
									ttVO.setTtProduct(cell.getStringCellValue());
										
								}if(columnindex==6) {
									ttVO.setTtTaxStat(cell.getStringCellValue());
										
								}if(columnindex==7) {
									ttVO.setTtTotalPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==8) {
									ttVO.setTtTaxPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==9) {
									ttVO.setTtDutyFreePrice((int)cell.getNumericCellValue());
									
								}if(columnindex==10) {
									ttVO.setTtCreditPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==11) {
									ttVO.setTtCashDeductionPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==12) {
									ttVO.setTtCashReceiptPrice((int)cell.getNumericCellValue());
									
								}if(columnindex==13) {
									ttVO.setTtAnotherPrice((int)cell.getNumericCellValue());
									
									taxList.add(ttVO);
									//ptService.insertTaxTableData(ttVO);
								}
								
								
							}//for
						}
						
						
						
					}//for
				
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		int [] result = ptService.insertTaxTableDataBatch(taxList);
		
		System.out.println("입력 완료  = "+result[0]+" , 중복값 = "+result[1]+" , 총 합 = "+result[2]);
		
		String msg = "입력완료";
		String url = "/tax/tax_table.do";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
public String cellTypeReturn(XSSFCell cell) {
		
		String value = "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 switch (cell.getCellType()){
           case HSSFCell.CELL_TYPE_FORMULA:
               value=cell.getCellFormula()+"";
               break;
               
           case HSSFCell.CELL_TYPE_NUMERIC:
	           	if(HSSFDateUtil.isCellDateFormatted(cell)) {
	           		value=dateFormat.format(cell.getDateCellValue());
	           		
	           	}else {		                            		
	           		value=cell.getNumericCellValue()+"";
	           	}
	           	
               break;
           case HSSFCell.CELL_TYPE_STRING:
               value=cell.getStringCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_BLANK:
               value=cell.getBooleanCellValue()+"";
               break;
               
           case HSSFCell.CELL_TYPE_ERROR:
               value=cell.getErrorCellValue()+"";
               break;
               
           default: 
        	   value=cell.getStringCellValue()+"";
           	break;
           }
		 
		return value;
	}
}
