package com.zillus.coronadiary.dal;

import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.microstream.DB;
import org.junit.jupiter.api.*;

class PersonDAOTest {

    @BeforeEach
    void clearStore() {
        DB.root().getPersonEntities().clear();
        DB.storageManager().store(DB.root().getPersonEntities());
    }

    @AfterAll
    static void shutdown() {
        DB.shutDown();
    }

    @Test
    void isSavedReflectsPresence() {
        PatientEntity patient = new PatientEntity();
        Assertions.assertFalse(PersonDAO.isSaved(patient));

        PersonDAO.addEntity(patient);
        Assertions.assertTrue(PersonDAO.isSaved(patient));

        PersonDAO.removeEntity(patient);
        Assertions.assertFalse(PersonDAO.isSaved(patient));
    }

    @Test
    void isSavedNullReturnsFalse() {
        Assertions.assertFalse(PersonDAO.isSaved(null));
    }

    @Test
    void addAndRemoveUpdatesCollection() {
        PatientEntity patient = new PatientEntity();
        PersonDAO.addEntity(patient);
        Assertions.assertTrue(PersonDAO.findAll().contains(patient));

        PersonDAO.removeEntity(patient);
        Assertions.assertFalse(PersonDAO.findAll().contains(patient));
    }
}
