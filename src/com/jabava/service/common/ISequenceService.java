package com.jabava.service.common;

import com.jabava.pojo.common.EhrSequence;

public interface ISequenceService {
	public Long next(EhrSequence paramSequence) throws Exception;
}
