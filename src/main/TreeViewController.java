/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import main.model.Activity;

/**
 * FXML Controller class
 *
 * @author krisli
 */
public class TreeViewController implements Initializable {

	public TreeItem<String> dummyRoot = new TreeItem<String>();
	private Main mainApp;
	private ObservableList<Activity> data;

	private TreeView<String> treeView;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	public void startTreeView() {
		dummyRoot.setExpanded(true);

		ArrayList<Float> floatList = new ArrayList<>();

		for (int i = 0; i < data.size(); i++) {
			String tempString = data.get(i).getIdString();
			Float tempFloat = Float.parseFloat(tempString);
			floatList.add(tempFloat);
		}

		TreeItem<String> root;
		TreeItem<String> item;

		ArrayList<TreeItem<String>> rootList = new ArrayList<>();
		ArrayList<TreeItem<String>> itemList = new ArrayList<>();

		ArrayList<String> tempStr = new ArrayList<>();

		for (int i = 0; i < data.size(); i++) {
			if (((floatList.get(i) * 10 % 10)) == 0) {
				root = new TreeItem<String>(data.get(i).getName());
				rootList.add(root);
			} else {
				item = new TreeItem<String>(data.get(i).getIdString() + " " + data.get(i).getName());
				tempStr.add(data.get(i).getIdString() + " " + data.get(i).getName());
				itemList.add(item);
			}
		}

		TreeItem<String> tempItem;

		for (int i = 0; i < rootList.size(); i++) {
			dummyRoot.getChildren().add(rootList.get(i));
			for (int j = 0; j < itemList.size(); j++) {
				String data = tempStr.get(j);
				if (checkVal(data) == i + 1) {
					tempItem = new TreeItem<String>(tempStr.get(j));
					rootList.get(i).getChildren().add(tempItem);
				}
				// System.out.println(itemList.get(i));
			}
		}

		treeView = new TreeView<String>(dummyRoot);
		treeView.setShowRoot(false);
	}

	public int checkVal(String str) {
		return Integer.parseInt(String.valueOf(str.charAt(0)));
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public void setTableData(ObservableList<Activity> activityData) {
		this.data = activityData;
	}

	public TreeView<String> getTree() {
		return treeView;
	}
}