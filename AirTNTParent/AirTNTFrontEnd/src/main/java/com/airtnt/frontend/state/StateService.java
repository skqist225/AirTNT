package com.airtnt.frontend.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.State;

@Service
public class StateService {
    @Autowired
    StateRepository stateRepository;

    public State getStateByName(String stateName) {
        State state = stateRepository.findByName(stateName);

        return state;
    }

    public State addState(String stateName, String stateCode, Country country) {
        State s = new State(stateName, stateCode, country);
        State savedState = stateRepository.save(s);

        return savedState;
    }
}
