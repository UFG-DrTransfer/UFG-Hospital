package br.ufg.inf.danilloparreira.hosptal;

public class Hospital extends SuperClasse implements Mostrar {

    private String nome;
    private String cnes;
    private boolean disponivel;
    private String responsavel;
    private String contato;

    public Hospital(int id, String nome, String cnes, String responsavel, String contato) {
        super(id);
        this.nome = nome;
        this.cnes = cnes;
        this.responsavel = responsavel;
        this.contato = contato;
        this.disponivel = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

//    public void setDisponivel(boolean disponivel) {
//        this.disponivel = disponivel;
//    }
    public void atualizaDisponivel() {
        this.disponivel = !disponivel;
        System.out.printf("Hospital: %s\nSituacao: %s\n", this.nome, disponibilidade());
    }

    private String disponibilidade() {
        return disponivel ? "Disponivel" : "Indisponivel";
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public void mostrarDados() {
        HospitalUtil.separador();
        HospitalUtil.dadosId(id);
        System.out.printf("Hospital: %s\nCNES: %s\nSituacao: %s\nResponsavel: %s\nContato %s\n",
                nome, cnes, disponibilidade(), responsavel, contato);
    }
}
