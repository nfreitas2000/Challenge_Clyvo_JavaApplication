package br.com.fiap.clyvo_java.dto.individuos;

public class TutorDTO {

    private String nm_tutor;
    private String email;
    private String num_celular;

    public TutorDTO() {}


    public String getNm_tutor() {
        return nm_tutor;
    }

    public void setNm_tutor(String nm_tutor) {
        this.nm_tutor = nm_tutor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum_celular() {
        return num_celular;
    }

    public void setNum_celular(String num_celular) {
        this.num_celular = num_celular;
    }
}