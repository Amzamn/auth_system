import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateController {

    @FXML
    private TextField nouveauNomTextField;

    @FXML
    private TextField nouveauPrenomTextField;

    @FXML
    private TextField nouvelleDateNaissanceTextField;

    @FXML
    private TextField nouveauxLoisirsTextField;

    private String ancienNom;
    private String ancienPrenom;
    // Méthode appelée lors du clic sur le bouton "Sauvegarder"
    @FXML
    void sauvegarderDansBaseDeDonnees() {
        // Récupérer les valeurs mises à jour depuis les champs de texte
        String nouveauNom = nouveauNomTextField.getText();
        String nouveauPrenom = nouveauPrenomTextField.getText();
        String nouvelleDateNaissance = nouvelleDateNaissanceTextField.getText();
        String nouveauxLoisirs = nouveauxLoisirsTextField.getText();

        // Vérifier si les champs sont vides
        if (nouveauNom.isEmpty() || nouveauPrenom.isEmpty() || nouvelleDateNaissance.isEmpty() || nouveauxLoisirs.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs.");
            return;
        }

        // Établir la connexion à la base de données et exécuter la requête d'update
        try (Connection conn = DataBaseConnection.getConnection()) {
            // Préparer la requête de mise à jour des informations du profil
            String query = "UPDATE users SET nom=?, prenom=?, date_naissance=?, loisirs=? WHERE nom=? AND prenom=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nouveauNom);
            statement.setString(2, nouveauPrenom);
            statement.setString(3, nouvelleDateNaissance);
            statement.setString(4, nouveauxLoisirs);
            statement.setString(5, ancienNom);
            statement.setString(6, ancienPrenom);

            // Exécuter la requête d'update
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Mise à jour réussie
                System.out.println("Mise à jour réussie dans la base de données !");
                // Fermer la fenêtre de mise à jour
                Stage stage = (Stage) nouveauNomTextField.getScene().getWindow();
                stage.close();
            } else {
                // Aucune mise à jour effectuée
                System.out.println("Aucune mise à jour effectuée dans la base de données.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initData(String ancienNom, String ancienPrenom) {
        // Utiliser les anciennes valeurs pour pré-remplir les champs de texte
        nouveauNomTextField.setText(ancienNom);
        nouveauPrenomTextField.setText(ancienPrenom);
        this.ancienNom = ancienNom;
        this.ancienPrenom = ancienPrenom;
    }
}