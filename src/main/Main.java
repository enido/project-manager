/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.controller.ChartTabController;
import main.controller.RootController;
import main.controller.TableOverviewController;
import main.model.Activity;
import main.model.ActivityListWrapper;

/**
 * @author krisli
 */
public class Main extends Application {

	private Stage primaryStage;
	private BorderPane root;
	private BorderPane[] ContentRootLayout = new BorderPane[100];
	private TabPane ProjectTab;
	private ObservableList<Activity> tableData = FXCollections.observableArrayList();
	private Activity sum = new Activity();
	private ChartTabController chartTabController = new ChartTabController();
	public int tabIndex = 0;
	public int MAX_TABS = 15;
	public Content[] content = new Content[MAX_TABS];
	public SingleSelectionModel<Tab> sm;
	public TableOverviewController controller[] = new TableOverviewController[MAX_TABS];
        private int Settings[] = new int[10];

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.primaryStage = stage;
		this.primaryStage.setTitle("Main");

		initRoot();

		//Content enido = new Content();




		content[tabIndex] = new Content();
		content[tabIndex].setMainApp(this);
		content[tabIndex].setIndex(tabIndex);
		content[tabIndex].initialize();

		BorderPane temp = content[tabIndex].getContentRoot();

		ProjectTab.getTabs().add(new Tab("New"));
//		this.setProjectTab();
		ProjectTab.getTabs().get(tabIndex).setContent(temp);

		temp.prefWidthProperty().bind(root.widthProperty());
		temp.prefHeightProperty().bind(root.heightProperty());

		//ProjectTab = new TabPane();
		//ProjectTab.getTabs().add(new Tab("New"));
		//enido.setEmpty(true);
		//initContentLayout(enido);
		//incrementTabIndex();

//		tabIndex = 1;
//
//		Content test[] = new Content[tabIndex];
//		test[tabIndex].setEmpty(true);
//
//		test[tabIndex].setMainApp(this);
//		test[tabIndex].setIndex(tabIndex);
//
//		initContentLayout(test[tabIndex]);

//		Content enido = new Content();
//		enido.setEmpty(true);
//		enido.setMainApp(this);
//		BorderPane temp = enido.getContentRoot();
//		ProjectTab.getTabs().add(new Tab("New"));
//		initContentLayout(enido);
//		incrementTabIndex();
	}

	public void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootNew.fxml"));
			root = (BorderPane) loader.load();

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
			primaryStage.setScene(scene);

			RootController controller = loader.getController();
			this.ProjectTab = controller.getProjectTab();
			controller.setMainApp(this);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initContentLayout(Content contentData) {
		Content data;
		if (contentData.isEmpty()) {
			content[tabIndex] = new Content();
			content[tabIndex].setMainApp(this);
			content[tabIndex].setIndex(tabIndex);
			content[tabIndex].initialize();
			data = content[tabIndex];
		} else
			data = contentData;

		BorderPane temp = data.getContentRoot();
		ProjectTab.getTabs().get(tabIndex).setContent(temp);
		temp.prefWidthProperty().bind(root.widthProperty());
		temp.prefHeightProperty().bind(root.heightProperty());
	}

	public File getActivityFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);

		if (filePath != null)
			return new File(filePath);
		else
			return null;
	}

	public void setActivityFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update titullin e stage
			primaryStage.setTitle("PROING - " + file.getName());
                        
		} else {
			prefs.remove("filePath");

			// Update titullin e stage
			primaryStage.setTitle("PROING");
		}
	}

	public void loadActivityDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ActivityListWrapper.class);

			Unmarshaller um = context.createUnmarshaller();

			// Lexon XML-n nga skedari dhe ben unmarshall
			ActivityListWrapper wrapper = (ActivityListWrapper) um.unmarshal(file);

			ObservableList<Activity> tableData = FXCollections.observableArrayList();
			tableData.clear();
			tableData.addAll(wrapper.getActivities());
			content[tabIndex] = new Content();
			content[tabIndex].setIndex(tabIndex);
			content[tabIndex].setTableData(tableData);
			content[tabIndex].setMainApp(this);
			content[tabIndex].Refresh();
			initContentLayout(content[tabIndex]);
			incrementTabIndex();

			// Save the file path to the registry.
			setActivityFilePath(file);
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Te dhenat nuk mund te ngarkoheshin!");
			alert.setContentText("Te dhenat nuk mund te ngarkoheshin nga skedari:\n" + file.getPath());
			e.printStackTrace();
			alert.showAndWait();
		}
	}

	public void saveActivityDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ActivityListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			sm = ProjectTab.getSelectionModel();
			int selectedIndex = sm.getSelectedIndex();
			// Wrapping e te dhenave te activity
			ActivityListWrapper wrapper = new ActivityListWrapper();
			wrapper.setActivities(content[selectedIndex].getTableData());

			// Marshalling dhe ruajtja e XML ne skedar
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			setActivityFilePath(file);
		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Te dhenat nuk mund te ruhen!");
			alert.setContentText("Te dhenat nuk mund te ruhen ne skedarin:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	public void setProjectTab(TabPane tabPane) {
		this.ProjectTab = tabPane;
	}

	public void incrementTabIndex() {
		this.tabIndex++;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
        
        public int[] getSettings(){
            return Settings;
        }
        
        public void setSettings(int[] Settings){
            this.Settings = Settings;
        }

        public void Refresh(int index) {
		BorderPane temp = content[index].getContentRoot();
		System.out.println("Tab Index: " + tabIndex);
		System.out.println("Content Index:" + index);
		ProjectTab.getTabs().get(index).setContent(temp);
		temp.prefWidthProperty().bind(root.widthProperty());
		temp.prefHeightProperty().bind(root.heightProperty());

	}
}