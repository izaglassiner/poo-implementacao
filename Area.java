import java.util.ArrayList;

public class Area {
    private String nome;
    private String codigo;
    private ArrayList<Curso> cursos;

    public Area(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.cursos = new ArrayList<>();
    }

    public String toString() {
        return this.nome + " (" + this.codigo + ")";
    }

    public void adicionarCurso(Curso c){
        this.cursos.add(c);
    }

    public ArrayList<Curso> getCursos(){
        return this.cursos;
    }

    public String getNome(){
        return this.nome;
    }

    public String getCodigo(){
        return this.codigo;
    }

}
