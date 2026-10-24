import java.util.ArrayList;

public class Curso {
    private String codigo;
    private String nome;
    private ArrayList<Aluno> alunos;

    public Curso(String codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
        this.alunos = new ArrayList<>();
    }

    public String toString(){
        return this.nome + " (" + this.codigo + ")";
    }

    public void adicionarAluno(Aluno a){
        this.alunos.add(a);
    }

    public double calcularMedia(){
        double soma = 0;
        int cont = 0;

        for (Aluno a : this.alunos){
            if (a instanceof Concluinte c){
                soma += c.calcularNota();
                cont++;
            }
        }
        if (cont == 0){
            return 0.0;
        }
        return soma / cont;
    }

    public int calcularConceito() {
        double media;
        media = this.calcularMedia();

        if (media < 2.0) {
            return 1;
        } else if (media < 4.0) {
            return 2;
        } else if (media < 6.0) {
            return 3;
        } else if (media < 8.0) {
            return 4;
        } else {
            return 5;
        }
    }

    public ArrayList<Ingressante> ingressantes(){
        ArrayList<Ingressante> lista = new ArrayList<>();

        for (Aluno a : this.alunos){
            if (a instanceof Ingressante i){
                lista.add(i);
            }
        }

        return lista;
    }

    public ArrayList<Ingressante> ingressantes(int semestre){
        ArrayList<Ingressante> lista = new ArrayList<>();

        for (Ingressante i : this.ingressantes()){
            if (i.getSemIngresso() == semestre){
                lista.add(i);
            }
        }
        return lista;
    }

    public String getNome(){
        return this.nome;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public ArrayList<Aluno> getAlunos(){
        return this.alunos;
    }
}
