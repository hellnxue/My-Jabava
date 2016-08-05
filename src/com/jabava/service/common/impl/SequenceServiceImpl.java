package com.jabava.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.common.EhrSequenceMapper;
import com.jabava.pojo.common.EhrSequence;
import com.jabava.service.common.ISequenceService;

@Service
public class SequenceServiceImpl implements ISequenceService {
	@Autowired
	private EhrSequenceMapper sequenceMapper;
	
	@Override
	public Long next(EhrSequence paramSequence) throws Exception{
		EhrSequence seq = sequenceMapper.queryByParams(paramSequence);
		Long value = null;
		if(seq == null){
			seq = new EhrSequence();
			seq.setPrefix(paramSequence.getPrefix());
			seq.setCompanyId(paramSequence.getCompanyId());
			seq.setSuffix(paramSequence.getSuffix());
			seq.setValue(1L);
			seq.setRemark(null);
			sequenceMapper.insertSelective(seq);
			value = 1L;
		}else{
			if(sequenceMapper.updateByIdAndValue(seq.getId(), seq.getValue()) == 0){
				throw new JabavaServiceException("更新序列号失败");
			}
			value = seq.getValue() + 1;
		}
		
		return value;
	}

}
