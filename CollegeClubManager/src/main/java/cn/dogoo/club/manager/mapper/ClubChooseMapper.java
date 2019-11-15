package cn.dogoo.club.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dogoo.club.pojo.ClubChoose;
import cn.dogoo.club.pojo.User;

public interface ClubChooseMapper {

	int queryCountByUC(@Param("userUid") String userUid,@Param("clubUid")  String clubUid);

	int insertClub(ClubChoose cc);

	List<ClubChoose> queryclubsByuserId(String userId);

	List<ClubChoose> queryusersByclubuid(String clubUid);

	User getUserByuid(@Param("userUid") String userUid);

	List<ClubChoose> queryusersByclubuid(@Param("clubUid") String clubUid,@Param("start") Integer start,@Param("rows") int rows);
	
	int queryClubUserCount(String clubUid);

	int deleteClubChoose(@Param("clubUid")  String clubUid,@Param("userUid") String userUid);

	int updatecuType(@Param("userUid") String userUid,@Param("clubUid") String clubUid,@Param("cuType") Integer cuType);

	int deleteClubs(String[] idss);

	//预留方法:查询社团人数方法
	int queryclubUsercount(String clubUid);
	
}
