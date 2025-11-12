package com.progressoft.clustereddatawarehouse.service.implementation;


import com.progressoft.clustereddatawarehouse.dto.DealRequestDto;
import com.progressoft.clustereddatawarehouse.dto.DealResponseDto;
import com.progressoft.clustereddatawarehouse.exception.DuplicateDealException;
import com.progressoft.clustereddatawarehouse.mapper.DealMapper;
import com.progressoft.clustereddatawarehouse.model.Deal;
import com.progressoft.clustereddatawarehouse.repository.DealRepository;
import com.progressoft.clustereddatawarehouse.service.DealValidationService;
import com.progressoft.clustereddatawarehouse.service.implementation.DealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DealServiceImplTest {

    @InjectMocks
    private DealServiceImpl dealService;

    @Mock
    private DealRepository dealRepository;

    @Mock
    private DealMapper dealMapper;

    @Mock
    private DealValidationService validationService;

    private DealRequestDto dealRequest;
    private Deal dealEntity;
    private DealResponseDto dealResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dealRequest = new DealRequestDto(
                "DEAL-001",
                "USD",
                "EUR",
                LocalDateTime.of(2025, 8, 1, 12, 0),
                BigDecimal.valueOf(1500.75)
        );

        dealEntity = new Deal("DEAL-001", "USD", "EUR",
                LocalDateTime.of(2025, 8, 1, 12, 0),
                BigDecimal.valueOf(1500.75), LocalDateTime.now());

        dealResponse = new DealResponseDto(
                "DEAL-001", "USD", "EUR",
                LocalDateTime.of(2025, 8, 1, 12, 0),
                BigDecimal.valueOf(1500.75),
                LocalDateTime.now()
        );
    }

    // Scénario: création réussie
    @Test
    void createDeal_ShouldReturnDealResponse_WhenDealIsValid() {

        doNothing().when(validationService).validateDealNotExists(dealRequest);
        doNothing().when(validationService).validateCurrencies("USD", "EUR");
        when(dealMapper.toEntity(dealRequest)).thenReturn(dealEntity);
        when(dealRepository.save(dealEntity)).thenReturn(dealEntity);
        when(dealMapper.toDto(dealEntity)).thenReturn(dealResponse);

        DealResponseDto result = dealService.createDeal(dealRequest);

        assertNotNull(result);
        assertEquals("DEAL-001", result.id());
        verify(dealRepository).save(dealEntity);
    }
    // Scénario: le deal existe déjà
    @Test
    void createDeal_ShouldThrowDuplicateDealException_WhenDealExists() {

        doThrow(new DuplicateDealException("DEAL-001")).when(validationService).validateDealNotExists(dealRequest);

        assertThrows(DuplicateDealException.class, () -> dealService.createDeal(dealRequest));
        verify(dealRepository, never()).save(any());
    }
    // Scénario: devise source nulle
    @Test
    void createDeal_ShouldThrowIllegalArgumentException_WhenFromCurrencyIsNull() {

        dealRequest = new DealRequestDto("DEAL-002", null, "EUR", LocalDateTime.now(), BigDecimal.TEN);
        doNothing().when(validationService).validateDealNotExists(dealRequest);
        doThrow(new IllegalArgumentException("Currencies cannot be null."))
                .when(validationService).validateCurrencies(null, "EUR");

        assertThrows(IllegalArgumentException.class, () -> dealService.createDeal(dealRequest));
        verify(dealRepository, never()).save(any());
    }

    // Scénario: devise cible nulle
    @Test
    void createDeal_ShouldThrowIllegalArgumentException_WhenToCurrencyIsNull() {

        dealRequest = new DealRequestDto("DEAL-003", "USD", null, LocalDateTime.now(), BigDecimal.TEN);
        doNothing().when(validationService).validateDealNotExists(dealRequest);
        doThrow(new IllegalArgumentException("Currencies cannot be null."))
                .when(validationService).validateCurrencies("USD", null);

        assertThrows(IllegalArgumentException.class, () -> dealService.createDeal(dealRequest));
        verify(dealRepository, never()).save(any());
    }
    // Scénario: devise source et cible identiques
    @Test
    void createDeal_ShouldThrowIllegalArgumentException_WhenCurrenciesAreSame() {

        dealRequest = new DealRequestDto("DEAL-004", "USD", "USD", LocalDateTime.now(), BigDecimal.TEN);
        doNothing().when(validationService).validateDealNotExists(dealRequest);
        doThrow(new IllegalArgumentException("Source and target currencies cannot be the same."))
                .when(validationService).validateCurrencies("USD", "USD");

        assertThrows(IllegalArgumentException.class, () -> dealService.createDeal(dealRequest));
        verify(dealRepository, never()).save(any());
    }

    // Scénario: exception du mapper
    @Test
    void createDeal_ShouldPropagateException_WhenMapperFails() {

        doNothing().when(validationService).validateDealNotExists(dealRequest);
        doNothing().when(validationService).validateCurrencies("USD", "EUR");
        when(dealMapper.toEntity(dealRequest)).thenThrow(new RuntimeException("Mapper failed"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> dealService.createDeal(dealRequest));
        assertEquals("Mapper failed", ex.getMessage());
        verify(dealRepository, never()).save(any());
    }

    // Scénario: exception du repository
    @Test
    void createDeal_ShouldPropagateException_WhenRepositoryFails() {

        doNothing().when(validationService).validateDealNotExists(dealRequest);
        doNothing().when(validationService).validateCurrencies("USD", "EUR");
        when(dealMapper.toEntity(dealRequest)).thenReturn(dealEntity);
        when(dealRepository.save(dealEntity)).thenThrow(new RuntimeException("DB failure"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> dealService.createDeal(dealRequest));
        assertEquals("DB failure", ex.getMessage());
    }
}
