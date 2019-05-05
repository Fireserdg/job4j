package ru.job4j.beans.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.job4j.beans.service.UserService;
import ru.job4j.beans.storage.Storage;

/**
 * UserService configuration
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-05-05
 */
@ComponentScan(basePackages = "ru.job4j.beans.java")
@Configuration
public class UserServiceConfiguration {

    @Bean
    public Storage memoryStorageJavaConfig() {
        return new MemoryStorageJavaConfig();
    }

    @Bean
    public UserService userServiceJavaConfig() {
        return new UserServiceJavaConfig(memoryStorageJavaConfig());
    }

}
