import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe com as rotinas de entrada e saída do projeto
 * @author Hilario Seibel Junior, Gariela Prajo Beretta e Izabelly Cristine Glassiner Coutinho
 */
public class Entrada {

    /**
     * Se houver um arquivo input.txt na pasta em que o projeto está sendo executado,
     * define que o Scanner vai ler deste arquivo e não do teclado.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     * NÃO ALTERE O ESTE CODIGO!!!!
     * @return Um objeto da classe Scanner de onde a entrada será lida
     */
    public static Scanner abrirEntrada() {
        Scanner input = null;
        try {
            // Se houver um arquivo input.txt, o Scanner vai ler dele.
            input = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            // Locale.US para que o Java sempre leia double com "pontos" ao invés de "vírgulas"
            input = new Scanner(System.in).useLocale(Locale.US);
        }
        finally {
            return input;
        }
    }

    /**
     * Fecha o Scanner antes de sair do programa.
     * @param input: Um objeto da classe Scanner de onde a entrada está sendo lida
     */
    public static void fecharEntrada(Scanner input) {
        input.close();
    }

    /**
     * Faz a leitura de uma linha inteira
     * @param msg: Mensagem que será exibida ao usuário
     * @param input: Um objeto da classe Scanner de onde a entrada está sendo lida
     * @return Uma String contendo a linha que foi lida
     */
    public static String lerLinha(String msg, Scanner input) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        String linha = input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @param input: Um objeto da classe Scanner de onde a entrada está sendo lida
     * @return O número digitado pelo usuário convertido para int
     */
    public static int lerInteiro(String msg, Scanner input) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = Entrada.lerLinha(msg, input);
        return Integer.parseInt(linha);
    }

    /**
     * Faz a leitura de um double
     * @param msg: Mensagem que será exibida ao usuário
     * @param input: Um objeto da classe Scanner de onde a entrada está sendo lida
     * @return O número digitado pelo usuário convertido para double
     */
    private static double lerDouble(String msg, Scanner input) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um double e retorna este double
        String linha = Entrada.lerLinha(msg, input);
        return Double.parseDouble(linha);
    }

    /**
     * Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.
     * @return Inteiro contendo a opção escolhida pelo usuário
     */
    public static int menu(Scanner input) {
        // Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.
        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastro\n" +
                "2) Relatórios\n" +
                "0) Sair\n";

        int op = Entrada.lerInteiro(msg, input);

        while (op < 0 || op > 2) {
            System.out.println("Opção inválida. Tente novamente: ");
            op = Entrada.lerInteiro(msg, input);
        }

        return op;
    }

    public static void menu_cadastro(Sistema s, Scanner input) {
        String msg = "*********************\n" +
                "1) Cadastrar Área\n" +
                "2) Cadastrar Curso\n" +
                "3) Cadastrar Aluno Ingressante\n" +
                "4) Cadastrar Aluno Concluinte\n" +
                "5) Cadastrar Resposta de Questionário\n" +
                "6) Cadastrar Resultado de Prova\n" +
                "0) Voltar\n";

        int op = Entrada.lerInteiro(msg, input);

        if (op == 0) return;

        switch(op) {
            case 1:
                Entrada.cadArea(s, input);
                break;
            case 2:
                Entrada.cadCurso(s, input);
                break;
            case 3:
                Entrada.cadIngressante(s, input);
                break;
            case 4:
                Entrada.cadConcluinte(s, input);
                break;
            case 5:
                Entrada.cadResposta(s, input);
                break;
            case 6:
                Entrada.cadProva(s, input);
                break;
            default:
                System.out.println("Opção Inválida.");
        }

    }

    public static void cadArea(Sistema s, Scanner input) {
        String nome = Entrada.lerLinha("Digite o nome da área: ", input);
        String codigo = Entrada.lerLinha("Digite o código da área: ", input);

        s.cadastrarArea(new Area(nome, codigo));
        System.out.println("Área cadastrada com sucesso!");
    }

    public static void cadCurso(Sistema s, Scanner input) {
        String nome = Entrada.lerLinha("Digite o nome do curso: ", input);
        String codigo = Entrada.lerLinha("Digite o código do curso: ", input);

        System.out.println("Áreas cadastradas:");
        for (Area a : s.getAreas()) {
            System.out.println(a);
        }

        String codigoArea = Entrada.lerLinha("Digite o código da área do curso: ", input);
        Area area = Entrada.buscarArea(s, codigoArea);

        if (area == null) {
            System.out.println("Erro: área não encontrada.");
        } else {
            s.cadastrarCurso(new Curso(codigo, nome), area);
            System.out.println("Curso cadastrado com sucesso!");
        }
    }

    public static void cadIngressante(Sistema s, Scanner input) {
        String nome = Entrada.lerLinha("Digite o nome do ingressante: ", input);
        String matricula = Entrada.lerLinha("Digite a matrícula do ingressante: ", input);
        int semestre = Entrada.lerInteiro("Digite o semestre de ingresso: ", input);

        System.out.println("Cursos cadastrados:");
        for (Curso c : s.getCursos()) {
            System.out.println(c);
        }

        String codigoCurso = Entrada.lerLinha("Digite o código do curso do aluno: ", input);
        Curso curso = Entrada.buscarCurso(s, codigoCurso);

        if (curso == null) {
            System.out.println("Erro: curso não encontrado.");
        } else {
            s.cadastrarAluno(new Ingressante(nome, matricula, semestre), curso);
            System.out.println("Aluno ingressante cadastrado com sucesso!");
        }
    }

    public static void cadConcluinte(Sistema s, Scanner input) {
        String nome = Entrada.lerLinha("Digite o nome do concluinte: ", input);
        String matricula = Entrada.lerLinha("Digite a matrícula do concluinte: ", input);

        System.out.println("Cursos cadastrados:");
        for (Curso c : s.getCursos()) {
            System.out.println(c);
        }

        String codigoCurso = Entrada.lerLinha("Digite o código do curso do aluno: ", input);
        Curso curso = Entrada.buscarCurso(s, codigoCurso);

        if (curso == null) {
            System.out.println("Erro: curso não encontrado.");
        } else {
            s.cadastrarAluno(new Concluinte(nome, matricula), curso);
            System.out.println("Aluno concluinte cadastrado com sucesso!");
        }
    }

    public static void cadResposta(Sistema s, Scanner input) {
        System.out.println("Alunos cadastrados:");
        for (Aluno a : s.getAlunos()) {
            if (!a.getQuestionarioRespondido()) {
                System.out.println(a);
            }
        }

        String matricula = Entrada.lerLinha("Digite a matrícula do aluno que respondeu ao questionário: ", input);
        Aluno aluno = Entrada.buscarAluno(s, matricula);

        if (aluno == null) {
            System.out.println("Erro: aluno não encontrado.");
        } else {
            aluno.cadastrarResposta();
            System.out.println("Resposta cadastrada com sucesso!");
        }
    }

    public static void cadProva(Sistema s, Scanner input) {
        System.out.println("Concluintes cadastrados:");
        for (Aluno a : s.getAlunos()) {
            if (a instanceof Concluinte c && !c.getPresente()) {
                System.out.println(c);
            }
        }

        String matricula = Entrada.lerLinha("Digite a matrícula do aluno que realizou a prova: ", input);
        Aluno aluno = Entrada.buscarAluno(s, matricula);

        if (aluno instanceof Concluinte c) {
            double n1 = Entrada.lerDouble("Digite o valor da nota de formação geral: ", input);
            double n2 = Entrada.lerDouble("Digite o valor da nota de conhecimentos específicos: ", input);

            c.cadastrarProva(n1, n2);
            System.out.println("Prova cadastrada com sucesso!");
        } else {
            System.out.println("Erro: concluinte não encontrado.");
        }
    }

    public static void menu_relatorios(Sistema s, Scanner input) {
        String msg = "*********************\n" +
                "1) Listar Resultados por Conceito\n" +
                "2) Listar Resultados por Área\n" +
                "3) Listar Alunos Irregulares\n" +
                "0) Voltar\n";

        int op = Entrada.lerInteiro(msg, input);

        if (op == 1) {
            Entrada.relConceito(s, input);
        } else if (op == 2) {
            Entrada.relArea(s, input);
        } else if (op == 3) {
            Entrada.relIrregulares(s);
        } else if (op != 0) {
            System.out.println("Opção Inválida.");
        }
    }

    public static void relConceito(Sistema s, Scanner input) {
        int conceito = Entrada.lerInteiro("Digite o conceito desejado (1 a 5): ", input);
        s.listarResultados(conceito);
    }

    public static void relArea(Sistema s, Scanner input) {
        System.out.println("Áreas cadastradas:");
        for (Area a : s.getAreas()) {
            System.out.println(a);
        }

        String nomeArea = Entrada.lerLinha("Digite o nome da área: ", input);
        s.listarResultados(nomeArea);
    }

    public static void relIrregulares(Sistema s) {
        s.listarAlunosIrregulares();
    }

    // Metodos de impressao chamados pelo Sistema

    public static void imprimirTituloConceito(int conceito) {
        System.out.println("Cursos com conceito " + conceito + ":");
    }

    public static void imprimirCursoComConceito(Curso c, Area a) {
        System.out.println(c + " - Área: " + a);
    }

    public static void imprimirNenhumCursoConceito(int conceito) {
        System.out.println("Nenhum curso com conceito " + conceito + " foi encontrado.");
    }

    public static void imprimirTituloArea(Area a) {
        System.out.println("Conceitos dos cursos da área " + a + ":");
    }

    public static void imprimirConceitoDoCurso(Curso c) {
        System.out.println(c + ": " + c.calcularConceito());
    }

    public static void imprimirNenhumCursoNaArea(String nomeArea) {
        System.out.println("Nenhum curso na área " + nomeArea + " foi encontrado.");
    }

    public static void imprimirTituloIrregulares() {
        System.out.println("Alunos irregulares com o ENADE:");
    }

    public static void imprimirAlunoIrregular(Aluno a, Curso c) {
        System.out.println(a + " - Curso: " + c);
    }

    // Metodos de busca usados pelos cadastros

    private static Area buscarArea(Sistema s, String codigo) {
        Area encontrada = null;

        for (Area a : s.getAreas()) {
            if (a.getCodigo().equals(codigo)) {
                encontrada = a;
            }
        }
        return encontrada;
    }

    private static Curso buscarCurso(Sistema s, String codigo) {
        Curso encontrado = null;

        for (Curso c : s.getCursos()) {
            if (c.getCodigo().equals(codigo)) {
                encontrado = c;
            }
        }
        return encontrado;
    }

    private static Aluno buscarAluno(Sistema s, String matricula) {
        Aluno encontrado = null;

        for (Aluno a : s.getAlunos()) {
            if (a.getMatricula().equals(matricula)) {
                encontrado = a;
            }
        }
        return encontrado;
    }

}