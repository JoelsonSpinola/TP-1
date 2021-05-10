package po.leit.ui;

public class Option {
    private String title;
    private String descr;
    private boolean obrigatorio;

    public Option(String title, String descr) {
        this.setTitle(title);
        this.setDescr(descr);
        this.setObrigatorio(false);

    }

    public Option(String title, String descr, boolean obri) {
        this.setTitle(title);
        this.setDescr(descr);
        this.setObrigatorio(obri);

    }

    String getDescr() {
        return descr;
    }

    void setDescr(String descr) {
        this.descr = descr;
    }

    boolean isObrigatorio() {
        return obrigatorio;
    }

    void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }
}