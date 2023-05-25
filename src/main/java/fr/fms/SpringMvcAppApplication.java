package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

@SpringBootApplication
public class SpringMvcAppApplication implements CommandLineRunner {

	@Autowired
	private ArticleRepository articleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcAppApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//generatedata(5);
		
	}
	
	public void generatedata(){

				articleRepository.save(new Article("Apple I10", 250));
				articleRepository.save(new Article("Apple I11", 250));
				articleRepository.save(new Article("Apple I12", 350));
				articleRepository.save(new Article("Samsung S9", 250));
				articleRepository.save(new Article("Samsung S10", 350));

	}
	public void generatedata(int nbEntry){
int nbDbEntries = (int)articleRepository.count() + 1;
for (int i =0;i< nbEntry;i++) {
	articleRepository.save(new Article(String.valueOf(nbDbEntries+i),Math.random() > 0.5? 250:350));

}

}
}
