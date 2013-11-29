package it.seda.sem.domain;
import java.io.Serializable;


public class Test implements Serializable{
     
        private java.lang.String name;
        private java.lang.String cognome;
        private java.util.Date registrazione;
        private java.lang.Integer id;
	
	
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
           return " Test [name=" +  name + 
	          ", cognome=" + cognome + 
	          ", registrazione=" + registrazione + 
	          ", id=" + id + 
              "]";
        }
    
    
}

