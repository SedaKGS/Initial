package it.seda.sem.mvc.manager.models;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;



public class FormForm{

        @NotEmpty(message="{formTest.name.NotEmpty}")
        @Pattern(regexp="^a$",message="{formTest.name.Pattern}")
        private java.lang.String name;
        private java.lang.String cognome;
        private java.util.Date registrazione;
        private java.lang.Integer id;
        private String esito;
        
        public String getEsito(){
           return this.esito;
        }
        
        public void setEsito(String esito){
           this.esito=esito;
        }
            
        public  java.lang.String getName(){
           return this.name;
        }
    
        public void setName(java.lang.String name){
           this.name=name;
        }
        public  java.lang.String getCognome(){
           return this.cognome;
        }
    
        public void setCognome(java.lang.String cognome){
           this.cognome=cognome;
        }
        public  java.util.Date getRegistrazione(){
           return this.registrazione;
        }
    
        public void setRegistrazione(java.util.Date registrazione){
           this.registrazione=registrazione;
        }

        public  java.lang.Integer getId(){
           return this.id;
        }
    
        public void setId(java.lang.Integer id){
           this.id=id;
        }
    
    
        public String toString(){
           return " FormTest [name=" +  name + 
	        " , cognome=" + cognome + 
	        " , registrazione=" + registrazione + 
	        " , id=" + id + 
        "]";
        }
    





}
