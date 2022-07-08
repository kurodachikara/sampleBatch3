package jp.kuroda.sampleBatch3;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberInfoRepository{
	@Select("SELECT * FROM memberinfo WHERE flag=true")
	List<MemberInfo> selectMemberInfoFlagTrue();
	
	@Insert("INSERT INTO memberinfo VALUES (id,#{username},#{mailaddress},#{telnumber},#{memberaddress},#{flag})")
	void insert(String username,String mailaddress,String telnumber,String memberaddress,Boolean flag);
	
}
