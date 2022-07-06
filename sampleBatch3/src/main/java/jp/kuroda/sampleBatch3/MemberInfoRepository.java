package jp.kuroda.sampleBatch3;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberInfoRepository{
	@Select("SELECT id,username,mailaddress FROM memberinfo WHERE id=#{id} flag=true")
	MemberInfo selectMemberInfoFlagTrue(Integer id);
	
	@Insert("INSERT INTO memberinfo VALUES (#{username},#{mailaddress},#{telnumber},#{address},#{flag})")
	void insert(String username,String mailaddress,String telnumber,String address,Boolean flag);
	
}
