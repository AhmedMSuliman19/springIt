package com.example.springIt;

import com.example.springIt.domain.Comment;
import com.example.springIt.domain.Link;
import com.example.springIt.repository.CommentRepository;
import com.example.springIt.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringItApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringItApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringItApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
		return args -> {
			Link link = new Link("Getting started with spring boot 2", "https://therealdanvega.com");
			linkRepository.save(link);

			Comment comment = new Comment("This spring boot 2 link is awesome!", link);
			commentRepository.save(comment);
			link.addComment(comment);

		};
	}
}
