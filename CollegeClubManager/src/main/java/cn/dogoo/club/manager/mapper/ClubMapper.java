package cn.dogoo.club.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dogoo.club.pojo.Club;
import cn.dogoo.club.pojo.ClubChoose;

public interface ClubMapper {

	List<Club> queryList(@Param("start") int start,@Param("rows") Integer rows,@Param("clubType") Integer clubType);

	int queryCount(@Param("clubType") Integer clubType);

	Club queryClubByUid(String uid);

	int insertClub(Club club);

	int updateClub(Club club);

	int deleteClub(String[] idss);

	List<Club> queryclubByClubid(List<ClubChoose> clubcs);
	
	int updateClubNowNumer(@Param("type") int type,@Param("clubUid") String clubUid);

	int updatepass(@Param("type") String type, @Param("idss") String[] idss);
	
	
}
