import java.util.ArrayList;

public class Sistema {
    private ArrayList<Area> areas;
    private ArrayList<Curso> cursos;
    private ArrayList<Aluno> alunos;

    public Sistema() {
        this.areas = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    public void cadastrarArea(Area a){
        this.areas.add(a);
    }

    public void cadastrarCurso(Curso c, Area a){
        this.cursos.add(c);
        a.adicionarCurso(c);
    }

    public void cadastrarAluno(Aluno a, Curso c){
        this.alunos.add(a);
        c.adicionarAluno(a);
    }

    public void listarResultados(int conceito){
        boolean achou = false;
        Entrada.imprimirTituloConceito(conceito);

        for (Area a : this.areas){
            for (Curso c : a.getCursos()){
                if (c.calcularConceito() == conceito){
                    Entrada.imprimirCursoComConceito(c, a);
                    achou = true;
                }
            }
        }

        if (!achou){
            Entrada.imprimirNenhumCursoConceito(conceito);
        }
    }

    public void listarResultados(String nomeArea){
        Area area = null;

        for (Area a : this.areas){
            if (a.getNome().equals(nomeArea)){
                area = a;
            }
        }

        if (area == null || area.getCursos().isEmpty()){
            Entrada.imprimirNenhumCursoNaArea(nomeArea);
        } else {
            Entrada.imprimirTituloArea(area);
            for (Curso c : area.getCursos()){
                Entrada.imprimirConceitoDoCurso(c);
            }
        }
    }

    public void listarAlunosIrregulares(){
        Entrada.imprimirTituloIrregulares();

        for (Aluno a : this.alunos){
            if (!a.regular()){
                Curso curso = null;

                for (Curso c : this.cursos){
                    if (c.getAlunos().contains(a)){
                        curso = c;
                    }
                }
                Entrada.imprimirAlunoIrregular(a, curso);
            }
        }
    }

    public ArrayList<Area> getAreas(){
        return this.areas;
    }

    public ArrayList<Curso> getCursos(){
        return this.cursos;
    }

    public ArrayList<Aluno> getAlunos(){
        return this.alunos;
    }

}