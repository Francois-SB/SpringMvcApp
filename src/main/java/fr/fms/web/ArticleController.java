package fr.fms.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import net.bytebuddy.asm.Advice.Return;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "keyword", defaultValue = "") String kw,
			@RequestParam(name = "categoryId" , defaultValue = "0") Long catId) {
		//affichage categories
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("listCategories",categories);
		model.addAttribute("keyword",kw);
		model.addAttribute("categoryId",catId);

		System.out.println("kw : "+kw);
		System.out.println("cat ID : "+catId);
		Page<Article> articles = null;//new Page();
if (catId!=0) {//si on a une cat
	if(kw.equalsIgnoreCase("")) {//sans recherche
		articles = articleRepository.findByCategoryId(catId, PageRequest.of(page, 5));

	}else {//avec recheche
		articles = articleRepository.findByCategoryIdAndDescriptionContains(catId,kw, PageRequest.of(page, 5));

	}
}
else {//sans cat, avec recherche
	if (!kw.equalsIgnoreCase("")) {
		articles = articleRepository.findByDescriptionContains(kw,PageRequest.of(page, 5));
	}else {
			articles = articleRepository.findAll(PageRequest.of(page, 5));
		}
	
}//sans cat et une recherche

		//affichage 

		model.addAttribute("listArticle", articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("currentPage", page);
		
		System.out.println("currentPage : " + page + "pages : " + model.getAttribute("currentPage"));
		return "articles";// retourne au dispacterServlet une vue
	}
	
	@GetMapping("/category")//affichage par categories
	public String category(Model model, Long catId, int page, String keyword) {

		return "redirect:/index?page="+page+"&keyword="+keyword+"&catId"+catId;
	}
	
	@GetMapping("/delete")
	public String delete(Long id, int page, String keyword, Long catId) {
//		Model.addAttribute("delete",delete(id, page, keyword));
		articleRepository.deleteById(id);
		return "redirect:/index?page="+page+"&keyword="+keyword+"&catId"+catId;
	}
	
	@GetMapping("/article")
	public String article(Model model) {
		model.addAttribute("article", new Article());
		return "article";
	}
	//TODO
	@GetMapping("/update")
	public String article(Model model, Long id) {
		
		Article updtArticle = articleRepository.findById(id).get();
		model.addAttribute("article", updtArticle);
		List<Category> listCategories = categoryRepository.findAll();
		model.addAttribute("listCategories", listCategories);

	
		return "article";
	}
	
	@PostMapping("/save")//
	public String save(@Valid  @ModelAttribute Article article, BindingResult bindingResult,
			@RequestParam(name = "categoryId")Long categoryId) {//@PathVariable
		System.out.println("categoryId : "+categoryId);
//		if(bindingResult.hasErrors() || categoryId == null) return "article";
//		
		Category newCategory = categoryRepository.findById(categoryId).get();
		System.out.println("catNAMEEEEEEEE :  "+newCategory.getName());
		//TODO set cat pb
		article.setCategory(newCategory);
//		System.out.println("art before save modif : "+article);
//		articleRepository.save(article);
		System.out.println("saved art : "+article);
		return "article";
	}
	
	
}
