package cn.dogoo.club.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dogoo.club.pojo.ClubActUser;

public interface ClubActUserMapper {

	int queryUserInAct(@Param("caUid") String caUid,@Param("userUid") String userUid);

	int inertClubAct(ClubActUser actuser);

	int deleteClubAct(@Param("caUid") String caUid,@Param("userUid") String userUid);

	ClubActUser querActUser(@Param("userUid") String userUid,@Param("caUid") String caUid);
	
	List<ClubActUser> queryMyAct(@Param("userUid") String userUid);

	List<ClubActUser> queryUserBycaUid(@Param("caUid") String caUid);
	
	int updateUserInAct(ClubActUser act);

	int updateUserInActScore(@Param("score") Integer score,@Param("caUid") String caUid, @Param("idss") String[] idss);

	int updateUserPF(@Param("userUid") String userUid,@Param("caUid") String caUid,@Param("score") Integer score,@Param("msg") String msg);

	List<ClubActUser> queryUserBycaUidByLimit(@Param("caUid") String caUid,@Param("start") int start,@Param("rows") int rows);

    int queryUserBycaUidTotal(@Param("caUid") String caUid);
}
