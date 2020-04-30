package example.backEndApp.components;

import example.backEndApp.dao.MessageDao;
import example.backEndApp.entities.Message;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
public class ContextPersistConfiguration {
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase embeddedDatabase = embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2)
                .setName("chatsDataBase")
                .addScript("sql/createTables.sql")
                .build();
        return embeddedDatabase;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("example.backEndApp.entities");
        System.out.println("sessionFactory datasource " + dataSource);
        return localSessionFactoryBean;
    }
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
        return hibernateTransactionManager;
    }
    @Bean
    public MessageDao messageDao(){
        return new MessageDao() {
            @Override
            public boolean addMessage(Message message) {
                return false;
            }
        };
    }
}
