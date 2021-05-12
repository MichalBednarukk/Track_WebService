package com.bednaruk.Track_WebService.gui;

import com.bednaruk.Track_WebService.controller.ChordController;
import com.bednaruk.Track_WebService.entity.ChordApp;
import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.repository.ChordRepo;
import com.bednaruk.Track_WebService.repository.TrackRepo;
import com.bednaruk.Track_WebService.controller.TrackController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Route("trackaddgui")
public class TrackAddGui extends VerticalLayout {

    TrackController trackController;
    TrackRepo trackRepo;
    ChordRepo chordRepo;
    ChordController chordController;

    Label chosenChords;
    TextField trackName;
    TextArea trackBody;
    Button addButton, deleteChordsButton;
    DatePicker datePicker;
    Select<String> selectOfChords;

    List<ChordApp> listOfSelectedChords;
    List<ChordApp> listOfAllChords;

    @Autowired
    public TrackAddGui(TrackRepo trackRepo, TrackController trackController, ChordRepo chordRepo,ChordController chordController) {
        this.trackRepo = trackRepo;
        this.trackController = trackController;
        this.chordRepo = chordRepo;
        this.chordController = chordController;
        initializeUI();//initialize interface and fields;

        addButton.addClickListener(buttonClickEvent -> {

            TrackApp trackApp = TrackApp.builder()
                    .name(trackName.getValue())
                    .body(trackBody.getValue())
                    .username(SecurityContextHolder.getContext().getAuthentication().getName())
                    .build();

            int statusCodeValue = saveInDatabase(trackApp,listOfSelectedChords);

            if (statusCodeValue == 200) {
                Notification notification = new Notification(
                        "Track add Succesfully", 3000,
                        Notification.Position.TOP_START);
                notification.open();
            } else {
                Notification notification = new Notification(
                        "Track add Unsuccesfull", 3000,
                        Notification.Position.TOP_START);
                notification.open();
            }
        });

    }

    private int saveInDatabase(TrackApp trackApp, List<ChordApp> listOfSelectedChords) {
        int statusCodeValue = this.trackController.addTrack(trackApp).getStatusCodeValue();

        trackApp.setChordApps(new ArrayList<>());

        for (ChordApp chordApp : listOfSelectedChords){
            trackApp.getChordApps().add(chordApp);
            chordApp.getTrackApps().add(trackApp);
            statusCodeValue = chordController.addChord(chordApp).getStatusCodeValue();
        }
        return statusCodeValue;
    }

    public void initializeUI() {
        listOfSelectedChords = new ArrayList<>();
        listOfAllChords = getListOfAllChords();

        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        trackName = new TextField("Title");

        trackBody = new TextArea("Lyrics");
        trackBody.setWidth("60%");
        trackBody.getStyle().set("50%", "50%");

        trackBody.setPlaceholder("Write here ...");

        addButton = new Button("Add Track");
        deleteChordsButton = new Button("Remove");

        chosenChords = new Label();
        chosenChords.setMaxWidth("80px");
        chosenChords.setMinWidth("80px");

        selectOfChords = new Select<>();
        selectOfChords.setLabel("Select chords");

        setChordsToSelect();//set items in Vaadin selector

        selectOfChords.addValueChangeListener(event -> {
            addToListOfSelectedChords(event.getValue());
            setTextOfChosenChords();//set text in chosenChord label
        });

        deleteChordsButton.addClickListener(event -> {
            removeLastFromListOfSelectedChords();
            setTextOfChosenChords();//set text in chosenChord label
        });

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(trackName, selectOfChords, chosenChords, deleteChordsButton);
        verticalLayout.add(horizontalLayout, trackBody, addButton);
        verticalLayout.setAlignItems(Alignment.CENTER);
        add(verticalLayout);
    }

    private void setChordsToSelect(){
        selectOfChords.setItems(listOfAllChords.stream().map(ChordApp::getChordName).sorted().collect(Collectors.toList()));
    }
    private List<ChordApp> getListOfAllChords() {
        return chordRepo.findAll();
    }

    private List<ChordApp> getListOfSelectedChords() {
        return listOfSelectedChords;
    }

    private void addToListOfSelectedChords(String chordName) {
        List<ChordApp> toSort = new ArrayList<>(listOfAllChords);
        for (ChordApp chordApp : toSort) {
            if (chordApp.getChordName().equals(chordName)) {
                listOfSelectedChords.add(chordApp);
            }
        }
    }

    private void removeLastFromListOfSelectedChords() {
        listOfSelectedChords.remove(listOfSelectedChords.size() - 1);
    }

    private void setTextOfChosenChords() {
        chosenChords.removeAll();
        for (ChordApp chordApp : getListOfSelectedChords()
        ) {
            chosenChords.add(chordApp.getChordName() + ", ");
        }
    }
}