package po.leit.ui;

public class Comando {
    private final String nome;
    private String descri;
    private Option[] option;
    private int numeroOps;

    public Comando(String nome, int numeroOps) {
        this.nome = nome;
        this.numeroOps = numeroOps;
        option = new Option[numeroOps];
    }

    public Comando(String nome, String descri, int numeroOps) {
        this.nome = nome;
        this.numeroOps = numeroOps;
        option = new Option[numeroOps];
        this.descri  = descri;
    }

    public Comando(String nome) {
        this.nome = nome;
    }

    public Comando(String nome, String descri) {
        this.nome = nome;
        this.descri = descri;
    }

    public Comando(String nome, String descri, String tituloOp, String descriOp, boolean obrigatorio) {
        option = new Option[1];
        this.nome = nome;
        this.descri = descri;
        System.out.println("tituloOp = " + tituloOp);
        this.option[0] = new Option(tituloOp,descriOp,obrigatorio);
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Option[] getOption() {
        return option;
    }

    public void setOption(Option[] option) {
        this.option = option;
    }

    public int getNumeroOps() {
        return numeroOps;
    }

    public void setNumeroOps(int numeroOps) {
        this.numeroOps = numeroOps;
    }

    public String getNome() {
        return nome;
    }

    public void help() {
        StringBuilder h = new StringBuilder();
        h.append("Comando "+this.nome+'\n');
        h.append("Opções: '\n'");
        for (Option op : this.option) {
            h.append("\t"+op.getTitle()+' '+op.getDescr()+'\n' );
        }




    }

}
