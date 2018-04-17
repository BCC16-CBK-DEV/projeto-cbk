/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbk.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author igorcasconi
 */
public class Cbk extends Application {
    public static Scene loginTelaFxml;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Parent loginTela = FXMLLoader.load(getClass().getResource("login_1.fxml"));
        
        primaryStage.setResizable(false);
        primaryStage.setWidth(906);
        primaryStage.setHeight(628);
        primaryStage.setTitle("CBK - Soluções para Gerenciamento de Assistência Técnica ");
        
        loginTelaFxml = new Scene(loginTela);
        
        primaryStage.setScene(loginTelaFxml);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
