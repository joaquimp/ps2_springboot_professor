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

	private List<Professor> professores;

	public ProfessorController() {
		this.professores = new ArrayList<>();
		professores.add(new Professor(1, "Ana", 1111, "Segurança"));
		professores.add(new Professor(2, "Bob", 2222, "Biologia"));
		professores.add(new Professor(3, "Charles", 3333, "Economia"));
	}

	@GetMapping("/api/professores")
	Iterable<Professor> getProfessores() {
		return this.professores;
	}
	
	@GetMapping("/api/professores/{id}")
	Optional<Professor> getProfessor(@PathVariable long id) {
		for (Professor p: professores) {
			if (p.getId() == id) {
				return Optional.of(p);
			}
		}
		return Optional.empty();
	}
	
	@PostMapping("/api/professores")
	Professor createProfessor(@RequestBody Professor p) {
		long maxId = 1;
		for (Professor prof: professores) {
			if (prof.getId() > maxId) {
				maxId = prof.getId();
			}
		}
		p.setId(maxId+1);
		professores.add(p);
		return p;
	}
	
	@PutMapping("/api/professores/{professorId}")
	Optional<Professor> updateProfessor(@RequestBody Professor professorRequest, @PathVariable long professorId) {
		Optional<Professor> opt = this.getProfessor(professorId);
		if (opt.isPresent()) {
			Professor professor = opt.get();
			professor.setNome(professorRequest.getNome());
			professor.setMatricula(professorRequest.getMatricula());
			professor.setArea(professorRequest.getArea());
		}

		return opt;				
	}	
	
	@DeleteMapping(value = "/api/professores/{id}")
	void deleteProfessor(@PathVariable long id) {
		professores.removeIf(p -> p.getId() == id);
	}		
}
