package ps2.restapi;

import java.util.ArrayList;
import java.util.List;

public class ProfessorRepositorio {
    // Singleton
    private static ProfessorRepositorio instance;
    public static ProfessorRepositorio getInstance() {
        if(instance == null) instance = new ProfessorRepositorio();
        return instance;
    }
    private ProfessorRepositorio() {
        professores = new ArrayList<>();
    }
    // Repositorio
    private List<Professor> professores;

    public List<Professor> getProfessores() {
        return this.professores;
    }

    public void addProfessor(Professor p) {
        this.professores.add(p);
    }

    public Professor getProfessor(long id) {
        for(Professor p: this.professores) {
            if(p.getId() == id) return p;
        }
        return null;
    }

    public void removerProfessor(long id) {
        this.professores.removeIf(p -> p.getId() == id);
    }
}
