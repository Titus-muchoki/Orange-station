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


        get("/services/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Service> services = serviceDao.getAll(); //refresh list of links for navbar
            model.put("services", services);
            return new ModelAndView(model, "service-form.hbs"); //new layout
        }, new HandlebarsTemplateEngine());
        //post: process a form to create a new teacher

        post("/services", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
//            String comment = req.queryParams("comment");
//            int studentId = Integer.parseInt(req.queryParams("studentId"));
            Service newService = new Service(name);
            serviceDao.add(newService);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/services/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfServiceToFind = Integer.parseInt(req.params("id")); //new
            Service foundService = serviceDao.findById(idOfServiceToFind);
            model.put("service", foundService);
            List<Client> allClientByServices = serviceDao.getAllClientsByService(idOfServiceToFind);
            model.put("clients", allClientByServices);
            model.put("services", serviceDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "service-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());
        //get: show a form to update a category

        get("/services/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editService", true);
            Service service = serviceDao.findById(Integer.parseInt(req.params("id")));
            model.put("service", service);
            model.put("services", serviceDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "service-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/services/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfServiceToEdit = Integer.parseInt(req.params("id"));
            String newService = req.queryParams("newServiceName");
            serviceDao.update(idOfServiceToEdit, newService);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

//        //get: delete an individual viral test

        get("/services/:service_id/clients/:client_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfClientToDelete = Integer.parseInt(req.params("client_id"));
            clientDao.deleteById(idOfClientToDelete);
            res.redirect("/");
            return null;
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

        get("/clients/:client_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfClientToFind = Integer.parseInt(req.params("client_id")); //pull id - must match route segment
            Client foundClient = clientDao.findById(idOfClientToFind); //use it to find task
            model.put("client", foundClient); //add it to model for template to display
            model.put("clients", clientDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "client-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());
    }
}
