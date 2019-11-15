package cn.dogoo.club.manager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dogoo.club.manager.mapper.ClubMsgMapper;
import cn.dogoo.club.pojo.ClubMsg;
import cn.dogoo.common.util.UUIDUtil;

@Service
public class ClubMsgService {

	@Autowired
	private ClubMsgMapper msgMapper;
	
	public List<ClubMsg> queryMsg(String clubUid, Integer page, Integer rows) {
		int start=(page-1)*rows;
		return msgMapper.queryMsgByLimit(clubUid, start, rows);
	}

	public int queryMsgTotal(String clubUid) {
		return msgMapper.queryMsgClubTotal(clubUid);
	}

	public int insertMsg(ClubMsg clubmsg) {
		clubmsg.setCmUid(UUIDUtil.getUUID());
		clubmsg.setCmTime(new Date());
		return msgMapper.insertMsg(clubmsg);
	}

}
