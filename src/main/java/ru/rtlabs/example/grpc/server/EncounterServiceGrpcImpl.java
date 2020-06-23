package ru.rtlabs.example.grpc.server;

import com.company.fhir.r4.core.EncounterServiceGrpc;
import com.company.fhir.r4.core.HospitalEncounter;
import com.google.fhir.r4.core.Id;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.rtlabs.example.repository.EncounterRepository;

@GrpcService
@RequiredArgsConstructor
public class EncounterServiceGrpcImpl extends EncounterServiceGrpc.EncounterServiceImplBase {

    private final EncounterRepository encounterRepository;


    @Override
    public void getHospitalEncountertById(
            HospitalEncounter request,
            StreamObserver<HospitalEncounter> responseObserver
    ) {
        Id encounterId = request.getId();
        HospitalEncounter patient = encounterRepository.getById(encounterId);
        if (patient != null) {
            responseObserver.onNext(patient);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND
                                             .withDescription("Patient with id = " + encounterId + " not found")
                                             .asRuntimeException()
            );
        }
    }

}
