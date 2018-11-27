package hello;

import csv.CSVReader;
import csv.CSVwrapper;
import models.CsvRow;
import models.Dao;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class Application {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


        Transaction tr = null;
        try {
            SessionFactory sessFactory = createSessionFactory();
            Dao dao = new Dao(sessFactory);
            if (dao.first(CsvRow.class) == null) {
                tr=sessFactory.getCurrentSession().beginTransaction();
                ClassLoader classLoader = dao.getClass().getClassLoader();
                File file = new File(classLoader.getResource("test_case.csv").getFile());

                CSVReader.parseDocument(file, new CSVReader.CSVReaderCallback() {
                    @Override
                    public void process(CSVwrapper wrapper) {
                        CsvRow csvRow = new CsvRow();
                        csvRow.setIndex(wrapper.getIndex());
                        csvRow.setSsoid(wrapper.getSsoid());
                        csvRow.setTs(wrapper.getTs());
                        csvRow.setGrp(wrapper.getGrp());
                        csvRow.setType(wrapper.getType());
                        csvRow.setSubtype(wrapper.getSubtype());
                        csvRow.setUrl(wrapper.getUrl());
                        csvRow.setOrgid(wrapper.getOrgid());
                        csvRow.setFormid(wrapper.getFormid());
                        csvRow.setLtpa(wrapper.getLtpa());
                        csvRow.setSudirresponse(wrapper.getSudirresponse());
                        csvRow.setYmdh(wrapper.getYmdh());
                        dao.save(csvRow);
                    }
                });
            }
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                tr = null;
            }
            e.printStackTrace();
        } finally {
            if (tr != null)
                tr.commit();
        }
    }

    @Bean
    public static Dao createDao()
    {
        return new Dao();
    }

    @Bean(name = "sessionFactory")
    public static SessionFactory createSessionFactory()
    {
        if (sessionFactory == null) {
            StandardServiceRegistry standardRegistry =
                    new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metaData =
                    new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory = metaData.getSessionFactoryBuilder().build();
        }

        return sessionFactory;
    }
}
