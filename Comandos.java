package po.leit.ui;

import java.util.ArrayList;

public class Comandos {
    private ArrayList<Comando> lista;

    public Comandos() {
        lista = new ArrayList<>();

    }

    public Comandos(Comando[] lis) {
        lista = new ArrayList<>();
        for (int i=0; i < lis.length; i++) {
            lista.add(lis[i]);
        }
    }

    public void adicionar(Comando e) {
        this.lista.add(e);
    }

    public boolean validar() {
        return false;

    }

    public ArrayList<Comando> getLista() {
        return lista;
    }

    public void showHelp() {
        System.out.println("TP1 - Lista de comandos válidos:");
        for (Comando cm: lista) {
            System.out.println("\t"+ cm.getNome()+" :- "+cm.getDescri());
        }
        System.out.println("pressionar enter para ccntinuar ... ");
        Le.umChar();
    }

}
