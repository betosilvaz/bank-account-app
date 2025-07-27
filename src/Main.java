import java.util.Locale;
import java.util.Scanner;

import dao.UserDAO;
import entities.User;
import exceptions.CouldntRegisterException;
import util.Autenticator;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static User user = null;
    
    public static void main(String[] args) {
        while(true) {
            System.out.println("===== Bem vindo ao seus sistema bancário! =====");
            System.out.println("Opções disponíveis: ");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar-se");
            System.out.println("3 - Fechar programa");
            System.out.print("\nDigite a sua escolha: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 3)
                break;
            if(choice == 2)
                register();
            if(choice == 1)
                login();

            // while(true) {
            //     System.out.println("===== Bem vindo, " + user.getName()  + "! =====");
            //     System.out.println("As seguintes operações estão disponíveis: ");
            //     System.out.println("1 - Consultar saldo");
            //     System.err.println("2 - Consultar cheque especial");
            //     System.out.println("3 - Depositar dinheiro");
            //     System.out.println("4 - Sacar dinheiro");
            //     System.out.println("5 - Pagar boleto");
            //     System.out.println("6 - Verificar uso do cheque especial");
            //     System.out.println("7 - Sair");
            //     System.out.print("\nEscolha: ");
            //     int action = sc.nextInt();
            // }

            break;
        }

        sc.close();
    }

    public static void register() {
        Locale.setDefault(Locale.US);
        boolean tryAgain = false;
        while(true) {
            try {
                if(tryAgain == true)
                    System.out.println("O usuário já existe!");
                System.out.println("Para completar o seu cadastro, preencha os seguintes campos: \n");
                System.out.print("Digite o seu email: ");
                String email = sc.nextLine();
                System.out.print("Digite o seu nome: ");
                String name = sc.nextLine();
                System.out.print("Digite a sua senha: ");
                String password = sc.nextLine();
                System.out.print("Digite o saldo inicial: ");
                double balance = sc.nextDouble();
                sc.nextLine();

                double special_check = 0;
                if(balance <= 500)
                    special_check = 50.0d;
                if(balance > 500)
                    special_check = 0.5 * balance;

                if(UserDAO.find(email).getEmail() != null)
                    throw new CouldntRegisterException("O usuário já existe!");

                Autenticator.register(email, name, password, balance, special_check);
                user = new User(email, name, password, balance, special_check);
                break;
            } catch(CouldntRegisterException ex) {
                tryAgain = true;
                continue;
            }
        }

    }

    public static void login() {
        boolean error = false;
        while(true) {
            if(error == true) 
                System.out.println("\nUsuário não existe ou senha incorreta. Tente novamente!");
            System.out.println("Preencha os campos a seguir para logar: \n");
            System.out.print("Digite o seu email: ");
            String email = sc.nextLine();
            System.out.print("Digite a sua senha: ");
            String password = sc.nextLine();

            if(Autenticator.login(email, password) == true) {
                user = UserDAO.find(email);
                break;
            }
            error = true;
        }
    }
}