package br.com.example.upafacil.ms_filtro_unidade_atendimento.integrationTest;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.controller.UpaController;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto.UpaDto;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto.UpaLocationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import templateDto.UpaTemplateDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UpaTest {

    @Autowired
    private UpaController upaController;

    @Autowired
    private ObjectMapper objectMapper;


    private Long upaId;

    @BeforeEach
    void setUp() {
        UpaDto upaDto = UpaTemplateDto.upaTemplate(null,
                "UPA Test",
                100,
                0,
                "Test Street",
                "Test City",
                1,
                "12345",
                10.0,
                20.0);

        Mono<ResponseEntity<UpaDto>> response = upaController.createUpa(upaDto);
        ResponseEntity<UpaDto> responseEntity = response.block();
        upaId = responseEntity.getBody().upaId();
    }

    @Test
    void createUpaTest() throws Exception {
        UpaDto upaDto = UpaTemplateDto.upaTemplate(null,
                "UPA Test",
                100,
                0,
                "Test Street",
                "Test City",
                1,
                "12345",
                10.0,
                20.0);

        Mono<ResponseEntity<UpaDto>> response = upaController.createUpa(upaDto);
        ResponseEntity<UpaDto> responseEntity = response.block();

        int status = responseEntity.getStatusCodeValue();
        UpaDto responseBody = responseEntity.getBody();
        String responseContent = objectMapper.writeValueAsString(responseEntity.getBody());

        assertEquals(201, status, "Expected HTTP status 201");
        assertFalse(responseContent.isEmpty(), "Response content is empty");
        assertEquals("UPA Test", responseBody.name(), "Expected name to be 'UPA Test'");
    }

    @Test
    void findUpaByUpaIdTest() throws Exception {
        Mono<ResponseEntity<UpaDto>> response = upaController.findUpaByUpaId(upaId);
        ResponseEntity<UpaDto> responseEntity = response.block();

        int status = responseEntity.getStatusCodeValue();
        UpaDto responseBody = responseEntity.getBody();
        String responseContent = objectMapper.writeValueAsString(responseEntity.getBody());

        assertEquals(200, status, "Expected HTTP status 200");
        assertFalse(responseContent.isEmpty(), "Response content is empty");
        assertEquals(upaId, responseBody.upaId(), "Expected UPA ID to match");
    }

    @Test
    void findAllUpaTest() throws Exception {
        Mono<ResponseEntity<Flux<UpaDto>>> response = upaController.findAllUpa();
        ResponseEntity<Flux<UpaDto>> responseEntity = response.block();

        int status = responseEntity.getStatusCodeValue();
        Flux<UpaDto> responseBody = responseEntity.getBody();

        assertEquals(202, status, "Expected HTTP status 202");
        assertFalse(responseBody.collectList().block().isEmpty(), "Response content is empty");
    }

    @Test
    void getNearestUpaTest() throws Exception {
        Double latitude = 10.0;
        Double longitude = 20.0;

        Flux<UpaLocationDto> response = upaController.getNearestUpa(latitude, longitude);
        UpaLocationDto responseBody = response.blockFirst();

        assertFalse(responseBody == null, "Response content is empty");
    }

    @Test
    void findUpaWithLowerQueueTest() throws Exception {
        Integer state = 1;

        Mono<ResponseEntity<UpaDto>> response = upaController.findUpaWithLowerQueue(state);
        ResponseEntity<UpaDto> responseEntity = response.block();

        int status = responseEntity.getStatusCodeValue();
        String responseContent = objectMapper.writeValueAsString(responseEntity.getBody());

        assertEquals(200, status, "Expected HTTP status 200");
        assertFalse(responseContent.isEmpty(), "Response content is empty");
    }

    @Test
    void registerServiceUpaTest() throws Exception {
        Mono<UpaDto> response = upaController.registerServiceUpa(upaId);
        UpaDto responseBody = response.block();

        assertFalse(responseBody == null, "Response content is empty");
    }

    @Test
    void freesUpaCapacityTest_ShouldThrowValidationException() {
        UpaDto upaDto = UpaTemplateDto.upaTemplate(null,
                "UPA Test",
                100,
                0,
                "Test Street",
                "Test City",
                1,
                "12345",
                10.0,
                20.0);

        Mono<ResponseEntity<UpaDto>> createResponse = upaController.createUpa(upaDto);
        ResponseEntity<UpaDto> createResponseEntity = createResponse.block();
        Long upaId = createResponseEntity.getBody().upaId();

        Mono<UpaDto> response = upaController.freesUpaCapacity(upaId);

        assertThrows(ValidationException.class, () -> {
            response.block();
        }, "Expected ValidationException to be thrown");
    }

    @Test
    void deleteUpaTest() throws Exception {
        Mono<ResponseEntity<Void>> response = upaController.deleteUpa(upaId);
        ResponseEntity<Void> responseEntity = response.block();

        int status = responseEntity.getStatusCodeValue();

        assertEquals(204, status, "Expected HTTP status 204");
    }

    @Test
    void updateUpaTest() throws Exception {
        UpaDto upaDto = UpaTemplateDto.upaTemplate(null,
                "Updated UPA Test",
                100,
                0,
                "Updated Test Street",
                "Updated Test City",
                1,
                "12345",
                10.0,
                20.0);

        Mono<ResponseEntity<UpaDto>> response = upaController.updateUpa(upaId, upaDto);
        ResponseEntity<UpaDto> responseEntity = response.block();

        int status = responseEntity.getStatusCodeValue();
        UpaDto responseBody = responseEntity.getBody();
        String responseContent = objectMapper.writeValueAsString(responseEntity.getBody());

        assertEquals(200, status, "Expected HTTP status 200");
        assertFalse(responseContent.isEmpty(), "Response content is empty");
        assertEquals("Updated UPA Test", responseBody.name(), "Expected name to be 'Updated UPA Test'");
    }
}