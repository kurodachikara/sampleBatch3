package jp.kuroda.sampleBatch3;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class SampleBatch3Controller {
	@Autowired
	private MemberService service;
	
	@GetMapping("")
	public String index(@ModelAttribute MemberInfo memberInfo) {
		return "index";
	}
	@PostMapping("register")
	public String register(@Valid @ModelAttribute MemberInfo memberInfo,BindingResult result ) {
		if(result.hasErrors()) {
			return "index";
		}
		service.createMemberInfo(memberInfo);
		return "redirect:/";
		
	}
}
