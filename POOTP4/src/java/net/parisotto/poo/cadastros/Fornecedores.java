package net.parisotto.poo.cadastros;
public class Fornecedores {
    private String nome;
    private String cnpj;
    private String razaosocial;
    private String email;
    private String telefone;
    private String endereco;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getRazaosocial() { return razaosocial; }
    public void setRazaosocial(String razaosocial) { this.razaosocial = razaosocial; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
