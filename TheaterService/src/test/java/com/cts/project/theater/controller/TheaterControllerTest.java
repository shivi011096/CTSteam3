package com.cts.project.theater.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.cts.project.theater.entity.Theater;
import com.cts.project.theater.repository.TheaterRepository;



@SpringBootTest
@Runwith(SpringRunner.class)
class TheaterControllerTest {
	
	@MockBean
	@Autowired
    private TheaterRepository theaterRepository; 

	@Test
	public void testListAllTheater() {
		when(theaterRepository.findAll())
		.thenReturn(Stream.of(new Theater("T111", "PVR", "NEW DELHI")
				, new Theater("T999", "SRS", "BANGALORE"))
		.collect(Collectors.toList()));
		assertEquals(2, theaterRepository.findAll().size());
		
		
	}

	@Test
	public void testCreateTheater() {
		Theater theater= new Theater("T888", "AWW", "PUNE");
		when(theaterRepository.save(theater)).thenReturn(theater);
		assertEquals(theater, theaterRepository.save(theater));
		

	}

	@Test
	public void testDeleteTheater() {
		Theater theater= new Theater("T888", "AWW", "PUNE");
		theaterRepository.delete(theater);
		verify(theaterRepository, times(1)).delete(theater);
		

	}

	@Test
	public void testDeleteAllTheater() {
		theaterRepository.deleteAll();;
		verify(theaterRepository, times(1)).deleteAll();
		

	}
	
//	@Test
//	public void testGetTheater() {
//		int theaterId= 6;
//		when(theaterRepository.findById(theaterId))
//		.thenReturn(Stream.of(new Theater("111", "PVR", "NEW DELHI"))
//		.collect(Collectors.toList()));
//		assertEquals(1,theaterRepository.findById(theaterId));
//		
//	}

}
