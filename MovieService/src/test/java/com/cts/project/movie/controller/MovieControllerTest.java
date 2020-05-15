package com.cts.project.movie.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.project.movie.entity.Movie;
import com.cts.project.movie.repository.MovieRepository;

@SpringBootTest
@Runwith(SpringRunner.class)
class MovieControllerTest {
	
	@Autowired
	@MockBean
    private MovieRepository movieRepository;

	@Test
	public void testListAllMovie() {
		
		when(movieRepository.findAll())
		.thenReturn(Stream.of(new Movie("M111", "URI"),new Movie("M222","M M"))
		.collect(Collectors.toList()));
		assertEquals(2, movieRepository.findAll().size());
		
	}

//	@Test
//	public void testGetMovie() {
//		int movieId= 6;
//		when(movieRepository.findById(movieId))
//		.thenReturn(Stream.of(new Movie(111, "URI"))
//		.collect(Collectors.toList()));
//		assertEquals(1,movieRepository.findById(movieId));
//		}

	@Test
	public void testCreateMovie() {
		Movie movie= new Movie("M888", "LOC");
		when(movieRepository.save(movie)).thenReturn(movie);
		assertEquals(movie, movieRepository.save(movie));
	}

	@Test
	public void testDeleteMovie() {
		Movie movie= new Movie("M888", "LOC");
		movieRepository.delete(movie);
		verify(movieRepository, times(1)).delete(movie);
	}
	
	@Test
	public void testDeleteAllMovies() {
		movieRepository.deleteAll();;
		verify(movieRepository, times(1)).deleteAll();
	}
	
//	@Test
//	public void testGetMovie() {
//		int movieId= 6;
//		when(movieRepository.findById(movieId))
//		.thenReturn(Stream.of(new Movie(111, "URI"))
//		.collect(Collectors.toList()));
//		assertEquals(1,movieRepository.findById(movieId));
//		}

}