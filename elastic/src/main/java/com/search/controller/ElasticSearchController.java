package com.search.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.elastic.service.EpisodeElasticService;
import com.search.model.Episode;
import com.search.utility.TechnicalException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@SuppressWarnings("unchecked")
@RestController
@Api(description="Api Feature!")
public class ElasticSearchController {
	
	public static final String successMsg="Data saved Successfully";
	
	public static String errorMsg="Error occured while creating Episode!";

	
	@Autowired
	private EpisodeElasticService episodeElasticService;
	
	
	@PostMapping(value="/create")
	@ApiOperation(value = "Create a new Episode", response = String.class )
	public String createEpisode(
			@Valid @RequestBody Episode epsiode,
			HttpServletRequest request,HttpServletResponse response) {
		Episode episode=episodeElasticService.saveEpisode(epsiode);
		if(episode!=null) return successMsg;
		else return errorMsg;
	}
	
	@PostMapping(value="/update",produces = "application/json")
	@ApiOperation(value = "Update existing data")
	public Map<String,String> updateEpisodeData(
			@Valid @RequestBody Episode epsiode,
			HttpServletRequest request,HttpServletResponse response) throws TechnicalException {
			episodeElasticService.updateEpisode(epsiode);
		return (Map<String, String>) new HashMap<>().put("success",successMsg);
	}
	
	@DeleteMapping(value="/delete")
	@ApiOperation(value = "Delete a record from repository")
	public void deleteEpisode(@RequestParam(value="id",required=true)String id,HttpServletRequest request,HttpServletResponse response) throws TechnicalException {
		episodeElasticService.deleteByEpisodeId(id);
	}
	
	@RequestMapping(value="/getByIdFromElasticRepo",method= RequestMethod.GET,produces = "application/json")
	@ApiOperation(value = "Get A Records From Repostiory By UniqueId", response = Episode.class )
	public Episode getByIdElastic(
			@RequestParam(value="id",required=true)String id,
			HttpServletRequest request,	HttpServletResponse response) throws TechnicalException {
			return	episodeElasticService.findById(id);
	}
	
	@RequestMapping( value="/getAllFromElasticRepo",method= RequestMethod.GET,produces = "application/json")
	@ApiOperation(value = "Get All Records From Repostiory", response = Episode[].class )
	public List<Episode> getAllEpisodeFromElasticRepo() {
			return	episodeElasticService.getAllEpisodeFromElasticRepo();
	}
	
	 @RequestMapping(value="/getByRegionFromElasticRepo",method=RequestMethod.GET,produces = "application/json")
	 @ApiOperation(value = "Get All Records From Repostiory By Region", response = Episode[].class )
	    public List<Episode> getAllEpisodeElasticByRegion(
	    		@RequestParam(value="region",required=true)String region,
	    		@RequestParam(value="pageNo",required=true)int pageNo,
	    		@RequestParam(value="size",required=true)int size) throws TechnicalException {
	        return episodeElasticService.getAllEpisodeByRegion(region, pageNo, size);
	 }
	 
	 @RequestMapping( value="/getByTagsFromElasticRepo",method= RequestMethod.GET,produces = "application/json")
	 @ApiOperation(value = "Get All Records From Repostiory By Tags", response = Episode[].class )
	 public List<Episode> getEpisodeByTagsFromElasticRepo(
	    		@RequestParam(value="tags",required=true)String[] tags,
	    		@RequestParam(value="pageNo",required=true)int pageNo,
	    		@RequestParam(value="size",required=true)int size,
				HttpServletRequest request,	HttpServletResponse response) {
	        return episodeElasticService.getAllEpisodeByTags(Arrays.asList(tags), pageNo, size);
	    }
	
	

}
