package com.diego.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.workshopmongo.domain.Post;
import com.diego.workshopmongo.repository.PostRepository;
import com.diego.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id){
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repository.searchTitle(text);
	}
	
	
	public List<Post> fullSearch(String text, Date minData, Date maxData){
		maxData = new Date(maxData.getTime() + 24 * 60 * 60 * 1000); //24 horas em milissegundos precisa multiplicar
		return repository.fullSearch(text, minData, maxData);
	}
	
}
