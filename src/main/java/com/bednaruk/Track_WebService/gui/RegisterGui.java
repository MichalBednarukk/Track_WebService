package com.bednaruk.Track_WebService.gui;


import com.bednaruk.Track_WebService.entity.UserApp;
import com.bednaruk.Track_WebService.repository.UserRepo;
import com.bednaruk.Track_WebService.controller.UserController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Route("registry")
public class RegisterGui extends VerticalLayout {

    UserRepo userRepo;
    UserController userController;

    Label label;
    TextField textFieldUsername;
    PasswordField passwordField;
    PasswordField passwordFieldConfirm;
    VerticalLayout verticalLayout;

    Button buttonRegister;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public RegisterGui(UserRepo userRepo, UserController userController) {
        this.userRepo = userRepo;
        this.userController = userController;
        initialize();


        buttonRegister.addClickListener(buttonClickEvent -> {
            if (!textFieldUsername.isEmpty() && !passwordField.isEmpty() && !passwordFieldConfirm.isEmpty()) {
                if (passwordField.getValue().equals(passwordFieldConfirm.getValue())) {
                    UserApp userApp = new UserApp(textFieldUsername.getValue(), passwordEncoder().encode(passwordField.getValue()), "ROLE_USER");

                    if (this.userController.userRegister(userApp).getStatusCodeValue() == 200) {
                        Notification notification = new Notification(
                                "Register Succesfully", 3000,
                                Notification.Position.TOP_START);
                        notification.open();
                    } else {
                        Notification notification = new Notification(
                                "Register Unsuccesfull", 3000,
                                Notification.Position.TOP_START);
                        notification.open();
                    }
                } else {
                    Notification notification = new Notification(
                            "Please make sure your passwords match", 3000,
                            Notification.Position.TOP_START);
                    notification.open();
                }
            } else {
                Notification notification = new Notification(
                        "Username and Password required", 3000,
                        Notification.Position.TOP_START);
                notification.open();
            }


        });
    }

    public void initialize() {

        verticalLayout = new VerticalLayout();

        label = new Label("Registration");

        textFieldUsername = new TextField("Username", "Enter username");
        textFieldUsername.setRequired(true);

        passwordField = new PasswordField("Password", "Enter password");
        passwordField.setValue("Password");


        passwordFieldConfirm = new PasswordField("Reapeat Password", "Repeat password");
        passwordFieldConfirm.setValue("Password");

        buttonRegister = new Button("Register");

        Notification notification = new Notification(
                "Username and Password required", 3000,
                Notification.Position.TOP_START);
        notification.open();


        verticalLayout.add(label, textFieldUsername, passwordField, passwordFieldConfirm, buttonRegister);
        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setHeight("100%");
        add(verticalLayout);

    }

}