package view;

import controller.UserController;
import doa.impl.FileOperation;
import model.User;
import repository.GBRepository;
import repository.impl.UserRepository;

import static util.DBController.DB_PATH;
import static util.DBController.createDB;

public class View {
    public void showConsoleMenu() {
        createDB();
        FileOperation operation = new FileOperation(DB_PATH);
        GBRepository<User, Long> repository = new UserRepository(operation);
        UserController controller = new UserController(repository);
        MainUserView view = new MainUserView(controller);
        view.showConsoleListMenu();
        view.run();

    }
}

