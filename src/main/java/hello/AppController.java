package hello;

import java.util.List;

import models.CsvRow;
import models.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {

    @Autowired
    private Dao dao;


    @RequestMapping("/postgreQuery1")
    public RequestedPage getNamedQuery1(@RequestParam(value="name", defaultValue="World") String name) {
        List<CsvRow> listOfObjects = dao.getPostgreQuery(CsvRow.class);
        return new RequestedPage(listOfObjects);
    }

    @RequestMapping("/namedQuery2")
    public RequestedPage getNamedQuery2(@RequestParam(value="name", defaultValue="World") String name) {
        List<CsvRow> listOfObjects = dao.getNamedQuery2(CsvRow.class);
        return new RequestedPage(listOfObjects);
    }

    @RequestMapping("/namedQuery3")
    public RequestedPage getNamedQuery3(@RequestParam(value="name", defaultValue="World") String name) {
        List<CsvRow> listOfObjects = dao.getNamedQuery3(CsvRow.class);
        return new RequestedPage(listOfObjects);
    }


}
