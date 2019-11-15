package cn.dogoo.club.sso.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dogoo.club.pojo.User;

public interface UserMapper {

	int checkUserId(String userId);

	int insertUser(User user);

	User checkLogin(User _user);

	int updateuserpassword(@Param("userId") String userId, @Param("op") String op,@Param("np") String np);

	List<User> queryList(@Param("start") int start, @Param("rows") Integer rows);

	int queryAll();

	int updateUser(User user);

	int DeleteUserByIds(String[] idss);

	User queryUserByuserUid(String userUid);

	int UpdateUserScore(@Param("score") Integer score,@Param("idss") String[] idss);

}
