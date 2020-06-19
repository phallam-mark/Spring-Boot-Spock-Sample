package com.loyalty.testautomationexample.repository

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import com.loyalty.testautomationexample.utilities.Logger

@Repository
public class PlanetsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate

    int getCountOfPlanets(){

        String sqlQuery = "select count(*) from STARWARSPLANETS"
        int count = jdbcTemplate.queryForObject(sqlQuery, Integer.class )
        return count
    }

    String getClimateForPlanet(String planet){

        String climate

        String sqlQuery = "select name, climate from STARWARSPLANETS where name = '${planet}'"
        Logger.logMessage("sqlQuery = ${sqlQuery}")

        try{
            List<Map<String, Object>> resultRows = jdbcTemplate.queryForList(sqlQuery)
            if(resultRows.size() > 1){
                Logger.logMessage("more than one climate found for planet: ${planet}")
                return climate
            }
            if(resultRows.size() == 0){
                Logger.logMessage("no rows found in table for planet ${planet}")
                return climate
            }
            for(Map row : resultRows) {
                if(row.get("name").equals(planet)){
                    climate = row.get("climate")
                }
            }
            return climate
        }
        catch(EmptyResultDataAccessException e) {
            Logger.logMessage("no rows found in table for planet ${planet}")
            return climate
        }
    }
}
