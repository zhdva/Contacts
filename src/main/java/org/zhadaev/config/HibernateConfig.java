package org.zhadaev.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.zhadaev.model.*;

public class HibernateConfig {

    private static SessionFactory sessionFactory;

    private HibernateConfig() {}

    //метод конфигурирует hibernate и возвращает фабрику сессий для работы с БД
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            //считать конфигурацию hibernate из файла hibernate.cfg.xml
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            //добавить классы сущностей
            configuration.addAnnotatedClass(Contact.class);
            configuration.addAnnotatedClass(ContactType.class);
            configuration.addAnnotatedClass(Person.class);

            //создать фабрику сессий
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        }

        return sessionFactory;
    }

}