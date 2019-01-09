package io.firestrap.oauthserver.controller;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.firestrap.oauthserver.config.AuthorityPropertyEditor;
import io.firestrap.oauthserver.config.SplitCollectionEditor;
import io.firestrap.oauthserver.entity.Approvals;
import io.firestrap.oauthserver.model.ResponseBean;
import io.firestrap.oauthserver.service.ApprovalService;

@RestController
@RequestMapping("approvals")
public class ApprovalsController {
	
	
	@Autowired
	ApprovalService approvalService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Collection.class, new SplitCollectionEditor(Set.class, ","));
		binder.registerCustomEditor(GrantedAuthority.class, new AuthorityPropertyEditor());
	}
	

	@RequestMapping(value = "/getApprovals", method = RequestMethod.GET)	
	public List<Approvals> getApprovals(Model model) {
		List<Approvals> approvals = approvalService.getApprovals();				
		return approvals;
	}
	
	
	@RequestMapping(value = "/addApprovals", method = RequestMethod.POST)	
	public ResponseBean addApprovals(@RequestBody Approvals approvalrequest) {
		
		String statusMsg="";
		String statusCode="0";
		try {
		String userId = approvalService.addApprovals(approvalrequest);
		statusMsg="Successfully added the approval for the userId : "+userId;
		}catch(Exception e){
			statusMsg="Failed to add approvals";
			statusCode="-1";			
		}
		return new ResponseBean(statusMsg,statusCode);
	}
	
	@RequestMapping(value = "/editApprovals", method = RequestMethod.POST)	
	public ResponseBean editApprovals(@RequestBody Approvals approvalrequest) {
		
		String statusMsg="";
		String statusCode="0";
		try {
		String userId = approvalService.addApprovals(approvalrequest);
		statusMsg="Successfully added the approval for the userId : "+userId;
		}catch(Exception e){
			statusMsg="Failed to add approvals";
			statusCode="-1";			
		}
		return new ResponseBean(statusMsg,statusCode);
	}
	
	@RequestMapping(value = "/deleteApprovals", method = RequestMethod.GET)	
	public ResponseBean deleteApprovals(@RequestParam String userId) {
		
		String statusMsg="";
		String statusCode="0";
		try {
		approvalService.deleteApprovals(userId);
		statusMsg="Successfully deleted the approval for the userId : "+userId;
		}catch(Exception e){
			statusMsg="Failed to delete approvals";
			statusCode="-1";
			e.printStackTrace();
		}
		return new ResponseBean(statusMsg,statusCode);
	}
	
	


}
