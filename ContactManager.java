import java.io.*; 
import java.util.*; 
 
public class ContactManager
{ 
    private List<Contact> contacts; 
    private final String FICHIER = "contacts.txt"; 
 
    public ContactManager() { 
        contacts = new ArrayList<>(); 
    } 
 
    public void ajouterContact(Contact contact) { 
        contacts.add(contact); 
        System.out.println("Contact ajouté !"); 
    } 
 
    public void afficherContacts() { 
        if (contacts.isEmpty()) { 
            System.out.println("Aucun contact à afficher."); 
        } else { 
            System.out.println("Liste des contacts :"); 
            for (Contact c : contacts) { 
                System.out.println(c); 
            } 
        } 
    } 
 
    public void rechercherContact(String nom) { 
        boolean trouve = false; 
        for (Contact c : contacts) { 
            if (c.getNom().equalsIgnoreCase(nom)) { 
                System.out.println("Contact trouvé : " + c); 
                trouve = true; 
            } 
        } 
        if (!trouve) { 
            System.out.println("Aucun contact trouvé avec ce nom."); 
        } 
    } 
 
    public void supprimerContact(String nom) { 
        Iterator<Contact> it = contacts.iterator(); 
        boolean supprimé = false; 
        while (it.hasNext()) { 
            Contact c = it.next(); 
            if (c.getNom().equalsIgnoreCase(nom)) { 
                it.remove(); 
                System.out.println("Contact supprimé : " + c.getNom()); 
                supprimé = true; 
            } 
        } 
        if (!supprimé) { 
            System.out.println("Aucun contact trouvé à supprimer."); 
        } 
    } 
 
    public void sauvegarderDansFichier() { 
        try (PrintWriter writer = new PrintWriter(new FileWriter(FICHIER))) { 
            for (Contact c : contacts) { 
                writer.println(c.getNom() + ";" + c.getTelephone() + ";" + c.getEmail()); 
            } 
            System.out.println("Contacts sauvegardés avec succès !"); 
        } catch (IOException e) { 
            System.out.println("Erreur lors de la sauvegarde."); 
        } 
    } 
 
    public void chargerDepuisFichier() { 
        File fichier = new File(FICHIER); 
        if (!fichier.exists()) return; 
 
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHIER))) { 
            String ligne; 
            while ((ligne = reader.readLine()) != null) { 
                String[] parties = ligne.split(";"); 
                if (parties.length == 3) { 
                    contacts.add(new Contact(parties[0], parties[1], parties[2])); 
                } 
            } 
            System.out.println("Contacts chargés depuis le fichier."); 
        } catch (IOException e) { 
            System.out.println("Erreur lors du chargement du fichier."); 
        } 
    } 
 
    public void menu() { 
        Scanner scanner = new Scanner(System.in); 
        int choix; 
        do { 
            System.out.println("\n=== MENU CONTACTS ==="); 
            System.out.println("1. Ajouter un contact"); 
            System.out.println("2. Afficher tous les contacts"); 
            System.out.println("3. Rechercher un contact"); 
            System.out.println("4. Supprimer un contact"); 
            System.out.println("5. Sauvegarder les contacts"); 
            System.out.println("0. Quitter"); 
            System.out.print("Choix : "); 
            choix = scanner.nextInt(); 
            scanner.nextLine(); // vider le buffer 
 
            switch (choix) { 
                case 1: 
                    System.out.print("Nom : "); 
                    String nom = scanner.nextLine(); 
                    System.out.print("Téléphone : "); 
                    String tel = scanner.nextLine(); 
                    System.out.print("Email : "); 
                    String email = scanner.nextLine(); 
                    ajouterContact(new Contact(nom, tel, email)); 
                    break; 
 
                case 2: 
                    afficherContacts(); 
                    break; 
 
                case 3: 
                    System.out.print("Nom à rechercher : "); 
                    String nomRecherche = scanner.nextLine(); 
                    rechercherContact(nomRecherche); 
                    break; 
 
                case 4: 
                    System.out.print("Nom à supprimer : "); 
                    String nomSup = scanner.nextLine(); 
                    supprimerContact(nomSup); 
                    break; 
 
                case 5: 
                    sauvegarderDansFichier(); 
                    break; 
 
                case 0: 
                    System.out.println("Au revoir !"); 
                    break; 
 
                default: 
                    System.out.println(" Choix invalide !"); 
            } 
 
        } while (choix != 0); 
 
        scanner.close(); 
    } 
} 