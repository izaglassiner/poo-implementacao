import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = Entrada.abrirEntrada();
        Sistema s = new Sistema();

        int op = Entrada.menu(input);

        while (op != 0) {
            switch(op) {
                case 1:
                    Entrada.menu_cadastro(s, input);
                    break;
                case 2:
                    Entrada.menu_relatorios(s, input);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
            op = Entrada.menu(input);
        }

        Entrada.fecharEntrada(input);
    }
}