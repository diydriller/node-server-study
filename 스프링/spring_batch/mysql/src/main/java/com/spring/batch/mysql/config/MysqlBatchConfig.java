package com.spring.batch.mysql.config;

import com.spring.batch.mysql.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@EnableBatchProcessing
@Configuration
public class MysqlBatchConfig {

    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;
    @Autowired
    DataSource dataSource;


    @Bean
    public JdbcCursorItemReader jdbcCursorItemReader(){
        JdbcCursorItemReader reader = new JdbcCursorItemReader();
        reader.setDataSource(dataSource);
        reader.setSql("select * from products");
        reader.setRowMapper(new BeanPropertyRowMapper(){
            {
                setMappedClass(Product.class);
            }
        });
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter dbWriter(){
        JdbcBatchItemWriter writer=new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("update products set unit=? "+
                "where product_id=? ");
        writer.setItemPreparedStatementSetter(new ItemPreparedStatementSetter<Product>() {
            @Override
            public void setValues(Product item, PreparedStatement ps) throws SQLException {
                ps.setInt(1,item.getUnit()+1);
                ps.setInt(2,item.getProductId());
            }
        });

        return writer;
    }

    @Bean
    public Step step7(){
        return steps.get("step7")
                .chunk(2)
                .reader(jdbcCursorItemReader())
                .writer(dbWriter())
                .build();
    }


    @Bean
    public Job mysqlJob(){
        return jobs.get("mysqlJob")
                .start(step7())
                .build();
    }
}
