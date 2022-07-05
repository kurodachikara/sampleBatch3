package jp.kuroda.sampleBatch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberInfoRepository repository;
	
	public void createMemberInfo(String name,String mail,int tel,String adress) {
		MemberInfo info=new MemberInfo();
		info.setName(name);
		info.setMailadress(mail);
		info.setTelNumber(tel);
		info.setAdress(adress);
		repository.save(info);
		
	}
}
