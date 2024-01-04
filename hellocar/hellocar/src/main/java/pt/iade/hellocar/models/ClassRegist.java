package pt.iade.hellocar.models;

public class ClassRegist {
    private int Id;
    private String Username;
    private String Password;
    private String Nome;
    private String Apelido;
    private String Email;
    private String Nascimento;

    public ClassRegist() {
        this(0, "", "", "", "", "", "");
    }

    public ClassRegist(int id, String username, String password, String nome, String apelido, String email, String nascimento) {
        this.Id = id;
        this.Username = username;
        this.Password = password;
        this.Nome = nome;
        this.Apelido = apelido;
        this.Email = email;
        this.Nascimento = nascimento;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public void setApelido(String apelido) {
        this.Apelido = apelido;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setNascimento(String nascimento) {
        this.Nascimento = nascimento;
    }

    public int getId() {
        return this.Id;
    }

    public String getUsername() {
        return this.Username;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getNome() {
        return this.Nome;
    }

    public String getApelido() {
        return this.Apelido;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getNascimento() {
        return this.Nascimento;
    }


}
