package po.leit;

import po.leit.ui.Le;
import po.leit.ui.MyCommand;

import javax.print.DocFlavor.STRING;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import javax.swing.text.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class TP1 {

    private static MyCommand interC;
    static final int MAX_ALUNOS = 35;
    private static int alunosLidos = 0;
    private static int notaMax = 0;
    private static int notaMin = 0;
    private static int notaAvg = 0;

    private static String[] nomeAlunos = new String[MAX_ALUNOS];
    private static int[] notasAlunos = new int[MAX_ALUNOS];

    public static void main(String[] args) {
        boolean querSair = false;

        interC = new MyCommand();

        do {
            interC.limparEcra();
            interC.showPrompt();
            String[] cmdEscrito = interC.lerComando();
            ArrayList<String> cmd = interC.validarComando(cmdEscrito);

            if (cmd == null) {
                interC.showMsg("Comando inválido. Digite help para ajuda");

            } else {
                if (cmd.get(0).equalsIgnoreCase("carregar")) {
                    alunosLidos = loadData(nomeAlunos, "turmaLeit.txt");
                    int notA = loadData(notasAlunos);
                    if (alunosLidos != notA) {
                        System.out.println("alunos = " + alunosLidos);
                        System.out.println("notaA = " + notA);
                        interC.showMsg("Erro carregando dados");
                    }

                    else

                        interC.showMsg("Dados carregados OK!");
                } else if (cmd.get(0).equalsIgnoreCase("listar")) {
                    mostrarAlunos();

                } else if (cmd.get(0).equalsIgnoreCase("paginar")) {
                    String input = JOptionPane.showInputDialog("Nũmeros estudantes por pãgina :");
                    int numeroU = Integer.parseInt(input);
                    mostrarAlunos(numeroU);

                } else if (cmd.get(0).equalsIgnoreCase("mostrarp")) {
                    mostrarPauta();

                } else if (cmd.get(0).equalsIgnoreCase("mostrarr")) {
                    mostraResumo();

                } else if (cmd.get(0).equalsIgnoreCase("top")) {
                    mostrarTop();

                } else if (cmd.get(0).equalsIgnoreCase("pesquisarnome")) {
                    String nomePesq = JOptionPane.showInputDialog("O que procura  :");
                    pesquisar(nomePesq);

                } else if (cmd.get(0).equalsIgnoreCase("pesquisarnota")) {
                    String vaPesq = JOptionPane.showInputDialog("O que procura  :");
                    int notaPesq = Integer.parseInt(vaPesq);
                    pesquisar(notaPesq);
                } else if (cmd.get(0).equalsIgnoreCase("help")) {
                    interC.showHelp();

                } else if (cmd.get(0).equalsIgnoreCase("terminar")) {
                    querSair = true;
                }
            }

        } while (!querSair);

    }

    /**
     * Método implementado por Prof. Não devem alterar. Este método recebe como
     * parâmetros um array e um ficheiro Lẽ cada linha do ficheiro e guarda no
     * array. Retorna o número de linhas que forma lidas do ficheiro.
     * 
     * @param lAlunos
     * @param nomeFicheiro
     * @return quantos nomes foram lidos do ficheiro -1 se não possível ler ficheiro
     */
    public static int loadData(String[] lAlunos, String nomeFicheiro) {
        Scanner in = null;
        File inputFile = new File(nomeFicheiro);
        // PrintWriter out = new PrintWriter(outputFileName);
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        while (in.hasNextLine()) {
            String nomeAl = in.nextLine();
            if ((nomeAl != null) && !(nomeAl.isBlank()) && !(nomeAl.isEmpty())) {
                lAlunos[i] = nomeAl;
                i++;
            }

        }
        in.close();
        return i;
    }

    /**
     * Método implementado por Prof. Não devem alterar. Este método recebe como
     * parâmetros um array de inteiros e vai gerar aleatoriamente valores inteiros
     * entre 0 e 20 que representam a nota de cada aluno.
     * 
     * @param lNotas
     * @return how much name was read from the files -1 if was not able to read the
     *         file
     */
    public static int loadData(int[] lNotas) {
        Random rand = new Random();
        int cont = 0;
        for (cont = 0; cont < alunosLidos; cont++) {
            int randomNum = rand.nextInt(20) + 1;
            notasAlunos[cont] = randomNum;
        }
        return cont;
    }

    /**
     * Método a ser implementando no TP1. O método deverá listar todos os nomes dos
     * alunos guardados no array nomesAlunos. O método deverá verificar se já foi
     * carregado os dados para o array. Se sim mostra os nomes dos alunos. Senão
     * deve mostrar a mensagem "Não há dados"
     * 
     * @param
     * @return
     */
    public static void mostrarAlunos() {
        if (nomeAlunos[0] == null) {
            interC.showMsg("Não há dados");
        } else {
            System.out.printf("%-2s %-25s", "Codigo", "Nome do Estudante");
            System.out.print("\n");
            int contador = 1;
            for (String nome : nomeAlunos) {
                if (nome != null && nome.length() > 0) {
                    System.out.printf("%06d %-25s", contador++, nome.trim().strip());
                    System.out.print("\n");
                } else {
                    continue;
                }
            }
            Le.umChar();
        }
    }

    /**
     * Método a ser implementando no TP1 O método deverá listar todos os nomes dos
     * alunos guardados no array nomesAlunos. O método deverá verificar se já foi
     * carregado os dados para o array. Se sim mostra os nomes dos alunos. Senão
     * deve mostrar a mensagem "Não há dados". Neste método os dados não são
     * mostrados todos de uma só vez. Devem ser apresentados até encher a tela.
     * Vamos supor que 10 nomes enchem a tela. Então deverá ser apresentados de 10
     * em 10. Esse número que indica quantos nomes enchem a tela é um parâmetro do
     * método.
     * 
     * @param tela é um inteiro que indica quantos alunos são mostrados.
     */
    public static void mostrarAlunos(int tela) {
        boolean mudarPagina = false;
        int pagina = 0;
        if (nomeAlunos[0] == null) {
            interC.showMsg("Não há dados");
        } else {
            System.out.printf("%-2s %-25s", "Codigo", "Nome do Estudante");
            System.out.print("\n");
            int contador = 1;
            for (String nome : nomeAlunos) {
                if (nome != null && nome.length() > 0) {
                    if (mudarPagina) {
                        System.out.printf("%-2s %-25s", "Codigo", "Nome do Estudante");
                        System.out.print("\n");
                        mudarPagina = false;
                    }
                    System.out.printf("%06d %-25s", contador++, nome.trim().strip());
                    pagina++;
                    System.out.print("\n");
                } else {
                    continue;
                }
                if (pagina == tela) {
                    interC.showMsg("\nEnter para continuar ... ");
                    pagina = 0;
                    mudarPagina = true;
                }
            }
        }
        Le.umChar();
    }

    /**
     * Método a ser implementando no TP1. O método deverá percorrer o array de
     * notas, calcular o valor da média aritmética de notas, a nota máximo e a nota
     * mínima. Os valores calculados devem ser guaraddos na variáveis notaAVG
     * (média), notaMax (nota máxima) e notaMin(nota mínima) Devem validar se o
     * array de notas tem elementos. Se estiver vazio devem somente apresentar a
     * mensagem "Não há dados"
     */
    private static void calcularMaxMinAvg() {
        int coutersup = 0;  // incialização de variavel para alunos acima da media
        int couterinf = 0; // incialização de variavel para alunos abaixo da media
        int dimSala=0; //incialização de variavel para mostrar a quantidade de alunos
        // comparar os dados para ver se a lista não está vazia
        if (nomeAlunos == null) {
            System.out.println("Não há dados");
        } else {
            // calcular a media entre os alunos
            for (int Nota : notasAlunos) {
                notaAvg = notaAvg + Nota;
            }
            notaAvg = notaAvg / alunosLidos;

            // ver qual a maior nota e a menor nota
            
            //maior nota
            for(int i=0;i<notasAlunos.length;i++)
            {
                if(notasAlunos[i]>notaMax)
                {
                    notaMax=notasAlunos[i];
                }
            }
            //menor nota
            notaMin=notasAlunos[0];
            for(int i=0;i<notasAlunos.length;i++)
                {
                        if(notasAlunos[i]!=0){
                        if(notasAlunos[i]<notaMin)
                        {
                            notaMin=notasAlunos[i];
                        }
                    dimSala++;
                    }
                }
            
            //numero de notas superior a media e inferior a media
            for (int Nota : notasAlunos) {
                // numero de alunos com nota superior a media
                if (notaAvg < Nota) {
                    coutersup++;
                }
                // numero de alunos com nota superior a media
                else {
                    couterinf++;
                }
            }
        }
        System.out.println("A nota media é de "+ notaAvg);
        System.out.println("A nota maxima é de "+notaMax);
        System.out.println("A nota minima é de "+notaMin);
        System.out.println("O número de alunos com nota inferior a media é de " + couterinf);
        System.out.println("O número de alunos com nota superior a media é de " + coutersup);
        System.out.println("O número de alunos da sala é " + dimSala);

        Le.umChar();
    }

    /**
     * Método a ser implementando no TP1. O método deverá apresentar um resumo da
     * avaliação; Nota máxima, Nota mínima, Nota média. Número de alunos com nota
     * superior a média e número de alunos com nota inferior a média. a mensagem
     * "Não há dados"
     */
    public static void mostraResumo() {
        if (notasAlunos == null) {
            System.out.println("Não há dados");
        }

        else {
            // resume de avaliação
            calcularMaxMinAvg();

        }

        Le.umChar();

    }

    /**
     * Método a ser implementando no TP1. O método deverá apresentar o nome dos três
     * alunos que têm as melhores notas.
     */
    public static void mostrarTop() {
  
    String[]nomeorde=new String[nomeAlunos.length];
	int []numerorde=new int[nomeAlunos.length];
	String trocanome;
	int trocanum;

	for(int i=0;i<nomeAlunos.length;i++)
		{
			numerorde[i]=notasAlunos[i];
			nomeorde[i]=nomeAlunos[i];
		}
	boolean ord=true;

	while(ord){
		ord=false;
		for(int i=1;i<numerorde.length;i++)
		{
			if(numerorde[i] < numerorde[i-1])
			{
				trocanum=numerorde[i-1];
				numerorde[i-1]=numerorde[i];
				numerorde[i]=trocanum;

				trocanome=nomeorde[i-1];
				nomeorde[i-1]=nomeorde[i];
				nomeorde[i]=trocanome;
				
				ord=true;
			}
		
		}
		

	}
	System.out.println(numerorde[numerorde.length-1]+"  "+nomeorde[numerorde.length-1]);
	System.out.println(numerorde[numerorde.length-2]+"  "+nomeorde[numerorde.length-2]);
	System.out.println(numerorde[numerorde.length-3]+"  "+nomeorde[numerorde.length-3]);

    Le.umChar();
    }

    /**
     * Método a ser implementando no TP1. Apresentar a pauta com nomes dos alunos e
     * á frente cada nome a respectiva nota obtida.
     */
    public static void mostrarPauta() {
        //mostrarAlunos(10);
        boolean mudarPagina = false;
        int pagina = 0;
        int i=0;
        if (nomeAlunos[0] == null) {
            interC.showMsg("Não há dados");
        } else {
            System.out.printf("%-2s %-25s %-2s", "Codigo", "Nome do Estudante","Nota do Estudante");
            System.out.print("\n");
            int contador = 1;
            for (String nome : nomeAlunos) {
                if (nome != null && nome.length() > 0) {
                    if (mudarPagina) {
                        System.out.printf("%-2s %-25s %-2s", "Codigo", "Nome do Estudante","Nota do Estudante");
                        System.out.print("\n");
                        mudarPagina = false;
                    }
                    System.out.printf("%06d %-25s %d", contador++, nome.trim().strip(),notasAlunos[i]);
                    i++;
                    pagina++;
                    System.out.print("\n");
                } else {
                    continue;
                }
                if (pagina == 10) {
                    interC.showMsg("\nEnter para continuar ... ");
                    pagina = 0;
                    mudarPagina = true;
                }
            }
        }
        Le.umChar();
    }

    /**
     * Método a ser implementando no TP1 Apresentar para um aluno específico em que
     * o nome é dado como parâmetro a nota de avaliação
     * 
     * @param nome é uma string contendo o nome do aluno que queremos apresentar a
     *             sua nota
     * @return
     */
    public static void mostrarDetalhesAluno(String nome) {
        
    }

    /**
     * Método a ser implementando no TP1 O método deverá pedir um nome e pesquisar o
     * array de nomes. Caso existir ou caso existem nomes parecidos apresentar a
     * lista de nomes. Nomes parecidos são nomes que iniciam com as mesmas duas ou
     * três primeiras letras. Ou apelidos iguais.
     */
    public static void pesquisar(String nome) {
        for (String name : nomeAlunos) 
        {
            if(name== null)continue;{
            if ((name.toLowerCase()).contains(nome.toLowerCase())) // testa se foi insirido o nome ou apelido
            {
                System.out.println(name);// mostrar nome na tela
            }
        }
    }
    Le.umChar();
}

    /**
     * Método a ser implementando no TP1 O método deverá pedir um nome e pesquisar o
     * array de nomes. Caso existir ou caso existem nomes parecidos apresentar a
     * lista de nomes. Nomes parecidos são nomes que iniciam com as mesmas duas ou
     * três primeiras letras. Ou apelidos iguais.
     */
    public static void pesquisar(int nota) {
        if (notasAlunos[0] == 0) {
            System.out.println("Não da dados para essa nota");
        }
        
        for (int i = 0; i < notasAlunos.length; i++) {
            if (nota == notasAlunos[i]) {
                System.out.println(nomeAlunos[i] + "  " + notasAlunos[i]);
            } 
        }
        System.out.println("precione ENTER para continuar");

    }

    private String[] searchByName(String nome) {
        return null;
    }

}
