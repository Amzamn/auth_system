import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

    @FXML
    private Label dateNaissanceLabel;

    @FXML
    private Label loisirsLabel;

    @FXML
    private TextField nouveauNomTextField;

    @FXML
    private TextField nouveauPrenomTextField;

    @FXML
    private TextField nouvelleDateNaissanceTextField;

    @FXML
    private TextField nouveauxLoisirsTextField;

    @FXML
    private Label motDePasseLabel;

    @FXML
    private String nom;

    @FXML
    private String prenom;

    @FXML
    private String dateNaissance;

    @FXML
    private String loisirs;

    @FXML
    private String motDePasse;
    @FXML
    private Node loginButton;

    // Méthode pour initialiser les données de l'utilisateur
    public void initData(String nom, String prenom, String dateNaissance, String loisirs, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.loisirs = loisirs;
        this.motDePasse = motDePasse;

        nomLabel.setText(nom);
        prenomLabel.setText(prenom);
        dateNaissanceLabel.setText(dateNaissance);
        loisirsLabel.setText(loisirs);
        motDePasseLabel.setText(motDePasse);
    }


    @FXML
    void miseAJourUtilisateur() {
        try {
            // Récupérer les anciennes valeurs de nom et prénom
            String ancienNom = nomLabel.getText();
            String ancienPrenom = prenomLabel.getText();

            // Charger la nouvelle page update.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de mise à jour
            UpdateController updateController = loader.getController();

            // Passer les anciennes valeurs de nom et prénom au contrôleur de mise à jour
            updateController.initData(ancienNom, ancienPrenom);

            // Créer une nouvelle scène pour la page update.fxml
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Fermer la fenêtre actuelle de profil
            Stage profileStage = (Stage) nomLabel.getScene().getWindow();
            profileStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}