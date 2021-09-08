package br.org.generation.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.org.generation.farmacia.model.Produtos;
import br.org.generation.farmacia.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity <List<Produtos>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity <Produtos> getById(@PathVariable long id){
		return produtoRepository.findById(id)
				.map(resp-> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	@PostMapping
	public ResponseEntity <Produtos> postCategoria(@RequestBody Produtos produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	@PutMapping
	public ResponseEntity <Produtos> putCategoria(@RequestBody Produtos produto){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
	}
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id){
		produtoRepository.deleteById(id);
	} 
}
