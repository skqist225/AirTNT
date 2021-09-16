package com.airtnt.backend.rule;

import com.airtnt.common.entity.Rule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
