public class Aluno {
    private String nome;
    private String matricula;
    private boolean questionarioRespondido;

    public Aluno(String nome, String matricula){
        this.nome = nome;
        this.matricula = matricula;
        this.questionarioRespondido = false;
    }

    public boolean regular(){
        return this.questionarioRespondido;
    }

    public void cadastrarResposta(){
        this.questionarioRespondido = true;
    }

    public String toString(){
        return this.nome + " (" + this.matricula + ")";
    }

    public String getNome(){
        return this.nome;
    }

    public String getMatricula(){
        return this.matricula;
    }

    public boolean getQuestionarioRespondido(){
        return this.questionarioRespondido;
    }
}
