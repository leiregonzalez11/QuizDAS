package com.example.quizdas;

public class Pregunta {

    private int idPreg;
    private String pregunta;
    private String resp1;
    private String resp2;
    private String resp3;
    private  String respCorrecta;

    public Pregunta(int pIdPreg, String pPregunta, String pResp1, String pResp2, String pResp3, String pRespCorrecta){
        this.idPreg = pIdPreg;
        this.pregunta = pPregunta;
        this.resp1 = pResp1;
        this.resp2 =pResp2;
        this.resp3 = pResp3;
        this.respCorrecta = pRespCorrecta;
    }

    public int getIdPreg() {
        return idPreg;
    }

    public String getPregunta() {
        return pregunta;
    }
    public String getResp1() {
        return pregunta;
    }
    public String getResp2() {
        return pregunta;
    }
    public String getResp3() {
        return pregunta;
    }

    public String getRespCorrecta() {
        return respCorrecta;
    }
}
