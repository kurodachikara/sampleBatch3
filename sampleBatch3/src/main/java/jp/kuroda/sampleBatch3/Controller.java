package jp.kuroda.sampleBatch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	private MemberService service;
	
	@GetMapping("")
	public String index() {
		return "index";
	}
	@PostMapping("register")
	public String register(String name,String mail,Integer tel,String adress) {
		service.createMemberInfo(name, mail, tel, adress);
		return "redirect:/index";
		
	}
}
