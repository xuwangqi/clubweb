package cn.dogoo.club.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dogoo.club.pojo.ClubActUser;
import cn.dogoo.club.pojo.ClubActivity;

public interface ClubActMapper {

	int insertClubAct(ClubActivity clubact);

	List<ClubActivity> queryClubAct(@Param("type") int type,@Param("start") int start,@Param("rows") int rows);
	
	List<ClubActivity> queryClubActByClub(@Param("clubUid") String clubUid,@Param("type") int type,@Param("start") int start,@Param("rows") int rows);

    int queryClubActCountByClub(@Param("clubUid") String clubUid,@Param("type") int type);

	ClubActivity queryClubActDetail(String caUid);

	int queryCountClubActUser(String caUid);

	int queryClubActCount(@Param("type") Integer type);

	int deleteClubAct(String[] idss);

	int updateClubActPass(@Param("type") Integer type,@Param("idss") String[] idss);

	int updateClubAct(ClubActivity clubact);
	
	int updateClubActNowNumber(@Param("number") int number,@Param("caUid") String caUid);

	List<ClubActivity> queryMyAct(@Param("user") List<ClubActUser> user,@Param("clubUid") String clubUid, @Param("type") Integer type, 
			@Param("start") int start,@Param("rows") Integer rows);

	int queryMyActCount(@Param("user") List<ClubActUser> user,@Param("clubUid") String clubUid,@Param("type") Integer type);

	List<ClubActivity> queryMyAllAct(@Param("user") List<ClubActUser> user, @Param("type") Integer type, 
			@Param("start") int start,@Param("rows") Integer rows);

	int queryMyAllActCount(@Param("user") List<ClubActUser> user,@Param("type") Integer type);

	int updateClubActScore(@Param("caUid") String caUid,@Param("score") int score);
}
