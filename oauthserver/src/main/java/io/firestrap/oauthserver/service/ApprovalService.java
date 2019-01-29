package io.firestrap.oauthserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.firestrap.oauthserver.entity.Approvals;
import io.firestrap.oauthserver.repository.ApprovalsRepository;

@Service
public class ApprovalService {

	@Autowired
	ApprovalsRepository approvalsRepository;

	
	
	
	public List<Approvals> getApprovals()
	{
		Approvals addapproval= new Approvals();
		
		List<Approvals> getApprovals= new ArrayList();
		getApprovals=  approvalsRepository.findAll();	
		
		return getApprovals;				
	}
	
	
	public String addApprovals(Approvals approvalrequest)
	{
		Approvals addapproval= new Approvals();
		
		addapproval.setUserId(approvalrequest.getUserId());
		addapproval.setClientId(approvalrequest.getClientId());
		addapproval.setScope(approvalrequest.getScope());
		addapproval.setStatus(approvalrequest.getStatus());
		addapproval.setExpireAt(approvalrequest.getExpireAt());
		addapproval.setLastModifiedDate(approvalrequest.getLastModifiedDate());
		
		Approvals addedApprovals= approvalsRepository.save(addapproval);	
		
		return addedApprovals.getUserId();				
	}
	
	public void deleteApprovals(String userId)
	{
		 approvalsRepository.deleteByUserId(userId);					 			
	}
 	
 	
	
	 
	
}
