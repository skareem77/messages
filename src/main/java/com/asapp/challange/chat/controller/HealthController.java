package com.asapp.challange.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealthController implements HealthIndicator {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Health getHealth(boolean includeDetails) {
        return this.health();
    }

    @GetMapping("/check")
    @Override
    public Health health() {
        if(check() != 1) {
            return Health.down().withDetail("Db is down", 500).build();
        }
        return Health.status("ok").build();
    }

    public int check(){
        List<Object> results = template.query("select 1",
                new SingleColumnRowMapper<>());
        return results.size();
    }
}
