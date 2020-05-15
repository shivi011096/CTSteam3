package com.cts.project.theater.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.cts.project.theater.entity.Theater;

@RepositoryRestResource
public interface TheaterRepository extends JpaRepository<Theater, Integer>
{
	boolean existsTheaterBytCode(String gettCode);
}
