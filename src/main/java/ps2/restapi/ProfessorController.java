package ps2.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProfessorController {

	private ProfessorRepositorio repo;

	public ProfessorController() {
		this.repo = ProfessorRepositorio.getInstance();
		
		repo.addProfessor(new Professor(1, "Ana", 1111, "Segurança"));
		repo.addProfessor(new Professor(2, "Bob", 2222, "Biologia"));
		repo.addProfessor(new Professor(3, "Charles", 3333, "Economia"));
	}

	@GetMapping("/api/professores")
	Iterable<Professor> getProfessores() {
		return this.repo.getProfessores();
	}
	
	@GetMapping("/api/professores/{id}")
	Professor getProfessor(@PathVariable long id) {
		Professor p = this.repo.getProfessor(id);
		return p;
	}
	
	@PostMapping("/api/professores")
	Professor createProfessor(@RequestBody Professor p) {
		long maxId = 1;
		for (Professor prof: this.repo.getProfessores()) {
			if (prof.getId() > maxId) {
				maxId = prof.getId();
			}
		}
		p.setId(maxId+1);
		this.repo.addProfessor(p);
		return p;
	}
	
	@PutMapping("/api/professores/{professorId}")
	Professor updateProfessor(@RequestBody Professor professorRequest, @PathVariable long professorId) {
		Professor p = this.getProfessor(professorId);
		if (p != null) {
			p.setNome(professorRequest.getNome());
			p.setMatricula(professorRequest.getMatricula());
			p.setArea(professorRequest.getArea());
		}

		return p;
	}	
	
	@DeleteMapping(value = "/api/professores/{id}")
	void deleteProfessor(@PathVariable long id) {
		this.repo.removerProfessor(id);
	}		
}
