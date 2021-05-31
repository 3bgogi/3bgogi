package com.gogi.proj.aligo.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.gogi.proj.aligo.util.AligoVO.aligoBuilder;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.util.FileuploadUtil;

@Controller
@RequestMapping("/aligo_msg")
public class AligoController {

	private static final Logger logger = LoggerFactory.getLogger(AligoController.class);
	
	@Autowired
	private FileuploadUtil fileUploadUtil;
	
	@Autowired
	private AligoService aligoService;
	
	
	/**
	 * 
	 * @MethodName : aligoMsgGet
	 * @date : 2020. 7. 9.
	 * @author : Jeon KiChan
	 * @param aligoList
	 * @param model
	 * @return
	 * @메소드설명 : 문자 보내는 페이지로 가기
	 */
	@RequestMapping(value="/view.do", method=RequestMethod.GET)
	public String aligoMsgGet(@ModelAttribute AligoVOList aligoList, Model model) {
		
		AligoSendingForm asf = new AligoSendingForm();
		List<AligoSendingFormVO> asfList = aligoService.selectAligoSendingFormList();
		
		Map<String, Object> aligoRemain = asf.aligoRemain();
		
		model.addAttribute("aligoRemain", aligoRemain);
		model.addAttribute("asfList",asfList);
		model.addAttribute("aligoList", aligoList);
		
		System.out.println(aligoRemain.toString());
		
		return "aligo_msg/view";
	}
	
	@RequestMapping(value="/view.do", method=RequestMethod.POST)
	public String aligoMsgPost(@ModelAttribute AligoVOList aligoList, Model model) {
		
		AligoSendingForm asf = new AligoSendingForm();
		List<AligoSendingFormVO> asfList = aligoService.selectAligoSendingFormList();
		
		Map<String, Object> aligoRemain = asf.aligoRemain();
		
		model.addAttribute("aligoRemain", aligoRemain);
		model.addAttribute("asfList",asfList);
		model.addAttribute("aligoList", aligoList);
		
		System.out.println(aligoRemain.toString());
		
		return "aligo_msg/view";
	}
	
	
	@RequestMapping(value="/excel.do", method=RequestMethod.POST)
	@ResponseBody
	public List<AligoVO> aligoExcelFileRead(HttpServletRequest request){
		
		AligoExcelUitl aeu = new AligoExcelUitl();
		
		String fileName = "";
		
		try {
			
			fileName = fileUploadUtil.fileupload(request, FileuploadUtil.ORDER_EXCEL);
			
		} catch (Exception e) {
			
		}
		
		List<AligoVO> results = null;
		
		try {
			results = aeu.saxParsing(fileName, "C:\\Users\\3bgogi\\Desktop\\server_file\\order_excel\\");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OpenXML4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// List<AligoVO> aligoList = aeu.aligoExcelFileRead(fileName);
		
		return results;
	}
	
	@RequestMapping(value="/sending.do", method=RequestMethod.POST)
	@ResponseBody
	public String aligoMsgPost(@ModelAttribute OrdersVOList orList, @ModelAttribute AligoVO aligoVO, Model model) {
		
		AligoSendingForm asf = new AligoSendingForm();
		
		String msg = asf.smsMsg(aligoVO);
		
		return msg;
	}
	
	
	/**
	 * 
	 * @MethodName : aligoSendingFormPost
	 * @date : 2020. 7. 9.
	 * @author : Jeon KiChan
	 * @param asfVO
	 * @param model
	 * @return
	 * @메소드설명 : 문자 입력 양식 입력하기
	 */
	@RequestMapping(value="/form_insert.do", method=RequestMethod.POST)
	public String aligoSendingFormPost(@ModelAttribute AligoSendingFormVO asfVO, Model model) {
		String msg = "";
		String url = "/aligo_msg/view.do";
		
		int result = aligoService.insertAligoSendingForm(asfVO);
		
		if(result > 0) {
			msg = "문자 폼 입력 완료";
		}else {
			msg = "문자 폼 입력 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
}
