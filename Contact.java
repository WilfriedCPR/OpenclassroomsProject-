public class Contact 
{ 
    private String nom; 
    private String telephone; 
    private String email; 
 
    public Contact(String nom, String telephone, String email) 
    { 
        this.nom = nom; 
        this.telephone = telephone; 
        this.email = email; 
    } 
 
    public String getNom() 
      { 
          return nom; 
      } 
   
    public String getTelephone()
      { 
          return telephone; 
      } 
 
    public String getEmail()
      { 
          return email; 
      } 
 
    public String toString() 
      { 
          return "Nom: " + nom + ", Téléphone: " + telephone + ", Email: " + email; 
      } 
} 