package com.cts.project.theater.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cts.project.theater.entity.Theater;
import com.cts.project.theater.errorHandling.CustomErrorType;
import com.cts.project.theater.errorHandling.TheaterNotFoundException;
import com.cts.project.theater.repository.TheaterRepository;

@RestController
@RequestMapping("/theater")
public class TheaterController 
{
		 @Autowired
	     private TheaterRepository theaterRepository;
	
		 private final Logger logger = LoggerFactory.getLogger(this.getClass());
			
		 @RequestMapping(value = "/", method = RequestMethod.GET) 
		 public ResponseEntity<List<Theater>> listAllTheater() throws IOException
		 {
			    logger.info("Fetching all Theater's details...");
		        List<Theater> theater;
		        try 
		        {
		        	theater= theaterRepository.findAll();
			        
			        if (theater.isEmpty()) {
			            return new ResponseEntity<List<Theater>>(HttpStatus.NO_CONTENT);
			            // You many decide to return HttpStatus.NOT_FOUND
			        }
		        }
		        catch(Exception e) 
				 { 
					logger.error("RunTimeException is thrown... ",e);
					throw new RuntimeException(e);
				 }
		        
		        return new ResponseEntity<List<Theater>>(theater, HttpStatus.OK);
		  } 
		 
		 @SuppressWarnings({ "unchecked", "rawtypes" })
		 @RequestMapping(value = "/{theaterId}", method = RequestMethod.GET)
		 public ResponseEntity<Theater> getTheaterById(@PathVariable("theaterId") Integer theaterId) throws IOException
	     {
			   logger.info("Fetching Theater with id {}", theaterId);
			   Theater theater;
			   try 
			   {
				   theater = theaterRepository.findById(theaterId).orElse(null);
				 
				  if (theater == null)
				  {
					  logger.error("Theater with id {} not found.", theaterId);
					  return new ResponseEntity(new CustomErrorType("404","Unable to find Theater with id: " 
					                            +  theaterId + ", does not exist."),HttpStatus.NOT_FOUND);
				  }
			   }
			   catch(Exception e) 
			   { 
				 logger.error("RunTimeException is thrown... ",e);
				 throw new RuntimeException(e);
			   }
		
			   return new ResponseEntity<Theater>(theater, HttpStatus.OK);
			
		 }
	    
	     @SuppressWarnings({ "unchecked", "rawtypes" })
		 @RequestMapping(value = "/", method = RequestMethod.POST)
	     public ResponseEntity<?> createTheater(@Valid @RequestBody Theater theater, UriComponentsBuilder ucBuilder) throws IOException 
	     {
	    	  logger.info("Creating new Theater : {}", theater);
	    	  try 
	    	  {
	    		 if (theaterRepository.existsTheaterBytCode(theater.gettCode() )) 
		    	 {
		             logger.error("Unable to create Theater with code {} , already exist!", theater.gettCode());
		             return new ResponseEntity(new CustomErrorType("409","Unable to create Theater with code " 
		                                       +  theater.gettCode() + ", already exist."),HttpStatus.CONFLICT);
		         }
	    	  }
	    	  catch(Exception e)
	    	  {
	    		 logger.error("RunTimeException is thrown... ",e);
				 throw new RuntimeException(e);
	    	  }
	         
	    	  theaterRepository.save(theater);
	        
	    	  logger.info("Theater : {} created sucessfully...", theater);
	          HttpHeaders headers = new HttpHeaders();
	          headers.setLocation(ucBuilder.path("/theater/{theaterId}").buildAndExpand(theater.getId()).toUri());
	          return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	     }
	
	     @SuppressWarnings({ "unchecked", "rawtypes" })
		 @RequestMapping(value = "/{theaterId}", method = RequestMethod.PUT)
	     public ResponseEntity<Theater> updateMovie(@PathVariable Integer theaterId, @Valid @RequestBody Theater theaterRequest) 
	     {
		    	logger.info("Updating Theater with id {}", theaterId);
		    	Theater theater;
		    	try 
		    	{
		    		theater = theaterRepository.findById(theaterId).orElse(null);
		    		
		    		 if (theater == null)
					 {
						 logger.error("Theater with id {} not found.", theaterId);
						 return new ResponseEntity(new CustomErrorType("404","Unable to find Theater with id: " +  theaterId + ", does not exist."),HttpStatus.NOT_FOUND);
					 }
	
		    		 theater.settName(theaterRequest.gettName());
		    		
		    	}
		    	catch(Exception e)
		    	{
		    		logger.error("RunTimeException is thrown... ",e);
					throw new RuntimeException(e);
		    	}

		    	theaterRepository.save(theater);
		    	logger.info("Theater : {} updated sucessfully...", theater);
		    	return new ResponseEntity<Theater>(theater, HttpStatus.OK);
		       
	     }
	
	
	    @RequestMapping(value = "/{theaterId}", method = RequestMethod.DELETE)
		 public ResponseEntity<?> deleteTheater(@PathVariable (value="theaterId") Integer theaterId) 
	    {
	    	 logger.info("Deleting Theater with id {}", theaterId);
	    	 
		         return theaterRepository.findById(theaterId).map(theater -> {
		        	 theaterRepository.delete(theater);
		             return ResponseEntity.ok().build();
		         }).orElseThrow(() -> new TheaterNotFoundException("Theater Id : " + theaterId + " not found"));
		  }
	
	     
	     @RequestMapping(value = "/", method = RequestMethod.DELETE)
	     public ResponseEntity<Theater> deleteAllTheater() 
	     {
	         logger.info("Deleting All Theaters...");
	  
	         theaterRepository.deleteAll();
	         return new ResponseEntity<Theater>(HttpStatus.NO_CONTENT);
	     }
}
