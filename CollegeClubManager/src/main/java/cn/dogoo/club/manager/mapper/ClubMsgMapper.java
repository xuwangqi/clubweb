package cn.dogoo.club.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dogoo.club.pojo.ClubMsg;

public interface ClubMsgMapper {

	List<ClubMsg> queryMsgByLimit(@Param("clubUid") String clubUid
			,@Param("start") Integer start,@Param("rows") int rows);
	
	int insertMsg(ClubMsg clubmsg);
	
	int queryMsgClubTotal(@Param("clubUid") String clubUid);
}
