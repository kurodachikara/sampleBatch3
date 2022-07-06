package jp.kuroda.sampleBatch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class SampleBatch3Controller {
	@Autowired
	private MemberService service;
	
	@GetMapping("")
	public String index() {
		return "index";
	}
	@PostMapping("register")
	public String register(@ModelAttribute MemberInfo info) {
		service.createMemberInfo(info);
		return "redirect:/index";
		
	}
}
