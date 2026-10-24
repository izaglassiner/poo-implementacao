public class Concluinte extends Aluno{
    private double notaFormacaoGeral;
    private double notaComponentEspecifico;
    private boolean presente;

    public Concluinte(String nome, String matricula){
        super(nome, matricula);
        this.notaFormacaoGeral = 0.00;
        this.notaComponentEspecifico = 0.00;
        this.presente = false;
    }

    public void cadastrarProva(double n1, double n2){
        this.presente = true;
        this.notaFormacaoGeral = n1;
        this.notaComponentEspecifico = n2;
    }

    public double calcularNota(){
        double nota = ((3 * this.notaFormacaoGeral) + (7 * this.notaComponentEspecifico)) / 10;
        return nota;
    }

    public boolean regular(){
        return this.presente && super.regular();
    }

    public String toString(){
        return super.toString() + " - CONCLUINTE";
    }

    public boolean getPresente(){
        return this.presente;
    }

}
