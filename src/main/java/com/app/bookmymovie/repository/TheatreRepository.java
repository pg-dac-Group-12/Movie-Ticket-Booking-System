package com.app.bookmymovie.repository;



import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bookmymovie.pojo.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre,Integer>{

	

	Optional<Theatre> findByEmailAndPassword(String email,String password);

}
