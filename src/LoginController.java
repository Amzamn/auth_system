import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button inscriptionButton;
    @FXML
    private Label loginMessageLabel;

    @FXML
    void loginButtonAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        try (Connection conn = DataBaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE nom = ? AND mot_de_passe = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Si l'utilisateur existe, récupérer ses informations
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String dateNaissance = resultSet.getString("date_naissance");
                String loisirs = resultSet.getString("loisirs");
                String motDePasse = resultSet.getString("mot_de_passe");

                // Charger le fichier FXML de la page de profil
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                Parent root = loader.load();

                // Passer les informations de l'utilisateur à la nouvelle page
                ProfileController controller = loader.getController();
                controller.initData(nom, prenom, dateNaissance, loisirs, motDePasse);

                // Afficher la nouvelle scène
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                // Fermer la fenêtre de connexion
                Stage loginStage = (Stage) loginButton.getScene().getWindow();
                loginStage.close();
            } else {
                loginMessageLabel.setText("Identifiants incorrects. Veuillez réessayer.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            loginMessageLabel.setText("Erreur de connexion à la base de données.");
        }
    }

    @FXML
    void inscriptionButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
