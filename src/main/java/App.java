import dao.Sql2oClientDao;
import dao.Sql2oMechanicDao;
import dao.Sql2oServiceDao;
import models.Client;
import models.Mechanic;
import models.Service;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args){
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/garage";

        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        Sql2oClientDao clientDao = new Sql2oClientDao(sql2o);
        Sql2oServiceDao serviceDao = new Sql2oServiceDao(sql2o);
        Sql2oMechanicDao mechanicDao = new Sql2oMechanicDao(sql2o);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Mechanic> allMechanics = mechanicDao.getAll();
            model.put("mechanics", allMechanics);
            model = new HashMap<>();
            List<Service> services = serviceDao.getAll();
            model.put("services", services);
            List<Client> clients = clientDao.getAll();
            model.put("clients", clients);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/clients/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Service> services = serviceDao.getAll();
            model.put("services", services);
            return new ModelAndView(model, "client-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/clients", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            List<Service> allServices = serviceDao.getAll();
            model.put("services", allServices);
            String newName = req.queryParams("name");
            String newEmail = req.queryParams("email");
            String newTel = req.queryParams("tel");
            String newCar = req.queryParams("car");
            int serviceId = Integer.parseInt(req.queryParams("serviceId"));
            Client newClient = new Client(newName, newEmail, newTel,newCar, serviceId);        //See what we did with the hard coded categoryId?
            clientDao.add(newClient);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
