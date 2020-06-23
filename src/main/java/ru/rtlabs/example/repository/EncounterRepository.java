package ru.rtlabs.example.repository;

import com.company.fhir.r4.core.HospitalEncounter;
import com.google.fhir.r4.core.Boolean;
import com.google.fhir.r4.core.Id;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class EncounterRepository {

    private final List<HospitalEncounter> patients = new CopyOnWriteArrayList<>();

    @PostConstruct
    private void init() {
        HospitalEncounter first = HospitalEncounter.newBuilder()
                                                   .setId(Id.newBuilder().setValue("c11a9-bc0e-420b-9aa7-47d9f44ecd0b").build())
                                                   .setVitality(Boolean.newBuilder().setValue(true).build())
                                                   .build();

        HospitalEncounter second = HospitalEncounter.newBuilder()
                                                    .setId(Id
                                                                   .newBuilder()
                                                                   .setValue("55d42ac3-283d-443f-a764-3f63207cb430").build())
                                                    .setVitality(Boolean.newBuilder().setValue(true).build())
                                                    .build();

        patients.add(first);
        patients.add(second);
    }

    public HospitalEncounter getById(Id id) {
        return patients.stream()
                       .filter(patient -> id.equals(patient.getId()))
                       .findAny()
                       .orElse(null);
    }

}
