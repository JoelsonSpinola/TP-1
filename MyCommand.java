package po.leit.ui;

import java.util.ArrayList;

/**
 * Implementa funcionalidades para ler comandos de Tp1
 * @author Paulo Silva
 */
public class MyCommand {
    String cmd;
    private Comandos comandos;

    public MyCommand() {
        comandos = new Comandos();
        initComandos();
    }

    private void initComandos() {
        Comando cm = new Comando("carregar", "ler dados dos ficheiros de nomes e notas","","",false);
        
        this.comandos.adicionar(cm);

        cm = new Comando("listar", "apresenta lista todos alunos", "","",false);
        this.comandos.adicionar(cm);

        cm = new Comando("paginar", "apresentar lista com um determinado número de nomes de cada vez");
        this.comandos.adicionar(cm);

        cm = new Comando("mostrarp", "apresentar pauta. nome e respectiva nota de cada aluno");
        this.comandos.adicionar(cm);

        cm = new Comando("mostrarr", "apresentar um resumo");
        this.comandos.adicionar(cm);

        cm = new Comando("top", "apresentar os três melhores notas");
        this.comandos.adicionar(cm);

        cm = new Comando("pesquisarnome", "pesquisar se um determinada string corresponde totalmente ou a parte do nome ou apelido");
        this.comandos.adicionar(cm);

        cm = new Comando("pesquisarnota", "pesquisar quem são os estudantes que tiveram determinada nota");
        this.comandos.adicionar(cm);

        cm = new Comando("help", "ajuda");
        this.comandos.adicionar(cm);

        cm = new Comando("terminar", "terminar programa");
        this.comandos.adicionar(cm);

    }

    /**
     * limpar texto do ecra
     */
    public void limparEcra() {

        for (int y = 0; y < 25; y++)
            System.out.println("\n");
    }


    /**
     * Apresenta mensagem de prompt
     */

    public void showPrompt() {
        limparEcra();
        System.out.print(" ooP2021_TP1. Digite seu comando  >>> ");
    }


    /**
     * Método para apresentar uma mensgagem no ecra
     * @param ms String que será apresentada
     */
    public void  showMsg(String ms) {

        System.out.println(ms);
        Le.umChar();
    }

    public void showHelp() {
        comandos.showHelp();
    }

    /**
     * Método para ler uma string que representa um comando para uma funcionalidade
     * @return String com o comando do utilizador
     */
    public String[] lerComando() {
        cmd = Le.umaString();

        String[] aux = cmd.trim().split(" ");

        return aux;
    }

    /**
     * Valida se uma string digitada é um comando válido do programa
     * @param lcmd string dada pelo utilizador
     * @return array com comando e parâmetros se houver
     */
    public ArrayList<String> validarComando(String[] lcmd) {
        ArrayList<String> aux = new ArrayList<>() ;

        int tamanho = lcmd.length;
        String cmd = lcmd[0];

        int existe = procurar(cmd);

        if (existe == -1) {
            return null;
        } else {
            Option[] ops = comandos.getLista().get(existe).getOption();
            for (Option o: ops)
                //aux.add(o.getTitle());
                //System.out.println("o.getTitle() = " + o.getTitle());
            aux.add(cmd);
        }

        return aux;

//            if (tamanho > 0) {
//
//                switch (cmd) {
//                    case ("novo") :  {
//                        if (tamanho == 1) {
//                        	resultado=false;
//                            break;
//                        }
//
//                        else if ((tamanho == 2) &&
//                              (lcmd[1].equalsIgnoreCase("student") || lcmd[1].equalsIgnoreCase("turista")
//                                     || lcmd[1].equalsIgnoreCase("festival")) ) {
//						} else
//                            resultado = false;
//                        break;
//                    }
//                    case ("alterar") : {
//                        if (tamanho == 1)
//                            break;
//                        else if (tamanho > 3) {resultado = false; break;}
//
//                        else if ((tamanho == 3) &&
//                              (lcmd[1].equalsIgnoreCase("nome") || lcmd[1].equalsIgnoreCase("email")
//                                     || lcmd[1].equalsIgnoreCase("telefone")
//                                     || lcmd[1].equalsIgnoreCase("morada")) ) {
//						}
//
//                        break;
//                    }
//
//                    case ("carregar") : {
//                        if (tamanho == 1)
//                            break;
//                        else if (tamanho > 3)
//                            {resultado = false; break;}
//
//                        else if  (tamanho == 3) {
//						}
//                        break;
//                    }
//
//                    case ("guardar") : {
//                        if (tamanho == 1)
//                            break;
//                    }
//
//                    case ("importar") : {
//                        if (tamanho == 1)
//                            break;
//                    }
//
//                    case("mostrar") : {
//                        if (tamanho == 1)
//                            break;
//
//                        else if  (tamanho > 2)
//                            {resultado = false; break;}
//
//                        else if  (tamanho == 2) {
//						}
//                        break;
//                    }
//
//                    case ("passar saldo") : {
//                        if (tamanho == 2)
//                            break;
//
//                        else if ((tamanho == 1) || (tamanho > 5))
//                            {resultado = false; break;}
//
//                        else if  (tamanho == 5) {
//						}
//                        break;
//                    }
//                    case ("info") : {
//                        if (tamanho == 1)
//                            break;
//                        else if (tamanho > 1) {
//                            resultado = false; break;
//                        }
//                    } case ("terminar") : {
//                        if (tamanho > 1) resultado = false; break;
//                    } default : lcmd[0] = "NOCMD"; // quer dizer que não foi inserido nenhum comando
//                }
//
//            }
//
//            if (!resultado && tamanho > 1)
//                lcmd[1] = cmd.concat("PARINV"); // quer dizer que número parametros inválidos
//
//            else if (!resultado && tamanho == 1)
//                lcmd[0] = cmd.concat("NOPARAM"); // não foi passado parâmetros
//
//
//            return  lcmd;
//
    }

    private int procurar(String c) {
        int res = -1;
        for (Comando aux: comandos.getLista() )  {
            if (aux.getNome().equalsIgnoreCase(c) ) {
                res = res + 1;
            }
        }

        return res;
    }

    /*
     * Apresenta mensagem standard de erro
     */
    public void mensagemErro() {
        limparEcra();
        System.out.println("Comando inválido neste contexto\n");
        mensagemPrompt();
    }


    private void mensagemPrompt() {
        System.out.println("\nDigite tecla ENTER para continuar");
        Le.umChar();
    }

}