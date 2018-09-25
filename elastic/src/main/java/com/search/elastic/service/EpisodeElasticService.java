package com.search.elastic.service;

import java.util.List;

import com.search.model.Episode;
import com.search.utility.TechnicalException;

public interface EpisodeElasticService {
	
	Episode findById(String id) throws TechnicalException;
	
	Episode saveEpisode(Episode episode);
	
	Episode updateEpisode(Episode episode) throws TechnicalException;
	
	List<Episode>  getAllEpisodeFromElasticRepo();


	List<Episode> getAllEpisodeByRegion(String region, int pageNo, int size) throws TechnicalException;

	List<Episode> getAllEpisodeByTags(List<String> tags, int pageNo, int size);

	boolean deleteByEpisodeId(String episodeId) throws TechnicalException;
	

}
