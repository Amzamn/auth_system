import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscController {
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private DatePicker dateNaissancePicker;
    @FXML
    private TextField loisirsTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField photoTextField;
    @FXML
    private Button inscriptionButton;

    @FXML
    private void passerPageInscription(ActionEvent event) throws IOException {
        // Charger le fichier FXML de la page d'inscription
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Récupérer la scène actuelle à partir de l'événement
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Définir la nouvelle scène
        stage.setScene(scene);
        stage.show();
    }

    // Méthode appelée lorsque le bouton "S'inscrire" est cliqué
    @FXML
    void inscrireUtilisateur(ActionEvent event) {
        // Récupérer les valeurs des champs de saisie
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String loisirs = loisirsTextField.getText();
        String motDePasse = passwordField.getText();
        // Récupérer la date de naissance
        String dateNaissance = dateNaissancePicker.getValue().toString(); // Convertir en chaîne de caractères

        // Établir la connexion à la base de données
        try (Connection conn = DataBaseConnection.getConnection()) {
            // Préparer la requête d'insertion
            String query = "INSERT INTO users (nom, prenom, date_naissance, loisirs, mot_de_passe) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, dateNaissance);
            statement.setString(4, loisirs);
            statement.setString(5, motDePasse);

            // Exécuter la requête d'insertion
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("L'utilisateur a été inscrit avec succès !");
                // Réinitialiser les champs de saisie après l'inscription
                nomTextField.clear();
                prenomTextField.clear();
                loisirsTextField.clear();
                passwordField.clear();
                dateNaissancePicker.getEditor().clear(); // Effacer la date de naissance sélectionnée
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'inscription de l'utilisateur : " + e.getMessage());
        }
    }
}
