/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import main.model.Activity;

public class TreeViewController implements Initializable {

	public TreeItem<String> dummyRoot = new TreeItem<String>("Activities");
	private Content mainApp;
	private ObservableList<Activity> data;
	private Node rootNode;
	public static int treeIndex = 0;

	ObservableList<String> tempData = FXCollections.observableArrayList();
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
		System.out.println("Tree Index: " + treeIndex);
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
			}
		}

		treeView = new TreeView<String>(dummyRoot);
		//treeView.setPrefHeight(10000);
		treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				ArrayList<TreeItem<String>> tempList = new ArrayList<>();

				if (mouseEvent.getClickCount() == 2) {
					TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
					if (treeView.getSelectionModel().getSelectedItem().getValue().toString().equals("Activities")) {
						for (int i = 0; i < data.size(); i++) {
							if (data.get(i).getParentValue() != 0)
								tempData.add(data.get(i).getIdString() + " " + data.get(i).getName());
						}
						mainApp.setData(tempData);
						mainApp.showGanttOverview();
					} else {
						tempList.addAll(item.getChildren());
						for (int i = 0; i < tempList.size(); i++) {
							tempData.add(tempList.get(i).getValue());
						}
						mainApp.setData(tempData);
						mainApp.showGanttOverview();
						tempList.clear();
						tempData.clear();
					}
				}
			}
		});
		treeView.setShowRoot(true);

	}

	public int checkVal(String str) {
		return Integer.parseInt(String.valueOf(str.charAt(0)));
	}

	public void setMainApp(Content mainApp) {
		this.mainApp = mainApp;
	}

	public void setTableData(ObservableList<Activity> activityData) {
		this.data = activityData;
	}

	public void setGanttData(ObservableList<String> tempData) {
		this.tempData = tempData;
	}

	public TreeView<String> getTree() {
		return treeView;
	}
}