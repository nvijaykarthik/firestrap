package io.firestrap.oauthserver.controller;

import static java.util.Arrays.asList;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private JdbcClientDetailsService clientDetailsService;

	@Autowired
	private ApprovalStore approvalStore;

	@RequestMapping("/")
	public ModelAndView root(Map<String, Object> model, Principal principal) {

		List<Approval> approvals = clientDetailsService.listClientDetails().stream()
				.map(clientDetails -> approvalStore.getApprovals(principal.getName(), clientDetails.getClientId()))
				.flatMap(Collection::stream).collect(Collectors.toList());
		log.debug("approvals {}", approvals);
		log.debug("Clients {}", clientDetailsService.listClientDetails());
		model.put("approvals", approvals);
		model.put("clientDetails", clientDetailsService.listClientDetails());
		return new ModelAndView("index", model);

	}

	@Autowired
	private TokenStore tokenStore;

	@RequestMapping(value = "/approval/revoke", method = RequestMethod.POST)
	public String revokApproval(@ModelAttribute Approval approval) {

		approvalStore.revokeApprovals(asList(approval));
		tokenStore.findTokensByClientIdAndUserName(approval.getClientId(), approval.getUserId())
				.forEach(tokenStore::removeAccessToken);
		return "redirect:/";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}
