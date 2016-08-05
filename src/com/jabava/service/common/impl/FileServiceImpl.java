package com.jabava.service.common.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.common.EhrFileMapper;
import com.jabava.pojo.common.EhrFile;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.common.IFileService;

@Service
public class FileServiceImpl implements IFileService {
	@Autowired
	private EhrFileMapper fileMapper;
	
	@Override
	public int saveOrUpdate(EhrUser user, EhrFile file) {
		Date now = new Date();
		file.setCompanyId(user.getCompanyId());
		file.setLastModifyDate(now);
		file.setLastModifyUserId(user.getUserId());
		file.setLastModifyUserName(user.getUserName());
		if(file.getFileId() == null){
			file.setCreateDate(now);
			file.setCreateUserId(user.getUserId());
			file.setCreateUserName(user.getUserName());
			return fileMapper.insert(file);
		}else{
			return fileMapper.updateByPrimaryKey(file);
		}
	}

	@Override
	public EhrFile getFileById(Long companyId, Long fileId) {
		return fileMapper.getByFileId(companyId, fileId);
	}

}
