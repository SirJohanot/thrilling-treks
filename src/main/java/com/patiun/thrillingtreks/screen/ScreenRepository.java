package com.patiun.thrillingtreks.screen;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends CrudRepository<Screen, Long> {
}
