package com.search.elastic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.search.elastic.repository.EpisodeElasticSearchRepository;
import com.search.elastic.service.EpisodeElasticService;
import com.search.model.Episode;
import com.search.model.Metadata;
import com.search.utility.CustomException;
import com.search.utility.TechnicalException;

@Service
public class EpisodeElasticServiceImpl implements EpisodeElasticService {
	
	 @Autowired
	 private EpisodeElasticSearchRepository episodeElasticSearchRepository;
	 
	 @Override
	 public Episode findById(String id) throws TechnicalException {
		 Episode epic = episodeElasticSearchRepository.findById(id);
		 if (epic == null) {
				throw new TechnicalException(CustomException.INVALID_EPISODE_ID_EXCEPTION.getCode(),
						HttpStatus.BAD_REQUEST);
			}
		 return epic;
	 }

	@Override
	public Episode saveEpisode(Episode episode) {
		return episodeElasticSearchRepository.save(episode);
	}
	
	@Override
	public Episode updateEpisode(Episode episode) throws TechnicalException {
		if(episode==null)throw new TechnicalException(CustomException.DATA_NOT_PRESENT.getCode(),HttpStatus.BAD_REQUEST);
		Episode epic=findById(episode.getId());
		epic=new Episode(episode!=null && episode.getTitle()!=null && !episode.getTitle().isEmpty()?episode.getTitle():epic.getTitle(),
				new Metadata(
						episode.getMetadata()!=null && episode.getMetadata().getRegion()!=null && !episode.getMetadata().getRegion().isEmpty()?episode.getMetadata().getRegion():epic.getMetadata().getRegion(),
						episode.getMetadata()!=null && episode.getMetadata().getLongSynopsis()!=null && !episode.getMetadata().getLongSynopsis().isEmpty()?episode.getMetadata().getLongSynopsis():epic.getMetadata().getLongSynopsis(),
						episode.getMetadata()!=null && episode.getMetadata().getMetaDesc()!=null && !episode.getMetadata().getMetaDesc().isEmpty()?episode.getMetadata().getMetaDesc():epic.getMetadata().getMetaDesc(),
						episode.getMetadata()!=null && episode.getMetadata().getTags()!=null && !episode.getMetadata().getTags().isEmpty()?episode.getMetadata().getTags():epic.getMetadata().getTags()),
				episode!=null && episode.getStatus()!=null && !episode.getStatus().isEmpty()?episode.getStatus():epic.getStatus(),
				episode!=null && episode.getDate()>0?episode.getDate():epic.getDate());
		epic.setId(episode.getId());
		return episodeElasticSearchRepository.save(epic);
	}
	

	@Override
	public List<Episode> getAllEpisodeFromElasticRepo() {
		return Lists.newArrayList(episodeElasticSearchRepository.findAll());
	}
	
	@Override
	public List<Episode> getAllEpisodeByRegion(String region,int pageNo,int size) throws TechnicalException {
	      Page<Episode> page=  episodeElasticSearchRepository.findByRegion(region,  new PageRequest(pageNo, size));
	      List<Episode> episodeList=page.getContent();
	      if(episodeList==null || episodeList.isEmpty())
	    	  throw new TechnicalException(CustomException.DATA_NOT_PRESENT.getCode(),HttpStatus.BAD_REQUEST);
		return page.getContent();
	}
	
	@Override
	public List<Episode> getAllEpisodeByTags(List<String> tags,int pageNo,int size) {
	      Page<Episode> page=  episodeElasticSearchRepository.findByTags(tags,new PageRequest(pageNo, size));
		return page.getContent();
	}
	
	
	@Override
	public boolean deleteByEpisodeId(String episodeId) throws TechnicalException {
		Episode epic = episodeElasticSearchRepository.findById(episodeId);
		if (epic == null) {
			throw new TechnicalException(CustomException.INVALID_EPISODE_ID_EXCEPTION.getCode(),
					HttpStatus.BAD_REQUEST);
		}
		episodeElasticSearchRepository.delete(epic);
		return true;

	}


}
