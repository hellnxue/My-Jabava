package com.jabava.service.common;

import com.jabava.pojo.common.EhrFile;
import com.jabava.pojo.manage.EhrUser;

public interface IFileService {
	int saveOrUpdate(EhrUser user, EhrFile file);
	EhrFile getFileById(Long companyId, Long fileId);
}
