package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	//Declarando a dependencia com o DepartmentService
	private DepartmentService service;//Se fizesse o new, estaria deixando com forte acoplamento
	
	//Fazendo injeção de dependencia sem deixar o aclopamento forte
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	
	@FXML 
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btnNew;
	
	//Utilizo a ObservableList do JavaFX collections
	//Uso a collection para mostrar as informações dentro do meu Table View
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("new Action");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		//Inicializando o comportamento das colunas
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//Macete para fazer o TableView acompanhar a altura/largura da janela
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	
	}
	
	public void updateTableView() {
		//Método responsavel por acessar o servico, carregar o departamento, e jogar os dados na observable list que será associada com o meu Table View
		if ( service == null) { //Proteger o servico, dizendo para instanciar o service
			throw new IllegalStateException("Service was null");
		}
		
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);//Observable list recebe a minha list
		tableViewDepartment.setItems(obsList);//Seto os items na minha view
	}

} 
