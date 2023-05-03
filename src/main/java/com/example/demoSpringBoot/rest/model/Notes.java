package com.example.demoSpringBoot.rest.model;

import java.util.Date;

public class Notes {

    //componentes entidad
    private Long id;
    private String text;
    private boolean status;
    private Date date;

    public Notes(Long id, String text, boolean status, Date date) {
        this.id = id;
        this.text = text;
        this.status = status;
        this.date = date;
    }

    //metodos getter y setter para obetener y actualizar datos
    public boolean isStatus() {
        return this.status;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id =id;
    }
    public String getText(){
        return text;
    }
    public void setText( String text){
        this.text = text;
    }
    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status=status;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
