package hello;

import models.CsvRow;

import java.util.List;

public class RequestedPage {

    private List<CsvRow> listDaoObjects;

    public RequestedPage(){}

    public RequestedPage(List<CsvRow> listDaoObjects) {
        this.listDaoObjects = listDaoObjects;
    }

    public List<CsvRow> getListDaoObjects() {
        return listDaoObjects;
    }

    public void setListDaoObjects(List<CsvRow> listDaoObjects) {
        this.listDaoObjects = listDaoObjects;
    }

}
