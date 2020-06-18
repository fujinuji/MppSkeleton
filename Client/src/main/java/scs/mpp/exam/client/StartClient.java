package scs.mpp.exam.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import scs.mpp.exam.client.controller.LoginController;
import scs.mpp.exam.services.Services;

public class StartClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        Services server=(Services) factory.getBean("service");

        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("loginView.fxml"));
        Parent root=loader.load();

        LoginController ctrl = loader.getController();
        ctrl.setServices(server);
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }
}
