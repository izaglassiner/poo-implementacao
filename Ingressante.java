public class Ingressante extends Aluno {
    private int semIngresso;

    public Ingressante(String nome, String matricula, int semIngresso){
        super(nome, matricula);
        this.semIngresso = semIngresso;

    }

    public String toString(){
        return super.toString() + " - INGRESSANTE";
    }

    public int getSemIngresso(){
        return this.semIngresso;
    }
}
