package pairmatching;

import pairmatching.controller.Controller;
import pairmatching.service.Service;

public class Application {

    public static void main(String[] args) {
        Service service = new Service();
        Controller controller = new Controller(service);
        try {
            controller.run();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}

