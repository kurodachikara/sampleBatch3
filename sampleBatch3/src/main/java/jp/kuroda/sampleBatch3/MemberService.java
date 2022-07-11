package jp.kuroda.sampleBatch3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
	@Autowired
	private MemberInfoRepository repository;
	
	@Transactional
	public void createMemberInfo(MemberInfo info) {
		repository.insert(info.getUsername(),info.getMailaddress(),info.getTelNumber(),info.getMemberaddress(),info.getFlag());
	}
	@Transactional
	public List<MemberInfo> selectMember(){
		return repository.selectMemberInfoFlagTrue();
	}
}
