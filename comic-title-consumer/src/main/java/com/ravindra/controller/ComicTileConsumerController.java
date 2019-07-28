package com.ravindra.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravindra.service.ComicTileConsumerService;
/**
 * This is Consumer Rest Controller to call the Comic api
 * @author Veera Shankara RavindraReddy Kakarla
 */
@RestController
@RequestMapping("/comic")
@CrossOrigin(origins="*")
@RefreshScope
public class ComicTileConsumerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ComicTileConsumerService consumerService;
	
	/**
	 * This method to call the Comic API to get the comic title
	 * @return comic title
	 */
	@GetMapping(value="/title")
	public String getComicTitle()
	{
		logger.info("Begin ComicTileConsumerController - getComicTitle()");
		return consumerService.getComicTitle();
	}
}