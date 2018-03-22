package com.live.service.impl.artist;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.live.service.artist.ArtistServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("artistService")
@Transactional
public class ArtistServiceImpl extends CommonServiceImpl implements ArtistServiceI {
	
}