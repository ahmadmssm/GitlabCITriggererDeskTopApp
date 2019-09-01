package mainView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Project;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowView implements IMainWindowView, Initializable {

    @FXML private StackPane rootView;
    @FXML private Button resetGitlabConfigsButton;
    @FXML private Button majorInternalReleaseVersionButton;
    @FXML private Button minorInternalReleaseVersionButton;
    @FXML private Button fixInternalReleaseVersionButton;
    @FXML private Button majorCustomerReleaseVersionButton;
    @FXML private Button minorCustomerReleaseVersionButton;
    @FXML private Button fixCustomerReleaseVersionButton;
    @FXML private Button debugVersionButton;
    @FXML private TextField projectNameTextField;
    @FXML private TextField projectIdTextField;
    @FXML private ListView<Project> projectsListView;
    //
    private ObservableList<Project> listViewData = FXCollections.observableArrayList();
    private VBox progressIndicatorContainer;
    private Alert alert;
    private MainWindowPresenter presenter;
    private int step = 100;
    //

    // Life cycle
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableButtons(true);
        setUpListViewRendering();
        addListViewSelectionListener();
        presenter = new MainWindowPresenter(this);
    }

    // Button click actions
    public void onDebugVersionButtonClicked() {
        if (projectIdTextField.getText() != null) {
            long projectId = Long.parseLong(projectIdTextField.getText().trim());
            presenter.configurePipelineForDebugMode(projectId);
        }
    }

    public void onInternalMajorVersionButtonClicked() {
        if (projectIdTextField.getText() != null) {
            long projectId = Long.parseLong(projectIdTextField.getText().trim());
            presenter.configurePipelineForInternalMajorReleaseMode(projectId);
        }
    }

    public void onInternalMinorVersionButtonClicked() {
        if (projectIdTextField.getText() != null) {
            long projectId = Long.parseLong(projectIdTextField.getText().trim());
            presenter.configurePipelineForInternalMinorReleaseMode(projectId);
        }
    }

    public void onInternalFixVersionButtonClicked() {
        if (projectIdTextField.getText() != null) {
            long projectId = Long.parseLong(projectIdTextField.getText().trim());
            presenter.configurePipelineForInternalFixReleaseMode(projectId);
        }
    }
    //
    public void onCustomerMajorVersionButtonClicked() {
        if (projectIdTextField.getText() != null) {
            long projectId = Long.parseLong(projectIdTextField.getText().trim());
            presenter.configurePipelineForCustomerMajorReleaseMode(projectId);
        }
    }

    public void onCustomerMinorVersionButtonClicked() {
        if (projectIdTextField.getText() != null) {
            long projectId = Long.parseLong(projectIdTextField.getText().trim());
            presenter.configurePipelineForCustomerMinorReleaseMode(projectId);
        }
    }

    public void onCustomerFixVersionButtonClicked() {
        if (projectIdTextField.getText() != null) {
            long projectId = Long.parseLong(projectIdTextField.getText().trim());
            presenter.configurePipelineForCustomerFixReleaseMode(projectId);
        }
    }

    // View callbacks
    @Override
    public void onGitlabProjectsList(List<Project> projectsList) {
        listViewData.addAll(projectsList);
        projectsListView.setItems(listViewData);
        projectsListView.scrollTo((step - 100) + 1);
        configureLoadMore();
    }

    @Override
    public void onGitlabProjectsListFailure(String error) {
        showError(error);
    }

    @Override
    public void onSetPipelineMode() {
        showSuccess("Congratulations, Gitlab CI pipeline mode set successfully");
    }

    @Override
    public void onSetPipelineModeFailure(String error) {
        showError(error);
    }

    @Override
    public void onFailure(String error) {
        resetGitlabConfigsButton.setDisable(false);
        showError(error);
    }

    @Override
    public void showLoader() {
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicatorContainer = new VBox(progressIndicator);
        progressIndicatorContainer.setAlignment(Pos.CENTER);
        rootView.getChildren().add(progressIndicatorContainer);
    }

    @Override
    public void hideLoader() {
        rootView.getChildren().remove(progressIndicatorContainer);
    }

    @Override
    public void onNoMoreProjectsToLoad() {
        showInfo("No more projects to load !");
    }

    public void onReloadGitlabProfileSettingsButtonClicked() {
        projectNameTextField.setText(null);
        projectIdTextField.setText(null);
        listViewData.clear();
        projectsListView.refresh();
        disableButtons(true);
        presenter.resetAndReloadProjects();
    }

    // Utils functions
    @SuppressWarnings("SameParameterValue")
    private void showSuccess(String message) {
        if (alert != null && alert.isShowing()) {
            alert.close();
        }
        alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    @SuppressWarnings("SameParameterValue")
    private void showInfo(String message) {
        if (alert != null && alert.isShowing()) {
            alert.close();
        }
        alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    private void showError(String message) {
        if (alert != null && alert.isShowing()) {
            alert.close();
        }
        alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    private void addListViewSelectionListener() {
        projectsListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        projectNameTextField.setText(newSelection.getName());
                        projectIdTextField.setText(String.valueOf(newSelection.getId()));
                        disableButtons(false);
                    }
                });
    }

    private void setUpListViewRendering() {
        projectsListView.setCellFactory((list) -> new ListCell<Project>() {
            @Override
            protected void updateItem(Project project, boolean empty) {
                super.updateItem(project, empty);
                //
                if (project == null || empty) {
                    setText(null);
                }
                else {
                    String projectName = project.getName();
                    long projectId = project.getId();
                    setText("Name:  " + projectName + "\n" + "ID:        " + projectId);
                }
            }
        });
    }

    private void configureLoadMore() {
        ScrollBar listViewScrollBar = getListViewScrollBar(projectsListView);
        listViewScrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            double position = newValue.doubleValue();
            ScrollBar scrollBar = getListViewScrollBar(projectsListView);
            if (position == scrollBar.getMax()) {
                if (step <= listViewData.size()) {
                    presenter.getMyGitlabProjects();
                    step += 100;
                }
                else {
                    onNoMoreProjectsToLoad();
                }
            }
        });
    }

    private ScrollBar getListViewScrollBar(ListView<?> listView) {
        ScrollBar scrollbar = null;
        for (Node node : listView.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar) {
                ScrollBar bar = (ScrollBar) node;
                if (bar.getOrientation().equals(Orientation.VERTICAL)) {
                    scrollbar = bar;
                }
            }
        }
        return scrollbar;
    }

    private void disableButtons(boolean disabled) {
        resetGitlabConfigsButton.setDisable(disabled);
        debugVersionButton.setDisable(disabled);
        majorInternalReleaseVersionButton.setDisable(disabled);
        minorInternalReleaseVersionButton.setDisable(disabled);
        fixInternalReleaseVersionButton.setDisable(disabled);
        //
        majorCustomerReleaseVersionButton.setDisable(disabled);
        minorCustomerReleaseVersionButton.setDisable(disabled);
        fixCustomerReleaseVersionButton.setDisable(disabled);
    }
}
