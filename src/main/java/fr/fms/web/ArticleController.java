package fr.fms.web;

import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;

	@GetMapping("/index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String kw) {
//		Page<Article> articles = articleRepository.findAll(PageRequest.of(page, 5));
		Page<Article> articles = articleRepository.findByDescriptionContains(kw,PageRequest.of(page, 5));
		model.addAttribute("listArticle", articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword",kw);
		System.out.println("currentPage : " + page + "pages : " + model.getAttribute("currentPage"));
		return "articles";// retourne au dispacterServlet une vue
	}
	
	@GetMapping("/delete")
	public String delete(Long id, int page, String keyword) {
		articleRepository.deleteById(id);
		return "redirect:/index?page="+page+"&keyword="+keyword;
	}
}
